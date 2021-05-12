package com.qt.dtzf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.base.BaseActivity;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.SafeTaskDetailsAdapter;

/**
 * 详情
 */
public class TaskSafeDetailsActivity extends BaseActivity {

    private RecyclerView mDetailsRv;
    private SafeTaskDetailsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safe_task_details_activity);
        initView();
    }

    private void initView() {
        mDetailsRv = findViewById(R.id.safe_task_details_rv);
        mDetailsRv.setLayoutManager(new GridLayoutManager(this, 1));
        mAdapter = new SafeTaskDetailsAdapter(this);
        mDetailsRv.setAdapter(mAdapter);


        findViewById(R.id.safe_task_details_describe_btn).setOnClickListener(mClickListener);
        findViewById(R.id.safe_task_details_confirm_btn).setOnClickListener(mClickListener);

    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.safe_task_details_confirm_btn:
                    finish();
                    break;
                case R.id.safe_task_details_describe_btn:

                    Intent intent = new Intent(TaskSafeDetailsActivity.this, TaskSafeDescribeActivity.class);
                    startActivity(intent);

                    break;
            }
        }
    };
}
