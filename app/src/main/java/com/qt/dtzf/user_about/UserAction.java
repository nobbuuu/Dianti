package com.qt.dtzf.user_about;


import android.text.TextUtils;

import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;
import com.google.gson.Gson;
import com.base.baselib.bean.LoginBean;

/**
 * 用于封装用户的基本行为
 * 可继承扩展更多权限用户的行为
 */
public class UserAction {

    private static LoginBean mUserInfo;
    private Gson mGson = new Gson();

    public void saveLoginInfo(LoginBean info) {

        if (info == null) return;
        mUserInfo = info;

        String s = mGson.toJson(info);

        SpUtils.putString(SpUtilsConstant.userInfo, s);
        SpUtils.putString(SpUtilsConstant.apiKey, mUserInfo.getUser().getToken());
        SpUtils.putString(SpUtilsConstant.userID, mUserInfo.getUser().getId());
        int type = info.getUser().getType();
        int typeId = 1;
        switch (type) {
            case 5:
                typeId = 1;
                break;
            case 2:
                typeId = 2;
                break;
            case 3:
                typeId = 3;
                break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                typeId = 4;
                break;
        }
        SpUtils.putString(SpUtilsConstant.userType, String.valueOf(typeId));
        SpUtils.putInt(SpUtilsConstant.otherId, mUserInfo.getUser().getOtherId());
        SpUtils.putString(SpUtilsConstant.userName, mUserInfo.getUser().getUsername());
        SpUtils.putString(SpUtilsConstant.userPhone, mUserInfo.getUser().getPhone());
    }


    public void setHeadImage(String src) {
//        String s = mGson.toJson(mUserInfo);
        SpUtils.putString(SpUtilsConstant.userImage, src);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public LoginBean getUserInfo() {

        if (mUserInfo != null) return mUserInfo;
        String s = SpUtils.getString(SpUtilsConstant.userInfo);
        if (TextUtils.isEmpty(s)) return null;
        mUserInfo = mGson.fromJson(s, LoginBean.class);
        return mUserInfo;
    }


    public void logInOut() {

        SpUtils.putString(SpUtilsConstant.apiKey, "");
        SpUtils.putString(SpUtilsConstant.userInfo, "");
//        mUserInfo = null;
//        SpUtils.putString(SpUtilsConstant.userInfo, "");
    }
}
