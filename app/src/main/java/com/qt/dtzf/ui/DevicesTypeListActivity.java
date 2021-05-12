package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.DeviceTypeItem;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.DeviceTypesAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DevicesTypeListActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private DeviceTypesAdapter mTypesAdapter;
    private List<DeviceTypeItem> dataList = new ArrayList<>();

    public static void gotoActivity(Activity activity, String taskId) {
        Intent intent = new Intent(activity, DevicesTypeListActivity.class);
        intent.putExtra("taskId", taskId);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_type_list);
        mRecyclerView = findViewById(R.id.device_type_rv);
        String taskId = getIntent().getStringExtra("taskId");
        mTypesAdapter = new DeviceTypesAdapter(this, dataList, R.layout.rvitem_deviceitem,taskId);
        mRecyclerView.setAdapter(mTypesAdapter);
        getData();
    }

    private void getData() {
        Observable<BeanList<DeviceTypeItem>> upLocation = WorkModel.getInstance().getSpecialTypeList();
        showWaitDialog();
        upLocation.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BeanList<DeviceTypeItem>>() {
                    @Override
                    public void onSuccess(BeanList<DeviceTypeItem> data) {
                        dataList.clear();
                        dataList.addAll(data.data);
                        Log.d("aaa","dataList.size() = " + dataList.size());
                        mTypesAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });
    }

}
