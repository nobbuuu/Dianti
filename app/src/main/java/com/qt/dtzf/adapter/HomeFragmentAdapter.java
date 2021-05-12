package com.qt.dtzf.adapter;


import com.base.baselib.base.BaseFragment;
import com.qt.dtzf.ui.fragment.TaskListFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomeFragmentAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragmentList;


    public HomeFragmentAdapter(@NonNull FragmentManager fm, int behavior, List<BaseFragment> fragmentList) {
        super(fm, behavior);
        mFragmentList = fragmentList;
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
