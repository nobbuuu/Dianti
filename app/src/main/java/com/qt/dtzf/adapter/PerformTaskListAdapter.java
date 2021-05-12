package com.qt.dtzf.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.base.baselib.bean.PerformBean;
import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;
import com.qt.dtzf.R;
import com.qt.dtzf.ui.WebDetailsActivity;
import com.qt.dtzf.ui.WebDetailsCaseListActivity;

import java.util.ArrayList;
import java.util.List;

public class PerformTaskListAdapter extends RVBaseAdapter<PerformBean.ListBean> {

    public List<PerformBean.ListBean> mInfoList;

    public PerformTaskListAdapter(Context context, List<PerformBean.ListBean> data, int layoutId) {
        super(context, data, layoutId);
        mInfoList = data;
    }

    @Override
    public void onBind(RVBaseHolder holder, PerformBean.ListBean dataBean, int position) {
        String dataName = dataBean.getDataName();
        String transferTime = dataBean.getTransferTime();
        holder.setText(R.id.task_item_time_tag, dataName);
        holder.setText(R.id.task_item_company_tag, transferTime);
        holder.setText(R.id.perform_status_tv, "流程：" + dataBean.getStepNum() + "/7");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskUrl = dataBean.getTaskUrl();
                if (!TextUtils.isEmpty(taskUrl)) {
                    String url = taskUrl + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + dataBean.getId();
                    WebDetailsCaseListActivity.gotoActivity(context, url);
                }
            }
        });

    }

    public void addList(List<PerformBean.ListBean> infoList) {
        if (infoList == null || infoList.size() <= 0) return;
        if (mInfoList == null) mInfoList = new ArrayList<>();
        mInfoList.addAll(infoList);
        notifyDataSetChanged();
    }
}
