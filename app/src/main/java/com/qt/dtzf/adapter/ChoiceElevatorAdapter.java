package com.qt.dtzf.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.ElevatorInfo;
import com.base.baselib.utils.Utils;
import com.qt.dtzf.R;

import java.util.ArrayList;
import java.util.List;

public class ChoiceElevatorAdapter extends RVBaseAdapter<ElevatorInfo.ListElevatorBeanX> {

    private List<ElevatorInfo.ListElevatorBeanX> mInfoList;
    private Context mContext;

    public ChoiceElevatorAdapter(Context context, List<ElevatorInfo.ListElevatorBeanX> data, int[] layoutIds) {
        super(context, data, layoutIds);
        mContext = context;
        mInfoList = data;
    }

    public void setData(List<ElevatorInfo.ListElevatorBeanX> infoList) {
        if (infoList == null || infoList.size() <= 0) return;
        mInfoList = infoList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = mInfoList.get(position).getViewType();
        return viewType;
    }

    @Override
    public void onBind(RVBaseHolder holder, ElevatorInfo.ListElevatorBeanX listElevatorBeanX, int position) {

        int viewType = listElevatorBeanX.getViewType();
        if (viewType == 1) {
            holder.setText(R.id.choice_elevator_main_tv, listElevatorBeanX.getCountNum() + "台");
        } else {
            holder.setText(R.id.choice_elevator_title_tv, listElevatorBeanX.getTypeName() + "");
            holder.setText(R.id.choice_elevator_number_tv, listElevatorBeanX.getCountNum() + "台");
            GridView choiceItemRv = holder.getView(R.id.choice_item_rv);
            ChoiceElevatorItemAdapter choiceElevatorItemAdapter = new ChoiceElevatorItemAdapter(mContext, listElevatorBeanX.getListElevator(), R.layout.choice_elevator_item);
            choiceItemRv.setAdapter(choiceElevatorItemAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return mInfoList == null ? 0 : mInfoList.size();
    }
}
