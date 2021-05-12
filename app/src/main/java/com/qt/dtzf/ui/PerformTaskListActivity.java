package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.base.ErrorFragment;
import com.base.baselib.bean.PerformBean;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.PerformTaskListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PerformTaskListActivity extends BaseActivity {

    PerformTaskListAdapter mAdapter;
    @BindView(R.id.task_list_rv)
    RecyclerView taskListRv;
    @BindView(R.id.task_list_srl)
    SmartRefreshLayout mSrl;
    @BindView(R.id.error_fl)
    FrameLayout errorFl;
    private List<PerformBean.ListBean> dataList = new ArrayList<>();
    private int mPage = 1;
    private Unbinder bind;
    public static void gotoActivity(Activity activity) {
        Intent intent = new Intent(activity, PerformTaskListActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform);
        bind = ButterKnife.bind(this);
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter.mInfoList != null) {
            mAdapter.mInfoList.clear();
        }
        mPage = 1;
        getMoreData();
    }

    private void initData() {
        mAdapter = new PerformTaskListAdapter(this, dataList, R.layout.rvitem_perform);
        taskListRv.setAdapter(mAdapter);
    }

    private void getMoreData() {

        Observable<Bean<PerformBean>> observable = WorkModel.getInstance().getTaskEnforceList(mPage);
        observable.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<PerformBean>>() {
                    @Override
                    public void onSuccess(Bean<PerformBean> data) {
                        PerformBean performBean = data.data;
                        if (performBean != null) {
                            int pages = performBean.getPages();
                            List<PerformBean.ListBean> beanList = performBean.getList();
                            if (mPage <= pages && beanList != null) {
                                mAdapter.addList(beanList);
                            }
                        }
                        showError();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError();
                    }

                    @Override
                    public void onFail(String code, String msg) {
                        showError();
                    }
                });
    }

    private void showError() {
        mSrl.finishRefresh();
        mSrl.finishLoadMore();
        List<PerformBean.ListBean> mList = mAdapter.mInfoList;
        if (mList == null || mList.size() <= 0) {
            showErrorView(mErrorViewRefreshListener);
        } else {
            hideErrorView();
        }
    }

    OnRefreshLoadMoreListener mLoadMoreListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            if (mAdapter.mInfoList != null) {
                mAdapter.mInfoList.clear();
            }
            mPage = 1;
            getMoreData();
        }

        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            mPage++;
            getMoreData();
        }
    };

    ErrorFragment.RefreshListener mErrorViewRefreshListener = new ErrorFragment.RefreshListener() {
        @Override
        public void onRefresh() {
            if (mAdapter.mInfoList != null) {
                mAdapter.mInfoList.clear();
            }
            mPage = 1;
            getData();
        }
    };

    private void getData() {
        showWaitFragment();
        getMoreData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
