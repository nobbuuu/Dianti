package com.base.baselib.bean;

import java.io.Serializable;

public class ImgBean implements Serializable {
    private String filePath;
    private String imgUrl;

    public ImgBean(String filePath) {
        this.filePath = filePath;
    }
    public ImgBean() {
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
