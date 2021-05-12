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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.base.ErrorFragment;
import com.base.baselib.bean.DevManagerBean;
import com.base.baselib.bean.InspectionTask;
import com.base.baselib.bean.UserFiles;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.LogUtils;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.PersonnelFilesAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * 人员档案
 */
public class PersonnelFilesActivity extends BaseActivity {

    private SmartRefreshLayout mSl;
    private EditText mSearchEt;
    private RecyclerView mRv;

    private int mPage = 1;
    private String mTag = "";
    private PersonnelFilesAdapter mAdapter;

    public static void gotoActivity(Activity activity) {
        Intent intent = new Intent(activity, PersonnelFilesActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personnel_files_activity);
        initView();
        getData();
    }

    private void initView() {
        mSl = findViewById(R.id.personnel_files_sl);
        mSl.setOnRefreshLoadMoreListener(mRefreshListener);

        mSearchEt = findViewById(R.id.user_search_et);
        mSearchEt.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mSearchEt.setOnEditorActionListener(mEditorActionListener);

        mRv = findViewById(R.id.personnel_files_rv);
        mRv.setLayoutManager(new GridLayoutManager(PersonnelFilesActivity.this, 1));
        mAdapter = new PersonnelFilesAdapter(this);

        mAdapter.addItemClickListener(mItemClickListener);
        mRv.setAdapter(mAdapter);
        findViewById(R.id.user_search_iv).setOnClickListener(mClickListener);
        findViewById(R.id.title_right_tv).setOnClickListener(mClickListener);
    }


    private void getData() {
        showWaitFragment();
        getDataMore();
    }

    private void getDataMore() {
        Observable<BeanList<UserFiles>> userList = WorkModel.getInstance().getUserList(mTag, "" + mPage);
        userList.compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BeanList<UserFiles>>() {
                    @Override
                    public void onSuccess(BeanList<UserFiles> list) {
                        List<UserFiles> data = list.data;
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
        List<UserFiles> mList = mAdapter.userFiles;
        if (mList == null || mList.size() <= 0) {
            showErrorView(mErrorViewRefreshListener);
        } else {
            hideErrorView();
        }
    }

    private void delWorkerInfo(String id) {
        showWaitDialog();
        Observable<Bean<String>> info = WorkModel.getInstance().delWorkerInfo(id);
        info.compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<String>>() {
                    @Override
                    public void onSuccess(Bean<String> stringBean) {
                        mAdapter.delItem(id);
                        showError();
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });
    }

    PersonnelFilesAdapter.ClickListener mItemClickListener = new PersonnelFilesAdapter.ClickListener() {
        @Override
        public void onClickListener(UserFiles item) {
            //删除按钮
            delWorkerInfo(item.id);
        }
    };

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.user_search_iv:
                    mTag = mSearchEt.getText().toString().trim();
                    mPage = 1;
                    getData();
                    break;
                case R.id.title_right_tv:
                    //添加
                    PersonnelAddActivity.gotoActivity(PersonnelFilesActivity.this);
                    break;
            }
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


    ErrorFragment.RefreshListener mErrorViewRefreshListener = new ErrorFragment.RefreshListener() {
        @Override
        public void onRefresh() {
            mPage = 1;
            getData();
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
                mAdapter.userFiles.clear();
                getData();
                return true;
            }
            return false;
        }
    };
}
