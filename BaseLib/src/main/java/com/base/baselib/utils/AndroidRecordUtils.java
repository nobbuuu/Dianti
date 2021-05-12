package com.base.baselib.utils;

import android.media.AudioFormat;
import android.os.Environment;


import com.base.baselib.BaseAPP;
import com.zlw.main.recorderlib.RecordManager;
import com.zlw.main.recorderlib.recorder.RecordConfig;
import com.zlw.main.recorderlib.recorder.RecordHelper;
import com.zlw.main.recorderlib.recorder.listener.RecordFftDataListener;
import com.zlw.main.recorderlib.recorder.listener.RecordResultListener;

import java.util.Locale;

/**
 * 录音工具类
 */
public class AndroidRecordUtils {

    public RecordManager mRecordManager;
    private boolean isStop = false;

    private static class AndroidRecordUtilsHolder {
        private static final AndroidRecordUtils utils = new AndroidRecordUtils();
    }

    public static AndroidRecordUtils getInstance() {
        return AndroidRecordUtilsHolder.utils;
    }

    private AndroidRecordUtils() {
        initUtils();
    }

    public void initUtils() {
        if (mRecordManager == null) mRecordManager = RecordManager.getInstance();

        mRecordManager.init(BaseAPP.mBaseAPP, true);
        //修改录音格式(默认:WAV)
        mRecordManager.changeFormat(RecordConfig.RecordFormat.MP3);

        mRecordManager.changeRecordConfig(mRecordManager.getRecordConfig().setSampleRate(16000));
        mRecordManager.changeRecordConfig(mRecordManager.getRecordConfig().setEncodingConfig(AudioFormat.ENCODING_PCM_8BIT));

        String recordDir = String.format(Locale.getDefault(), "%s/Record/app/",
                Environment.getExternalStorageDirectory().getAbsolutePath());
        RecordManager.getInstance().changeRecordDir(recordDir);
    }

    public void setRecordFftDataListener(RecordFftDataListener recordFftDataListener) {
        mRecordManager.setRecordFftDataListener(recordFftDataListener);
    }

    public void start() {
        RecordHelper.RecordState state = mRecordManager.getState();
        LogUtils.e("--->" + state);
        mRecordManager.start();
    }



    public void stop() {
        RecordHelper.RecordState state = mRecordManager.getState();
        LogUtils.e("--->" + state);
        isStop = true;
        mRecordManager.stop();
    }

    /**
     * 恢复录音
     */
    public void restart() {
        if (isStop) {
            start();
            isStop = false;
        } else {
            mRecordManager.resume();
        }
    }

    /**
     * 暂停录音
     */
    public void pause() {
        mRecordManager.pause();
    }

    public void setRecordResultListener(RecordResultListener listener) {
        mRecordManager.setRecordResultListener(listener);
    }

}
