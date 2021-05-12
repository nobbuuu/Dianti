package com.qt.dtzf.bean;

import java.io.Serializable;

public class TaskInfoBean implements Serializable {
    private String id;
    private String taskId;
    private long latitute;
    private long longtitue;
    private String address;

    public TaskInfoBean(String id, String taskId, long latitute, long longtitue, String address) {
        this.id = id;
        this.taskId = taskId;
        this.latitute = latitute;
        this.longtitue = longtitue;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public long getLatitute() {
        return latitute;
    }

    public void setLatitute(long latitute) {
        this.latitute = latitute;
    }

    public long getLongtitue() {
        return longtitue;
    }

    public void setLongtitue(long longtitue) {
        this.longtitue = longtitue;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
