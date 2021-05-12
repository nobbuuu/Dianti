package com.qt.dtzf.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.base.BaseActivity;
import com.qt.dtzf.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.task_confirm_btn, R.id.task_confirm_lay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.task_confirm_btn:
                break;
            case R.id.task_confirm_lay:
                break;
        }
    }
}
