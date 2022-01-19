package com.qt.dtzf.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.EmptyBean;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.bean.SignBean;
import com.base.baselib.bean.TaskInfo;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.AlbumUtils;
import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;
import com.base.baselib.utils.Utils;
import com.qt.dtzf.R;
import com.base.baselib.bean.ChoiceTaskListBean;
import com.qt.dtzf.adapter.ChoiceTaskListAdapter;
import com.qt.dtzf.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.qt.dtzf.ui.TaskMainActivity.ImageRequestCode;
import static com.qt.dtzf.ui.TaskMainActivity.ImageResultCode;

public class ChoiceTaskListActivity extends BaseActivity {
    @BindView(R.id.task_confirm_btn)
    AppCompatButton taskConfirmBtn;
    @BindView(R.id.listtitle_lay)
    LinearLayout listtitleLay;
    @BindView(R.id.tasklist_rv)
    RecyclerView tasklistRv;
    @BindView(R.id.task_confirm_prove_tv)
    TextView taskConfirmProveTv;
    @BindView(R.id.task_confirm_lay)
    LinearLayout taskConfirmLay;
    private TaskInfo.ListBean mTaskInfo;
    private String xcqzImg;
    private AlbumUtils mAlbumUtils;
    private boolean isDone;

    public static void start(Context context, TaskInfo.ListBean taskInfoBean) {
        Intent intent = new Intent(context, ChoiceTaskListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("taskInfoBean", taskInfoBean);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        ButterKnife.bind(this);
        mTaskInfo = (TaskInfo.ListBean) getIntent().getSerializableExtra("taskInfoBean");
        if (mTaskInfo == null) {
            ToastUtils.Toast_long("数据异常，请重试");
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mTaskInfo == null) {
            ToastUtils.Toast_long("数据异常，请重试");
            return;
        }
        int type = mTaskInfo.getCategoryType() / 2;
        if (mTaskInfo.getCategoryType() == 5) {
            type = 3;
        }
        Observable<Bean<ChoiceTaskListBean>> pwd = WorkModel.getInstance().getPointByType(mTaskInfo.getId(), type);
        showWaitDialog();
        pwd.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<ChoiceTaskListBean>>() {
                    @Override
                    public void onSuccess(Bean<ChoiceTaskListBean> dataBean) {
                        List<ChoiceTaskListBean.ListBean> dataList = dataBean.data.getList();
                        xcqzImg = dataBean.data.getXcqzImg();
                        if (dataList != null && dataList.size() > 0) {
                            ChoiceTaskListAdapter adapter = new ChoiceTaskListAdapter(ChoiceTaskListActivity.this, dataList, R.layout.rvitem_deviceitem);
                            tasklistRv.setAdapter(adapter);
                            for (ChoiceTaskListBean.ListBean bean : dataList) {
                                if (bean.getStatus() == 1) {
                                    isDone = true;
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

    private void checkComplete() {
        Observable<Bean<EmptyBean>> request = null;
        switch (mTaskInfo.getCategoryType()) {
            case 2:
                request = WorkModel.getInstance().saveFoodAllInfo(mTaskInfo.getId());
                break;
            case 4:
                request = WorkModel.getInstance().saveDrugAllInfo(mTaskInfo.getId());
                break;
            case 5:
                request = WorkModel.getInstance().saveSpecialAllInfo(mTaskInfo.getId());
                break;
        }
        showWaitDialog();
        request.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<EmptyBean>>() {
                    @Override
                    public void onSuccess(Bean<EmptyBean> dataBean) {
                           /* String taskUrl = mTaskInfo.getTaskUrl();
                            int otherId = SpUtils.getInt(SpUtilsConstant.otherId);
                            if (!TextUtils.isEmpty(taskUrl)) {
                                //拼接url
                                taskUrl = taskUrl + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + mTaskInfo.getId() + "&dataId=" + mTaskInfo.getDataId()
                                        + "&otherId=" + otherId + "&dicName=" + mTaskInfo.getDicName() + "&qualityType=" + mTaskInfo.getQualityType();

                                WebDetailsActivity.gotoActivity(mContext, taskUrl);
                            }*/
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

    @OnClick({R.id.task_confirm_btn, R.id.task_confirm_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.task_confirm_btn:
                int categoryType = mTaskInfo.getCategoryType();
                int otherId = SpUtils.getInt(SpUtilsConstant.otherId);
                if (categoryType == 2 || categoryType == 4 || categoryType == 5) {
                    if (isDone) {
                        checkComplete();
                    } else {
                        ToastUtils.Toast_long("检查要点表未填写");
                    }
                } else if (categoryType == 1) {
                    if (mTaskInfo.getQualityType() == 1) {
                        TaskMainActivity.gotoActivity(mContext, mTaskInfo);
                    } else {
                        String taskUrl = mTaskInfo.getTaskUrl();
                        if (!TextUtils.isEmpty(taskUrl)) {
                            //拼接url
                            taskUrl = taskUrl + "?token=" + SpUtils.getString(SpUtilsConstant.apiKey) + "&id=" + mTaskInfo.getId() + "&dataId=" + mTaskInfo.getDataId()
                                    + "&otherId=" + otherId + "&dicName=" + mTaskInfo.getDicName() + "&qualityType=" + mTaskInfo.getQualityType()
                                    + "&isView=0" + "&type=0";
                            WebDetailsActivity.gotoActivity(mContext, taskUrl);
                        }
                    }
                }
                break;
            case R.id.task_confirm_lay:
                getImageListForActivity();
                break;
        }
    }

    private void getImageListForActivity() {
        Intent intent = new Intent(mContext, UploadImageActivity.class);
        if (mTaskInfo != null) {
            intent.putExtra("taskId", mTaskInfo.getId());
        }
        if (!TextUtils.isEmpty(xcqzImg)) {
            intent.putExtra("xcqzImg", xcqzImg);
        }
        startActivityForResult(intent, ImageRequestCode);
    }

    private String image;
    AlbumUtils.AlbumListener mAlbumListener = new AlbumUtils.AlbumListener() {
        @Override
        public void onListener(List<ImgBean> list, boolean is) {
            if (list == null || list.size() <= 0) return;
            image = list.get(0).getFilePath();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mAlbumUtils != null) mAlbumUtils.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageRequestCode && resultCode == ImageResultCode) {
            image = data.getStringExtra("image");
        }

    }
}
