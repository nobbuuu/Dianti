package com.qt.dtzf.adapter;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.base.baselib.bean.ElevatorInfo;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.glide.GlideUtils;
import com.qt.dtzf.R;

import java.util.List;

public class PerformImgAdapter extends RVBaseAdapter<ImgBean> {

    private List<ImgBean> mInfoList;
    private Context mContext;

    public PerformImgAdapter(Context context, List<ImgBean> data, int layoutIds) {
        super(context, data, layoutIds);
        mContext = context;
        mInfoList = data;
    }

    public void setData(List<ImgBean> infoList) {
        if (infoList == null || infoList.size() <= 0) return;
        mInfoList = infoList;
        notifyDataSetChanged();
    }

    @Override
    public void onBind(RVBaseHolder holder, ImgBean item, int position) {
        ImageView imgView = holder.getView(R.id.imgIv);
        GlideUtils.loadImage(mContext,item.getImgUrl(),imgView);
    }

    @Override
    public int getItemCount() {
        return mInfoList == null ? 0 : mInfoList.size();
    }
}
