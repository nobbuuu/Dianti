package com.qt.dtzf.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.utils.AlbumUtils;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.SafeCheckItemAdapter;
import com.qt.dtzf.adapter.SafeTaskDetailsAdapter;

import java.util.List;

/**
 * 问题描述详情
 */
public class TaskSafeDescribeActivity extends BaseActivity {

    private RecyclerView mDescribeRv;
    private SafeCheckItemAdapter mAdapter;
    private AlbumUtils mAlbumUtils;
    private int mMaxSize = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.safe_task_describe_activity);

        mAlbumUtils = new AlbumUtils(this, mAlbumListener);
        initView();
    }

    private void initView() {

        mDescribeRv = findViewById(R.id.safe_task_describe_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        mDescribeRv.setLayoutManager(layoutManager);
        mAdapter = new SafeCheckItemAdapter(this);

        mAdapter.addImageListener(mImageListener);
        mDescribeRv.setAdapter(mAdapter);
    }

    AlbumUtils.AlbumListener mAlbumListener = (list, is) -> {
        mAdapter.addImageList(list);
    };

    SafeCheckItemAdapter.ImageListener mImageListener = new SafeCheckItemAdapter.ImageListener() {
        @Override
        public void addImageListener() {
            List<ImgBean> list = mAdapter.getImageList();
            int maxSize = mMaxSize - list.size();
            mAlbumUtils.showDialog(getSupportFragmentManager(), maxSize);
        }

        @Override
        public void deleteImageListener() {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mAlbumUtils != null) mAlbumUtils.onActivityResult(requestCode, resultCode, data);
    }
}
