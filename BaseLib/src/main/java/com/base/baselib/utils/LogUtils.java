package com.base.baselib.utils;

import android.util.Log;

import com.base.baselib.AppConstant;


public class LogUtils {

    private static final boolean isDeBug = AppConstant.mIsDebug;

    public static void i(String tag, String msg) {
        if (isDeBug) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDeBug) {
            Log.e(tag, msg);
        }
    }

    public static void e(String msg) {
        e("text123", msg);
    }
}
