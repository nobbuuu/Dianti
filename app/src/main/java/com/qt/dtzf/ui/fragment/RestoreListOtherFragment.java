package com.qt.dtzf.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
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
import com.base.baselib.bean.UserFiles;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.OtherTaskAdapter;
import com.qt.dtzf.ui.PersonnelFilesActivity;
import com.qt.dtzf.ui.RestoreListActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 其他
 */
public class RestoreListOtherFragment extends BaseFragment {
    public RestoreListActivity mActivity;
    private String mTitle;
    private SmartRefreshLayout mSl;
    private int mPage = 1;
    private OtherTaskAdapter mAdapter;

    public static RestoreListOtherFragment getInstance(String title) {
        RestoreListOtherFragment fragment = new RestoreListOtherFragment();
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

    private void initView() {
        mSl = mView.findViewById(R.id.restore_list_sl);
        mSl.setOnRefreshLoadMoreListener(mRefreshListener);

        RecyclerView mRv = mView.findViewById(R.id.restore_list_rv);
        mAdapter = new OtherTaskAdapter(getActivity());
        mRv.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mRv.setAdapter(mAdapter);
    }

    private void initData() {
        showWaitFragment();
        getMoreData();
    }

    private void getMoreData() {

        Observable<BeanList<OtherTask>> userList = WorkModel.getInstance().getOtherTaskList(mPage);
        userList.compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BeanList<OtherTask>>() {
                    @Override
                    public void onSuccess(BeanList<OtherTask> list) {
                        List<OtherTask> data = list.data;
                        if (mPage == 1) {
                            mAdapter.setList(data);
                        } else {
                            mAdapter.addList(data);
                        }
                        showError();
                    }

                    @Override
                    public void onFail(String code, String msg) {
                        showError();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError();
                    }
                });

    }

    private void showError() {
        mSl.finishRefresh();
        mSl.finishLoadMore();
        List<OtherTask> mList = mAdapter.mList;
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
            initData();
        }
    };

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
}
