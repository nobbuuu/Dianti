package com.base.baselib.bean;

import java.util.List;

public class CommentsBean {

    /**
     * taskDistributeId : 2
     * phone : 15580835064
     * contentPic : https://img.znzhjh.com/627d71584f33012316be534b1344831e,https://img.znzhjh.com/b8e3b8e77129208ec2dcbab0fec6b0f5
     * createTime : 2021-11-08T17:08:49.000+0000
     * children : []
     * replyUserName : null
     * id : 10
     * avatar : https://img.znzhjh.com/c4e3022a7f11429fedc3a61b3f60b05e
     * userName : 不选择任务人员1
     * taskId : 1
     * content : 考虑考虑
     */

    private int taskDistributeId;
    private String phone;
    private String contentPic;
    private String createTime;
    private Object replyUserName;
    private int id;
    private String avatar;
    private String userName;
    private int taskId;
    private String content;
    private List<?> children;

    public int getTaskDistributeId() {
        return taskDistributeId;
    }

    public void setTaskDistributeId(int taskDistributeId) {
        this.taskDistributeId = taskDistributeId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContentPic() {
        return contentPic;
    }

    public void setContentPic(String contentPic) {
        this.contentPic = contentPic;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(Object replyUserName) {
        this.replyUserName = replyUserName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<?> getChildren() {
        return children;
    }

    public void setChildren(List<?> children) {
        this.children = children;
    }
}
