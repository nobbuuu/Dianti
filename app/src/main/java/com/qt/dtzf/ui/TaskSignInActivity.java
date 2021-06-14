package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.base.baselib.BaseAPP;
import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.AddressInfo;
import com.base.baselib.bean.SignBean;
import com.base.baselib.bean.TaskInfo;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.LogUtils;
import com.base.baselib.utils.MapHelper;
import com.base.baselib.utils.PermissionUtils;
import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;
import com.base.baselib.utils.Utils;
import com.qt.dtzf.R;
import com.qt.dtzf.utils.ToastUtils;

import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 签到界面
 */
public class TaskSignInActivity extends BaseActivity {

    private TextView mAddressTagTv;
    private TextView mAddressTv;
    private TextView mOrderAddressTv;

    private double mLatitude = 0;
    private double mLongitude = 0;

    private double mLatitudeText = 29.515467;
    private double mLongitudeText = 106.521165;
    private int mDistance;
    private int mTagDistance = 50;
    private TaskInfo.ListBean mTaskInfo;

    public static void gotoActivity(Activity activity, TaskInfo.ListBean taskInfoBean) {
        Intent intent = new Intent(activity, TaskSignInActivity.class);
        intent.putExtra("taskInfoBean", taskInfoBean);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);
        getTaskInfo();
        mPermissionUtils = new PermissionUtils(this, mPermissionCallBack);
    }

    private void getTaskInfo() {
        Intent intent = getIntent();
        mTaskInfo = (TaskInfo.ListBean) intent.getSerializableExtra("taskInfoBean");
        if (mTaskInfo == null) {
            Utils.showToast("参数错误");
            finish();
            return;
        }
        initView();
        setData();
    }


    @Override
    protected void onStart() {
        super.onStart();
        mPermissionUtils.checkLocationPermission();
    }

    PermissionUtils.PermissionCallBack mPermissionCallBack = new PermissionUtils.PermissionCallBack() {
        @Override
        public void onBack(int code, boolean b) {
            if (code != PermissionUtils.mRequestCodeLocation) return;


            if (!b) {
                mPermissionUtils.checkSelfPermission();
                return;
            }

            getLocation();
        }
    };

    private void initView() {

        mAddressTagTv = findViewById(R.id.sin_in_address_tag_tv);
        mAddressTv = findViewById(R.id.sin_in_user_address_tv);
        mOrderAddressTv = findViewById(R.id.sin_in_user_order_address_tv);

        findViewById(R.id.sign_in_btn).setOnClickListener(v -> {
            upLocation();
        });
    }


    private void setData() {
        mOrderAddressTv.setText(mTaskInfo.getDataAddress());
        if (TextUtils.isEmpty(mTaskInfo.getDataLongitude()) || TextUtils.isEmpty(mTaskInfo.getDataLatitude())) {
            ToastUtils.Toast_long("任务位置信息有误");
            finish();
            return;
        }
        mLongitudeText = Double.valueOf(mTaskInfo.getDataLongitude());
        mLatitudeText = Double.valueOf(mTaskInfo.getDataLatitude());
    }

    private void getLocation() {
        BaseAPP.getLocationUtils().addLocationListener(mLocationListener);
        BaseAPP.getLocationUtils().startLocation();
    }

    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation.getErrorCode() == 0) {
                mLatitude = aMapLocation.getLatitude();
                mLongitude = aMapLocation.getLongitude();
                mDistance = (int) (MapHelper.distance(mLongitude, mLatitude, mLongitudeText, mLatitudeText) * 1000);
                mAddressTagTv.setText("距离目标位置还有" + mDistance + "米");

                String address = aMapLocation.getAddress();
                LogUtils.e("address = " + address);
                mAddressTv.setText(address);
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());

                mAddressTv.setText("定位失败" + aMapLocation.getErrorInfo());
            }
        }
    };

    public void upLocation() {

        if (mLatitude == 0 || mLongitude == 0) {
            Utils.showToast("手机定位中请稍后...");
            return;
        }
//        if (mDistance > mTagDistance) {
//            Utils.showToast("未到目标位置附件...");
//            return;
//        }

        String address = mAddressTv.getText().toString().trim();

        AddressInfo addressInfo = new AddressInfo();
        addressInfo.address = address;
        addressInfo.latitude = "" + mLatitude;
        addressInfo.longitude = "" + mLongitude;
        upData(addressInfo);
    }


