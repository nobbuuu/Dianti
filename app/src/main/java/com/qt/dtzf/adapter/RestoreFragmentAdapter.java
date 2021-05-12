package com.qt.dtzf.adapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.base.baselib.base.BaseFragment;
import com.qt.dtzf.ui.fragment.RestoreListFragment;
import com.qt.dtzf.ui.fragment.TaskListFragment;

import java.util.List;

public class RestoreFragmentAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragmentList;
    private String[] mTitles;

    public RestoreFragmentAdapter(@NonNull FragmentManager fm, int behavior, List<BaseFragment> fragmentList, String[] titles) {
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
    public BaseFragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }
}
