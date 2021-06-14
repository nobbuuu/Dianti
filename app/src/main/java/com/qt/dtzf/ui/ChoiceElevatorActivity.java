package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.ChoicedBean;
import com.base.baselib.bean.DeviceTypeItem;
import com.base.baselib.bean.ElevatorInfo;
import com.base.baselib.bean.TaskDetail;
import com.base.baselib.bean.TaskElevator;
import com.base.baselib.bean.TaskInfo;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;
import com.base.baselib.utils.Utils;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.ChoiceElevatorAdapter;
import com.qt.dtzf.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 选择电梯界面
 */
public class ChoiceElevatorActivity extends BaseActivity {

    private TaskDetail mTaskInfo;
    private ChoiceElevatorAdapter mAdapter;
    private List<ElevatorInfo.ListElevatorBeanX> dataList = new ArrayList<>();
    private String taskId;

    public static void gotoActivity(Context activity, String taskId) {
        Intent intent = new Intent(activity, ChoiceElevatorActivity.class);
        intent.putExtra("taskId", taskId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_elevator_activity);
        initView();
        getData();
    }

    private void getData() {
        taskId = getIntent().getStringExtra("taskId");
        Log.d("aaa", "taskId = " + taskId);
        if (TextUtils.isEmpty(taskId)) {
            return;
        }
        Observable<Bean<ElevatorInfo>> upLocation = WorkModel.getInstance().getElevatorList(taskId);
        showWaitDialog();
        upLocation.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<ElevatorInfo>>() {
                    @Override
                    public void onSuccess(Bean<ElevatorInfo> data) {
                        ElevatorInfo dataBean = data.data;
                        if (dataBean != null) {
                            List<ElevatorInfo.ListElevatorBeanX> listElevator = dataBean.getListElevator();
                            List<ElevatorInfo.ListExitBeanX> listExit = dataBean.getListExit();
                            if (listElevator != null) {
                                int sum = 0;
                                for (ElevatorInfo.ListElevatorBeanX beanX : listElevator) {
                                    sum += beanX.getCountNum();
                                    if (listExit != null) {
                                        List<ElevatorInfo.ListElevatorBeanX.ListElevatorBean> beans = beanX.getListElevator();
                                        for (ElevatorInfo.ListElevatorBeanX.ListElevatorBean b : beans) {
                                            for (ElevatorInfo.ListExitBeanX choiced : listExit) {
                                                if (choiced.getElevatorId() == b.getId()) {
                                                    b.setSelect(true);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                ElevatorInfo.ListElevatorBeanX headData = new ElevatorInfo.ListElevatorBeanX();
                                headData.setViewType(1);
                                headData.setCountNum(sum);
                                dataList.clear();
                                dataList.add(headData);
                                dataList.addAll(listElevator);

                                mAdapter.setData(dataList);
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

    private void initView() {
        RecyclerView mRv = findViewById(R.id.choice_elevator_rv);
        mAdapter = new ChoiceElevatorAdapter(this, dataList, new int[]{R.layout.rvitem_choice_item, R.layout.choice_elevator_main_title});
        mRv.setAdapter(mAdapter);

        findViewById(R.id.choice_elevator_btn).setOnClickListener(mClickListener);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String ids = getChoiceIds();
            if (!TextUtils.isEmpty(ids)) {
                startCheck(ids);
            } else if (dataList.size() > 0) {
                ToastUtils.Toast_long("请选择电梯");
            }
        }
    };

    private String getChoiceIds() {
        StringBuffer buffer = new StringBuffer();
        for (ElevatorInfo.ListElevatorBeanX f : dataList) {
            List<ElevatorInfo.ListElevatorBeanX.ListElevatorBean> listElevator = f.getListElevator();
            if (listElevator != null) {
                for (ElevatorInfo.ListElevatorBeanX.ListElevatorBean s : listElevator) {
                    if (s.isSelect()) {
                        buffer.append(s.getId() + ",");
                    }
                }
            }
        }
        String tempIds = buffer.toString();
        if (tempIds.length() > 0 && tempIds.contains(",")) {
            String ids = tempIds.substring(0, tempIds.lastIndexOf(","));
            Log.d("aaa", "ids = " + ids);
            return ids;
        }
        return null;
    }

    private void startCheck(String ids) {
        Observable<Bean<ChoicedBean>> upLocation = WorkModel.getInstance().saveTaskElevator(taskId, ids);
        showWaitDialog();
        upLocation.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<ChoicedBean>>() {
                    @Override
                    public void onSuccess(Bean<ChoicedBean> data) {
                        ChoicedBean bean = data.data;
                        if (bean != null) {
                            String taskUrl = bean.getTaskUrl();
                            if (!TextUtils.isEmpty(taskUrl)) {
                                taskUrl = taskUrl + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + bean.getId()
                                        + "&otherId=" + SpUtils.getInt(SpUtilsConstant.otherId) + "&dicName=" + bean.getDicName() + "&&pointType=" + bean.getPointType();
                                WebDetailsActivity.gotoActivity(ChoiceElevatorActivity.this, taskUrl);
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
}
