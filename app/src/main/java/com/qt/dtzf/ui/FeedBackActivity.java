package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
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
import com.base.baselib.view.TitleView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.PerformHistoryAdapter;
import com.qt.dtzf.adapter.PerformImgAdapter;
import com.qt.dtzf.common.ActionCallback;
import com.qt.dtzf.common.WaterFallItemDecoration;
import com.qt.dtzf.dialog.ConfirmTaskDialog;
import com.qt.dtzf.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.qt.dtzf.ui.TaskMainActivity.ImageRequestCode;

/**
 * Create By s on
 */
public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.task_list_title)
    TitleView taskListTitle;
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

    private TaskInfo.ListBean mTaskInfo;
    private String imgUrls = "";

    public static void gotoActivity(Activity activity, TaskInfo.ListBean taskInfoBean) {
        Intent intent = new Intent(activity, FeedBackActivity.class);
        intent.putExtra("taskInfoBean", taskInfoBean);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_task);
        ButterKnife.bind(this);
        mTaskInfo = (TaskInfo.ListBean) getIntent().getSerializableExtra("taskInfoBean");
        uploadImgLay = findViewById(R.id.uploadImgLay);
        onevent();
        getData();
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
                        if (list!=null && list.size()>0){
                            List<HistotyBean.ListBean.PointListBean> pointList = list.get(0).getPointList();
                            if (pointList != null && pointList.size() > 0){
                                PerformHistoryAdapter adapter = new PerformHistoryAdapter(FeedBackActivity.this, pointList, R.layout.rvitem_affairs_history);
                                uploadHisRv.setAdapter(adapter);
                            }
                        }
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });
    }

    private void onevent() {
        uploadImgLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toImageListForActivity();
            }
        });
        commitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commitTask();
            }
        });

        taskListTitle.mTitleRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ConfirmTaskDialog(FeedBackActivity.this, new ActionCallback() {
                    @Override
                    public void onAction() {
                        confirmAffairTask();
                    }
                }).show();
            }
        });
    }

    private void commitTask() {
        String inputStr = inputEdt.getText().toString();
        if (imgUrls.isEmpty()){
            ToastUtils.Toast_long("请先上传现场照片");
            return;
        }
        Observable<Bean<EmptyBean>> detail = WorkModel.getInstance()
                .submitAffairPoint(mTaskInfo.getId(),inputStr,imgUrls,"");
        detail.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<EmptyBean>>() {
                    @Override
                    public void onSuccess(Bean<EmptyBean> taskDetailBean) {
                        ToastUtils.Toast_long("提交成功");
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

            imgUrls = data.getStringExtra("imgList");
            List<ImgBean> mList = new Gson().fromJson(imgUrls, new TypeToken<List<ImgBean>>(){}.getType());
            if (mList != null) {
                imgRv.setLayoutManager(new StaggeredGridLayoutManager(3,RecyclerView.VERTICAL));
                imgRv.addItemDecoration(new WaterFallItemDecoration(30,30));
                imgRv.setAdapter(new PerformImgAdapter(this,mList,R.layout.rvitem_onlyimg));
            }
        }
    }
}
