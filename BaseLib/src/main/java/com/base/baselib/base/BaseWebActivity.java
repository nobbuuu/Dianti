package com.base.baselib.base;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.base.baselib.web.IFunctionForJS;
import com.base.baselib.web.base.D;
import com.base.baselib.web.base.WebChromeClientImpl;
import com.base.baselib.web.base.WebViewLocal;

/**
 * 基础webActivity 封装了web的接口和回调
 */
public abstract class BaseWebActivity extends BaseActivity implements IFunctionForJS {

    protected WebViewLocal mWebViewLocal;
    protected WebView mWebView;


    protected void initWebView(WebViewLocal webViewLocal) {
        if (webViewLocal == null) return;
        mWebViewLocal = webViewLocal;
        mWebView = mWebViewLocal.mWebView;
        mWebViewLocal.initWebView();
        mWebViewLocal.addJSCallBack(this, this);
    }

    protected void initWebView(WebViewLocal webViewLocal,WebChromeClientImpl.OnWebChromeListener listener) {
        if (webViewLocal == null) return;
        mWebViewLocal = webViewLocal;
        mWebView = mWebViewLocal.mWebView;
        mWebViewLocal.initWebView(listener);
        mWebViewLocal.addJSCallBack(this, this);
    }

    protected void setUrl(String url) {
        if (mWebView == null || TextUtils.isEmpty(url)) return;
        mWebView.loadUrl(url);
//        mWebView.loadDataWithBaseURL(null,url, "text/html; charset=UTF-8", "utf-8",null);

    }


    /**
     * app 主动和h5交互
     *
     * @param function
     * @param meg
     */
    protected void androidToH5(final String function, final String meg) {
        if (mWebView == null) {
            return;
        }
        if (TextUtils.isEmpty(function)) {
            return;
        }
        final String JsMeg = "('" + meg + "')";
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl(D.js_tag + function + JsMeg);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (mWebViewLocal != null) mWebViewLocal.mWebChromeClient.onActivityResult(requestCode, resultCode, data, mCameraUri);
    }

    @Override
    protected void onDestroy() {
        if (mWebViewLocal != null) mWebViewLocal.viewDestroy();
        super.onDestroy();
    }
}
