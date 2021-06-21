package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.EmptyBean;
import com.base.baselib.bean.H5ResultBean;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.bean.TaskInfo;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.AlbumUtils;
import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;
import com.base.baselib.utils.Utils;
import com.base.baselib.view.TitleView;
import com.google.gson.Gson;
import com.qt.dtzf.R;
import com.qt.dtzf.utils.ToastUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.observers.BlockingBaseObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * 任务 开始界面
 */
public class TaskMainActivity extends BaseActivity {

    @BindView(R.id.task_title)
    TitleView taskTitle;
    @BindView(R.id.task_confirm_check_record_tv)
    TextView taskConfirmCheckRecordTv;
    @BindView(R.id.task_confirm_check_record_ll)
    LinearLayout taskConfirmCheckRecordLl;
    @BindView(R.id.task_confirm_doc_tv)
    TextView taskConfirmDocTv;
    @BindView(R.id.task_confirm_doc_ll)
    LinearLayout taskConfirmDocLl;
    @BindView(R.id.task_safe_check_ll)
    LinearLayout taskSafeCheckLl;
    @BindView(R.id.task_item_ll)
    FrameLayout taskItemLl;
    @BindView(R.id.task_problem_tv)
    TextView taskProblemTv;
    @BindView(R.id.task_problem_explain_tv)
    TextView taskProblemExplainTv;
    @BindView(R.id.task_confirm_sc)
    SwitchCompat taskConfirmSc;
    @BindView(R.id.task_confirm_problem_tv)
    TextView taskConfirmProblemTv;
    @BindView(R.id.task_confirm_problem_ll)
    LinearLayout taskConfirmProblemLl;
    @BindView(R.id.task_confirm_big_problem_tv)
    TextView taskConfirmBigProblemTv;
    @BindView(R.id.task_confirm_big_problem_ll)
    LinearLayout taskConfirmBigProblemLl;
    @BindView(R.id.task_confirm_record_tv)
    TextView taskConfirmRecordTv;
    @BindView(R.id.task_confirm_record_ll)
    LinearLayout taskConfirmRecordLl;
    @BindView(R.id.task_confirm_prove_tv)
    TextView taskConfirmProveTv;
    @BindView(R.id.task_confirm_prove_ll)
    LinearLayout taskConfirmProveLl;
    @BindView(R.id.task_confirm_1_fl)
    FrameLayout taskConfirm1Fl;
    @BindView(R.id.task_confirm_btn)
    AppCompatButton taskConfirmBtn;
    private TaskInfo.ListBean mTaskInfo;
    private AlbumUtils mAlbumUtils;

    public static int ImageRequestCode = 10010;
    public static int ImageResultCode = 10011;
    private Gson mGson = new Gson();
    private String mProblem = "1";
    private String image;
    private Activity mActivity;
    private H5ResultBean mH5ResultBean;

