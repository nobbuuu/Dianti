package com.base.baselib.bean.base;

/**
 * 基础bean类
 */
public class Bean<T> extends BaseBean {
    public T data;

    @Override
    public String toString() {
        return "Bean{" +
                "data=" + data +
                '}';
    }
}

