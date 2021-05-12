package com.qt.dtzf.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.baselib.bean.UserFiles;
import com.qt.dtzf.APP;
import com.qt.dtzf.R;
import com.qt.dtzf.ui.WebDetailsActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 人员列表adapter
 */
public class PersonnelFilesAdapter extends RecyclerView.Adapter<PersonnelFilesAdapter.VH> {


    public List<UserFiles> userFiles;
    private Activity mContext;

    private ClickListener mClickListener;

    public PersonnelFilesAdapter(Activity context) {
        mContext = context;
    }

    public void setList(List<UserFiles> list) {
        if (list == null || list.size() <= 0) {
            userFiles.clear();
        } else {
            userFiles = list;
        }
        notifyDataSetChanged();
    }

    public void addList(List<UserFiles> list) {
        if (list == null || list.size() <= 0) return;
        userFiles.addAll(list);
        notifyDataSetChanged();
    }

    public void delItem(String id) {
        for (int i = 0; i < userFiles.size(); i++) {
            UserFiles item = userFiles.get(i);
            if (TextUtils.equals(id, item.id)) {
                userFiles.remove(item);
                break;
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.personnel_files_item, null);
        return new VH(view);
    }

    public void addItemClickListener(ClickListener listener) {
        mClickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        UserFiles item = this.userFiles.get(position);
        holder.nameTv.setText(item.realname);
        holder.phoneTv.setText(item.phone);
        holder.timeTv.setText(item.cardstardate + "-" + item.cardenddate);
        holder.companyTv.setText(item.company);
        holder.addressTv.setText(item.region1);
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
                if (mClickListener != null) mClickListener.onClickListener(item);
            }
        });

        holder.itemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //详情
            }
        });
    }

    @Override
    public int getItemCount() {
        return userFiles == null ? 0 : userFiles.size();
    }

    class VH extends RecyclerView.ViewHolder {

        public final TextView nameTv;
        public final TextView phoneTv;
        public final TextView timeTv;
        public final TextView companyTv;
        public final TextView addressTv;
        public final View deleteBtn;
        public final View itemBtn;

        public VH(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.personnel_files_item_name_tv);
            phoneTv = itemView.findViewById(R.id.personnel_files_item_phone_tv);
            timeTv = itemView.findViewById(R.id.personnel_files_item_time_tv);
            companyTv = itemView.findViewById(R.id.personnel_files_item_company_tv);
            addressTv = itemView.findViewById(R.id.personnel_files_item_address_tv);
            deleteBtn = itemView.findViewById(R.id.personnel_files_item_delete_btn);
            itemBtn = itemView.findViewById(R.id.personnel_files_item_btn);
        }
    }

    public interface ClickListener {
        void onClickListener(UserFiles item);
    }
}
