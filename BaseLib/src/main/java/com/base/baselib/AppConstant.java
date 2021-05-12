package com.base.baselib;

/**
 * app常量
 */
public class AppConstant {

    public static boolean mIsDebug = true;
//    public static String mBasePI = "8.129.55.112:8009";//正式环境
    public static String mBasePI = "101.37.86.119:8009";//测试环境
    //    public static String mBaseDebugPI = "119.84.8.162:8644";
    public static String mBaseDebugPI = mBasePI;

    public static String API_KEY = "flxjt1008";

    /**
     * 请求返回正确码
     */
    public static String httpBaseSuccessCode = "0";
    /**
     * 默认http异常码
     */
    public static String httpDefErrorCode = "10010";

    /**
     * 基础服务器
     * 公共参数key
     */
    public static String publicKyeTime = "Time";
    public static String publicKyeAuthToken = "AuthToken";

    public static String token = "token";
}
