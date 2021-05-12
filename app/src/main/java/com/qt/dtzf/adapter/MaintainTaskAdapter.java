package com.qt.dtzf.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.bean.MaintainTask;
import com.qt.dtzf.APP;
import com.qt.dtzf.R;
import com.qt.dtzf.ui.WebDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class MaintainTaskAdapter extends RecyclerView.Adapter<MaintainTaskAdapter.VH> {

    private Activity mContext;
    public List<MaintainTask> mList = new ArrayList<>();

    public void setList(List<MaintainTask> list) {
        if (list == null || list.size() <= 0) {
            mList.clear();
        } else {
            mList = list;
        }
        notifyDataSetChanged();
    }

    public void addList(List<MaintainTask> list) {
        if (list == null || list.size() <= 0) return;
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public MaintainTaskAdapter(Activity context) {
        mContext = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.maintain_task_item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        MaintainTask item = mList.get(position);
        holder.nameTypeTv.setText(item.status);
        holder.codeTv.setText(item.code);
        holder.unitTv.setText(item.company);
        holder.nameTv.setText(item.name);
        holder.addressTv.setText(item.security_useaddress);
        holder.typeTv.setText(item.model_type);
        holder.numberTv.setText(item.numberno);
        if (TextUtils.equals("保养中", item.status) || TextUtils.equals("已完成", item.status)) {
            holder.btn.setVisibility(View.GONE);
        } else {
            holder.btn.setVisibility(View.VISIBLE);
        }
        holder.btn.setOnClickListener(v -> gotoWebActivity(item));
        holder.itemCv.setOnClickListener(v -> gotoWebActivity(item));
    }

    private void gotoWebActivity(MaintainTask item) {
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private final TextView nameTypeTv;
        private final TextView codeTv;
        private final TextView unitTv;
        private final TextView nameTv;
        private final TextView addressTv;
        private final TextView typeTv;
        private final TextView numberTv;
        private final View btn;
        private final View itemCv;

        public VH(@NonNull View itemView) {
            super(itemView);
            nameTypeTv = itemView.findViewById(R.id.maintain_task_item_type_name_tv);

            codeTv = itemView.findViewById(R.id.maintain_task_item_code_tv);
            unitTv = itemView.findViewById(R.id.maintain_task_item_unit_tv);
            nameTv = itemView.findViewById(R.id.maintain_task_item_name_tv);
            addressTv = itemView.findViewById(R.id.maintain_task_item_address_tv);
            typeTv = itemView.findViewById(R.id.maintain_task_item_type_tv);
            numberTv = itemView.findViewById(R.id.maintain_task_item_number_tv);

            btn = itemView.findViewById(R.id.maintain_task_item_btn);
            itemCv = itemView.findViewById(R.id.maintain_task_item_cv);
        }
    }
}
