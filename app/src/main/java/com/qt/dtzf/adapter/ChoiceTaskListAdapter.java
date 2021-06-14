package com.qt.dtzf.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.baselib.bean.ChoiceTaskListBean;
import com.base.baselib.bean.TaskInfo;
import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;
import com.qt.dtzf.R;
import com.qt.dtzf.ui.TaskMainActivity;
import com.qt.dtzf.ui.WebDetailsActivity;

import java.util.List;

public class ChoiceTaskListAdapter extends RVBaseAdapter<ChoiceTaskListBean.ListBean> {

    public ChoiceTaskListAdapter(Context context, List<ChoiceTaskListBean.ListBean> data, int layoutId) {
        super(context, data, layoutId);

    }

    @Override
    public void onBind(RVBaseHolder holder, ChoiceTaskListBean.ListBean listBean, int position) {
        holder.setText(R.id.device_type_name, listBean.getName());
        TextView status_tv = holder.getView(R.id.status_tv);
        if (listBean.getStatus() == 1) {
            status_tv.setVisibility(View.VISIBLE);
        } else {
            status_tv.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDone = false;
                for (ChoiceTaskListBean.ListBean bean : data) {
                    if (bean.getStatus() == 1) {
                        isDone = true;
                        break;
                    }
                }

                TaskInfo.ListBean taskInfo = new TaskInfo.ListBean();
                taskInfo.setCategoryType(listBean.getCategoryType());
                taskInfo.setDicName(listBean.getDicName());
                taskInfo.setPointType(listBean.getPointType());
                taskInfo.setDataId(listBean.getDataId());
                taskInfo.setQualityType(listBean.getQualityType());
                taskInfo.setStatus(listBean.getStatus());
                taskInfo.setTaskId(listBean.getTaskId() + "");
                taskInfo.setId(listBean.getId() + "");
                taskInfo.setTaskUrl(listBean.getTaskUrl());

                if (isDone && listBean.getStatus() == 1) {
                    String taskUrl = listBean.getTaskUrl();
                    if (!TextUtils.isEmpty(taskUrl)) {
                        taskUrl = taskUrl + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + listBean.getId() + "&otherId=" + SpUtils.getInt(SpUtilsConstant.otherId)
                                + "&dickName=" + listBean.getDicName() + "&pointType=" + listBean.getPointType();
                        WebDetailsActivity.gotoActivity(context, taskUrl, taskInfo);
                    }
                } else if (!isDone) {
                    String taskUrl = listBean.getTaskUrl();
                    if (!TextUtils.isEmpty(taskUrl)) {
                        taskUrl = taskUrl + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + listBean.getId() + "&otherId=" + SpUtils.getInt(SpUtilsConstant.otherId)
                                + "&dickName=" + listBean.getDicName() + "&pointType=" + listBean.getPointType();
                        WebDetailsActivity.gotoActivity(context, taskUrl, taskInfo);
                    }
                }
            }
        });
    }
}
