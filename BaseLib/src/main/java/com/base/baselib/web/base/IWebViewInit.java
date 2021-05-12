package com.base.baselib.web.base;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.base.baselib.base.BaseActivity;

/**
 * WebView初始化接口定义
 */
public interface IWebViewInit {
    /**
     * 1. 初始化和设置WebView
     */
    WebView initWebView(WebView webView);

    /**
     * 2. 初始化WebViewClient
     */
    WebViewClient initWebViewClient();

    /**
     * 3. 初始化WebChromeClient
     */
    WebChromeClientImpl initWebChromeClient(BaseActivity activity);
}
