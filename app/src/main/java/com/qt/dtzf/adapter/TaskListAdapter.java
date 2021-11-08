package com.qt.dtzf.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.baselib.AppConstant;
import com.base.baselib.bean.TaskInfo;
import com.base.baselib.glide.GlideUtils;
import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;
import com.qt.dtzf.R;
import com.qt.dtzf.bean.TaskInfoBean;
import com.qt.dtzf.ui.ChoiceTaskListActivity;
import com.qt.dtzf.ui.PerformAffairsActivity;
import com.qt.dtzf.ui.TaskListItemActivity;
import com.qt.dtzf.ui.TaskMainActivity;
import com.qt.dtzf.ui.TaskSignInActivity;
import com.qt.dtzf.ui.WebDetailsActivity;
import com.qt.dtzf.utils.DateFormatUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.VH> {

    private Activity mContext;
    public List<TaskInfo.ListBean> mInfoList;

    public TaskListAdapter(Activity context) {
        mContext = context;
    }


    public void addList(List<TaskInfo.ListBean> infoList) {
        if (infoList == null || infoList.size() <= 0) return;
        if (mInfoList == null) mInfoList = new ArrayList<>();
        mInfoList.addAll(infoList);
        notifyDataSetChanged();
    }

    public void setList(List<TaskInfo.ListBean> infoList) {
        if (infoList == null || infoList.size() <= 0) return;
        mInfoList = infoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.task_list_item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        TaskInfo.ListBean item = mInfoList.get(position);
        int status = item.getStatus();
        int categoryType = item.getCategoryType();
//        GlideUtils.loadImageCircleCrop(mContext, "text123", holder.task_item_iv);
        holder.task_item_name_tv.setText(item.getUserNames());
        holder.task_item_phone_tv.setText(item.getUserPhones());
        if (categoryType != 0){
            Date date = new Date(item.getStartdate() * 1000l);
            holder.task_item_time_tv.setText(DateFormatUtil.getTime(date, DateFormatUtil.Ymd));
            holder.task_item_address_tv.setText(item.getDataAddress());
            holder.task_item_tag_tv.setText(item.getContent());
            holder.task_company_tv.setText(item.getDataName());
        }
        switch (categoryType) {
            case 0://政务
                holder.tasktype_tv.setText(item.getSeTitle());
                holder.task_item_time_tv.setText(item.getStartDate() + "至" + item.getEndDate());
                holder.task_item_address_tv.setText(item.getSignAddress());
                holder.task_item_company_tag.setText("任务标题");
                holder.task_company_tv.setText(item.getTitle());
                break;
            case 1://电梯任务（特种任务）
                holder.tasktype_tv.setText("特种任务");
                break;
            case 2://食监任务
                holder.tasktype_tv.setText("食监任务");
                break;
            case 3://生产任务
                holder.tasktype_tv.setText("生产任务");
                break;
            case 4://药品任务
                holder.tasktype_tv.setText("药品任务");
                break;
        }
        if (status == 0) {
            holder.taskstatus_tv.setVisibility(View.GONE);
        } else {
            switch (status) {
                case 1://待接收
                    holder.taskstatus_tv.setText("待接收");
                    break;
                case 2://待签到
                    if (categoryType == 0) {
                        holder.taskstatus_tv.setText("待执行");
                    } else {
                        holder.taskstatus_tv.setText("待签到");
                    }
                    break;
                case 3://待执行
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    holder.taskstatus_tv.setText("待执行");
                    break;
                case 98://需复检
                    holder.taskstatus_tv.setText("需复检");
                    break;
                case 99://不通过
                    holder.taskstatus_tv.setText("不通过");
                    break;
                case 100://通过
                    holder.taskstatus_tv.setText("通过");
                    break;
            }
        }
        holder.task_item_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * 1：已发起，待接收 2：已接收，待签到（需要两人同时确定） 3：已签到，待执行 4.特种设备检查完成 5.行政文书已填写
                 * 6.一般问题已填写7.重大问题已填写8.现场笔录已填写 98：需复检 99：不通过 100：通过
                 * */
                String taskUrl = item.getTaskUrl();
                switch (status) {
                    case 1://待接收
                        TaskListItemActivity.gotoActivity(mContext, item);
                        break;
                    case 2://待签到
                        if (item.getQualityType() == 0){
                            PerformAffairsActivity.gotoActivity(mContext, item);
                        }else {
                            TaskSignInActivity.gotoActivity(mContext, item);
                        }
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8://待执行
                        if (item.getCategoryType() == 2 || item.getCategoryType() == 4) {//食品、药品任务
                            if (item.getQualityType() == 2) {//复检任务
                                //拼接url
                                String url = taskUrl + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + item.getId() + "&dataId=" + item.getDataId()
                                        + "&otherId=" + SpUtils.getInt(SpUtilsConstant.otherId) + "&dicName=" + item.getDicName() + "&qualityType=" + item.getQualityType()
                                        + "&pointType=" + item.getPointType();
                                WebDetailsActivity.gotoActivity(mContext, url);
                            } else {
                                ChoiceTaskListActivity.start(mContext, item);
                            }

                        } else if (item.getCategoryType() == 1) {
                            if (item.getQualityType() == 1) {
                                TaskMainActivity.gotoActivity(mContext, item);
                            } else {
                                String url = item.getTaskUrl();
                                if (!TextUtils.isEmpty(url)) {
                                    //拼接url
                                    url = url + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + item.getId() + "&dataId=" + item.getDataId()
                                            + "&otherId=" + SpUtils.getInt(SpUtilsConstant.otherId) + "&dicName=" + item.getDicName() + "&qualityType=" + item.getQualityType()
                                            + "&isView=0" + "&type=0" + "&pointType=" + item.getPointType();
                                    WebDetailsActivity.gotoActivity(mContext, url);
                                }
                            }
                        } else {//3
                            String url = item.getTaskUrl();
                            if (!TextUtils.isEmpty(url)) {
                                //拼接url
                                url = url + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + item.getId() + "&dataId=" + item.getDataId()
                                        + "&otherId=" + SpUtils.getInt(SpUtilsConstant.otherId) + "&dicName=" + item.getDicName() + "&qualityType=" + item.getQualityType()
                                        + "&isView=0" + "&type=0" + "&pointType=" + item.getPointType();
                                WebDetailsActivity.gotoActivity(mContext, url);
                            }
                        }
                        break;
                    case 98://需复检
                        String url = taskUrl + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + item.getId() + "&taskId=" + item.getTaskId()
                                + "&otherId=" + SpUtils.getInt(SpUtilsConstant.otherId) + "&isView=1" + "&type=0" + "&qualityType=" + item.getQualityType() + "&pointType=" + item.getPointType()
                                + "dicName=" + item.getDicName();
                        WebDetailsActivity.gotoActivity(mContext, url);
                        break;
                    case 100://通过
                        String url_complete = taskUrl + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + item.getId() + "&taskId=" + item.getTaskId()
                                + "&otherId=" + SpUtils.getInt(SpUtilsConstant.otherId) + "&isView=1" + "&type=1" + "&qualityType=" + item.getQualityType() + "&pointType=" + item.getPointType()
                                + "dicName=" + item.getDicName();
                        WebDetailsActivity.gotoActivity(mContext, url_complete);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInfoList == null ? 0 : mInfoList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        public final View task_item_cv;
        public final ImageView task_item_iv;
        public final TextView task_item_name_tv;
        public final TextView task_item_phone_tv;
        public final TextView task_item_time_tv;
        public final TextView task_item_address_tv;
        public final TextView task_item_company_tag;
        public final TextView task_item_tag_tv;
        public final TextView task_company_tv;
        public final TextView taskstatus_tv;
        public final TextView tasktype_tv;

        public VH(@NonNull View itemView) {
            super(itemView);
            task_item_cv = itemView.findViewById(R.id.task_item_cv);
            task_item_iv = itemView.findViewById(R.id.task_item_iv);
            task_item_name_tv = itemView.findViewById(R.id.task_item_name_tv);
            task_item_phone_tv = itemView.findViewById(R.id.task_item_phone_tv);
            task_item_time_tv = itemView.findViewById(R.id.task_item_time_tv);
            task_item_address_tv = itemView.findViewById(R.id.task_item_address_tv);
            task_item_tag_tv = itemView.findViewById(R.id.task_item_tag_tv);
            task_company_tv = itemView.findViewById(R.id.task_item_company_tv);
            taskstatus_tv = itemView.findViewById(R.id.taskstatus_tv);
            tasktype_tv = itemView.findViewById(R.id.tasktype_tv);
            task_item_company_tag = itemView.findViewById(R.id.task_item_company_tag);
        }
    }
}
