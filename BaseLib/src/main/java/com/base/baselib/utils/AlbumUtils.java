package com.base.baselib.utils;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.dialog.AlbumDialog;
import com.base.baselib.view.AlbumActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.ComponentCallbacks2.TRIM_MEMORY_BACKGROUND;

/**
 * 拍照或者选择相册工具
 * <p>
 * 回调
 * 显示弹框
 */
public class AlbumUtils {

    private AlbumDialog mDialog;
    private String mImagePath;
    private BaseActivity mActivity;
    private static final int Album_code = 10010;
    private static final int Camera_code = 10086;
    private static final int AlbumList_code = 10011;
    public static final String ImageList = "imageList";

    private AlbumListener mAlbumListener;
    private int mMaxSize = 0;

    public AlbumUtils(BaseActivity activity, AlbumListener listener) {
        mActivity = activity;
        mDialog = new AlbumDialog();
        mAlbumListener = listener;
    }

    public void showDialog(FragmentManager fm) {
        showDialog(fm, 0);
    }

    public void showDialog(FragmentManager fm, int maxSize) {
        if (mDialog == null) mDialog = new AlbumDialog();
        mDialog.showDialog(fm);
        mMaxSize = maxSize;
        mDialog.addClickListener(mClickListener);
    }

    AlbumDialog.AlbumDialogClickListener mClickListener = new AlbumDialog.AlbumDialogClickListener() {
        @Override
        public void onClickAlbum() {
            openAlbum();
        }

        @Override
        public void onClickPhotograph() {
            openPhotograph();
        }
    };

    /**
     * 打开相机
     */
    private void openPhotograph() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mImagePath = FileUtils.getImageFilePath();
        FileUtils.checkOrCreateDirectory(mImagePath);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mImagePath)));
        mActivity.startActivityForResult(intent, Camera_code);
    }

    /**
     * 打开相册
     */
    private void openAlbum() {
        //打开系统的相册
        if (mMaxSize == 0) {
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            mActivity.startActivityForResult(intent, Album_code);
            return;
        }
        //打开自定义的相册
        AlbumActivity.gotoActivity(mActivity, AlbumList_code, mMaxSize);

    }


    /**
     * 结果回掉
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (mAlbumListener == null) return;
        if (resultCode != RESULT_OK) return;
        List<ImgBean> list = new ArrayList<>();
         boolean isCamera = false;
        if (requestCode == AlbumList_code && data != null) {
            //多选图片
            list = (List<ImgBean>) data.getSerializableExtra(ImageList);
        }

        ImgBean bean = new ImgBean();
        if (requestCode == Album_code && data != null) {
            //单选图片
            mImagePath = FileUtils.getImageAbsolutePath(mActivity, data.getData());
            bean.setFilePath(mImagePath);
            list.add(bean);

        }
        if (requestCode == Camera_code) {
            //拍照图片
            bean.setFilePath(mImagePath);
            list.add(bean);
            isCamera = true;
//            list.add(mImagePath);
        }
        mAlbumListener.onListener(list,isCamera);
    }


    public interface AlbumListener {
        void onListener(List<ImgBean> list, boolean isCamera);
    }

}
