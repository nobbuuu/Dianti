package com.qt.dtzf.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.base.baselib.bean.HistotyBean;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.glide.GlideUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qt.dtzf.R;
import com.qt.dtzf.common.WaterFallItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class PerformHistoryAdapter extends RVBaseAdapter<HistotyBean.ListBean> {

    public PerformHistoryAdapter(Context context, List<HistotyBean.ListBean> data, int layoutIds) {
        super(context, data, layoutIds);
    }

    public void setData(List<HistotyBean.ListBean> infoList) {
        if (infoList == null || infoList.size() <= 0) return;
        notifyDataSetChanged();
    }

    @Override
    public void onBind(RVBaseHolder holder, HistotyBean.ListBean item, int position) {

        holder.setText(R.id.timeTv, "上传时间："+item.getSearchDate());
        List<HistotyBean.ListBean.PointListBean> pointList = item.getPointList();
        if (pointList != null && !pointList.isEmpty()) {
            ContentAdapter contentAdapter = new ContentAdapter(context, pointList, R.layout.rvitem_content);
            RecyclerView contentRv = holder.getView(R.id.contentRv);
            contentRv.setAdapter(contentAdapter);
        }
    }
}
