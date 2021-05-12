package com.qt.dtzf.adapter;

import android.content.Context;
import android.view.View;

import com.base.baselib.bean.DeviceTypeItem;
import com.qt.dtzf.R;
import com.qt.dtzf.ui.ChoiceElevatorActivity;

import java.util.List;

public class DeviceTypesAdapter extends RVBaseAdapter<DeviceTypeItem> {

    private String mTaskId;
    public DeviceTypesAdapter(Context context, List<DeviceTypeItem> data, int layoutId,String taskId) {
        super(context, data, layoutId);
        mTaskId = taskId;
    }

    @Override
    public void onBind(RVBaseHolder holder, DeviceTypeItem deviceTypeItem, int position) {
        holder.setText(R.id.device_type_name,deviceTypeItem.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0){
                    ChoiceElevatorActivity.gotoActivity(context,mTaskId);
                }
            }
        });
    }
}
