package com.qt.dtzf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;

import com.base.baselib.base.BaseActivity;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.SafeCheckAdapter;

/**
 * 电梯安全管控检查
 */
public class TaskSafeCheckActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safe_check_activity);
        initView();
    }

    private void initView() {
        ExpandableListView mElv = findViewById(R.id.safe_check_elv);
        SafeCheckAdapter mAdapter = new SafeCheckAdapter(this);
        mElv.setAdapter(mAdapter);
        mElv.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int count = mAdapter.getGroupCount();
                for (int i = 0; i < count; i++) {
                    if (groupPosition != i) {
                        mElv.collapseGroup(i);
                    }
                }
            }
        });

        findViewById(R.id.safe_check_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(TaskSafeCheckActivity.this, TaskSafeDescribeActivity.class);
//                startActivity(intent);
            }
        });
    }
}
