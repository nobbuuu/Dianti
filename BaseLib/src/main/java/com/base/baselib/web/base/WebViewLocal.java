package com.base.baselib.web.base;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.baselib.R;
import com.base.baselib.base.BaseActivity;
import com.base.baselib.utils.LogUtils;
import com.base.baselib.web.FunctionForJS;
import com.base.baselib.web.IFunctionForJS;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * 自定义web view
 * 并 添加基础功能设置
 */
public class WebViewLocal extends FrameLayout {

    private Context mContext;

    public WebView mWebView;

    public WebChromeClientImpl mWebChromeClient;
    public WebViewLocal(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public WebViewLocal(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public WebViewLocal(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View.inflate(mContext, R.layout.web_view, this);
        mWebView = findViewById(R.id.app_web_view);
    }


    public void initWebView() {
        if (mWebView == null) return;
        WebViewInitImpl webViewInit = new WebViewInitImpl();
        //WebView初始化
        webViewInit.initWebView(mWebView);

        if (mContext instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) mContext;
            //添加网络连接 Client
            mWebChromeClient = webViewInit.initWebChromeClient(activity);
            //webView操作Android文件初始化
            mWebView.setWebChromeClient(mWebChromeClient);
        }

        WebViewClient mWebViewClient = webViewInit.initWebViewClient();
        mWebView.setWebViewClient(mWebViewClient);
        //web进度回调设置 和标题
        webViewInit.setOnWebChromeListener(mOnWebChromeListener);
    }


    public void initWebView(WebChromeClientImpl.OnWebChromeListener listener) {
        if (mWebView == null) return;
        WebViewInitImpl webViewInit = new WebViewInitImpl();
        //WebView初始化
        webViewInit.initWebView(mWebView);

        if (mContext instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) mContext;
            //添加网络连接 Client
            mWebChromeClient = webViewInit.initWebChromeClient(activity);
            //webView操作Android文件初始化
            mWebView.setWebChromeClient(mWebChromeClient);
        }

        WebViewClient mWebViewClient = webViewInit.initWebViewClient();
        mWebView.setWebViewClient(mWebViewClient);
        //web进度回调设置 和标题
        webViewInit.setOnWebChromeListener(listener);
    }

    WebChromeClientImpl.OnWebChromeListener mOnWebChromeListener = new WebChromeClientImpl.OnWebChromeListener() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            LogUtils.e("我是标题--->"+title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {

        }
    };



    /**
     * 和JS 交互监听
     *
     * @param activity
     * @param anInterface
     */
    public void addJSCallBack(BaseActivity activity, IFunctionForJS anInterface) {
        FunctionForJS function = new FunctionForJS(activity, anInterface);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.addJavascriptInterface(function, D.AppWebTag);
    }

    /**
     * view的销毁
     */
    public void viewDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
