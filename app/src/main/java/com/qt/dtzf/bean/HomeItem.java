package com.qt.dtzf.bean;

import com.base.baselib.bean.Menu;

public class HomeItem {


    public int type;
    public String title;
    public Menu.ListBean.MenuSonBean item;

    @Override
    public String toString() {
        return "HomeItem{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", item=" + item +
                '}';
    }
}
