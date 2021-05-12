package com.base.baselib.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.base.baselib.R;
import com.base.baselib.dialog.WaitDialog;
import com.trello.rxlifecycle3.components.support.RxFragment;

public class BaseFragment extends RxFragment {

    private BaseActivity mActivity;
    public WaitDialog mWaitDialog;
    private ErrorFragment mErrorFragment;
    private WaitFragment mWaitFragment;
    protected View mView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
    }


    public void showFragment(int viewId, Fragment fragment) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(viewId, fragment);
        ft.commit();
    }

    public void showWaitDialog() {
        if (mActivity == null) return;
        if (mWaitDialog == null) mWaitDialog = new WaitDialog();
        mWaitDialog.showDialog(getChildFragmentManager());
    }

    public void hideWaitDialog() {
        if (mWaitDialog != null) mWaitDialog.dismiss();
    }

    public void showErrorView(ErrorFragment.RefreshListener listener) {
        if (listener == null) return;
        if (mView == null) return;
        FrameLayout error_fl = mView.findViewById(R.id.error_fl);
        if (error_fl == null) return;
        if (mErrorFragment == null) {
            mErrorFragment = ErrorFragment.newInstance();
            mErrorFragment.addRefreshListener(listener);
        }
        showFragment(R.id.error_fl, mErrorFragment);
    }

    public void showWaitFragment() {
        if (mView == null) return;
        FrameLayout error_fl = mView.findViewById(R.id.error_fl);
        if (error_fl == null) return;
        error_fl.setVisibility(View.VISIBLE);
        if (mWaitFragment == null) mWaitFragment = WaitFragment.newInstance();
        showFragment(R.id.error_fl, mWaitFragment);
    }


    public void hideErrorView() {
        if (mView == null) return;
        FrameLayout error_fl = mView.findViewById(R.id.error_fl);
        if (error_fl == null) return;
        error_fl.setVisibility(View.GONE);
    }

}
