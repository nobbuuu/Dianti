package com.base.baselib.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.baselib.R;

/**
 * 图片选择框
 */
public class AlbumDialog extends BaseDialogFragment {
    private AlbumDialogClickListener mAlbumDialogClickListener;

    @Override
    protected String setDialogTag() {
        return "AlbumDialog";
    }

    @Override
    protected Dialog setDialog() {
        return getDialog();
    }

    @Override
    protected void setDialogStyle(Window window) {

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        WindowManager.LayoutParams windowParams = window.getAttributes();
        window.setLayout(width, height);
        window.setAttributes(windowParams);
    }

    @Override
    protected void setStartAndEndAnimation() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.album_dialog, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(true);

        view.findViewById(R.id.album_bg).setOnClickListener(mClickListener);
        view.findViewById(R.id.album_cancel_btn).setOnClickListener(mClickListener);

        view.findViewById(R.id.album_album_btn).setOnClickListener(mClickListener);
        view.findViewById(R.id.album_photograph_btn).setOnClickListener(mClickListener);
    }

    public void addClickListener(AlbumDialogClickListener clickListener) {
        mAlbumDialogClickListener = clickListener;
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.album_bg || id == R.id.album_cancel_btn) {
            }
            if (id == R.id.album_album_btn) {
                //相册
                if (mAlbumDialogClickListener != null) mAlbumDialogClickListener.onClickAlbum();

            }
            if (id == R.id.album_photograph_btn) {
                //拍照
                if (mAlbumDialogClickListener != null) mAlbumDialogClickListener.onClickPhotograph();
            }
            dismiss();
        }
    };

    public interface AlbumDialogClickListener {
        void onClickAlbum();

        void onClickPhotograph();
    }
}
