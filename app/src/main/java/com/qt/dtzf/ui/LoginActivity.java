package com.qt.dtzf.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.model.UserAboutModel;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.Utils;
import com.qt.dtzf.APP;
import com.qt.dtzf.R;
import com.base.baselib.bean.LoginBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity {

    private EditText mPhoneEt;
    private EditText mPwdEt;

    public static void startActivity(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setData();
    }

    private void initView() {

        mPhoneEt = findViewById(R.id.login_phone_et);
        mPwdEt = findViewById(R.id.login_pwd_et);
        findViewById(R.id.login_btn).setOnClickListener(mClickListener);
    }

    private void setData() {
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //登录
            String phone = mPhoneEt.getText().toString().trim();
            String pwd = mPwdEt.getText().toString().trim();
            if (TextUtils.isEmpty(phone)) {
                Utils.showToast("请输入手机号");
                return;
            }
            if (TextUtils.isEmpty(pwd)) {
                Utils.showToast("请输入密码");
                return;
            }
            login(phone, pwd);
        }
    };

    /**
     * 登陆流程
     * 1登陆 并 获取用户信息
     * 2保存用户信息
     *
     * @param phone
     * @param pwd
     */

    private void login(String phone, String pwd) {

        showWaitDialog();
        Observable<Bean<LoginBean>> login = UserAboutModel.getInstance().login(phone, pwd);
        login.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<LoginBean>>() {
                    @Override
                    public void onSuccess(Bean<LoginBean> loginInfoBean) {
                        LoginBean info = loginInfoBean.data;
                        if (info != null) {
                            Log.d("login", "userToken = " + info.getUser().getToken());
                            APP.getUserAction().saveLoginInfo(info);
                            gotoMainActivity();
                        }
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
