package com.qt.dtzf.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.base.baselib.bean.HistotyBean;
import com.base.baselib.bean.ImgBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qt.dtzf.R;
import com.qt.dtzf.common.WaterFallItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ContentAdapter extends RVBaseAdapter<HistotyBean.ListBean.PointListBean> {

    private Context mContext;

    public ContentAdapter(Context context, List<HistotyBean.ListBean.PointListBean> data, int layoutIds) {
        super(context, data, layoutIds);
        mContext = context;
    }

    public void setData(List<HistotyBean.ListBean.PointListBean> infoList) {
        if (infoList == null || infoList.size() <= 0) return;
        notifyDataSetChanged();
    }

    @Override
    public void onBind(RVBaseHolder holder, HistotyBean.ListBean.PointListBean item, int position) {

        holder.setText(R.id.summaryTv, item.getCheckData());
        String xcqzImg = item.getXcqzImg();
        List<ImgBean> mList = new ArrayList<>();
        if (xcqzImg != null && !xcqzImg.isEmpty()) {
            try {
                mList = new Gson().fromJson(xcqzImg, new TypeToken<List<ImgBean>>() {}.getType());
            }catch (Exception e){
                e.printStackTrace();
                if (xcqzImg.contains(",")){
                    String[] split = xcqzImg.split(",");
                    for (int i = 0; i <split.length; i++) {
                        mList.add(new ImgBean("",split[i]));
                    }
                }else {
                    mList.add(new ImgBean("",xcqzImg));
                }
            }

            RecyclerView imgRv = holder.getView(R.id.hisImgRv);
            if (mList != null) {
                imgRv.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL));
                imgRv.addItemDecoration(new WaterFallItemDecoration(30, 30));
                imgRv.setAdapter(new PerformImgAdapter(mContext, mList, R.layout.rvitem_onlyimg));
            }
        }
    }
}
