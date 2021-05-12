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
import com.base.baselib.bean.DevManagerBean;
import com.base.baselib.bean.HeardInfo;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.DevManagerAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 设备管理 数据列表
 */
public class DevManagerActivity extends BaseActivity {

    private SmartRefreshLayout mSl;

    private int mPage = 1;
    private DevManagerAdapter mAdapter;

    public static void gotoActivity(Activity activity) {
        Intent intent = new Intent(activity, DevManagerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dev_manager_activity);
        initView();
        getData();
    }

    private void initView() {
        mSl = findViewById(R.id.dev_manager_list_sl);
        mSl.setOnRefreshLoadMoreListener(mRefreshListener);

        RecyclerView mRv = findViewById(R.id.dev_manager_list_rv);
        mRv.setLayoutManager(new GridLayoutManager(DevManagerActivity.this, 1));
        mAdapter = new DevManagerAdapter(this);
        mRv.setAdapter(mAdapter);

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

    private void getData() {
        showWaitFragment();
        getMoreData();
    }

    private void getMoreData() {
        Observable<BeanList<DevManagerBean>> list = WorkModel.getInstance().getUseList("" + mPage);
        list.compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BeanList<DevManagerBean>>() {
                    @Override
                    public void onSuccess(BeanList<DevManagerBean> list) {
                        List<DevManagerBean> data = list.data;
                        if (mPage == 1) {
                            mAdapter.setList(data);
                        } else {
                            mAdapter.addList(data);
                        }
                        showError();
                    }

                    @Override
                    public void onFail(String code, String msg) {
                        super.onFail(code, msg);
                        showError();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        showError();
                    }

                });
    }


    private void showError() {
        mSl.finishRefresh();
        mSl.finishLoadMore();
        List<DevManagerBean> mList = mAdapter.mList;
        if (mList == null || mList.size() <= 0) {
            showErrorView(mErrorViewRefreshListener);
        } else {
            hideErrorView();
        }
    }

    ErrorFragment.RefreshListener mErrorViewRefreshListener = new ErrorFragment.RefreshListener() {
        @Override
        public void onRefresh() {
            mPage = 1;
            getData();
        }
    };
}
