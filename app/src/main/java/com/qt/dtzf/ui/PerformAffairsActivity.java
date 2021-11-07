package com.qt.dtzf.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.bean.TaskInfo;
import com.base.baselib.view.TitleView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.PerformImgAdapter;
import com.qt.dtzf.common.WaterFallItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.qt.dtzf.ui.TaskMainActivity.ImageRequestCode;

/**
 * Create By s on
 */
public class PerformAffairsActivity extends BaseActivity {

    @BindView(R.id.task_list_title)
    TitleView taskListTitle;
    @BindView(R.id.uploadImgLay)
    CardView uploadImgLay;
    @BindView(R.id.taskTitle)
    TextView taskTitle;
    @BindView(R.id.inputEdt)
    EditText inputEdt;
    @BindView(R.id.imgRv)
    RecyclerView imgRv;
    @BindView(R.id.editLay)
    ConstraintLayout editLay;
    @BindView(R.id.uploadHisRv)
    RecyclerView uploadHisRv;
    private TaskInfo.ListBean mTaskInfo;

    public static void gotoActivity(Activity activity, TaskInfo.ListBean taskInfoBean) {
        Intent intent = new Intent(activity, PerformAffairsActivity.class);
        intent.putExtra("taskInfoBean", taskInfoBean);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_task);
        ButterKnife.bind(this);
        mTaskInfo = (TaskInfo.ListBean) getIntent().getSerializableExtra("taskInfoBean");
        uploadImgLay = findViewById(R.id.uploadImgLay);
        onevent();
    }

    private void onevent() {
        uploadImgLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toImageListForActivity();
            }
        });
    }

    private void toImageListForActivity() {
        Intent intent = new Intent(this, UploadImageActivity.class);
        if (mTaskInfo != null) {
            intent.putExtra("taskId", mTaskInfo.getId());
        }
        /*if (mH5ResultBean != null) {
            intent.putExtra("xcqzImg", mH5ResultBean.getXcqzImg());
        }*/
        startActivityForResult(intent, ImageRequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 117 && data != null) {

            String jsonData = data.getStringExtra("imgList");
            List<ImgBean> mList = new Gson().fromJson(jsonData, new TypeToken<List<ImgBean>>(){}.getType());
            if (mList != null) {
                imgRv.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL));
                imgRv.setAdapter(new PerformImgAdapter(this,mList,R.layout.rvitem_onlyimg));
            }
        }
    }
}
