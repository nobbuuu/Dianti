package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.base.ErrorFragment;
import com.base.baselib.bean.TaskDetail;
import com.base.baselib.bean.TaskElevator;
import com.base.baselib.bean.TaskInfo;
import com.base.baselib.bean.TaskInfoItem;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.qt.dtzf.R;
import com.qt.dtzf.utils.DateFormatUtil;
import com.qt.dtzf.utils.ToastUtils;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 任务确认界面
 */
public class TaskListItemActivity extends BaseActivity {

    private ImageView mHeadLeftIm;
    private ImageView mHeadRightIm;
    private TextView mNameTv;
    private TextView mTimeTv;
    private TextView mAddressTv;
    private TextView mNumberTv;
    private TextView mDevTv;
    private TextView mCheckTv;
    private TextView mCheckBasicTv;

    private TaskInfo.ListBean mTaskInfo;

    public static void gotoActivity(Activity activity, TaskInfo.ListBean item) {
        Intent intent = new Intent(activity, TaskListItemActivity.class);
        intent.putExtra("taskInfoBean", item);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_item_activity);
        mTaskInfo = (TaskInfo.ListBean) getIntent().getSerializableExtra("taskInfoBean");
        if (mTaskInfo == null) {
            Toast.makeText(this, "数据异常", Toast.LENGTH_SHORT).show();
            return;
        }
        initView();
        getData();
    }


    private void initView() {
        mHeadLeftIm = findViewById(R.id.sampling_task_head_l_im);
        mHeadRightIm = findViewById(R.id.sampling_task_head_r_im);
        mNameTv = findViewById(R.id.sampling_task_name_tv);
        mTimeTv = findViewById(R.id.sampling_task_day_tv);
        mAddressTv = findViewById(R.id.sampling_task_address_tv);
        mNumberTv = findViewById(R.id.sampling_task_number_tv);
        mDevTv = findViewById(R.id.sampling_task_dev_tv);
        mCheckTv = findViewById(R.id.sampling_task_check_tv);
        mCheckBasicTv = findViewById(R.id.sampling_task_basis_tv);
        if (TextUtils.isEmpty(mTaskInfo.getCheckBasis())){
            mCheckBasicTv.setVisibility(View.GONE);
        }else {
            mCheckBasicTv.setVisibility(View.VISIBLE);
        }
        mCheckBasicTv.setOnClickListener(mClickListener);
        findViewById(R.id.sampling_task_btn).setOnClickListener(mClickListener);
    }

    private void getData() {
        Observable<Bean<TaskDetail>> detail = WorkModel.getInstance().getTaskDetail(mTaskInfo.getId(), mTaskInfo.getTaskId(), mTaskInfo.getQualityType());
        showWaitFragment();
        detail.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<TaskDetail>>() {
                    @Override
                    public void onSuccess(Bean<TaskDetail> taskDetailBean) {
                        hideErrorView();
                        setData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        showErrorView(mRefreshListener);
                    }

                    @Override
                    public void onFail(String code, String msg) {
                        super.onFail(code, msg);
                        showErrorView(mRefreshListener);
                    }
                });

    }

    private void confirmTask() {
        Observable<Bean<TaskInfoItem>> detail = WorkModel.getInstance().confirmTask(mTaskInfo.getId(), mTaskInfo.getTaskId());
        detail.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<TaskInfoItem>>() {
                    @Override
                    public void onSuccess(Bean<TaskInfoItem> taskDetailBean) {
                        TaskSignInActivity.gotoActivity(TaskListItemActivity.this, mTaskInfo);
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });

    }

    ErrorFragment.RefreshListener mRefreshListener = new ErrorFragment.RefreshListener() {
        @Override
        public void onRefresh() {
            getData();
        }
    };

    private void setData() {
        if (mTaskInfo == null) return;
        mNameTv.setText(mTaskInfo.getUserNames());
        Date date = new Date(mTaskInfo.getStartdate() * 1000l);
        String time = DateFormatUtil.getTime(date, DateFormatUtil.Ymd);
        mTimeTv.setText(time);
        mAddressTv.setText(mTaskInfo.getDataAddress());
        int num = 10;
        /*List<TaskElevator> elevator_list = mTaskInfo.elevator_list;
        for (int i = 0; i < elevator_list.size(); i++) {
            TaskElevator item = elevator_list.get(i);
            num += item.total;
        }*/
        mNumberTv.setText(num + "台");
        mDevTv.setText(mTaskInfo.getDataName());
        mCheckTv.setText(mTaskInfo.getContent());
    }


    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.sampling_task_basis_tv:
                    //检查依据
                    if (mTaskInfo != null && !TextUtils.isEmpty(mTaskInfo.getCheckBasis())) {
                        toImageListForActivity();
                    }else {
                        ToastUtils.Toast_long("数据异常");
                    }
                    break;
                case R.id.sampling_task_btn:
                    //任务 确认
                    confirmTask();
                    break;
            }
        }
    };

    private void toImageListForActivity() {
        Intent intent = new Intent(TaskListItemActivity.this, UploadImageActivity.class);
        if (mTaskInfo != null) {
            intent.putExtra("taskId", mTaskInfo.getId());
            intent.putExtra("xcqzImg", mTaskInfo.getCheckBasis());
            intent.putExtra("checkBasis", true);
        }
        startActivity(intent);
    }

}