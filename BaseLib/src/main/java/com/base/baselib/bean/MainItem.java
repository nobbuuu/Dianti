package com.base.baselib.bean;

public class MainItem {
    public String id;//	5
    public String imgsrc;//
    public int num;//	0
    public String title;//	待审核
    public String type_id;//	3
    public String url;//

    @Override
    public String toString() {
        return "MainItem{" +
                "id='" + id + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", num='" + num + '\'' +
                ", title='" + title + '\'' +
                ", type_id='" + type_id + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
