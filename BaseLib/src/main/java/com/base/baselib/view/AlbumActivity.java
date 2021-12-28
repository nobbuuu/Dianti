package com.base.baselib.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.R;
import com.base.baselib.adapter.AlbumAdapter;
import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.AppImage;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.utils.AlbumUtils;
import com.base.baselib.utils.LogUtils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 多图片选择界面
 */
public class AlbumActivity extends BaseActivity {

    private AlbumAdapter mAdapter;
    private TitleView mTitle;

    public static String MaxSize = "maxSize";
    public int mMaxSize = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_activity);
        initData();
        initView();
        getAppImageList();
    }

    public static void gotoActivity(BaseActivity context, int requestCode, int maxSize) {
        Intent intent = new Intent(context, AlbumActivity.class);
        intent.putExtra(MaxSize, maxSize);
        context.startActivityForResult(intent, requestCode);
    }

    private void initView() {
        mTitle = findViewById(R.id.audio_title);
        mTitle.mTitleTv.setText("(" + 0 + "/" + mMaxSize + ")");
        findViewById(R.id.title_left_ll).setOnClickListener(mClickListener);
        findViewById(R.id.title_right_fl).setOnClickListener(mClickListener);
        RecyclerView mAudioRv = findViewById(R.id.audio_rv);
        mAudioRv.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new AlbumAdapter(this, mMaxSize, mSelectedListener);
        mAudioRv.setAdapter(mAdapter);
    }


    private void initData() {

        Intent intent = getIntent();
        if (intent == null) return;
        mMaxSize = intent.getIntExtra(MaxSize, 0);
        if (mMaxSize == 0) finish();

    }

    AlbumAdapter.SelectedListener mSelectedListener = map -> {
        int size = map.size();
        mTitle.mTitleTv.setText("(" + size + "/" + mMaxSize + ")");
    };

    View.OnClickListener mClickListener = v -> {

        if (v.getId() == R.id.title_left_ll) {
            finish();
            return;
        }

        if (v.getId() == R.id.title_right_fl) {
            List<ImgBean> list = mAdapter.getSelectedList();
            Intent intent = new Intent();
            intent.putExtra(AlbumUtils.ImageList, (Serializable) list);
            setResult(RESULT_OK, intent);
            finish();
        }

    };

    @SuppressLint("CheckResult")
    private void getAppImageList() {
        showWaitDialog();
        Observable.create((ObservableOnSubscribe<AppImage>) emitter -> {
            Cursor cursor = getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
            while (cursor.moveToNext()) {
                //获取图片的名称
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                //获取图片的生成日期
                byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                //获取图片的详细信息
                String desc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));

                AppImage image = new AppImage();
                image.name = name;
                image.desc = desc;
                image.fileName = new String(data, 0, data.length - 1);
                image.imageFile = new File(image.fileName);
                emitter.onNext(image);
            }
            cursor.close();
            emitter.onComplete();
        }).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe((appImages, throwable) -> {
                    hideWaitDialog();
                    Collections.sort(appImages, new FileComparator());
                    mAdapter.setData(appImages);
                });
    }

    private class FileComparator implements Comparator<AppImage> {

        @Override
        public int compare(AppImage lhs, AppImage rhs) {
            //最后修改的照片在前
            if (lhs.imageFile.lastModified() == rhs.imageFile.lastModified()) {
                return 0;
            } else if (lhs.imageFile.lastModified() < rhs.imageFile.lastModified()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
