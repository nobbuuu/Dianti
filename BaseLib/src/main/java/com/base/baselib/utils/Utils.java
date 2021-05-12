package com.base.baselib.utils;

import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.Toast;

import com.base.baselib.BaseAPP;

public class Utils {

    public static Resources getAppResources() {
        return BaseAPP.mContext.getResources();
    }

    public static void showToast(int msgId) {
        String msg = getAppResources().getString(msgId);
        showToast(msg);
    }

    public static void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) return;
        Toast.makeText(BaseAPP.mContext, msg, Toast.LENGTH_SHORT).show();
    }


}
