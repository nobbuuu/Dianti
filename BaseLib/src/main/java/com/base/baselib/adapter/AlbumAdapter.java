package com.base.baselib.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.R;
import com.base.baselib.bean.AppImage;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.glide.GlideUtils;
import com.base.baselib.utils.LogUtils;
import com.base.baselib.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.VH> {

    private Context mContext;
    private List<AppImage> mImageList;
    private List<AppImage> mImageTagList = new ArrayList<>();

    private int mMaxSize;
    private SelectedListener mSelectedListener;

    public AlbumAdapter(Context context, int maxSize, SelectedListener selectedListener) {
        mContext = context;
        if (maxSize < 0) maxSize = 3;
        mMaxSize = maxSize;
        mSelectedListener = selectedListener;
    }

    public void setData(List<AppImage> list) {
        if (list == null || list.size() <= 0) return;
        if (mImageList == null) mImageList = new ArrayList<>();
        mImageList.clear();
        mImageList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.audio_image_item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        AppImage item = mImageList.get(position);
        LogUtils.e("fileName = " + item.fileName);
        GlideUtils.loadImage(mContext, item.fileName, holder.mImageIv);

        holder.mImageIv.setOnClickListener((v) -> {

            int tag = -1;

            for (int i = 0; i < mImageTagList.size(); i++) {
                AppImage imageBean = mImageTagList.get(i);
                if (TextUtils.equals(item.fileName, imageBean.fileName)) {
                    tag = i;
                    break;
                }
            }

            if (tag != -1) {
                mImageTagList.remove(tag);
                holder.mImageCb.setChecked(false);
                if (mSelectedListener != null) mSelectedListener.getSelectedMap(mImageTagList);
                return;
            }
            int size = mImageTagList.size();
            if (size >= mMaxSize) {
                Utils.showToast("最多能选择" + mMaxSize + "张");
                return;
            }
            holder.mImageCb.setChecked(true);
            mImageTagList.add(item);
            if (mSelectedListener != null) mSelectedListener.getSelectedMap(mImageTagList);
        });
    }


    public List<ImgBean> getSelectedList() {
        List<ImgBean> list = new ArrayList<>();
        for (int i = 0; i < mImageTagList.size(); i++) {
            String name = mImageTagList.get(i).fileName;
            list.add(new ImgBean(name));
        }
//        List<String> list = new ArrayList<>();
//        for (String key : mMap.keySet()) {
//            String fileName = mMap.get(key).fileName;
//            list.add(fileName);
//        }
        return list;
    }

    public interface SelectedListener {
        void getSelectedMap(List<AppImage> map);
    }

    @Override
    public int getItemCount() {
        return mImageList == null ? 0 : mImageList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        public ImageView mImageIv;
        public CheckBox mImageCb;

        public VH(@NonNull View itemView) {
            super(itemView);
            mImageIv = itemView.findViewById(R.id.audio_image_iv);
            mImageCb = itemView.findViewById(R.id.audio_image_cb);
        }
    }
}
