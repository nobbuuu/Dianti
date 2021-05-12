package com.base.baselib.net;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * api 网络请求管理者
 */
public class ApiManager {

    private static ApiManager mApiManager = null;
    //超时时间
    public static final int DEFAULT_TIMEOUT = 5;

    private BaseApi mBaseApi;

    private BaseFileApi mBaseFileApi;


    private ApiManager() {
    }

    /**
     * 基础 httpClient
     * 拦截器要分开创建
     * 否则是会有问题的
     *
     * @return
     */
    private OkHttpClient getBaseHttpClient() {
        HttpInterceptor interceptor = new HttpInterceptor();
        HttpLoggingInterceptor loggingInterceptor = interceptor.loggingInterceptor;
        HttpLoggingInterceptor level = loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor.heatInterceptor)
                .addInterceptor(level)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .build();
    }

    /**
     * 文件上传httpClient
     *
     * @return
     */
    private OkHttpClient getFileHttpClient() {
        HttpInterceptor interceptor = new HttpInterceptor();
        HttpLoggingInterceptor loggingInterceptor = interceptor.loggingInterceptor;
        HttpLoggingInterceptor level = loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor.heatInterceptor)
                .addInterceptor(level)
                .build();
    }

    public static ApiManager getInstance() {
        if (mApiManager == null) mApiManager = new ApiManager();
        return mApiManager;
    }

    /**
     * 基础api初始化
     *
     * @return
     */
    public BaseApi getBaseApi() {
        if (mBaseApi != null) return mBaseApi;
        mBaseApi = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getBaseHttpClient())
                .baseUrl(ApiUrl.getBaseUrl())
                .build().create(BaseApi.class);
        return mBaseApi;
    }

    /**
     * 文件上传api
     *
     * @return
     */
    public BaseFileApi getBaseFileApi() {
        if (mBaseFileApi != null) return mBaseFileApi;
        mBaseFileApi = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiUrl.getBaseUrl())
                .client(getFileHttpClient())
                .build().create(BaseFileApi.class);
        return mBaseFileApi;
    }
}
