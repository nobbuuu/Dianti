package com.base.baselib.net;


import com.base.baselib.AppConstant;

public class ApiUrl {

    private static String mBasePI = AppConstant.mBasePI;
    private static String mBaseDebugPI = AppConstant.mBaseDebugPI;

    private static String mBaseUrl = "http://" + mBasePI + "/api/";
    private static String mDebugBaseUrl = "http://" + mBaseDebugPI + "/api/";

    private static String mBaseImageUrl = "http://" + mBasePI + "/";
    private static String mDebugBaseImageUrl = "http://" + mBaseDebugPI;
    public static String getBaseUrl() {

        if (AppConstant.mIsDebug) return mDebugBaseUrl;

        return mBaseUrl;
    }
    public static String getBaseImageUrl() {

        if (AppConstant.mIsDebug) return mDebugBaseImageUrl;
        return mBaseImageUrl;
    }
}
