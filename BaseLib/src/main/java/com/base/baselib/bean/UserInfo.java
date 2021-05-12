package com.base.baselib.bean;

public class UserInfo {
    public  int id;// 	int 	用户ID
    public String phone;// 	string 	用户手机号
    public String name;// 	string 	用户姓名
    public int sex;// 	int 	用户姓别：0未知，1男，2女
    public String avatar;// 	string 	用户头像图片地址
    public int is_real;// 	int 	是否实名认证：1是，2否
    public String autograph;//签名照片


    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", avatar='" + avatar + '\'' +
                ", is_real=" + is_real +
                ", autograph='" + autograph + '\'' +
                '}';
    }
}