    public static void gotoActivity(Activity activity, TaskInfo.ListBean taskInfo) {
        Intent intent = new Intent(activity, TaskMainActivity.class);
        intent.putExtra("taskInfo", taskInfo);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.task_activity);
        ButterKnife.bind(this);
        mAlbumUtils = new AlbumUtils(this, mAlbumListener);
        getTaskInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSpecialH5Page(false);
    }

    private void getTaskInfo() {

        Intent intent = getIntent();
        mTaskInfo = (TaskInfo.ListBean) intent.getSerializableExtra("taskInfo");
        if (mTaskInfo == null) {
            Utils.showToast("参数错误");
            finish();
            return;
        }
        initView();
    }

    AlbumUtils.AlbumListener mAlbumListener = new AlbumUtils.AlbumListener() {
        @Override
        public void onListener(List<ImgBean> list, boolean is) {
            if (list == null || list.size() <= 0) return;
            image = list.get(0).getFilePath();
        }
    };

    private void initView() {
        taskConfirmSc.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }

    private void getSpecialH5Page(boolean isCommit) {
        if (mTaskInfo != null) {
            Observable<Bean<H5ResultBean>> upLocation = WorkModel.getInstance().getSpecialH5Page(mTaskInfo.getId());
            showWaitDialog();
            upLocation.compose(this.bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DefaultObserver<Bean<H5ResultBean>>() {
                        @Override
                        public void onSuccess(Bean<H5ResultBean> data) {
                            mH5ResultBean = data.data;
                            if (mH5ResultBean != null) {
                                String jcjl_status = mH5ResultBean.getJCJL_status();
                                if (jcjl_status != null && jcjl_status.equals("1")) {
                                    taskConfirmCheckRecordTv.setText("已完成");
                                    taskConfirmCheckRecordTv.setTextColor(getResources().getColor(R.color.main_button_bg));
                                } else {
                                    taskConfirmCheckRecordTv.setText("待完成");
                                    taskConfirmCheckRecordTv.setTextColor(getResources().getColor(R.color.text_9_color));
                                }
                                String xzws = mH5ResultBean.getXZWS_status();
                                if (xzws != null && xzws.equals("1")) {
                                    taskConfirmDocTv.setText("已完成");
                                    taskConfirmDocTv.setTextColor(getResources().getColor(R.color.main_button_bg));
                                } else {
                                    taskConfirmDocTv.setText("待完成");
                                    taskConfirmDocTv.setTextColor(getResources().getColor(R.color.text_9_color));
                                }
                                String ybwt_status = mH5ResultBean.getYBWT_status();
                                if (ybwt_status != null && ybwt_status.equals("1")) {
                                    taskConfirmProblemTv.setText("已完成");
                                    taskConfirmProblemTv.setTextColor(getResources().getColor(R.color.main_button_bg));
                                } else {
                                    taskConfirmProblemTv.setText("待完成");
                                    taskConfirmProblemTv.setTextColor(getResources().getColor(R.color.text_9_color));
                                }

                                String xcbl_status = mH5ResultBean.getXCBL_status();
                                if (xcbl_status != null && xcbl_status.equals("1")) {
                                    taskConfirmRecordTv.setText("已完成");
                                    taskConfirmRecordTv.setTextColor(getResources().getColor(R.color.main_button_bg));
                                } else {
                                    taskConfirmRecordTv.setText("待完成");
                                    taskConfirmRecordTv.setTextColor(getResources().getColor(R.color.text_9_color));
                                }
                                String xcqz_status = mH5ResultBean.getXCQZ_status();
                                if (xcqz_status != null && xcqz_status.equals("1")) {
                                    taskConfirmProveTv.setText("已完成");
                                    taskConfirmProveTv.setTextColor(getResources().getColor(R.color.main_button_bg));
                                } else {
                                    taskConfirmProveTv.setText("待完成");
                                    taskConfirmProveTv.setTextColor(getResources().getColor(R.color.text_9_color));
                                }

                                if (isCommit) {
                                    if ((jcjl_status.equals("1") || xzws.equals("1"))) {
                                        acceptTask();
                                    } else {
                                        ToastUtils.Toast_long("请先提交各项检查结果");
                                    }
                                }
                            }
                        }

                        @Override
                        public void onStop() {
                            super.onStop();
                            hideWaitDialog();
                        }
                    });
        }
    }


    private void getImageListForActivity() {
        Intent intent = new Intent(TaskMainActivity.this, UploadImageActivity.class);
        if (mTaskInfo != null) {
            intent.putExtra("taskId", mTaskInfo.getId());
        }
        if (mH5ResultBean != null) {
            intent.putExtra("xcqzImg", mH5ResultBean.getXcqzImg());
        }
        startActivityForResult(intent, ImageRequestCode);
    }

    /**
     * 跳转网页 并设置 延时
     *
     * @param url
     */
    private void gotoWebActivity(String url) {
        showWaitDialog();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(url);
            }
        }).delay(500, TimeUnit.MILLISECONDS).subscribe(new BlockingBaseObserver<String>() {
            @Override
            public void onNext(String s) {
                hideWaitDialog();
                /*List<ElevatorInfo> tagList = mTaskInfo.tagList;
                String elevators = "";
                for (int i = 0; i < tagList.size(); i++) {
                    String number = tagList.get(i).number;
                    elevators = elevators + "," + number;
                }
                elevators = elevators.substring(1, elevators.length());
                String key = SpUtils.getString(SpUtilsConstant.apiKey);
                String url = s + "?id=" + mTaskInfo.id + "&category_type=" +
                        mTaskInfo.category_type + "&apikey=" + key + "&elevators=" + elevators;
                WebDetailsActivity.gotoActivity(TaskMainActivity.this, url);*/
            }

            @Override
            public void onError(Throwable e) {
                hideWaitDialog();
            }
        });
    }

    CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                taskConfirmProblemLl.setVisibility(View.VISIBLE);
                taskConfirmBigProblemLl.setVisibility(View.VISIBLE);
                mProblem = "1";
            } else {
                taskConfirmProblemLl.setVisibility(View.GONE);
                taskConfirmBigProblemLl.setVisibility(View.GONE);
                mProblem = "2";
            }
        }
    };


    private void acceptTask() {

        String id = mTaskInfo.getId();
        Observable<Bean<EmptyBean>> task = WorkModel.getInstance().saveAllInfo(id);
        showWaitDialog();
        task.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<EmptyBean>>() {
                    @Override
                    public void onSuccess(Bean<EmptyBean> stringBean) {
                        Utils.showToast("提交成功");
                        finish();
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mAlbumUtils != null) mAlbumUtils.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageRequestCode && resultCode == ImageResultCode) {
            image = data.getStringExtra("image");
        }

    }

    @OnClick({R.id.task_confirm_check_record_ll, R.id.task_confirm_doc_ll, R.id.task_confirm_problem_ll,
            R.id.task_confirm_big_problem_ll, R.id.task_confirm_record_ll, R.id.task_confirm_prove_ll, R.id.task_confirm_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.task_confirm_check_record_ll:
//                    gotoWebActivity(mTaskInfo.url4);
//                    gotoWebActivity(mTaskInfo.specialurl);
                if (mTaskInfo != null) {
                    if (mH5ResultBean != null) {
                        String jcjl_status = mH5ResultBean.getJCJL_status();
                        String taskUrl = mH5ResultBean.getJCJL();
                        if (jcjl_status != null && jcjl_status.equals("1")) {//已完成
                            if (!TextUtils.isEmpty(taskUrl)) {
                                taskUrl = taskUrl + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + mTaskInfo.getId() +
                                        "&otherId=" + SpUtils.getInt(SpUtilsConstant.otherId);
                                if (mH5ResultBean.getDicName() != null && mH5ResultBean.getPointType() != null) {
                                    taskUrl = taskUrl + "&dicName=" + mH5ResultBean.getDicName() + "&pointType=" + mH5ResultBean.getPointType();
                                }
                                toWebdetailActivity(taskUrl);
                            }
                        } else {
                            DevicesTypeListActivity.gotoActivity(mActivity, mTaskInfo.getId());
                        }
                    }
                }
                break;
            case R.id.task_confirm_doc_ll:
                //行政执法规范性文书
                if (mH5ResultBean != null) {
                    String taskUrl = mH5ResultBean.getXZWS() + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + mTaskInfo.getId() +
                            "&otherId=" + SpUtils.getInt(SpUtilsConstant.otherId) + "&isClient=" + "0";
                    toWebdetailActivity(taskUrl);
                }
                break;
            case R.id.task_confirm_problem_ll:
                //一般问题,限期整改
                if (mH5ResultBean != null) {
                    String taskUrl = mH5ResultBean.getYBWT() + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + mTaskInfo.getId() +
                            "&otherId=" + SpUtils.getInt(SpUtilsConstant.otherId) + "&isCase=" + "0";
                    toWebdetailActivity(taskUrl);
                }
                break;
            case R.id.task_confirm_big_problem_ll:
                //重大问题
                if (mH5ResultBean != null) {
                    String taskUrl = mH5ResultBean.getZDWT() + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + mTaskInfo.getId() +
                            "&otherId=" + SpUtils.getInt(SpUtilsConstant.otherId) + "&isCase=" + "1";
                    toWebdetailActivity(taskUrl);
                }
                break;
            case R.id.task_confirm_record_ll:
                //现场笔录
                if (mH5ResultBean != null) {
                    String taskUrl = mH5ResultBean.getXCBL() + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + mTaskInfo.getId() +
                            "&otherId=" + SpUtils.getInt(SpUtilsConstant.otherId) + "&isClient=" + "1";
                    toWebdetailActivity(taskUrl);
                }
                break;
            case R.id.task_confirm_prove_ll:
                //现场取证
                getImageListForActivity();
                break;
            case R.id.task_confirm_btn:
                //完成
                getSpecialH5Page(true);
                break;

        }
    }

    private void toWebdetailActivity(String taskUrl) {
        if (mTaskInfo != null) {
            WebDetailsActivity.gotoActivity(TaskMainActivity.this, taskUrl, mTaskInfo);
        }
    }
}
