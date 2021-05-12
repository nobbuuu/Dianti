package com.qt.dtzf.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.base.baselib.base.BaseActivity;
import com.qt.dtzf.R;
import com.qt.dtzf.bean.SafeCheckBean;
import com.qt.dtzf.bean.SafeCheckGroupBean;
import com.qt.dtzf.ui.TaskSafeDescribeActivity;
import com.qt.dtzf.ui.TaskSafeDetailsActivity;
import com.qt.dtzf.view.SafeCheckItemImageView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SafeCheckAdapter extends BaseExpandableListAdapter {

    private BaseActivity mContext;
    List<SafeCheckGroupBean> mGroupList = new ArrayList<>();
    List<SafeCheckBean> mCheckList = new ArrayList<>();
    private int mShowGroupPosition = -1;
    private int mShowChildPosition = -1;

    public SafeCheckAdapter(BaseActivity context) {
        mContext = context;
        SafeCheckBean bean = new SafeCheckBean();
        bean.title = "电梯定期维护保养制度，定期自行检查制度和 相关记录制度 ";
        mCheckList.add(bean);
        mCheckList.add(bean);
        mCheckList.add(bean);

        SafeCheckGroupBean groupBean = new SafeCheckGroupBean();
        groupBean.checkList = mCheckList;
        groupBean.maxTag = 11;
        groupBean.tag = 3;
        groupBean.title = "机构以及制度";

        mGroupList.add(groupBean);
        mGroupList.add(groupBean);
        mGroupList.add(groupBean);
    }

    @Override
    public int getGroupCount() {
        return mGroupList == null ? 0 : mGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        SafeCheckGroupBean bean = mGroupList.get(groupPosition);
        List<SafeCheckBean> checkList = bean.checkList;
        return checkList == null ? 0 : checkList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroupList.get(groupPosition).checkList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
        mShowGroupPosition = groupPosition;
        mShowChildPosition = -1;
    }

    public void setShowOnlyChildImageView() {

    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupHolder mGhv;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.safe_check_group_item, null);
            mGhv = new GroupHolder(convertView);
            convertView.setTag(mGhv);
        } else {
            mGhv = (GroupHolder) convertView.getTag();
        }
        SafeCheckGroupBean item = mGroupList.get(groupPosition);
        mGhv.mTitleTv.setText(item.title);

        mGhv.mItemTv.setText(item.tag + "/" + item.maxTag);
        mGhv.mPb.setMax(item.maxTag);
        mGhv.mPb.setProgress(item.tag);


        //是否展开
        if (isExpanded) {
            mGhv.mTagIv.setImageResource(R.mipmap.zk_icon_k);
        } else {
            mGhv.mTagIv.setImageResource(R.mipmap.zk_icon_s);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder mCHv;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.safe_check_item, null);
            mCHv = new ChildHolder(convertView);
            convertView.setTag(mCHv);
        } else {
            mCHv = (ChildHolder) convertView.getTag();
        }
        SafeCheckBean item = mGroupList.get(groupPosition).checkList.get(childPosition);
        mCHv.mItemTv.setText(item.title);
//        if (mShowChildPosition != childPosition) mCHv.mItemSci.setVisibility(View.GONE);
        mCHv.mItemAllTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TaskSafeDetailsActivity.class);
                mContext.startActivity(intent);
            }
        });
        mCHv.mItemSci.mEt.setText("tex654564654564654");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupHolder {
        TextView mTitleTv;

        ProgressBar mPb;
        TextView mItemTv;
        ImageView mItemIv;
        ImageView mTagIv;

        public GroupHolder(View convertView) {
            mTitleTv = convertView.findViewById(R.id.group_safe_check_item_title_tv);
            mTagIv = convertView.findViewById(R.id.group_safe_check_item_tag_iv);
            mPb = convertView.findViewById(R.id.group_safe_check_item_pb);
            mItemTv = convertView.findViewById(R.id.group_safe_check_item_tv);
            mItemIv = convertView.findViewById(R.id.group_safe_check_item_iv);
        }
    }

    class ChildHolder {

        public final ImageView itemIv;
        public final TextView mItemTv;
        public final TextView mItemAllTv;
        public final SafeCheckItemImageView mItemSci;

        public ChildHolder(View convertView) {
            itemIv = convertView.findViewById(R.id.safe_check_item_l_iv);
            mItemTv = convertView.findViewById(R.id.safe_check_item_tv);
            mItemAllTv = convertView.findViewById(R.id.safe_check_item_all_tv);
            mItemSci = convertView.findViewById(R.id.safe_check_item_sci);
        }
    }
}
