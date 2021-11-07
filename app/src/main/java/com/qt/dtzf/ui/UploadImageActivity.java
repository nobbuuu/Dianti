package com.qt.dtzf.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.observers.DeferredScalarObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.EmptyBean;
import com.base.baselib.bean.H5ResultBean;
import com.base.baselib.bean.ImageUrl;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.AlbumUtils;
import com.base.baselib.utils.HttpUtils;
import com.base.baselib.utils.LogUtils;
import com.base.baselib.utils.NetResultParseUtils;
import com.base.baselib.utils.Utils;
import com.base.baselib.view.TitleView;
import com.google.gson.Gson;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.SafeCheckItemAdapter;
import com.qt.dtzf.bean.ImgResultBean;
import com.qt.dtzf.utils.ToastUtils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 问题描述详情
 */
public class UploadImageActivity extends BaseActivity {

    private RecyclerView mDescribeRv;
    private SafeCheckItemAdapter mAdapter;
    private AlbumUtils mAlbumUtils;
    private int mMaxSize = 6;
    private boolean isUploadAll;
    private int uploadNum;
    private String mImges = "";
    private boolean isCheckBasis;
    private List<ImgBean> dataList = new ArrayList<>();
    private List<ImgBean> originList = new ArrayList<>();
    private AppCompatButton uploadImageBtn;

