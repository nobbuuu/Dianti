package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.TaskFragmentAdapter;
import com.qt.dtzf.ui.fragment.TaskListFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * 任务列表
 * <p>
 * 0 日常监管
 * 1 双随机监管
 * 2 专项检查
 */
public class TaskListActivity extends BaseActivity {
    private String[] mTitles = new String[]{"日常监管", "双随机监管","专项检查"};
    private ArrayList<TaskListFragment> mFragments = new ArrayList<>();
    public String category_type = "1";

    public static void gotoActivity(Activity activity, String type) {
        Intent intent = new Intent(activity, TaskListActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_activity);
        category_type = getIntent().getStringExtra("type");
        initView();
    }

    private void initView() {
        TabLayout mTaskTl = findViewById(R.id.task_list_tl);
        ViewPager mTaskVp = findViewById(R.id.task_list_vp);

        for (int i = 0; i < mTitles.length; i++) {
            String title = mTitles[i];
            mTaskTl.addTab(mTaskTl.newTab().setText(title));
            mFragments.add(TaskListFragment.getInstance(title,category_type));
        }

        mTaskTl.setupWithViewPager(mTaskVp);
        TaskFragmentAdapter mAdapter = new TaskFragmentAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mFragments, mTitles);
        mTaskVp.setAdapter(mAdapter);
    }
}
