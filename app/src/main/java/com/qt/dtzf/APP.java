package com.qt.dtzf;

import com.amap.api.maps.MapsInitializer;
import com.base.baselib.BaseAPP;
import com.qt.dtzf.user_about.UserAction;

public class APP extends BaseAPP {
    private static UserAction mUserAction;

    @Override
    public void onCreate() {
        super.onCreate();
        MapsInitializer.updatePrivacyShow(this,true,true);
        MapsInitializer.updatePrivacyAgree(this,true);
    }

    public static UserAction getUserAction() {
        if (mUserAction == null) mUserAction = new UserAction();
        return mUserAction;
    }
}
