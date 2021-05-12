package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.base.ErrorFragment;
import com.base.baselib.bean.MaintainTask;
import com.base.baselib.bean.UserFiles;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.LogUtils;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.MaintainTaskAdapter;
import com.qt.dtzf.adapter.PersonnelFilesAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 保养任务列表
 */
public class MaintainTaskActivity extends BaseActivity {

    private EditText mSearchEt;
    private int mPage = 1;
    private String mTag = "";
    private SmartRefreshLayout mSl;
    private RecyclerView mRv;
    private MaintainTaskAdapter mAdapter;

    public static void gotoActivity(Activity activity) {
        Intent intent = new Intent(activity, MaintainTaskActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintain_task_activity);
        initView();
        getData();
    }

    private void initView() {
        mSearchEt = findViewById(R.id.maintain_search_et);
        mSearchEt.setOnEditorActionListener(mEditorActionListener);

        findViewById(R.id.maintain_search_iv).setOnClickListener(mClickListener);
        mSearchEt.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        mSl = findViewById(R.id.maintain_task_sl);
        mSl.setOnRefreshLoadMoreListener(mRefreshListener);

        mRv = findViewById(R.id.maintain_task_rv);
        mRv.setLayoutManager(new GridLayoutManager(MaintainTaskActivity.this, 1));
        mAdapter = new MaintainTaskAdapter(this);
        mRv.setAdapter(mAdapter);
    }


    private void getData() {
        showWaitFragment();
        getDataMore();
    }

    private void getDataMore() {
        Observable<BeanList<MaintainTask>> list = WorkModel.getInstance().getMaintainTaskList(mTag, "" + mPage);
        list.compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BeanList<MaintainTask>>() {
                    @Override
                    public void onSuccess(BeanList<MaintainTask> list) {
                        List<MaintainTask> data = list.data;
                        if (mPage == 1) {
                            mAdapter.setList(data);
                        } else {
                            mAdapter.addList(data);
                        }
                        showError(null);
                    }

                    @Override
                    public void onFail(String code, String msg) {
                        super.onFail(code, msg);
                        showError(msg);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        showError(e.getMessage());
                    }

                });
    }

    private void showError(String msg) {

        mSl.finishRefresh();
        mSl.finishLoadMore();
        if (mPage == 1 && !TextUtils.isEmpty(msg)) mAdapter.setList(null);
        List<MaintainTask> mList = mAdapter.mList;
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

    OnRefreshLoadMoreListener mRefreshListener = new OnRefreshLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            mPage++;
            getDataMore();
        }

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            mPage = 1;
            getDataMore();
        }
    };

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.maintain_search_iv:
                    mTag = mSearchEt.getText().toString().trim();
                    mPage = 1;
                    getData();
                    break;
            }
        }
    };

    TextView.OnEditorActionListener mEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                String str = v.getText().toString().trim();
                LogUtils.e("str = " + str);
                mTag = str;
                mPage = 1;
                mAdapter.mList.clear();
                getData();
                return true;
            }
            return false;
        }
    };
}