    private TitleView mTitleView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_image_activity);
        mAlbumUtils = new AlbumUtils(this, mAlbumListener);
        initView();
    }

    private void initView() {

        mDescribeRv = findViewById(R.id.upload_image_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mDescribeRv.setLayoutManager(layoutManager);
        mAdapter = new SafeCheckItemAdapter(this, mMaxSize);

        mAdapter.addImageListener(mImageListener);
        mDescribeRv.setAdapter(mAdapter);
        uploadImageBtn = findViewById(R.id.upload_image_btn);
        mTitleView = findViewById(R.id.upload_image_title);

        uploadImageBtn.setOnClickListener(mClickListener);
        mImges = getIntent().getStringExtra("xcqzImg");
        isCheckBasis = getIntent().getBooleanExtra("checkBasis", false);
        if (isCheckBasis) {
            uploadImageBtn.setVisibility(View.GONE);
            mTitleView.setTitleText("检查依据");
        }
        if (!TextUtils.isEmpty(mImges)) {
            if (mImges.length() > 0) {
                originList.clear();
                dataList.clear();
                if (mImges.contains(",")) {
                    String[] imges = mImges.split(",");
                    if (imges.length > 0) {
                        for (int i = 0; i < imges.length; i++) {
                            ImgBean bean = new ImgBean();
                            bean.setImgUrl(imges[i]);
                            bean.setFilePath(imges[i]);
                            originList.add(bean);
                        }
                    }
                } else {
                    ImgBean bean = new ImgBean();
                    bean.setImgUrl(mImges);
                    bean.setFilePath(mImges);
                    originList.add(bean);
                }
                dataList.addAll(originList);
                mAdapter.addImageList(dataList, false, isCheckBasis);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isUploadAll) {
            upAllImgFiles();
        } else {
            ToastUtils.Toast_long("上传图片中 ，请稍候重试");
        }
    }

    AlbumUtils.AlbumListener mAlbumListener = new AlbumUtils.AlbumListener() {
        @Override
        public void onListener(List<ImgBean> list, boolean isCamera) {
            Log.d("aaa", "mAlbumListener list.size() = " + list.size());
            if (list != null && list.size() > 0) {
                uploadNum = 0;
                isUploadAll = false;
                for (ImgBean fileBean : list) {
                    if (fileBean != null) {
                        String filePath = fileBean.getFilePath();
                        Log.d("aaa", "imgPath = " + filePath);
                        if (!TextUtils.isEmpty(filePath)) {
                            File file = new File(filePath);
                            HttpUtils.uploadImg(file, new Callback() {
                                @Override
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                                }

                                @Override
                                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                    String responseStr = response.body().string();
                                    Log.d("aaa", "responseStr = " + responseStr);
                                    ImgResultBean imgResultBean = new Gson().fromJson(responseStr, ImgResultBean.class);
                                    if (imgResultBean != null) {
                                        ImgResultBean.DataBean data = imgResultBean.getData();
                                        if (data != null) {
                                            fileBean.setImgUrl(data.getQnUrl());
                                            uploadNum++;
                                            if (uploadNum == list.size()) {
                                                isUploadAll = true;
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            }
            dataList.clear();
//            dataList.addAll(originList);
            dataList.addAll(list);
            mAdapter.addImageList(dataList, isCamera, isCheckBasis);
        }
    };

    SafeCheckItemAdapter.ImageListener mImageListener = new SafeCheckItemAdapter.ImageListener() {
        @Override
        public void addImageListener() {
            List<ImgBean> list = mAdapter.getImageList();
            int maxSize = mMaxSize - list.size();
            mAlbumUtils.showDialog(getSupportFragmentManager(), maxSize);
        }

        @Override
        public void deleteImageListener() {
            uploadNum--;
        }
    };

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isUploadAll) {
                upAllImgFiles();
            } else {
                ToastUtils.Toast_long("上传图片中 ，请稍候重试");
            }
        }
    };

    int tag = 0;
    List<ImgBean> mList;
    List<String> mImageList = new ArrayList<>();

    private void upAllImgFiles() {
        mList = mAdapter.getImageList();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < mList.size(); i++) {
            String imgUrl = mList.get(i).getImgUrl();
            if (!TextUtils.isEmpty(imgUrl)) {
                buffer.append(imgUrl + ",");
            }
        }
        String imgUrls = buffer.toString();
        if (!TextUtils.isEmpty(imgUrls)) {
            imgUrls = imgUrls.substring(0, imgUrls.lastIndexOf(","));
        }
        String taskId = getIntent().getStringExtra("taskId");
        showWaitDialog();
        Observable<Bean<EmptyBean>> observable = WorkModel.getInstance().saveXCQZImg(taskId, imgUrls);
        String finalImgUrls = imgUrls;
        observable.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<EmptyBean>>() {
                    @Override
                    public void onSuccess(Bean<EmptyBean> bean) {
                        ToastUtils.Toast_long("提交成功");
                        Intent intent = getIntent();
                        intent.putExtra("imgList", new Gson().toJson(mList));
                        setResult(117,intent);
                        finish();
                    }

                    @Override
                    public void onFail(String code, String msg) {
                        super.onFail(code, msg);
                        hideWaitDialog();
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });
    }

    private void upImages() {
        mList = mAdapter.getImageList();
        if (mList == null || mList.size() <= 0) return;
        tag = 0;
        mImageList.clear();
        showWaitDialog();
        upImageItem(mList.get(tag).getFilePath());
    }

    private void upImageList() {
        mList = mAdapter.getImageList();
        Observable<BeanList<ImageUrl>> imgList = WorkModel.getInstance().uploadImgList(mList);
        imgList.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BeanList<ImageUrl>>() {
                    @Override
                    public void onSuccess(BeanList<ImageUrl> imageUrlBeanList) {

                    }
                });
    }

    @SuppressLint("CheckResult")
    private void upAllImage() {
        mList = mAdapter.getImageList();
        String[] images = new String[mList.size()];
        for (int i = 0; i < mList.size(); i++) {
            images[i] = mList.get(i).getFilePath();
        }
        showWaitDialog();
        Observable.fromArray(images).flatMap(new Function<String, ObservableSource<Bean<ImageUrl>>>() {
            @Override
            public ObservableSource<Bean<ImageUrl>> apply(String s) throws Exception {
                return WorkModel.getInstance().uploadImg(s);
            }
        }).compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<Bean<ImageUrl>>() {
                    @Override
                    public boolean test(Bean<ImageUrl> imageUrlBean) throws Exception {
                        boolean b = NetResultParseUtils.parseNetResult(imageUrlBean);
                        if (!b) {
                            Utils.showToast(imageUrlBean.message);
                        }
                        return b;
                    }
                })
                .toList()
                .map(new Function<List<Bean<ImageUrl>>, String>() {
                    @Override
                    public String apply(List<Bean<ImageUrl>> beans) throws Exception {
                        String json = "";
                        for (int i = 0; i < beans.size(); i++) {
                            String s = beans.get(i).data.src;
                            json = json + "," + s;
                        }
                        return json.substring(1, json.length());
                    }
                })
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onSuccess(String s) {
                        hideWaitDialog();
                        Intent intent = new Intent();
                        LogUtils.e("上传图片地址 = " + s);
                        intent.putExtra("image", s);
                        setResult(TaskMainActivity.ImageResultCode, intent);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitDialog();
                    }
                });
    }

    private void upImageItem(String url) {
        Observable<Bean<ImageUrl>> observable = WorkModel.getInstance().uploadImg(url);
        observable.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<ImageUrl>>() {
                    @Override
                    public void onSuccess(Bean<ImageUrl> bean) {
                        mImageList.add(bean.data.src);
                        tag++;
                        if (tag < mList.size()) {
                            String s = mList.get(tag).getFilePath();
                            upImageItem(s);
                        } else if (tag >= mList.size()) {
                            hideWaitDialog();
                            if (mImageList.size() < mList.size()) {
                                Utils.showToast("图片上传失败");
                                return;
                            } else {
                                // 返回界面
                                Intent intent = new Intent();
                                String json = "";
                                for (int i = 0; i < mImageList.size(); i++) {
                                    String s = mImageList.get(i);
                                    json = json + "," + s;
                                }
                                json = json.substring(1, json.length());
                                LogUtils.e("上传图片地址 = " + json);
                                intent.putExtra("image", json);
                                setResult(TaskMainActivity.ImageResultCode, intent);
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onFail(String code, String msg) {
                        super.onFail(code, msg);
                        hideWaitDialog();
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
