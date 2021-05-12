package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.base.baselib.base.WebActivity;
import com.base.baselib.bean.EventMessage;
import com.base.baselib.bean.TaskInfo;
import com.base.baselib.utils.LogUtils;
import com.base.baselib.utils.Utils;
import com.base.baselib.view.TitleView;
import com.base.baselib.web.base.WebChromeClientImpl;
import com.base.baselib.web.base.WebViewLocal;
import com.qt.dtzf.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * h5使用界面
 */
public class WebDetailsActivity extends WebActivity {


    private String mUrl;
    private TitleView mTitle;
    private TaskInfo.ListBean taskInfo;

    public static void gotoActivity(Context activity, String url) {
        Intent intent = new Intent(activity, WebDetailsActivity.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    public static void gotoActivity(Context activity, String url, TaskInfo.ListBean taskInfo) {
        Intent intent = new Intent(activity, WebDetailsActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("taskInfo", taskInfo);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_details_activity);
        mTitle = findViewById(R.id.web_title);

        mTitle.mTitleLeftLl.setOnClickListener(mOnClickListener);
        mUrl = getIntent().getStringExtra("url");
        taskInfo = (TaskInfo.ListBean) getIntent().getSerializableExtra("taskInfo");
//        mUrl = "file:///android_asset/resulttest.html";
        LogUtils.e("text123 url = " + mUrl);
        WebViewLocal other_wvl = findViewById(R.id.other_wvl);
        initWebView(other_wvl, mWebChromeListener);
        showWaitDialog();
        setUrl(mUrl);
        EventBus.getDefault().register(this);
    }

    WebChromeClientImpl.OnWebChromeListener mWebChromeListener = new WebChromeClientImpl.OnWebChromeListener() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            mTitle.mTitleTv.setText(title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                hideWaitDialog();
            }
        }
    };

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goBack();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && mWebView.canGoBack()) {
            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void goBack() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(String message) {
        if (message.equals("handlerSubmit")) {
            Intent intent = null;
            if (taskInfo != null && taskInfo.getCategoryType() == 1) {
                intent = new Intent(this, TaskMainActivity.class);
                intent.putExtra("taskInfo", taskInfo);
            } else {
                intent = new Intent(this, TaskListActivity.class);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}
