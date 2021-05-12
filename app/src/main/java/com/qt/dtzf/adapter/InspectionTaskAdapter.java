package com.qt.dtzf.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.bean.InspectionTask;
import com.qt.dtzf.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 巡查任务adapter
 */
public class InspectionTaskAdapter extends RecyclerView.Adapter<InspectionTaskAdapter.VH> {

    private Activity mContext;
    public List<InspectionTask> mInfoList = new ArrayList<>();

    public InspectionTaskAdapter(Activity context) {
        mContext = context;
    }


    public void setList(List<InspectionTask> list) {
        if (list == null || list.size() <= 0) return;
        mInfoList = list;
        notifyDataSetChanged();
    }

    public void addList(List<InspectionTask> list) {
        if (list == null || list.size() <= 0) return;
        mInfoList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.inspection_task_item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        InspectionTask item = mInfoList.get(position);
        holder.timeTv.setText(item.creattime);
        holder.codeTv.setText(item.code);
        holder.numberTv.setText(item.numberno);
        holder.addressTv.setText(item.security_useaddress);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInfoList == null ? 0 : mInfoList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private final AppCompatTextView timeTv;
        private final AppCompatTextView codeTv;
        private final AppCompatTextView numberTv;
        private final AppCompatTextView addressTv;
        private final View btn;

        public VH(@NonNull View itemView) {
            super(itemView);
            timeTv = itemView.findViewById(R.id.inspection_item_time_tv);
            codeTv = itemView.findViewById(R.id.inspection_item_code_tv);
            numberTv = itemView.findViewById(R.id.inspection_item_number_tv);
            addressTv = itemView.findViewById(R.id.inspection_item_address_tv);
            btn = itemView.findViewById(R.id.inspection_btn);
        }
    }
}
