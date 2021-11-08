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

import java.util.List;

public class PerformHistoryAdapter extends RVBaseAdapter<HistotyBean.ListBean.PointListBean> {

    private List<HistotyBean.ListBean.PointListBean> mInfoList;
    private Context mContext;

    public PerformHistoryAdapter(Context context, List<HistotyBean.ListBean.PointListBean> data, int layoutIds) {
        super(context, data, layoutIds);
        mContext = context;
        mInfoList = data;
    }

    public void setData(List<HistotyBean.ListBean.PointListBean> infoList) {
        if (infoList == null || infoList.size() <= 0) return;
        mInfoList = infoList;
        notifyDataSetChanged();
    }

    @Override
    public void onBind(RVBaseHolder holder, HistotyBean.ListBean.PointListBean item, int position) {

        holder.setText(R.id.timeTv,item.getCreateTime());
        holder.setText(R.id.summaryTv,item.getCheckData());
        String xcqzImg = item.getXcqzImg();
        List<ImgBean> mList = new Gson().fromJson(xcqzImg, new TypeToken<List<ImgBean>>(){}.getType());
        RecyclerView imgRv = holder.getView(R.id.hisImgRv);
        if (mList != null) {
            imgRv.setLayoutManager(new StaggeredGridLayoutManager(3,RecyclerView.VERTICAL));
            imgRv.addItemDecoration(new WaterFallItemDecoration(30,30));
            imgRv.setAdapter(new PerformImgAdapter(mContext,mList,R.layout.rvitem_onlyimg));
        }
    }

    @Override
    public int getItemCount() {
        return mInfoList == null ? 0 : mInfoList.size();
    }
}
