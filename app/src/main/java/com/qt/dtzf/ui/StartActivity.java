package com.qt.dtzf.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.utils.PermissionUtils;
import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;
import com.qt.dtzf.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * 启动页面
 */
public class StartActivity extends BaseActivity {

    private int SplashTime = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        ImageView imageView = findViewById(R.id.welcome_iv);
        AlphaAnimation animation = new AlphaAnimation(0.4f,1f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setFillEnabled(true);
        animation.setRepeatMode(Animation.REVERSE);
        imageView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mPermissionUtils = new PermissionUtils(StartActivity.this, mPermissionCallBack);
                showTime();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @SuppressLint("CheckResult")
    private void showTime() {
        Observable.just("go")
                .delay(SplashTime, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mPermissionUtils.checkSelfPermission();
                    }
                });
    }

    PermissionUtils.PermissionCallBack mPermissionCallBack = new PermissionUtils.PermissionCallBack() {
        @Override
        public void onBack(int code, boolean b) {
            //权限必须同意
            if (!b) mPermissionUtils.checkSelfPermission();
            if (b) showView();
        }
    };

    private void showView() {
        
        String key = SpUtils.getString(SpUtilsConstant.apiKey);
        if (TextUtils.isEmpty(key)) {
            Intent intent = new Intent(StartActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
