package com.base.baselib.bean;

import java.io.Serializable;

public class ComplaintTask implements Serializable {

    public String id;// 	int 	编号id
    public String report_uid;// 	int 	监管人员用户id
    public String username;//	string 	投诉人姓名
    public String phone;// 	string ;//	投诉人电话
    public String creattime;// 	string 	日期
    public String time;// 	string 	时间
    public String week;// 	string 	星期
    public String content;// 	string 	投诉内容
    public String images;// 	string 	投诉凭证多图用,分割
    public String status;// 	string 	状态：1待指派、2待接收、3处理中、4已完成
    public String address;// 	string 	地址


    @Override
    public String toString() {
        return "ComplaintTask{" +
                "id='" + id + '\'' +
                ", report_uid='" + report_uid + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", creattime='" + creattime + '\'' +
                ", time='" + time + '\'' +
                ", week='" + week + '\'' +
                ", content='" + content + '\'' +
                ", images='" + images + '\'' +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
