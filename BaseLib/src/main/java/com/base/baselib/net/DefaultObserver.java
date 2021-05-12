package com.base.baselib.net;

import android.net.ParseException;

import com.base.baselib.R;
import com.base.baselib.bean.base.BaseBean;
import com.base.baselib.utils.LogUtils;
import com.base.baselib.utils.NetResultParseUtils;
import com.base.baselib.utils.Utils;
import com.google.gson.JsonParseException;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

import static com.base.baselib.AppConstant.httpDefErrorCode;


public abstract class DefaultObserver<T extends BaseBean> implements Observer<T> {


    public abstract void onSuccess(@NonNull T t);

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        if (NetResultParseUtils.parseNetResult(t)) {
            onSuccess(t);
        } else if (t == null) {
            onError(new NullPointerException());
        } else {
            onFail(t.code, t.message);
        }
        onStop();
    }


    public void onStop() {

    }

    public void onFail(String code, String msg) {
        Utils.showToast(msg);
        if (code.equals("-2")){
            EventBus.getDefault().post("reLogin");
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onStop();
        String msg;
        LogUtils.e("throwable = " + e.toString());
        if (e instanceof HttpException) {
            //HTTP错误
            msg = Utils.getAppResources().getString(R.string.bad_network);
        } else if (e instanceof ConnectException || e instanceof UnknownHostException || e instanceof NullPointerException) {
            // 连接错误
            msg = Utils.getAppResources().getString(R.string.connect_error);
        } else if (e instanceof InterruptedIOException) {
            //连接超时
            msg = Utils.getAppResources().getString(R.string.connect_timeout);
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            //解析错误
            msg = Utils.getAppResources().getString(R.string.parse_error);
        } else {
            //未知错误
            msg = Utils.getAppResources().getString(R.string.unknown_error);
        }
        onFail(httpDefErrorCode, msg);
    }

    @Override
    public void onComplete() {

    }
}
