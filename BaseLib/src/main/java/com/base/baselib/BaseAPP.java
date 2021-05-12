package com.base.baselib;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.base.baselib.utils.LocationUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

public class BaseAPP extends Application {
    public static Context mContext;

    public static Application mBaseAPP;
    private static LocationUtils mLocationUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mBaseAPP = this;
        initFileXml();
        initSmartRefreshLayout();
    }

    public static Context getmContext() {
        return mContext;
    }

    private void initFileXml() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

    private void initSmartRefreshLayout() {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.main_bg, R.color.text_color);
            //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));
            //指定为经典Header，默认是 贝塞尔雷达Header
            return new ClassicsHeader(context);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    public static LocationUtils getLocationUtils() {
        if (mLocationUtils == null) mLocationUtils = new LocationUtils();
        return mLocationUtils;
    }
}
