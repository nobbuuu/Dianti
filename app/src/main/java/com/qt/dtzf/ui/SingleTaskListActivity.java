package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.base.baselib.base.BaseActivity;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.TaskFragmentAdapter;
import com.qt.dtzf.ui.fragment.TaskListFragment;

import java.util.ArrayList;

/**
 * 任务列表
 * <p>
 * 0 日常监管
 * 1 双随机监管
 * 2 专项检查
 */
public class SingleTaskListActivity extends BaseActivity {
    private String[] mTitles = new String[]{"",};
    private ArrayList<TaskListFragment> mFragments = new ArrayList<>();
    public String category_type = "1";

    public static void gotoActivity(Activity activity, String type) {
        Intent intent = new Intent(activity, SingleTaskListActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recheck_task_list_activity);
        category_type = getIntent().getStringExtra("type");
        initView();
    }

    private void initView() {
        ViewPager mTaskVp = findViewById(R.id.task_list_vp);

        for (int i = 0; i < mTitles.length; i++) {
            String title = mTitles[i];
            mFragments.add(TaskListFragment.getInstance(title, category_type));
        }

        TaskFragmentAdapter mAdapter = new TaskFragmentAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mFragments, mTitles);
        mTaskVp.setAdapter(mAdapter);
    }
}
