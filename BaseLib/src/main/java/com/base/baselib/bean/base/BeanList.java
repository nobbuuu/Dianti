package com.base.baselib.bean.base;

import java.util.List;

public class BeanList<T> extends BaseBean {

    public List<T> data;

    @Override
    public String toString() {
        return "BeanList{" +
                "data=" + data +
                '}';
    }
}
