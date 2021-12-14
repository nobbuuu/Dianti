package com.qt.dtzf.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.base.baselib.bean.ImgBean;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.PerformImgAdapter;
import com.qt.dtzf.common.ActionCallback;
import com.qt.dtzf.common.DialogActionCallback;
import com.qt.dtzf.common.WaterFallItemDecoration;
import com.qt.dtzf.utils.ToastUtils;

import java.util.List;

public class ReplyDialog extends Dialog {

    private RecyclerView mImgRv;
    public ReplyDialog(@NonNull Context context, DialogActionCallback callback) {
        super(context, R.style.ActionSheetDialogStyle);
        setContentView(R.layout.dialog_reply);
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attr = window.getAttributes();
            if (attr != null) {
                attr.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                attr.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                attr.gravity = Gravity.CENTER;
                window.setAttributes(attr);
            }
        }
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        ImageView cameraIcon = findViewById(R.id.cameraIcon);
        TextView commitTv = findViewById(R.id.commitTv);
        EditText inputEdt = findViewById(R.id.inputEdt);
        mImgRv = findViewById(R.id.imgRv);
        cameraIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callback != null){
                    callback.onAction(1,inputEdt.getText().toString());
                }
            }
        });
        commitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputStr = inputEdt.getText().toString();
                if (inputStr.isEmpty()){
                    ToastUtils.Toast_long("请输入内容");
                    return;
                }
                if (callback != null){
                    callback.onAction(2,inputEdt.getText().toString());
                    dismiss();
                }
            }
        });
    }

    public void  setDialogImg(List<ImgBean> list){
        mImgRv.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL));
        mImgRv.addItemDecoration(new WaterFallItemDecoration(30, 30));
        mImgRv.setAdapter(new PerformImgAdapter(getContext(), list, R.layout.rvitem_onlyimg));
    }
}
