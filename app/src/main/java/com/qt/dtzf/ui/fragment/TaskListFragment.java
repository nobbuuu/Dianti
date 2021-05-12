package com.qt.dtzf.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.baselib.BaseAPP;
import com.base.baselib.base.BaseActivity;
import com.base.baselib.base.BaseFragment;
import com.base.baselib.base.ErrorFragment;
import com.base.baselib.bean.Restore;
import com.base.baselib.bean.TaskInfo;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.LogUtils;
import com.qt.dtzf.APP;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.TaskListAdapter;
import com.qt.dtzf.ui.TaskListActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TaskListFragment extends BaseFragment {

    public String mTitle;
    public String mCategory_type;
    private SmartRefreshLayout mSrl;
    private TaskListAdapter mAdapter;

    public String type_id = "1";
    private int mPage = 1;
    private String mUid;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public static TaskListFragment getInstance(String title, String category_type) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("category_type", category_type);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = View.inflate(getContext(), R.layout.task_list_fragment, null);
            Bundle arguments = getArguments();
            mTitle = arguments.getString("title");
            mCategory_type = arguments.getString("category_type");
            initView();
            initData();
        }
        return mView;
    }

    private void initView() {

        mSrl = mView.findViewById(R.id.task_list_srl);
        mSrl.setOnRefreshLoadMoreListener(mLoadMoreListener);

        RecyclerView mRv = mView.findViewById(R.id.task_list_rv);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
        mRv.setLayoutManager(manager);
        mAdapter = new TaskListAdapter(getActivity());
        mRv.setAdapter(mAdapter);

    }

    private void initData() {
        if (TextUtils.equals("日常监管", mTitle)) {
            type_id = "1";
        } else if (TextUtils.equals("双随机监管", mTitle)) {
            type_id = "2";
        } else if (TextUtils.equals("专项检查", mTitle)) {
            type_id = "3";
        } else {
            type_id = "0";
        }
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

    private void getData() {
        showWaitFragment();
        getMoreData();
    }

    private void getMoreData() {
        Observable<Bean<TaskInfo>> observable = WorkModel.getInstance().getTaskInfo(type_id, mCategory_type, mPage);
        observable.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<TaskInfo>>() {
                    @Override
                    public void onSuccess(Bean<TaskInfo> taskInfoBeanList) {
                        List<TaskInfo.ListBean> infoList = taskInfoBeanList.data.getList();
                        int pages = taskInfoBeanList.data.getPages();
                        Log.d("aaa", "pages = " + pages);
                        if (mPage <= pages) {
                            mAdapter.addList(infoList);
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
        List<TaskInfo.ListBean> mList = mAdapter.mInfoList;
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
}
