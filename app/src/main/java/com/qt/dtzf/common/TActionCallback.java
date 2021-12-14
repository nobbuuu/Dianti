package com.qt.dtzf.common;

public interface TActionCallback<T> {
    void onAction(int tag,T data,String inputStr);
}
