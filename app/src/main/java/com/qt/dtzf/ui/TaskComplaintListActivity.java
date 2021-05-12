package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.base.ErrorFragment;
import com.base.baselib.bean.ComplaintTask;
import com.base.baselib.bean.OtherTask;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.ComplaintTaskAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 投诉任务列表
 */
public class TaskComplaintListActivity extends BaseActivity {

    private ComplaintTaskAdapter mAdapter;
    private SmartRefreshLayout mSl;
    private int mPage = 1;

    public static void gotoActivity(Activity activity) {
        Intent intent = new Intent(activity, TaskComplaintListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_complaint_list_activity);
        initView();
        getData();
    }

    private void initView() {
        mSl = findViewById(R.id.task_complaint_list_sl);
        mSl.setOnRefreshLoadMoreListener(mRefreshListener);

        RecyclerView mRv = findViewById(R.id.task_complaint_list_rv);
        mRv.setLayoutManager(new GridLayoutManager(TaskComplaintListActivity.this, 1));
        mAdapter = new ComplaintTaskAdapter(TaskComplaintListActivity.this);
        mRv.setAdapter(mAdapter);
    }


    private void getData() {
        showWaitFragment();
        getMoreData();
    }

    private void getMoreData() {
        Observable<BeanList<ComplaintTask>> list = WorkModel.getInstance().getComplaintList(mPage);
        list.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BeanList<ComplaintTask>>() {
                    @Override
                    public void onSuccess(BeanList<ComplaintTask> beanList) {
                        List<ComplaintTask> data = beanList.data;
                        if (mPage == 1) {
                            mAdapter.setList(data);
                        } else {
                            mAdapter.addList(data);
                        }
                        showError();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        showError();
                    }

                    @Override
                    public void onFail(String code, String msg) {
                        super.onFail(code, msg);
                        showError();
                    }
                });
    }

    private void showError() {
        mSl.finishRefresh();
        mSl.finishLoadMore();
        List<ComplaintTask> mList = mAdapter.mTaskList;
        if (mList == null || mList.size() <= 0) {
            showErrorView(mErrorViewRefreshListener);
        } else {
            hideErrorView();
        }
    }

    OnRefreshLoadMoreListener mRefreshListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            mPage++;
            getMoreData();
        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            mPage = 1;
            getMoreData();
        }
    };

    ErrorFragment.RefreshListener mErrorViewRefreshListener = () -> getData();
}
