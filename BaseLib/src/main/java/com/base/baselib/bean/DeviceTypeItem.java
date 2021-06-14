package com.base.baselib.bean;

public class DeviceTypeItem {

    /**
     * taskUrl : http://ydjgh5.chenhl.cn/equipment/record/elevator
     * name : SPECIAL_EQUIPMENT
     * description : 特种设备现场安全监督检查记录（电梯）
     * pointType : 1
     * dicName : ELEVATOR_POINT
     * status : 0
     */

    private String taskUrl;
    private String name;
    private String description;
    private int pointType;
    private String dicName;
    private int status;

    public String getTaskUrl() {
        return taskUrl;
    }

    public void setTaskUrl(String taskUrl) {
        this.taskUrl = taskUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPointType() {
        return pointType;
    }

    public void setPointType(int pointType) {
        this.pointType = pointType;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
