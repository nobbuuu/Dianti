package com.qt.dtzf.adapter;


import com.qt.dtzf.ui.fragment.TaskListFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TaskFragmentAdapter extends FragmentPagerAdapter {
    private List<TaskListFragment> mFragmentList;
    private String[] mTitles;

    public TaskFragmentAdapter(@NonNull FragmentManager fm, int behavior, List<TaskListFragment> fragmentList,String[] titles) {
        super(fm, behavior);
        mFragmentList = fragmentList;
        mTitles = titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];

    }

    @NonNull
    @Override
    public TaskListFragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }
}
