package com.base.baselib.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.baselib.R;


public class WaitDialog extends BaseDialogFragment {

    private TextView mWaitDialogTv;
    private String mMsg;

    @Override
    protected String setDialogTag() {
        return "WaitDialog";
    }

    @Override
    protected Dialog setDialog() {
        return getDialog();
    }

    @Override
    protected void setDialogStyle(Window window) {
        int width = ViewGroup.LayoutParams.WRAP_CONTENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        WindowManager.LayoutParams windowParams = window.getAttributes();
        window.setLayout(width, height);
        window.setAttributes(windowParams);
    }

    @Override
    protected void setStartAndEndAnimation() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wait_dialog, container, false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWaitDialogTv = view.findViewById(R.id.wait_dialog_tv);
        initDialog();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!TextUtils.isEmpty(mMsg)) {
            mWaitDialogTv.setText(mMsg);
        }
    }

    public void setMessage(String msg) {
        mMsg = msg;
    }


    private void initDialog() {
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);

        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });
    }


}
