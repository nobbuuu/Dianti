package com.base.baselib.web.base;


import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.base.baselib.base.BaseActivity;

/**
 * WebView初始化的定义
 */
public class WebViewInitImpl implements IWebViewInit {

    private static final String USER_AGENT = "appBase";
    private WebChromeClientImpl mWebChromeClient;

    @Override
    public WebView initWebView(WebView webView) {
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings webSetting = webView.getSettings();
        webSetting.setUserAgentString(USER_AGENT + webSetting.getUserAgentString());

        webSetting.setDatabaseEnabled(true);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setTextSize(WebSettings.TextSize.NORMAL);

        // ===设置JS可用
        //是否可以和js进行交互
        webSetting.setJavaScriptEnabled(true);

        // JS打开窗口
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        // ===设置JS可用
        // 可以访问文件
        //设置可以访问app中assect中的文件
        webSetting.setAllowFileAccess(true);
        // ===缩放可用
        webSetting.setSupportZoom(false);
        //隐藏原生的缩放控件
        webSetting.setDisplayZoomControls(false);
        //设置缩放功能   //能不能缩放 取决于网页设置
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //是否将WebView调整到屏幕大小
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setBuiltInZoomControls(true);
        // ===缩放可用
        // 支持多窗口
        webSetting.setSupportMultipleWindows(true);
        // ===============缓存
        // 设置缓存的模式
        //
        //    WebSettings.LOAD_DEFAULT 默认的模式 根据cache-control决定是否从网络上取数据。
        //    WebSettings.LOAD_CACHE_ELSE_NETWORK 只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据
        //    WebSettings.LOAD_NO_CACHE 不使用缓存
        //    WebSettings.LOAD_CACHE_ONLY 不使用网络，只加载缓存
        webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);// 决定是否从网络上取数据。
        webSetting.setAppCacheEnabled(true);
        // ===============缓存

        //是否将图片调整到WebView的大小
        webSetting.setUseWideViewPort(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        //是否支持插件
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);

        // ==定位
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        // ==定位
        return webView;
    }

    @Override
    public WebViewClient initWebViewClient() {
        return new WebViewClientImpl();
    }

    @Override
    public WebChromeClientImpl initWebChromeClient(BaseActivity activity) {
        mWebChromeClient = new WebChromeClientImpl(activity);
        return mWebChromeClient;
    }

    /**
     * 页面标题、进度回调
     */
    public void setOnWebChromeListener(WebChromeClientImpl.OnWebChromeListener onWebChromeListener) {
        if (mWebChromeClient == null) return;
        mWebChromeClient.setOnWebChromeListener(onWebChromeListener);
    }

    /**
     * 选择相机相册处理
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data, Uri uri) {
        if (mWebChromeClient == null) return;
        mWebChromeClient.onActivityResult(requestCode, resultCode, data, uri);
    }
}
