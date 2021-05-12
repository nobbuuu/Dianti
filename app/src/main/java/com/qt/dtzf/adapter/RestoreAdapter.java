package com.qt.dtzf.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.bean.Restore;
import com.base.baselib.glide.GlideUtils;
import com.qt.dtzf.APP;
import com.qt.dtzf.R;
import com.qt.dtzf.ui.TaskListItemActivity;
import com.qt.dtzf.ui.WebDetailsActivity;

import java.util.List;

public class RestoreAdapter extends RecyclerView.Adapter<RestoreAdapter.VH> {

    private Activity mContext;
    public List<Restore> mInfoList;

    public RestoreAdapter(Activity context) {
        mContext = context;
    }


    public void setList(List<Restore> infoList) {
        if (infoList == null || infoList.size() <= 0) return;
        mInfoList = infoList;
        notifyDataSetChanged();
    }

    public void addList(List<Restore> infoList) {
        if (infoList == null || infoList.size() <= 0) return;
        mInfoList.addAll(infoList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.restore_item, null);
        return new VH(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Restore item = mInfoList.get(position);
        GlideUtils.loadImageCircleCrop(mContext, "text123", holder.item_iv);
        holder.item_name_tv.setText(item.username);
        holder.item_phone_tv.setText(item.phone);
        holder.item_time_tv.setText(item.week + " " + item.stardate);
        holder.item_address_tv.setText(item.address);
//        holder.item_tag_tv.setText(item.make_category);
        holder.company_tv.setText(item.company);
        holder.item_cv.setOnClickListener(new View.OnClickListener() {
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

        public final View item_cv;
        public final ImageView item_iv;
        public final TextView item_name_tv;
        public final TextView item_phone_tv;
        public final TextView item_time_tv;
        public final TextView item_address_tv;
        public final TextView item_tag_tv;
        public final TextView company_tv;

        public VH(@NonNull View itemView) {
            super(itemView);
            item_cv = itemView.findViewById(R.id.restore_item_cv);
            item_iv = itemView.findViewById(R.id.restore_item_iv);
            item_name_tv = itemView.findViewById(R.id.restore_item_name_tv);
            item_phone_tv = itemView.findViewById(R.id.restore_item_phone_tv);
            item_time_tv = itemView.findViewById(R.id.restore_item_time_tv);
            item_address_tv = itemView.findViewById(R.id.restore_item_address_tv);
            item_tag_tv = itemView.findViewById(R.id.restore_item_tag_tv);
            company_tv = itemView.findViewById(R.id.restore_item_company_tv);

        }
    }
}
