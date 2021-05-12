package com.qt.dtzf.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.bean.ImgBean;
import com.base.baselib.glide.GlideUtils;
import com.base.baselib.view.PhotoViewActivity;
import com.qt.dtzf.R;

import java.util.ArrayList;
import java.util.List;

public class SafeCheckItemAdapter extends RecyclerView.Adapter<SafeCheckItemAdapter.VH> {

    private Context mContext;
    private ImageListener mImageListener;
    private boolean mIsShowTag = true;
    private boolean mIsCheckBasis;
    private List<ImgBean> mList = new ArrayList<>();
    public String mTag = "tag";
    public int mMaxSize = 3;

    public SafeCheckItemAdapter(Context context) {
        mContext = context;
        mList.add(new ImgBean(mTag));
    }

    public SafeCheckItemAdapter(Context context, boolean showTag) {
        mContext = context;
        mIsShowTag = showTag;
        mList.add(new ImgBean(mTag));
    }

    public SafeCheckItemAdapter(Context context, int maxSize) {
        mContext = context;
        mMaxSize = maxSize;
        mList.add(new ImgBean(mTag));
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.safe_check_image_item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ImgBean item = mList.get(position);

        if (mTag.equals(item.getFilePath())) {
            holder.mAddImageIv.setVisibility(View.VISIBLE);
            holder.mImageIv.setVisibility(View.GONE);
            holder.mImageDeleteIv.setVisibility(View.GONE);

        } else {
            holder.mAddImageIv.setVisibility(View.GONE);
            holder.mImageIv.setVisibility(View.VISIBLE);
            holder.mImageDeleteIv.setVisibility(View.VISIBLE);
            GlideUtils.loadImage(mContext, item.getFilePath(), holder.mImageIv);
        }

        if (!mIsShowTag) {
            holder.mAddImageIv.setVisibility(View.GONE);
            holder.mImageDeleteIv.setVisibility(View.GONE);
        }
        if (mIsCheckBasis){
            holder.mImageDeleteIv.setVisibility(View.GONE);
        }
        holder.mAddImageIv.setOnClickListener(v -> {
            if (mImageListener != null) mImageListener.addImageListener();
        });
        holder.mImageDeleteIv.setOnClickListener(v -> {
            deleteImage(position);
        });
        holder.mImageIv.setOnClickListener((v) -> {
            //点击跳转图片预览
            PhotoViewActivity.gotoActivity(mContext, item.getFilePath());
        });
    }

    private void deleteImage(int position) {
        mList.remove(position);
        mImageListener.deleteImageListener();
        int size = mList.size();
        if (!mTag.equals(mList.get(size - 1).getFilePath())) mList.add(new ImgBean(mTag));
        notifyDataSetChanged();

    }

    public void addImage(String image) {
        if (mList.size() > mMaxSize) return;
        int size = mList.size();
        mList.remove(size - 1);
        mList.add(new ImgBean(image));
        mList.add(new ImgBean(mTag));
        if (mList.size() > mMaxSize) mList = mList.subList(0, mMaxSize);
        notifyDataSetChanged();
    }

    public void addImageList(List<ImgBean> image) {
        if (image == null) return;
        int size = mList.size();
        mList.remove(size - 1);
        mList.addAll(image);
        mList.add(new ImgBean(mTag));
        if (mList.size() > mMaxSize) mList = mList.subList(0, mMaxSize);
        notifyDataSetChanged();
    }

    public void addImageList(List<ImgBean> image, boolean isCamera,boolean isCheckBasis) {
        if (image == null) return;
        mIsCheckBasis = isCheckBasis;
        int size = mList.size();
        mList.remove(size - 1);
        if (isCamera) {
            mList.addAll(0, image);
        } else {
            mList.addAll(image);
        }
        if (!isCheckBasis){
            mList.add(new ImgBean(mTag));
        }
        if (mList.size() > mMaxSize) mList = mList.subList(0, mMaxSize);
        notifyDataSetChanged();
    }

    public void addImageNoTagList(List<ImgBean> image) {
        mList.addAll(image);
        notifyDataSetChanged();
    }

    public List<ImgBean> getImageList() {
        int size = mList.size();
        if (mTag.equals(mList.get(size - 1).getFilePath())) return mList.subList(0, size - 1);
        return mList;
    }

    public void addImageListener(ImageListener listener) {
        mImageListener = listener;
    }

    public interface ImageListener {
        void addImageListener();
        void deleteImageListener();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        public final ImageView mAddImageIv;
        public final ImageView mImageIv;
        public final TextView mImageDeleteIv;

        public VH(@NonNull View itemView) {
            super(itemView);
            mAddImageIv = itemView.findViewById(R.id.safe_add_image_iv);
            mImageIv = itemView.findViewById(R.id.safe_image_iv);
            mImageDeleteIv = itemView.findViewById(R.id.safe_image_delete_tv);
        }
    }
}
