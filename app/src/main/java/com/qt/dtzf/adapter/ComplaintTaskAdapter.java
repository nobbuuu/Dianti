package com.qt.dtzf.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.bean.ComplaintTask;
import com.qt.dtzf.R;

import java.util.ArrayList;
import java.util.List;

public class ComplaintTaskAdapter extends RecyclerView.Adapter<ComplaintTaskAdapter.VH> {

    private Activity mContext;

    public List<ComplaintTask> mTaskList = new ArrayList<>();

    public ComplaintTaskAdapter(Activity context) {
        mContext = context;
    }


    public void setList(List<ComplaintTask> tasks) {
        if (tasks == null || tasks.size() <= 0) return;
        mTaskList.clear();
        mTaskList.addAll(tasks);
        notifyDataSetChanged();
    }

    public void addList(List<ComplaintTask> tasks) {
        if (tasks == null || tasks.size() <= 0) return;
        mTaskList.addAll(tasks);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.task_complaint_item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ComplaintTask item = mTaskList.get(position);
        holder.mTimeTv.setText(item.week + " " + item.creattime);
        holder.mAddressTv.setText(item.address);
        holder.mCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mTaskList == null ? 0 : mTaskList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        public final TextView mTimeTv;
        public final TextView mAddressTv;
        public final View mCv;

        public VH(@NonNull View itemView) {
            super(itemView);
            mCv = itemView.findViewById(R.id.task_complaint_cv);
            mTimeTv = itemView.findViewById(R.id.task_complaint_time_tv);
            mAddressTv = itemView.findViewById(R.id.task_complaint_address_tv);
        }
    }

}
