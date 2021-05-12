package com.qt.dtzf.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.bean.DevManagerBean;
import com.qt.dtzf.APP;
import com.qt.dtzf.R;
import com.qt.dtzf.ui.WebDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class DevManagerAdapter extends RecyclerView.Adapter<DevManagerAdapter.VH> {

    public List<DevManagerBean> mList = new ArrayList<>();
    private Activity mContext;

    public DevManagerAdapter(Activity context) {
        mContext = context;
    }

    public void setList(List<DevManagerBean> list) {
        if (list == null || list.size() <= 0) return;
        mList = list;
        notifyDataSetChanged();
    }

    public void addList(List<DevManagerBean> list) {
        if (list == null || list.size() <= 0) return;
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.dev_manager_item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        DevManagerBean item = mList.get(position);
        holder.companyTv.setText(item.company);
        holder.numberTv.setText(item.num + "台");
        holder.addressTv.setText(item.address);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        public final TextView companyTv;
        public final TextView numberTv;
        public final TextView addressTv;
        public final View btn;

        public VH(@NonNull View itemView) {
            super(itemView);
            companyTv = itemView.findViewById(R.id.dev_manager_item_time_tv);
            numberTv = itemView.findViewById(R.id.dev_manager_item_company_tv);
            addressTv = itemView.findViewById(R.id.dev_manager_item_address_tv);
            btn = itemView.findViewById(R.id.dev_manager_item_btn);

        }
    }
}
