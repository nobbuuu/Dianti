package com.qt.dtzf.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.base.BaseFragment;
import com.base.baselib.base.ErrorFragment;
import com.base.baselib.bean.OtherTask;
import com.base.baselib.bean.Restore;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.RestoreAdapter;
import com.qt.dtzf.adapter.TaskListAdapter;
import com.qt.dtzf.ui.RestoreListActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RestoreListFragment extends BaseFragment {
    public String mTitle;
    public RestoreListActivity mActivity;
    public String type_id = "1";
    private RestoreAdapter mAdapter;
    private SmartRefreshLayout mSl;

    private int mPage = 1;

    public static RestoreListFragment getInstance(String title) {
        RestoreListFragment fragment = new RestoreListFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (RestoreListActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = View.inflate(getContext(), R.layout.restore_list_fragment, null);
            Bundle arguments = getArguments();
            mTitle = arguments.getString("title");
            initView();
            initData();

        }
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getData();
    }

    private void initView() {

        mSl = mView.findViewById(R.id.restore_list_sl);
        mSl.setOnRefreshLoadMoreListener(mRefreshListener);

        RecyclerView mRv = mView.findViewById(R.id.restore_list_rv);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
        mRv.setLayoutManager(manager);
        mAdapter = new RestoreAdapter(getActivity());
        mRv.setAdapter(mAdapter);
    }

    private void initData() {
        if (TextUtils.equals("日常监管", mTitle)) {
            type_id = "1";
        } else if (TextUtils.equals("双随机监管", mTitle)) {
            type_id = "2";
        } else if (TextUtils.equals("专项检查", mTitle)) {
            type_id = "3";
        }
    }

    private void getData() {
        showWaitFragment();
        getMoreData();
    }


    private void getMoreData() {
        Observable<BeanList<Restore>> mList;
        //等等
        if ("1".equals(mActivity.category_type)) {
            mList = WorkModel.getInstance().getRestoreList(type_id,mPage);
        } else {
            mList = WorkModel.getInstance().getManageTaskList(type_id,mPage);
        }
        mList.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BeanList<Restore>>() {
                    @Override
                    public void onSuccess(BeanList<Restore> restoreBeanList) {
                        List<Restore> data = restoreBeanList.data;

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
        List<Restore> mList = mAdapter.mInfoList;
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
