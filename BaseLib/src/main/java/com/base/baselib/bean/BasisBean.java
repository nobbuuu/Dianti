package com.base.baselib.bean;

public class BasisBean {

    /**
     * basePath :
     * filePath : https://img.znzhjh.com/d82818b5793cef446596b26c9cbf3664.docx
     * attachmentName : 政务需修改内容2021.11.15(2).docx
     * fileType : application/vnd.openxmlformats-officedocument.wordprocessingml.document
     */

    private String basePath;
    private String filePath;
    private String attachmentName;
    private String fileType;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