//    private void upData(AddressInfo addressInfo) {
//        String region2 = addressInfo.longitude + "," + addressInfo.latitude;
//        Observable<Bean<String>> upLocation = WorkModel.getInstance().upLocation("0", addressInfo.address, region2);
//        showWaitDialog();
//        upLocation.compose(this.bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DefaultObserver<Bean<String>>() {
//                    @Override
//                    public void onSuccess(Bean<String> stringBean) {
//                        mTaskInfo.tagAddress = addressInfo.address;
//                        mTaskInfo.tagLatitude = addressInfo.latitude;
//                        mTaskInfo.tagLongitude = addressInfo.longitude;
//                        ChoiceElevatorActivity.gotoActivity(TaskSignInActivity.this, mTaskInfo);
//                    }
//
//                    @Override
//                    public void onStop() {
//                        super.onStop();
//                        hideWaitDialog();
//                    }
//                });
//    }

    private void upData(AddressInfo addressInfo) {
        Observable<Bean<SignBean>> upLocation = WorkModel.getInstance().userAutograph(mTaskInfo.getId(), addressInfo.longitude, addressInfo.latitude, addressInfo.address,
                (int) System.currentTimeMillis() / 1000, mTaskInfo.getTaskId());
        upLocation.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<SignBean>>() {
                    @Override
                    public void onSuccess(Bean<SignBean> dataBean) {
                        if (dataBean.data != null) {
                            int categoryType = dataBean.data.getCategoryType();
                            if (categoryType == 2 || categoryType == 4) {
                                if (dataBean.data.getQualityType() == 2) {//复检任务
                                    String taskUrl = dataBean.data.getTaskUrl();
                                    if (!TextUtils.isEmpty(taskUrl)) {
                                        //拼接url
                                        taskUrl = taskUrl + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + dataBean.data.getId() + "&dataId=" + dataBean.data.getDataId()
                                                + "&otherId=" + dataBean.data.getOtherId() + "&dicName=" + dataBean.data.getDicName() + "&qualityType=" + dataBean.data.getQualityType()
                                                + "&pointType=" + dataBean.data.getPointType();

                                        WebDetailsActivity.gotoActivity(TaskSignInActivity.this, taskUrl);
                                    }
                                } else {
                                    if (mTaskInfo != null) {
                                        ChoiceTaskListActivity.start(TaskSignInActivity.this, mTaskInfo);
                                    }
                                }

                            } else if (categoryType == 1) {
                                if (dataBean.data.getQualityType() == 1) {
                                    TaskMainActivity.gotoActivity(TaskSignInActivity.this, mTaskInfo);
                                } else {
                                    String taskUrl = dataBean.data.getTaskUrl();
                                    if (!TextUtils.isEmpty(taskUrl)) {
                                        //拼接url
                                        taskUrl = taskUrl + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + dataBean.data.getId() + "&dataId=" + dataBean.data.getDataId()
                                                + "&otherId=" + dataBean.data.getOtherId() + "&dicName=" + dataBean.data.getDicName() + "&qualityType=" + dataBean.data.getQualityType()
                                                + "&isView=0" + "&type=0" + "&pointType=" + dataBean.data.getPointType() + "&dickName=" + dataBean.data.getDicName();
                                        WebDetailsActivity.gotoActivity(TaskSignInActivity.this, taskUrl);
                                    }
                                }
                            } else {//3
                                String taskUrl = dataBean.data.getTaskUrl();
                                if (!TextUtils.isEmpty(taskUrl)) {
                                    //拼接url
                                    taskUrl = taskUrl + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + dataBean.data.getId() + "&dataId=" + dataBean.data.getDataId()
                                            + "&otherId=" + dataBean.data.getOtherId() + "&dicName=" + dataBean.data.getDicName() + "&qualityType=" + dataBean.data.getQualityType()
                                            + "&isView=0" + "&type=0" + "&pointType=" + dataBean.data.getPointType() + "&dickName=" + dataBean.data.getDicName();
                                    WebDetailsActivity.gotoActivity(TaskSignInActivity.this, taskUrl);
                                }
                            }
                        }
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        BaseAPP.getLocationUtils().stopLocation();
    }


}
