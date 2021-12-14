package com.base.baselib.utils;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.base.baselib.BaseAPP;

public class LocationUtils {

    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;

    public LocationUtils() {
        initLocationClient();
    }

    private void initLocationClient() {
        //初始化定位
        try {
            mLocationClient = new AMapLocationClient(BaseAPP.mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AMapLocationClientOption option = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(false);
        option.setInterval(5000);
        option.setNeedAddress(true);
        if (mLocationClient == null) return;
        mLocationClient.setLocationOption(option);

    }

    public void addLocationListener(AMapLocationListener listener) {
        if (listener == null  || mLocationClient == null) return;
        //设置定位回调监听
        mLocationClient.setLocationListener(listener);
    }

    public void stopLocation() {
        if (mLocationClient != null) mLocationClient.stopLocation();
    }

    public void startLocation() {
        stopLocation();
        if (mLocationClient != null) mLocationClient.startLocation();
    }
}
