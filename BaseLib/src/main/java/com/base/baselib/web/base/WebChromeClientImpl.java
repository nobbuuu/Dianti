package com.base.baselib.web.base;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.base.baselib.base.BaseActivity;


public class WebChromeClientImpl extends WebChromeClient {
    // WebView打开相机相册的请求码
    public static final int FILE_REQUEST_CODE = 0x011;
    /**
     * 进度条的回调监听
     */
    private OnWebChromeListener onWebChromeListener;

    /**
     * 打开相册 本地文件等等
     */
    private ValueCallback<Uri[]> uploadFiles;
    private BaseActivity mActivity;

    public WebChromeClientImpl(BaseActivity activity) {
        mActivity = activity;
    }

    /**
     * 进度发生改变
     */
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (onWebChromeListener != null) {
            onWebChromeListener.onProgressChanged(view, newProgress);
        }
    }


    /**
     * 接收到标题
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (onWebChromeListener != null) {
            onWebChromeListener.onReceivedTitle(view, title);
        }
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        handleFileChooser(webView, filePathCallback);
        return true;
    }

    /**
     * 打开相册 本地文件等等
     */
    private void handleFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback) {

        if (mActivity == null || mActivity.isFinishing()) {
            return;
        }
        uploadFiles = filePathCallback;

//        MessageEvent event = new MessageEvent();
//        event.typeEvent = JSToAppKey.app_open_photo;
//        event.typeEvent = JSToAppKey.app_open_camera;
//        EventBus.getDefault().post(event);

//        BottomFragmentDialog bottomDialogFr = new BottomFragmentDialog();
//        bottomDialogFr.show(mActivity.getSupportFragmentManager(), "DF");

    }



    /**
     * Activity回调处理
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data, Uri uri) {
        if (requestCode != FILE_REQUEST_CODE) {
            return;
        }
        if (uploadFiles == null) {
            return;
        }
        // 处理相机相册选择
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case FILE_REQUEST_CODE:
                    Uri result = null;
                    if (uri != null) {
                        result = uri;
                    } else {
                        if (data != null) {
                            result = data.getData();
                        }
                    }
                    uploadFiles.onReceiveValue(new Uri[]{result});
                    uploadFiles = null;
                    break;
            }
        } else if (resultCode == Activity.RESULT_CANCELED) {
            uploadFiles.onReceiveValue(null);
            uploadFiles = null;
        }
    }


    // 页面标题、加载进度回调监听接口
    public interface OnWebChromeListener {
        void onReceivedTitle(WebView view, String title);

        void onProgressChanged(WebView view, int newProgress);
    }

    public void setOnWebChromeListener(OnWebChromeListener onWebChromeListener) {
        this.onWebChromeListener = onWebChromeListener;
    }
}
