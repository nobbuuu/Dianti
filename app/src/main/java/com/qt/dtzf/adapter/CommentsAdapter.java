package com.qt.dtzf.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.base.baselib.bean.CommentsBean;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.glide.GlideUtils;
import com.qt.dtzf.R;
import com.qt.dtzf.common.DialogActionCallback;
import com.qt.dtzf.common.TActionCallback;
import com.qt.dtzf.dialog.ReplyDialog;

import java.util.List;

public class CommentsAdapter extends RVBaseAdapter<CommentsBean> {

    private Context mContext;
    private TActionCallback<CommentsBean> mCallBack;
    private ReplyDialog mDialog;
    private CommentsBean tempItem;

    public CommentsAdapter(Context context, List<CommentsBean> data, int layoutIds) {
        super(context, data, layoutIds);
        mContext = context;
        mDialog = new ReplyDialog(mContext, new DialogActionCallback() {
            @Override
            public void onAction(int tag,String inputStr) {
                if (mCallBack != null && tempItem != null){
                    mCallBack.onAction(tag,tempItem,inputStr);
                }
            }
        });
    }

    public void setData(List<CommentsBean> infoList) {
        if (infoList == null || infoList.size() <= 0) return;
        notifyDataSetChanged();
    }

    @Override
    public void onBind(RVBaseHolder holder, CommentsBean item, int position) {
        holder.setText(R.id.userName,item.getUserName());
        holder.setText(R.id.phoneTv,item.getPhone());
        ImageView avatarIv = holder.getView(R.id.avatarIv);
        GlideUtils.loadImage(mContext,item.getAvatar(),avatarIv);
        holder.setText(R.id.comentsTv,item.getContent());
        holder.setOnClickListener(R.id.replyTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempItem = item;
                mDialog.show();
            }
        });
    }

    public void  setActionCallBack(TActionCallback<CommentsBean> callBack){
        mCallBack = callBack;
    }

    public void  setDialogImg(List<ImgBean> list){
        mDialog.setDialogImg(list);
    }
}
