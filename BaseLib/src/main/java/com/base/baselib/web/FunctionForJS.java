package com.base.baselib.web;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.utils.LogUtils;
import com.base.baselib.utils.Utils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

public class FunctionForJS {

    private IFunctionForJS mIFunctionForJS;
    private Activity mActivity;
    private final Gson mGson;

    public FunctionForJS(BaseActivity activity, IFunctionForJS functionForJS) {
        mActivity = activity;
        mIFunctionForJS = functionForJS;
        mGson = new Gson();
    }


    /**
     * @param msg
     */
    @android.webkit.JavascriptInterface
    public void postMessage(final String msg) {
        if (mIFunctionForJS == null) return;

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    LogUtils.e("postMessage msg ---> " + msg);
//                    BaseBean bean = mGson.fromJson(msg, BaseBean.class);
//                    mFunctionInterface.postMessage(bean);
                } catch (Exception e) {
//                    BaseBean bean = new BaseBean();
//                    bean.key = "-1";
//                    bean.paras = e.getMessage();
//                    mFunctionInterface.postMessage(bean);
                }
            }
        });
    }


    @android.webkit.JavascriptInterface
    public void accepttask_checklist(final String msg){
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Utils.showToast(msg);
                    LogUtils.e("postMessage msg ---> " + msg);
//                    BaseBean bean = mGson.fromJson(msg, BaseBean.class);
//                    mFunctionInterface.postMessage(bean);
                } catch (Exception e) {
//                    BaseBean bean = new BaseBean();
//                    bean.key = "-1";
//                    bean.paras = e.getMessage();
//                    mFunctionInterface.postMessage(bean);
                }
            }
        });
    }

    @android.webkit.JavascriptInterface
    public void task_detail(final String msg){
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Utils.showToast(msg);
                    LogUtils.e("postMessage msg ---> " + msg);
//                    BaseBean bean = mGson.fromJson(msg, BaseBean.class);
//                    mFunctionInterface.postMessage(bean);
                } catch (Exception e) {
//                    BaseBean bean = new BaseBean();
//                    bean.key = "-1";
//                    bean.paras = e.getMessage();
//                    mFunctionInterface.postMessage(bean);
                }
            }
        });
    }

    @android.webkit.JavascriptInterface
    public void toAndroid(final String order){
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Utils.showToast(order);
                    LogUtils.e("postMessage msg ---> " + order);
//                    BaseBean bean = mGson.fromJson(msg, BaseBean.class);
//                    mFunctionInterface.postMessage(bean);
                } catch (Exception e) {
//                    BaseBean bean = new BaseBean();
//                    bean.key = "-1";
//                    bean.paras = e.getMessage();
//                    mFunctionInterface.postMessage(bean);
                }
            }
        });
    }

    @android.webkit.JavascriptInterface
    public void showToast(final String msg){
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Utils.showToast(msg);
                    LogUtils.e("postMessage msg ---> " + msg);
//                    BaseBean bean = mGson.fromJson(msg, BaseBean.class);
//                    mFunctionInterface.postMessage(bean);
                } catch (Exception e) {
//                    BaseBean bean = new BaseBean();
//                    bean.key = "-1";
//                    bean.paras = e.getMessage();
//                    mFunctionInterface.postMessage(bean);
                }
            }
        });
    }


    @android.webkit.JavascriptInterface
    public void closeController(){
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LogUtils.e("text123");
                mActivity.finish();
            }
        });
    }

    @JavascriptInterface
    public void handlerSubmit (String msg){
        Log.d("jsResult","msg = " + msg);
    }
    @JavascriptInterface
    public void handlerSubmit (){
        Log.d("jsResult","onAction no param result");
        EventBus.getDefault().post("handlerSubmit");
    }
}
