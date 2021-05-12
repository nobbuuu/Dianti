package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.ComplaintTask;
import com.base.baselib.utils.Utils;
import com.qt.dtzf.R;

import java.io.Serializable;

/**
 * 投诉任务
 */
public class TaskComplaintActivity extends BaseActivity {


    private ComplaintTask mComplaintTask;

    public static void gotoActivity(Activity activity, ComplaintTask item) {
        Intent intent = new Intent(activity, TaskComplaintActivity.class);
        intent.putExtra("task", item);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_complaint_activity);
        mComplaintTask = (ComplaintTask) getIntent().getSerializableExtra("task");
        if (mComplaintTask == null) {
            Utils.showToast("参数错误");
            finish();
            return;
        }
        initView();
    }

    private void initView() {

        findViewById(R.id.task_complaint_btn).setOnClickListener(mClickListener);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            gotoNextActivity();
        }
    };

    private void gotoNextActivity() {
        Intent intent = new Intent(this, TaskSignInActivity.class);
        startActivity(intent);

    }
}
