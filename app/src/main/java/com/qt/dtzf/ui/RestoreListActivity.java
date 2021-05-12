package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.RestoreFragmentAdapter;
import com.qt.dtzf.ui.fragment.RestoreListFragment;
import com.qt.dtzf.ui.fragment.RestoreListOtherFragment;

import java.util.ArrayList;

/**
 * 使用单位 修复任务 列表
 */
public class RestoreListActivity extends BaseActivity {
    private String[] mTitles = new String[]{"日常监管", "双随机监管", "专项检查", "其他"};
    private ArrayList<BaseFragment> mFragments = new ArrayList<>();
    public String category_type = "1";


    public static void gotoActivity(Activity activity, String type) {
        Intent intent = new Intent(activity, RestoreListActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restore_list_activity);
        category_type = getIntent().getStringExtra("type");
        initView();
    }

    private void initView() {
        TabLayout mTaskTl = findViewById(R.id.restore_list_tl);
        ViewPager mTaskVp = findViewById(R.id.restore_list_vp);

        for (int i = 0; i < mTitles.length - 1; i++) {
            String title = mTitles[i];
            mTaskTl.addTab(mTaskTl.newTab().setText(title));
            mFragments.add(RestoreListFragment.getInstance(title));
        }
        mFragments.add(RestoreListOtherFragment.getInstance(mTitles[3]));

        mTaskTl.setupWithViewPager(mTaskVp);
        RestoreFragmentAdapter mAdapter = new RestoreFragmentAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mFragments, mTitles);
        mTaskVp.setAdapter(mAdapter);
    }
}
