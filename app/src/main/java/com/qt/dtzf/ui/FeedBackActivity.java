package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.CommentsBean;
import com.base.baselib.bean.HistotyBean;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.blankj.utilcode.util.BarUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.CommentsAdapter;
import com.qt.dtzf.adapter.PerformImgAdapter;
import com.qt.dtzf.common.TActionCallback;
import com.qt.dtzf.common.WaterFallItemDecoration;
import com.qt.dtzf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.qt.dtzf.ui.TaskMainActivity.ImageRequestCode;

/**
 * Create By s on
 */
public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.uploadImgLay)
    CardView uploadImgLay;
    @BindView(R.id.taskTitle)
    TextView taskTitle;
    @BindView(R.id.inputEdt)
    EditText inputEdt;
    @BindView(R.id.imgRv)
    RecyclerView imgRv;
    @BindView(R.id.editLay)
    ConstraintLayout editLay;
    @BindView(R.id.uploadHisRv)
    RecyclerView uploadHisRv;
    @BindView(R.id.commitTv)
    TextView commitTv;
    @BindView(R.id.cameraIcon)
    ImageView cameraIcon;
    @BindView(R.id.backIv)
    ImageView backIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.qustionIv)
    ImageView qustionIv;
    @BindView(R.id.countTv)
    TextView countTv;
    @BindView(R.id.completeTv)
    TextView completeTv;
    @BindView(R.id.titleBar)
    ConstraintLayout titleBar;

    private String mTaskId;
    private String mId;
    private String imgUrls = "";
    private int requestCode1 = 100;
    private int requestCode2 = 200;
    private List<ImgBean> mImgList = new ArrayList<>();
    private List<CommentsBean> commentsList = new ArrayList<>();
    private PerformImgAdapter imgAdapter;
    private CommentsAdapter commentsAdapter;

    public static void gotoActivity(Activity activity, String id, String taskId) {
        Intent intent = new Intent(activity, FeedBackActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("taskId", taskId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_task);
        ButterKnife.bind(this);
        titleBar.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0);
        mTaskId = getIntent().getStringExtra("taskId");
        mId = getIntent().getStringExtra("id");
        if (mTaskId == null || mTaskId.isEmpty()) {
            ToastUtils.Toast_long("数据异常");
            finish();
        }
        qustionIv.setVisibility(View.GONE);
        uploadImgLay.setVisibility(View.GONE);
        countTv.setVisibility(View.GONE);
        completeTv.setVisibility(View.GONE);
        cameraIcon.setVisibility(View.VISIBLE);
        titleTv.setText("问题反馈");
        initCommentsAdapter();
        getData();
        initRv();
    }

    private void initRv() {
        imgAdapter = new PerformImgAdapter(this, mImgList, R.layout.rvitem_onlyimg);
        imgRv.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL));
        imgRv.addItemDecoration(new WaterFallItemDecoration(30, 30));
        imgRv.setAdapter(imgAdapter);
    }

    private void initCommentsAdapter() {
        commentsAdapter = new CommentsAdapter(FeedBackActivity.this, commentsList, R.layout.rvitem_comments,null);
        commentsAdapter.setActionCallBack(new TActionCallback<CommentsBean>() {
            @Override
            public void onAction(int tag, CommentsBean data, String inputStr) {
                if (tag == 1) {
                    toImageListForActivity(requestCode2);
                } else if (tag == 2) {
                    commitTask(data.getId() + "", inputStr);
                }
            }
        });
        uploadHisRv.setAdapter(commentsAdapter);
    }

    private void getData() {
        Observable<BeanList<CommentsBean>> detail = WorkModel.getInstance().getAffairCommentList(mTaskId);
        detail.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BeanList<CommentsBean>>() {
                    @Override
                    public void onSuccess(BeanList<CommentsBean> data) {
                        commentsList.clear();
                        commentsList.addAll(data.data);
                        commentsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });
    }

    private void commitTask(String replyId, String inputStr) {
        if (inputStr.isEmpty()) {
            ToastUtils.Toast_long("请先输入内容");
            return;
        }
        Observable<Bean<HistotyBean>> detail = WorkModel.getInstance()
                .submitAffairComment(mId, inputStr, replyId, imgUrls);
        detail.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<HistotyBean>>() {
                    @Override
                    public void onSuccess(Bean<HistotyBean> taskDetailBean) {
                        if (replyId.equals("0")) {
                            ToastUtils.Toast_long("提交成功");
                        } else {
                            ToastUtils.Toast_long("回复成功");
                        }
                        imgUrls = "";
                        mImgList.clear();
                        imgAdapter.notifyDataSetChanged();
                        getData();
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });

    }

    private void toImageListForActivity(int requestCode) {
        Intent intent = new Intent(this, UploadImageActivity.class);
        intent.putExtra("taskId", mTaskId);
        intent.putExtra("fromTag", 2);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 117 && data != null) {
            String imgList = data.getStringExtra("imgList");
            List<ImgBean> mList = new Gson().fromJson(imgList, new TypeToken<List<ImgBean>>() {
            }.getType());
            if (mList != null && mList.size() > 0) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < mList.size(); i++) {
                    if (i == mList.size() - 1) {
                        stringBuffer.append(mList.get(i).getImgUrl());
                    } else {
                        stringBuffer.append(mList.get(i).getImgUrl() + ",");
                    }
                }
                imgUrls = stringBuffer.toString();
                if (requestCode == requestCode1) {
                    mImgList.clear();
                    mImgList.addAll(mList);
                    imgAdapter.notifyDataSetChanged();
                } else if (requestCode == requestCode2) {
                    commentsAdapter.setDialogImg(mList);
                }
            }
        }
    }

    @OnClick({R.id.backIv, R.id.commitTv, R.id.cameraIcon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backIv:
                finish();
                break;
            case R.id.cameraIcon:
                toImageListForActivity(requestCode1);
                break;
            case R.id.commitTv:
                String inputStr = inputEdt.getText().toString();
                commitTask("0", inputStr);
                break;
        }
    }
}
