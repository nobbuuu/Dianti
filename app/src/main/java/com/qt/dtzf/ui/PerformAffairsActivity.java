package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
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
import com.base.baselib.bean.EmptyBean;
import com.base.baselib.bean.HistotyBean;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.bean.TaskInfo;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.blankj.utilcode.util.BarUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.PerformHistoryAdapter;
import com.qt.dtzf.adapter.PerformImgAdapter;
import com.qt.dtzf.common.ActionCallback;
import com.qt.dtzf.common.WaterFallItemDecoration;
import com.qt.dtzf.dialog.ConfirmTaskDialog;
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
public class PerformAffairsActivity extends BaseActivity {

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
    @BindView(R.id.backIv)
    ImageView backIv;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.completeTv)
    TextView completeTv;
    @BindView(R.id.cameraIcon)
    ImageView cameraIcon;
    @BindView(R.id.qustionIv)
    ImageView qustionIv;
    @BindView(R.id.countTv)
    TextView countTv;
    @BindView(R.id.titleBar)
    ConstraintLayout titleBar;
    private TaskInfo.ListBean mTaskInfo;
    private String imgUrls = "";
    private PerformImgAdapter performImgAdapter;
    private List<ImgBean> mImgList = new ArrayList<>();

    public static void gotoActivity(Activity activity, TaskInfo.ListBean taskInfoBean) {
        Intent intent = new Intent(activity, PerformAffairsActivity.class);
        intent.putExtra("taskInfoBean", taskInfoBean);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_task);
        ButterKnife.bind(this);
        titleBar.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0);
        mTaskInfo = (TaskInfo.ListBean) getIntent().getSerializableExtra("taskInfoBean");
        if (mTaskInfo == null) {
            ToastUtils.Toast_long("数据异常");
            finish();
        }
        uploadImgLay = findViewById(R.id.uploadImgLay);
        getData();
        onevent();
    }

    private void onevent() {
        performImgAdapter = new PerformImgAdapter(this, mImgList, R.layout.rvitem_onlyimg);
        imgRv.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL));
        imgRv.addItemDecoration(new WaterFallItemDecoration(30, 30));
        imgRv.setAdapter(performImgAdapter);
    }

    private void getData() {
        Observable<Bean<HistotyBean>> detail = WorkModel.getInstance()
                .getAffairPointList(mTaskInfo.getId());
        detail.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<HistotyBean>>() {
                    @Override
                    public void onSuccess(Bean<HistotyBean> bean) {
                        List<HistotyBean.ListBean> list = bean.data.getList();
                        if (list != null && list.size() > 0) {
                            Log.d("aaa","list.size = " + list.size());
                            PerformHistoryAdapter adapter = new PerformHistoryAdapter(PerformAffairsActivity.this, list, R.layout.rvitem_affairs_history);
                            uploadHisRv.setAdapter(adapter);
                        }
                        int totalCount = bean.data.getTotalCount();
                        if (totalCount != 0) {
                            countTv.setVisibility(View.VISIBLE);
                            countTv.setText(totalCount + "");
                        } else {
                            countTv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });
    }

    private void commitTask() {
        String inputStr = inputEdt.getText().toString();
        if (inputStr.isEmpty()) {
            ToastUtils.Toast_long("请先输入检查结果");
            return;
        }
        Observable<Bean<EmptyBean>> detail = WorkModel.getInstance()
                .submitAffairPoint(mTaskInfo.getId(), inputStr, imgUrls, "");
        detail.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<EmptyBean>>() {
                    @Override
                    public void onSuccess(Bean<EmptyBean> taskDetailBean) {
                        ToastUtils.Toast_long("提交成功");
                        inputEdt.setText("");
                        mImgList.clear();
                        performImgAdapter.notifyDataSetChanged();
                        getData();
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });

    }

    private void confirmAffairTask() {
        Observable<Bean<EmptyBean>> detail = WorkModel.getInstance()
                .confirmAffairTask(mTaskInfo.getId());
        detail.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<EmptyBean>>() {
                    @Override
                    public void onSuccess(Bean<EmptyBean> taskDetailBean) {
                        ToastUtils.Toast_long("任务已完成");
                        SystemClock.sleep(800);
                        finish();
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });

    }

    private void toImageListForActivity() {
        Intent intent = new Intent(this, UploadImageActivity.class);
        if (mTaskInfo != null) {
            intent.putExtra("taskId", mTaskInfo.getId());
        }
        intent.putExtra("fromTag", 2);
        startActivityForResult(intent, ImageRequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 117 && data != null) {
            String imgList = data.getStringExtra("imgList");
            List<ImgBean> mList = new Gson().fromJson(imgList, new TypeToken<List<ImgBean>>() {}.getType());
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
                mImgList.clear();
                mImgList.addAll(mList);
                performImgAdapter.notifyDataSetChanged();
            }
        }
    }

    @OnClick({R.id.backIv, R.id.completeTv, R.id.uploadImgLay, R.id.commitTv, R.id.qustionIv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backIv:
                finish();
                break;
            case R.id.completeTv:
                new ConfirmTaskDialog(PerformAffairsActivity.this, new ActionCallback() {
                    @Override
                    public void onAction() {
                        confirmAffairTask();
                    }
                }).show();
                break;
            case R.id.uploadImgLay:
                toImageListForActivity();
                break;
            case R.id.commitTv:
                commitTask();
                break;
            case R.id.qustionIv:
                FeedBackActivity.gotoActivity(this, mTaskInfo.getId(), mTaskInfo.getTaskId());
                break;
        }
    }
}
