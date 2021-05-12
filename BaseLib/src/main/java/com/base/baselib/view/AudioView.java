package com.base.baselib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.base.baselib.R;

public class AudioView extends View {

    private Context mContext;
    private Paint mPaint;
    //显示条的颜色
    private int LUMP_COLOR = getResources().getColor(R.color.white);
    //显条的数量
    private int LUMP_COUNT = 64;
    private float LUMP_WIDTH = 6;
    private float LUMP_SPACE = 4;
    private float LUMP_MIN_HEIGHT = LUMP_WIDTH;
    private float LUMP_MAX_HEIGHT = 200;
    private float LUMP_SIZE = 0;
    private float[] waveData = new float[LUMP_COUNT];

    private int mViewHeight;
    private int mViewWidth;
    private float mItemWidthOther;

    public AudioView(Context context) {
        super(context);
        initView(context);
    }

    public AudioView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AudioView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(LUMP_COLOR);
        mPaint.setStrokeWidth(LUMP_SPACE);
        mPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewHeight = getMeasuredHeight() / 2;
        mViewWidth = getMeasuredWidth();

        float itemWidthAll = mViewWidth / LUMP_COUNT;
        mItemWidthOther = mViewWidth % LUMP_COUNT / 2;
        float itemWidth = itemWidthAll / 3;
        LUMP_WIDTH = itemWidth * 2;
        LUMP_SPACE = itemWidth;
        LUMP_SIZE = LUMP_WIDTH + LUMP_SPACE;
    }

    public void setWaveData(byte[] data) {
        waveData = readyData(data);
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mItemWidthOther, mViewHeight);
        for (int i = 0; i < LUMP_COUNT; i++) {
            drawLump(canvas, i);
        }
    }


    private void drawLump(Canvas canvas, int i) {

        float tag = waveData[i];
        if (tag < LUMP_MIN_HEIGHT) tag = LUMP_MIN_HEIGHT;
        if (tag > LUMP_MAX_HEIGHT) tag = LUMP_MAX_HEIGHT;
        float datum = tag / 2;
        float left = LUMP_SIZE * i;
        float top = -datum;
        float right = left + LUMP_WIDTH;
        float bottom = datum;
        canvas.drawRect(left, top, right, bottom, mPaint);

    }

    /**
     * 预处理数据
     *
     * @return
     */
    private float[] readyData(byte[] fft) {
        float[] newData = new float[LUMP_COUNT];
        for (int i = 0; i < Math.min(fft.length, LUMP_COUNT); i++) {
            int tag = Math.abs(fft[i]);
            newData[i] = tag;
        }
        return newData;
    }
}
