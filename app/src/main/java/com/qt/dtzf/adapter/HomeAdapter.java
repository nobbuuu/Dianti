package com.qt.dtzf.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.baselib.bean.MainItem;
import com.base.baselib.bean.Menu;
import com.base.baselib.bean.TaskNumber;
import com.base.baselib.glide.GlideUtils;
import com.qt.dtzf.R;
import com.qt.dtzf.bean.HomeItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.VH> {
    private List<HomeItem> mItemList;
    private ItemClickListener mItemClickListener;
    public TaskNumber mTask;
    private Activity mActivity;

    public HomeAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        return mItemList.get(position).type;

    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = View.inflate(parent.getContext(), R.layout.home_item_0, null);
        } else if (viewType == 1) {
            view = View.inflate(parent.getContext(), R.layout.home_item_1, null);
        } else {
            view = View.inflate(parent.getContext(), R.layout.home_item_title, null);
        }
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        HomeItem item = mItemList.get(position);
        if (item.type == 2) {
            holder.mTitle.setText(item.title);
            return;
        }
        Menu.ListBean.MenuSonBean content = item.item;
//        String imageSrc = ApiUrl.getBaseImageUrl() + content.imgsrc;
//        LogUtils.e("image = "+ imageSrc);
        GlideUtils.loadImage(mActivity, content.getImgsrc(), holder.mIv);
        holder.mTv.setText(content.getTitle());
        if (content.getCheck() == 0) {
            holder.mMsg.setVisibility(View.GONE);
        } else {
            holder.mMsg.setVisibility(View.VISIBLE);
//            holder.mMsg.setText(String.valueOf(content.getNum()));
        }


        if (holder.mItem_0_fl != null) {
            holder.mItem_0_fl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) mItemClickListener.onItemClick(item);
                }
            });
        }
        if (holder.mItem_1_fl != null) {
            holder.mItem_1_fl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) mItemClickListener.onItemClick(item);
                }
            });
        }

    }


    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(HomeItem item);
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        GridLayoutManager gridManager = null;
        if (manager instanceof GridLayoutManager) gridManager = ((GridLayoutManager) manager);
        if (gridManager == null) return;
        gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemType = mItemList.get(position).type;
                if (itemType == 0) return 3;
                if (itemType == 1) return 2;
                if (itemType == 2) return 6;
                return 0;
            }
        });
    }

    class VH extends RecyclerView.ViewHolder {

        public final TextView mTitle;
        public final ImageView mIv;
        public final TextView mMsg;
        public final TextView mTv;
        public final View mItem_0_fl;
        public final View mItem_1_fl;

        public VH(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.home_item_title);

            mItem_0_fl = itemView.findViewById(R.id.home_item_0_fl);
            mItem_1_fl = itemView.findViewById(R.id.home_item_1_fl);

            mIv = itemView.findViewById(R.id.home_item_iv);
            mMsg = itemView.findViewById(R.id.home_item_msg);
            mTv = itemView.findViewById(R.id.home_item_tv);
        }
    }


    public void setData(Menu data) {
        if (data == null || data.getList() == null) return;
        mItemList = new ArrayList<>();
        List<Menu.ListBean> list = data.getList();
        for (int i = 0; i < list.size(); i++) {
            Menu.ListBean parentItem = list.get(i);
            HomeItem title = new HomeItem();
            title.title = parentItem.getTitle();
            title.type = 2;
            mItemList.add(title);
            List<Menu.ListBean.MenuSonBean> menu = parentItem.getMenuSon();
            for (int n = 0; n < menu.size(); n++) {
                Menu.ListBean.MenuSonBean mainItem = menu.get(n);
                HomeItem itemHome = new HomeItem();
                itemHome.title = parentItem.getTitle();
                itemHome.item = mainItem;
                if("今日任务".equals(parentItem.getTitle())){
                    itemHome.type = 0;
                }else {
                    itemHome.type = 1;
                }
                mItemList.add(itemHome);
            }
        }
        mItemList.remove(0);
        notifyDataSetChanged();
    }
}
