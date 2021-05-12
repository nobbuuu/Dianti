package com.qt.dtzf.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.EventMessage;
import com.base.baselib.bean.ImageUrl;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.glide.GlideUtils;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.ApiUrl;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.AlbumUtils;
import com.base.baselib.utils.Utils;
import com.qt.dtzf.R;

import androidx.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 添加人员
 */
public class PersonnelAddActivity extends BaseActivity {

    private AlbumUtils mAlbumUtils;

    private EditText phoneEt;
    private EditText nameEt;
    private EditText cardEt;
    private EditText workCardEt;
    private ImageView cardIv_0;
    private ImageView cardIv_1;
    private ImageView workCardIv_0;
    private ImageView workCardIv_1;
    private ImageView workCardIv_2;

    private String[] images = new String[5];
    private ImageView[] imageViews = new ImageView[5];
    private int imageTag = 0;

    public static void gotoActivity(Activity activity) {
        Intent intent = new Intent(activity, PersonnelAddActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personnel_add_activity);
        mAlbumUtils = new AlbumUtils(this, mAlbumListener);
        initView();
    }

    private void initView() {
        phoneEt = findViewById(R.id.personnel_add_phone_et);
        nameEt = findViewById(R.id.personnel_add_name_et);
        cardEt = findViewById(R.id.personnel_add_card_et);
        workCardEt = findViewById(R.id.personnel_work_card_et);

        cardIv_0 = findViewById(R.id.personnel_card_iv);
        cardIv_1 = findViewById(R.id.personnel_card_1_iv);

        workCardIv_0 = findViewById(R.id.personnel_work_card_0_iv);
        workCardIv_1 = findViewById(R.id.personnel_work_card_1_iv);
        workCardIv_2 = findViewById(R.id.personnel_work_card_2_iv);

        imageViews[0] = cardIv_0;
        imageViews[1] = cardIv_1;
        imageViews[2] = workCardIv_0;
        imageViews[3] = workCardIv_1;
        imageViews[4] = workCardIv_2;

        findViewById(R.id.personnel_card_add_tv).setOnClickListener(mClickListener);
        findViewById(R.id.personnel_card_add_1_tv).setOnClickListener(mClickListener);

        findViewById(R.id.personnel_work_card_add_0_tv).setOnClickListener(mClickListener);
        findViewById(R.id.personnel_work_card_add_1_tv).setOnClickListener(mClickListener);
        findViewById(R.id.personnel_work_card_add_2_tv).setOnClickListener(mClickListener);
        findViewById(R.id.title_right_tv).setOnClickListener(mClickListener);
    }


    AlbumUtils.AlbumListener mAlbumListener = new AlbumUtils.AlbumListener() {
        @Override
        public void onListener(List<ImgBean> list, boolean isCamera) {
            upImage(list.get(0).getFilePath());
        }
    };

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.title_right_tv:
                    //提交
                    upUserInfo();
                    break;
                case R.id.personnel_card_add_tv:
                    //身份证正面
                    imageTag = 0;
                    mAlbumUtils.showDialog(getSupportFragmentManager(), 1);
                    break;
                case R.id.personnel_card_add_1_tv:
                    //反面
                    imageTag = 1;
                    mAlbumUtils.showDialog(getSupportFragmentManager(), 1);
                    break;
                case R.id.personnel_work_card_add_0_tv:
                    //工作正面
                    imageTag = 2;
                    mAlbumUtils.showDialog(getSupportFragmentManager(), 1);
                    break;
                case R.id.personnel_work_card_add_1_tv:
                    //工作反面
                    imageTag = 3;
                    mAlbumUtils.showDialog(getSupportFragmentManager(), 1);
                    break;
                case R.id.personnel_work_card_add_2_tv:
                    //工作都
                    imageTag = 4;
                    mAlbumUtils.showDialog(getSupportFragmentManager(), 1);
                    break;
            }
        }
    };

    @SuppressLint("CheckResult")
    private void upUserInfo() {
        String name = nameEt.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Utils.showToast("请输入姓名");
            return;
        }

        String phone = phoneEt.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Utils.showToast("请输入电话");
            return;
        }
        String cardId = cardEt.getText().toString().trim();
        if (TextUtils.isEmpty(cardId)) {
            Utils.showToast("请输入身份证号");
            return;
        }
        String workId = workCardEt.getText().toString().trim();
        if (TextUtils.isEmpty(workId)) {
            Utils.showToast("请输入工作证号");
            return;
        }
        for (int i = 0; i < images.length; i++) {
            String strUrl = images[i];
            if (TextUtils.isEmpty(strUrl)) {
                Utils.showToast("请上传图片");
                return;
            }
        }

        showWaitDialog();
        Observable<Bean<String>> info = WorkModel.getInstance().newWorkerInfo(name, phone, cardId, workId, images[0],
                images[1], images[2], images[3], images[4]);
        info.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<String>>() {
                    @Override
                    public void onSuccess(Bean<String> stringBean) {
                        Utils.showToast(stringBean.message);
                        EventBus.getDefault().post(new EventMessage(1));
                        Intent intent = new Intent(PersonnelAddActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });
    }


    private void upImage(String url) {
        showWaitDialog();
        Observable<Bean<ImageUrl>> observable = WorkModel.getInstance().uploadImg(url);
        observable.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<ImageUrl>>() {
                    @Override
                    public void onSuccess(Bean<ImageUrl> imageUrlBean) {
                        images[imageTag] = imageUrlBean.data.src;
                        String url = ApiUrl.getBaseImageUrl() + images[imageTag];
                        GlideUtils.loadImageRoundCrop(PersonnelAddActivity.this, url, imageViews[imageTag], 10);
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mAlbumUtils != null) mAlbumUtils.onActivityResult(requestCode, resultCode, data);
    }

}
