package com.base.baselib.web.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

public class WebViewClientImpl extends WebViewClient {
    /**
     * 设置相应的本APP内打开的方法
     *
     * @param webView
     * @param url
     * @return
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        Context context = webView.getContext();
        if (webView != null && url != null) {

            if (url.endsWith(".apk")) {
                Uri apkUri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, apkUri);
                context.startActivity(intent);
            } else if (url.startsWith("http")) {
                webView.loadUrl(url);
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                if (isInstall(context, intent)) {
                    context.startActivity(intent);
                }
            }
        }
        return true;
    }

    /**
     * 数据拦截
     *
     * @param view
     * @param request
     * @return
     */
    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }

    /**
     * 错误界面处理
     *
     * @param view
     * @param request
     * @param error
     */

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        // 断网或者网络连接超时
        if (error.getErrorCode() == ERROR_HOST_LOOKUP || error.getErrorCode() == ERROR_CONNECT || error.getErrorCode() == ERROR_TIMEOUT) {
            view.loadUrl("about:blank"); // 避免出现默认的错误界面
//            view.loadUrl(D.ErrorUrl);
        }
    }


    // 判断app是否安装
    private boolean isInstall(Context context, Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }
}
