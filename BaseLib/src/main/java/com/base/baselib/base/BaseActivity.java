package com.base.baselib.base;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.base.baselib.R;
import com.base.baselib.dialog.WaitDialog;
import com.base.baselib.utils.PermissionUtils;
import com.base.baselib.utils.StatusBarUtil;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

/**
 * 基础activity
 * 添加权限设置
 */
public class BaseActivity extends RxAppCompatActivity {

    protected PermissionUtils mPermissionUtils;
    private ErrorFragment mErrorFragment;
    private WaitFragment mWaitFragment;
    public WaitDialog mWaitDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        StatusBarUtil.setStatusBarColor(this);
    }

    protected void showFragment(int viewId, Fragment fragment) {
        FragmentTransaction bt = getSupportFragmentManager().beginTransaction();
        bt.replace(viewId, fragment);
        bt.commit();
    }

    protected void showWaitDialog() {
        if (mWaitDialog == null) mWaitDialog = new WaitDialog();
        mWaitDialog.showDialog(getSupportFragmentManager());
    }

    public void hideWaitDialog() {
        if (mWaitDialog != null) mWaitDialog.dismiss();
    }

    public void showErrorView(ErrorFragment.RefreshListener listener) {
        if (listener == null) return;
        FrameLayout error_fl = findViewById(R.id.error_fl);
        if (error_fl == null) return;
        if (mErrorFragment == null) {
            mErrorFragment = ErrorFragment.newInstance();
            mErrorFragment.addRefreshListener(listener);
        }
        error_fl.setVisibility(View.VISIBLE);
        showFragment(R.id.error_fl, mErrorFragment);
    }

    public void showWaitFragment() {

        FrameLayout error_fl = findViewById(R.id.error_fl);
        if (error_fl == null) return;
        error_fl.setVisibility(View.VISIBLE);
        if (mWaitFragment == null) mWaitFragment = WaitFragment.newInstance();
        showFragment(R.id.error_fl, mWaitFragment);
    }

    public void hideErrorView() {
        FrameLayout error_fl = findViewById(R.id.error_fl);
        if (error_fl == null) return;
        error_fl.setVisibility(View.GONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (mPermissionUtils != null)
            mPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
