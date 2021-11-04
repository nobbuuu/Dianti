package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.TaskInfo;
import com.qt.dtzf.R;

/**
 * Create By s on
 */
public class PerformAffairsActivity extends BaseActivity {

    public static void gotoActivity(Activity activity, TaskInfo.ListBean taskInfoBean) {
        Intent intent = new Intent(activity, PerformAffairsActivity.class);
        intent.putExtra("taskInfoBean", taskInfoBean);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_task);
    }
}
