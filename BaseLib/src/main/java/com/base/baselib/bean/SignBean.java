package com.base.baselib.bean;

public class SignBean {

    /**
     * otherId : 119
     * dataId : 230
     * taskUrl : http://101.132.166.78:8011/task/supervise
     * id : 151
     * qualityType : 1
     * taskId : 327
     */

    private int otherId;
    private int dataId;
    private String taskUrl;
    private String dicName;
    private int id;
    private int qualityType;
    private int taskId;
    private int categoryType;

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public int getOtherId() {
        return otherId;
    }

    public void setOtherId(int otherId) {
        this.otherId = otherId;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public String getTaskUrl() {
        return taskUrl;
    }

    public void setTaskUrl(String taskUrl) {
        this.taskUrl = taskUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQualityType() {
        return qualityType;
    }

    public void setQualityType(int qualityType) {
        this.qualityType = qualityType;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
