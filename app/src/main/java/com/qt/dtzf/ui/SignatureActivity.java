package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.baselib.BaseAPP;
import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.EmptyBean;
import com.base.baselib.bean.ImageUrl;
import com.base.baselib.bean.LoginInfo;
import com.base.baselib.bean.UserInfo;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.glide.GlideUtils;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.FileUtils;
import com.base.baselib.utils.HttpUtils;
import com.base.baselib.utils.LogUtils;
import com.base.baselib.utils.NetResultParseUtils;
import com.base.baselib.utils.Utils;
import com.base.baselib.view.SignatureView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.loc.s;
import com.qt.dtzf.APP;
import com.qt.dtzf.R;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 签名界面
 */
public class SignatureActivity extends BaseActivity {

    private SignatureView mSv;

    private ImageView mSaveIv;
    private String mUrl;
    private Activity mActivity;

    public static void gotoActivity(Activity activity, String url) {
        Intent intent = new Intent(activity, SignatureActivity.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.signature_activity);
        mUrl = getIntent().getStringExtra("url");
        initVIew();
    }

    private void initVIew() {
        mSv = findViewById(R.id.signature_sv);
        mSv.setBackColor(Utils.getAppResources().getColor(R.color.white));
        mSaveIv = findViewById(R.id.signature_save_iv);
        mSaveIv.setVisibility(View.GONE);
        findViewById(R.id.signature_clear_btn).setOnClickListener(mClickListener);
        findViewById(R.id.signature_save_btn).setOnClickListener(mClickListener);
        showOldBit();
    }

    private void showOldBit() {
        if (TextUtils.isEmpty(mUrl)) return;
        mSv.getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                mSv.setOldBitmap(mUrl);
            }
        });
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.signature_clear_btn:
                    mSv.clear();
                    break;
                case R.id.signature_save_btn:
                    try {
                        upSign();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

            }
        }
    };

    private void upSign() throws Exception {
//        showWaitDialog();
        String mImagePath = FileUtils.getImageFilePath();
        FileUtils.checkOrCreateDirectory(mImagePath);
        mSv.save(mImagePath);
        File bitmapFile = mSv.getBitmapFile();
        Log.d("aaa", "bitmapFile = " + bitmapFile);

        HttpUtils.upLoadImgFile(bitmapFile, "1", new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d("aaa", "上传成功");
                String result = response.body().string();
                Log.d("aaa", "result = " + result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SignatureActivity.this, "上传签名成功", Toast.LENGTH_LONG);
                        finish();
                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("aaa", "上传失败");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(BaseAPP.getmContext(), "上传失败，请重试", Toast.LENGTH_LONG);
                    }
                });
            }
        });
    }
}
