package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.Utils;
import com.qt.dtzf.APP;
import com.qt.dtzf.R;
import com.base.baselib.bean.EmptyBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 密码修改界面
 */
public class ChangePWDActivity extends BaseActivity {

    private EditText mPwdOldEt;
    private EditText mPwdNewEt;

    private Context mContext;

    public static void gotoActivity(Activity activity) {
        Intent intent = new Intent(activity, ChangePWDActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.change_pwd_activity);
        initView();
    }

    private void initView() {
        mPwdOldEt = findViewById(R.id.change_pwd_old_et);
        mPwdNewEt = findViewById(R.id.change_pwd_new_et);
        findViewById(R.id.change_pwd_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puData();
            }
        });
    }


    private void puData() {
        String oldPwd = mPwdOldEt.getText().toString().trim();
        String newPwd = mPwdNewEt.getText().toString().trim();

        if (TextUtils.isEmpty(oldPwd)) {
            Utils.showToast("请输入老密码");
            return;
        }

        if (TextUtils.isEmpty(newPwd)) {
            Utils.showToast("请输入新密码");
            return;
        }

        /*if (oldPwd.equals(newPwd)) {
            Utils.showToast("新密码与旧密码一致，请重新输入");
            mPwdNewEt.setText("");
            return;
        }*/


        Observable<Bean<EmptyBean>> pwd = WorkModel.getInstance().updatePwd(oldPwd, newPwd);
        showWaitDialog();
        pwd.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<EmptyBean>>() {
                    @Override
                    public void onSuccess(Bean<EmptyBean> stringBean) {
                        Utils.showToast("修改完成");
                        APP.getUserAction().logInOut();
                        LoginActivity.startActivity(mContext);
                        finish();
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });

    }
}
