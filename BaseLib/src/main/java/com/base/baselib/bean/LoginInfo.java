package com.base.baselib.bean;

public class LoginInfo {

    public String apikey;
    public String uid;
    public String phone;

    public String autograph;//签名照片

    public String avatar;
    public String name;//	安卓测试帐号
    public String url;//
    public String type;//登陆类型  1是监管人员登陆  2是使用单位登陆	2

    @Override
    public String toString() {
        return "LoginInfo{" +
                "apikey='" + apikey + '\'' +
                ", uid='" + uid + '\'' +
                ", phone='" + phone + '\'' +
                ", autograph='" + autograph + '\'' +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
