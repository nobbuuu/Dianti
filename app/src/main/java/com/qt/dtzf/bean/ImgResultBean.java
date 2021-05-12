package com.qt.dtzf.bean;

public class ImgResultBean {

    /**
     * code : 0
     * data : {"qnUrl":"https://img.znzhjh.com/d5922e7e87a30bc46d9a7c6a2eb5ec3a"}
     * message : 操作成功
     */

    private String code;
    private DataBean data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * qnUrl : https://img.znzhjh.com/d5922e7e87a30bc46d9a7c6a2eb5ec3a
         */

        private String qnUrl;

        public String getQnUrl() {
            return qnUrl;
        }

        public void setQnUrl(String qnUrl) {
            this.qnUrl = qnUrl;
        }
    }
}
