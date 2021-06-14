package com.qt.dtzf.adapter;

import android.content.Context;
import android.view.View;

import com.base.baselib.bean.DeviceTypeItem;
import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;
import com.qt.dtzf.R;
import com.qt.dtzf.ui.ChoiceElevatorActivity;
import com.qt.dtzf.ui.WebDetailsActivity;

import java.util.List;

public class DeviceTypesAdapter extends RVBaseAdapter<DeviceTypeItem> {

    private String mTaskId;

    public DeviceTypesAdapter(Context context, List<DeviceTypeItem> data, int layoutId, String taskId) {
        super(context, data, layoutId);
        mTaskId = taskId;
    }

    @Override
    public void onBind(RVBaseHolder holder, DeviceTypeItem deviceTypeItem, int position) {
        holder.setText(R.id.device_type_name, deviceTypeItem.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    ChoiceElevatorActivity.gotoActivity(context, mTaskId);
                } else {
                    String taskUrl = deviceTypeItem.getTaskUrl() + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + mTaskId
                            + "&otherId=" + SpUtils.getInt(SpUtilsConstant.otherId) + "&dicName=" + deviceTypeItem.getDicName() + "&&pointType=" + deviceTypeItem.getPointType();
                    WebDetailsActivity.gotoActivity(context, taskUrl);
                }
            }
        });
    }
}
