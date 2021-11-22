package com.qt.dtzf.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.base.baselib.bean.CommentsBean;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.glide.GlideUtils;
import com.qt.dtzf.R;
import com.qt.dtzf.common.DialogActionCallback;
import com.qt.dtzf.common.TActionCallback;
import com.qt.dtzf.common.WaterFallItemDecoration;
import com.qt.dtzf.dialog.ReplyDialog;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RVBaseAdapter<CommentsBean> {

    private Context mContext;
    private TActionCallback<CommentsBean> mCallBack;
    private ReplyDialog mDialog;
    private CommentsBean tempItem;
    private CommentsBean parentBean;

    public CommentsAdapter(Context context, List<CommentsBean> data, int layoutIds, CommentsBean parentBean) {
        super(context, data, layoutIds);
        mContext = context;
        this.parentBean = parentBean;
        mDialog = new ReplyDialog(mContext, new DialogActionCallback() {
            @Override
            public void onAction(int tag, String inputStr) {
                if (mCallBack != null && tempItem != null) {
                    mCallBack.onAction(tag, tempItem, inputStr);
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
        holder.setText(R.id.phoneTv, item.getPhone());
        ImageView avatarIv = holder.getView(R.id.avatarIv);
        String contentPic = item.getContentPic();
        if (contentPic != null && !contentPic.isEmpty()) {
            String[] split = contentPic.split(",");
            List<ImgBean> data = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                data.add(new ImgBean("", split[i]));
            }
            if (data.size() > 0) {
                RecyclerView replyImgRv = holder.getView(R.id.replyImgRv);
                replyImgRv.setLayoutManager(new StaggeredGridLayoutManager(3, RecyclerView.VERTICAL));
                replyImgRv.addItemDecoration(new WaterFallItemDecoration(30, 30));
                replyImgRv.setAdapter(new PerformImgAdapter(context, data, R.layout.rvitem_onlyimg));
            }
        }

        String title = item.getUserName() + " " + item.getPhone();
        if (parentBean != null) {
            String parentInfo = parentBean.getUserName() + "  " + item.getPhone();
            String spanStr = title + " 回复 " + parentInfo;
            SpannableString builder = new SpannableString(spanStr);
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.text_norm_black));
            builder.setSpan(colorSpan, spanStr.indexOf("回"), spanStr.indexOf("复") + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            TextView titleTv = holder.getView(R.id.userName);
            titleTv.setText(builder);
        } else {
            holder.setText(R.id.userName, title);
        }
        if (item.getChildren() != null && item.getChildren().size() > 0) {
            RecyclerView replyRv = holder.getView(R.id.replyRv);
            CommentsAdapter adapter = new CommentsAdapter(context, item.getChildren(), R.layout.rvitem_comments, item);
            adapter.setActionCallBack(mCallBack);
            replyRv.setAdapter(adapter);
        }
        GlideUtils.loadImage(mContext, item.getAvatar(), avatarIv);
        holder.setText(R.id.comentsTv, item.getContent());
        holder.setOnClickListener(R.id.replyTv, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempItem = item;
                mDialog.show();
            }
        });
    }

    public void setActionCallBack(TActionCallback<CommentsBean> callBack) {
        mCallBack = callBack;
    }

    public void setDialogImg(List<ImgBean> list) {
        mDialog.setDialogImg(list);
    }
}
