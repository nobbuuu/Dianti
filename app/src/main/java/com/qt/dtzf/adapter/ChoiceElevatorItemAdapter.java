package com.qt.dtzf.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.base.baselib.bean.ElevatorInfo;
import com.base.baselib.utils.Utils;
import com.qt.dtzf.R;

import java.util.List;

public class ChoiceElevatorItemAdapter extends CommonAdapter<ElevatorInfo.ListElevatorBeanX.ListElevatorBean> {

    public ChoiceElevatorItemAdapter(Context context, List<ElevatorInfo.ListElevatorBeanX.ListElevatorBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void onBind(BaseViewHolder holder, ElevatorInfo.ListElevatorBeanX.ListElevatorBean listElevatorBean, int position) {
        String numberno = listElevatorBean.getRegisterNo();
        TextView mTv = holder.getView(R.id.choice_elevator_tv);
        if (!TextUtils.isEmpty(numberno)) {
            holder.setText(R.id.choice_elevator_tv, numberno);
        }

        if (listElevatorBean.isSelect()){
            mTv.setBackgroundResource(R.drawable.main_btn_bg);
            mTv.setTextColor(Utils.getAppResources().getColor(R.color.white));
        }else {
            mTv.setBackgroundResource(R.drawable.wait_dialog_bg);
            mTv.setTextColor(Utils.getAppResources().getColor(R.color.text_9_color));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listElevatorBean.isSelect()){
                    listElevatorBean.setSelect(false);
                }else {
                    listElevatorBean.setSelect(true);
                }
                notifyDataSetChanged();
            }
        });
    }
}

