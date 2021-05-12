package com.base.baselib.utils;

import android.text.TextUtils;

import com.base.baselib.AppConstant;
import com.base.baselib.bean.base.BaseBean;
import com.base.baselib.bean.base.Bean;

import io.reactivex.Observable;


public class NetResultParseUtils {


    /**
     * 解析 返回的结果
     * 如果结果正常 返回null 反之返回一个Observable<Bean<String>>
     *
     * @param result
     * @return
     */
    public static Observable<Bean<String>> parseBeanToStr(BaseBean result) {
        Bean<String> bean = new Bean<String>();
        if (result == null) {
            bean.code = "201";
            bean.message = "后台无返回 appCode = 201 ";
        } else {
            bean.code = result.code;
            bean.message = result.message;
        }
        return Observable.just(bean);
    }


    public static boolean parseNetResult(BaseBean result) {

        if (result == null) {
            return false;
        }
        String code = result.code;
        if (TextUtils.equals(AppConstant.httpBaseSuccessCode, code)) {
            return true;
        } else {
            return false;
        }
    }

}
