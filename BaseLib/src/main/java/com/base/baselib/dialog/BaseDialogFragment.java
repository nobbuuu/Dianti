package com.base.baselib.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.base.baselib.R;


public abstract class BaseDialogFragment extends DialogFragment {
    protected Dialog mDialog;
    protected String mDialog_Tag = "BaseDialogFragment";

    protected abstract String setDialogTag();
    protected abstract Dialog setDialog();
    protected abstract void setDialogStyle(Window window);
    protected abstract void setStartAndEndAnimation();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogFullScreen);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setStartAndEndAnimation();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDialog_Tag = setDialogTag();
        mDialog = setDialog();
    }

    @Override
    public void onStart() {
        super.onStart();
        setInitDialog();
    }

    protected void setInitDialog() {
        if (mDialog == null) {
            return;
        }
        Window window = mDialog.getWindow();
        if (window == null) {
            return;
        }
        setDialogStyle(window);
    }

    public void showDialog(FragmentManager fm) {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment f = fm.findFragmentByTag(mDialog_Tag);
        if (f != null) {
            ft.remove(f);
        }
        show(ft, mDialog_Tag);
    }
}
