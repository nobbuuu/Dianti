package com.qt.dtzf;

import com.base.baselib.BaseAPP;
import com.qt.dtzf.user_about.UserAction;

public class APP extends BaseAPP {
    private static UserAction mUserAction;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static UserAction getUserAction() {
        if (mUserAction == null) mUserAction = new UserAction();
        return mUserAction;
    }
}
