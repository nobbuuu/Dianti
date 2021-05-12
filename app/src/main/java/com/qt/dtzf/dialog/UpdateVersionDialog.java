package com.qt.dtzf.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.qt.dtzf.R;

public class UpdateVersionDialog extends Dialog {
    public UpdateVersionDialog(@NonNull Context context) {
        super(context, R.style.ActionSheetDialogStyle);
        setContentView(R.layout.dialog_updateversion);
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
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        TextView cancle = findViewById(R.id.action_cancle);
        TextView sure = findViewById(R.id.action_sure);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

            }
        });
    }

    public UpdateVersionDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }


}
