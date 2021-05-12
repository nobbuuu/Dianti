package com.base.baselib.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.baselib.R;
import com.base.baselib.utils.AndroidRecordUtils;
import com.base.baselib.utils.LogUtils;
import com.zlw.main.recorderlib.recorder.listener.RecordFftDataListener;
import com.zlw.main.recorderlib.recorder.listener.RecordResultListener;

import java.io.File;
import java.io.IOException;

public class RecordView extends FrameLayout {

    private Context mContext;
    private View mRecordReadyFl;
    private View mRecordLl;
    private Button mRecordStartBtn;
    private TextView mRecordTimeTv;
    private AudioView mRecordAv;

    private String mRecordTag = "暂停";

    public RecordView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public RecordView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RecordView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View.inflate(mContext, R.layout.record_view, this);
        findView();

        AndroidRecordUtils.getInstance().setRecordFftDataListener(mRecordFftDataListener);

        AndroidRecordUtils.getInstance().setRecordResultListener(mRecordResultListener);
    }

    private void findView() {


        mRecordReadyFl = findViewById(R.id.record_ready_fl);
        findViewById(R.id.record_ready_btn).setOnClickListener(mClickListener);

        mRecordLl = findViewById(R.id.record_ll);
        mRecordAv = findViewById(R.id.record_av);
        mRecordTimeTv = findViewById(R.id.record_time_tv);
        mRecordStartBtn = findViewById(R.id.record_start_btn);
        mRecordStartBtn.setOnClickListener(mClickListener);
        findViewById(R.id.record_end_btn).setOnClickListener(mClickListener);
    }

    RecordFftDataListener mRecordFftDataListener = new RecordFftDataListener() {
        @Override
        public void onFftData(byte[] data) {
            mRecordAv.setWaveData(data);
        }
    };
    RecordResultListener mRecordResultListener = new RecordResultListener() {
        @Override
        public void onResult(File result) {
            LogUtils.e(""+result.getAbsolutePath());

            MediaPlayer mp = new MediaPlayer();
            try {
                mp.setDataSource(result.getAbsolutePath());
                mp.prepare();
                int length = mp.getDuration();
                LogUtils.e("length = "+length);
                mp.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.record_ready_btn) {
                mRecordReadyFl.setVisibility(GONE);
                mRecordLl.setVisibility(VISIBLE);
                AndroidRecordUtils.getInstance().start();
                mRecordTag = "暂停";
                mRecordStartBtn.setText(mRecordTag);

            } else if (id == R.id.record_start_btn) {
                //录音暂停或者从新开始
                //设置图标
                if ("开始".equals(mRecordTag)) {
                    mRecordTag = "暂停";
                    AndroidRecordUtils.getInstance().restart();
                } else {
                    mRecordTag = "开始";
                    AndroidRecordUtils.getInstance().pause();
                }
                mRecordStartBtn.setText(mRecordTag);
            } else if (id == R.id.record_end_btn) {
                //录音结束
                AndroidRecordUtils.getInstance().stop();
            }
        }
    };

}
