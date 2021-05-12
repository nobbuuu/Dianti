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
import com.base.baselib.bean.InspectionTask;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.InspectionTaskAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 巡查任务列表
 */
public class InspectionTaskActivity extends BaseActivity {

    private InspectionTaskAdapter mAdapter;
    private int mPage = 1;
    private SmartRefreshLayout mSl;

    public static void gotoActivity(Activity activity) {
        Intent intent = new Intent(activity, InspectionTaskActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inspection_task_activity);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void initView() {
        mSl = findViewById(R.id.inspection_list_sl);
        mSl.setOnRefreshLoadMoreListener(mRefreshListener);

        RecyclerView mRv = findViewById(R.id.inspection_list_rv);
        mRv.setLayoutManager(new GridLayoutManager(InspectionTaskActivity.this, 1));
        mAdapter = new InspectionTaskAdapter(InspectionTaskActivity.this);
        mRv.setAdapter(mAdapter);
    }

    private void getData() {
        showWaitFragment();
        getMoreData();
    }

    private void getMoreData() {
        Observable<BeanList<InspectionTask>> list = WorkModel.getInstance().getInspectionTaskList(mPage);
        list.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BeanList<InspectionTask>>() {
                    @Override
                    public void onSuccess(BeanList<InspectionTask> inspectionTaskBeanList) {
                        List<InspectionTask> data = inspectionTaskBeanList.data;
                        if (mPage == 1) {
                            mAdapter.setList(data);
                        } else {
                            mAdapter.addList(data);
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
        mSl.finishRefresh();
        mSl.finishLoadMore();
        List<InspectionTask> mList = mAdapter.mInfoList;
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


    ErrorFragment.RefreshListener mErrorViewRefreshListener = new ErrorFragment.RefreshListener() {
        @Override
        public void onRefresh() {
            mPage = 1;
            getData();
        }
    };
}
