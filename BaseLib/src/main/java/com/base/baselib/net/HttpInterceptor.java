package com.base.baselib.net;

import android.util.Log;

import com.base.baselib.utils.LogUtils;
import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpInterceptor {

    /**
     * token 拦截器
     */
    public Interceptor heatInterceptor = chain -> {

        String token = SpUtils.getString(SpUtilsConstant.apiKey);
        Log.d("aaa","token = " + token);
        Request cookie = chain.request().newBuilder().addHeader("TOKEN", token).build();
        try {
            Response proceed = chain.proceed(cookie);
            return proceed;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    };

    /**
     * log拦截器
     */
    public HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {

        try {
            String text = URLDecoder.decode(message, "utf-8");
            LogUtils.e("OKHttp-->", text);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LogUtils.e("OKHttp-->", message);
        }
    });


}
