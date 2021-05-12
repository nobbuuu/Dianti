package com.base.baselib.model;

import com.base.baselib.AppConstant;
import com.base.baselib.bean.EmptyBean;
import com.base.baselib.bean.HeardInfo;
import com.base.baselib.bean.LoginBean;
import com.base.baselib.bean.LoginInfo;
import com.base.baselib.bean.UserInfo;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.net.ApiManager;
import com.base.baselib.utils.BitmapUtils;
import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 用户相关的model
 */
public class UserAboutModel {

    private static UserAboutModel model;

    public static UserAboutModel getInstance() {
        if (model == null) model = new UserAboutModel();
        return model;
    }

    private UserAboutModel() {
    }


    /**
     * 登录
     *
     * @param phone
     * @param pwd
     * @return
     */
    public Observable<Bean<LoginBean>> login(String phone, String pwd) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", pwd);
        return ApiManager.getInstance().getBaseApi().login(map);
    }

    /**
     * 退出登录
     *
     * @return
     */
    public Observable<Bean<EmptyBean>> loginOut() {
        Map<String, String> map = new HashMap<>();
        map.put(AppConstant.token, SpUtils.getString(SpUtilsConstant.apiKey));
        return ApiManager.getInstance().getBaseApi().loginOut(map);
    }

    /**
     * 个人中心
     *
     * @return
     */
    public Observable<Bean<UserInfo>> getUserInfo() {
        Map<String, String> map = new HashMap<>();
        return ApiManager.getInstance().getBaseApi().getUserInfo(map);
    }

    /**
     * 头像上传
     *
     * @param url
     * @return
     */
    public Observable<Bean<HeardInfo>> setAvatar(String url) {
        File file = BitmapUtils.qualityCompress(url);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);
        return ApiManager.getInstance().getBaseFileApi().setAvatar(body);
    }


}
