package com.base.baselib.utils;

import android.content.res.Resources;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.base.baselib.BaseAPP;
import com.base.baselib.base.BaseActivity;

/**
 * 状态栏设置
 */
public class StatusBarUtil {

    public static int getStatusBarHeight() {
        Resources resources = BaseAPP.mContext.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    public static void setStatusBarColor(BaseActivity activity){
        //获取窗口区域
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
}
