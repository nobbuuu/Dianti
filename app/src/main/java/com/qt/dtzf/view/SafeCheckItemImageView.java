package com.qt.dtzf.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.bean.ImgBean;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.SafeCheckItemAdapter;

import java.util.ArrayList;
import java.util.List;


public class SafeCheckItemImageView extends FrameLayout {

    private Context mContext;

    public SafeCheckItemAdapter mAdapter;
    public EditText mEt;

    public SafeCheckItemImageView(Context context) {
        super(context);
        initView(context);
    }

    public SafeCheckItemImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SafeCheckItemImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View.inflate(mContext, R.layout.safe_check_item_image, this);
        List<ImgBean> list = new ArrayList<>();
        list.add(new ImgBean("https://imgcdn.toutiaoyule.com/20200513/20200513194127145583a.jpg"));
        list.add(new ImgBean("https://imgcdn.toutiaoyule.com/20200513/20200513194127145583a.jpg"));
        mEt = findViewById(R.id.safe_check_et);
        RecyclerView mRv = findViewById(R.id.safe_check_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1);
        layoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        mRv.setLayoutManager(layoutManager);
        mAdapter = new SafeCheckItemAdapter(mContext,false);
        mAdapter.addImageNoTagList(list);
        mRv.setAdapter(mAdapter);

    }



}
