package com.base.baselib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.base.baselib.R;
import com.base.baselib.base.BaseActivity;
import com.base.baselib.utils.StatusBarUtil;


/**
 * 自定义titleView
 */
public class TitleView extends Toolbar {

    private Context mContext;
    private static int mBarHeight = 0;
    private TitleViewAttrs mAttrs = new TitleViewAttrs();

    public View mTitleLeftLl;
    public TextView mTitleTv;
    public ImageView mTitleIm;
    public View mTitleLl;
    public View mTitleRightFl;
    public TextView mTitleRightTv;
    public ImageView mTitleRightIv;
    public FrameLayout mTitleFl;
    public FrameLayout mOtherFl;


    public TitleView(@NonNull Context context) {
        super(context);
        initView(context, null);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TitleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;
        if (attrs != null) initAttributeSet(attrs);
        View.inflate(mContext, R.layout.app_base_title, this);
        findView();
        setTitleBarHeight();
        initTitle();
    }

    private void setTitleBarHeight() {
        if (mBarHeight == 0) mBarHeight = StatusBarUtil.getStatusBarHeight();
        ImageView bar = findViewById(R.id.title_bar);
        ViewGroup.LayoutParams params = bar.getLayoutParams();
        params.height = mBarHeight;
        bar.setLayoutParams(params);
    }

    private void initAttributeSet(AttributeSet attrs) {
        TypedArray mTypedArray = mContext.obtainStyledAttributes(attrs, R.styleable.TitleView);
        mAttrs.mTitle = mTypedArray.getString(R.styleable.TitleView_textTitle);
        mAttrs.mDrawableIconTitle = mTypedArray.getDrawable(R.styleable.TitleView_iconTitle);
        mAttrs.mShowIconTitle = mTypedArray.getBoolean(R.styleable.TitleView_showIconTitle, false);
        mAttrs.mShowLeft = mTypedArray.getBoolean(R.styleable.TitleView_showLeft, true);
        mAttrs.mShowRight = mTypedArray.getBoolean(R.styleable.TitleView_showRight, false);
        mAttrs.mDrawableIconRight = mTypedArray.getDrawable(R.styleable.TitleView_iconRight);
        mAttrs.mTextRight = mTypedArray.getString(R.styleable.TitleView_textRight);
        mAttrs.mShowOther = mTypedArray.getBoolean(R.styleable.TitleView_showOther, false);
        mAttrs.mShowOtherID = mTypedArray.getInt(R.styleable.TitleView_showOtherID, -1);
        mTypedArray.recycle();
    }

    private void findView() {
        mTitleLeftLl = findViewById(R.id.title_left_ll);

        mTitleTv = findViewById(R.id.title_tv);
        mTitleIm = findViewById(R.id.app_title_im);
        mTitleLl = findViewById(R.id.title_ll);

        mTitleRightFl = findViewById(R.id.title_right_fl);
        mTitleRightTv = findViewById(R.id.title_right_tv);
        mTitleRightIv = findViewById(R.id.title_right_iv);

        mTitleFl = findViewById(R.id.title_bg_fl);
        mOtherFl = findViewById(R.id.title_other_fl);


        mTitleLeftLl.setOnClickListener(mClickListener);
    }

    OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mContext instanceof BaseActivity) {
                BaseActivity activity = (BaseActivity) mContext;
                activity.finish();
            }
        }
    };

    private void initTitle() {


        if (mAttrs.mShowLeft) mTitleLeftLl.setVisibility(VISIBLE);

        mTitleTv.setText(mAttrs.mTitle);
        if (mAttrs.mDrawableIconTitle != null) mTitleIm.setBackground(mAttrs.mDrawableIconTitle);
        if (mAttrs.mShowIconTitle) mTitleIm.setVisibility(VISIBLE);

        if (mAttrs.mShowRight) mTitleRightFl.setVisibility(VISIBLE);
        mTitleRightTv.setText(mAttrs.mTextRight);
        if (mAttrs.mDrawableIconRight != null)
            mTitleRightIv.setBackground(mAttrs.mDrawableIconRight);
    }

    public void setTitleText(String title){
        mTitleTv.setText(title);
    }

    public void addOtherView(int id) {
        mAttrs.mShowOtherID = id;
        if (mAttrs.mShowOther && mAttrs.mShowOtherID != -1) {
            mOtherFl.setVisibility(VISIBLE);
            View inflate = LayoutInflater.from(mContext).inflate(mAttrs.mShowOtherID, null);
            mOtherFl.addView(inflate);
        } else {
            mOtherFl.setVisibility(GONE);
        }
    }

    /**
     * title属性类
     */
    private class TitleViewAttrs {

        public String mTitle = "";
        public Drawable mDrawableIconTitle = null;
        public boolean mShowIconTitle = false;
        public boolean mShowLeft = true;
        public boolean mShowRight = false;
        public Drawable mDrawableIconRight = null;
        public String mTextRight = "";
        public boolean mShowOther = false;
        public int mShowOtherID = -1;

    }
}
