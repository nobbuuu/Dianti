package com.base.baselib.bean;

public class LoginBean {

    /**
     * user : {"id":7,"username":"","password":"e10adc3949ba59abbe56e057f20f883e","fullName":"111","email":null,"phone":"13066666661","type":"5","createUser":1,"createTime":"2020-11-04T14:32:23.000+0000","updateUser":null,"updateTime":null,"isDelete":null,"lastIp":null,"lastTime":"2020-11-14T07:18:19.000+0000","status":1,"avatar":null,"roleId":null,"otherId":1,"token":"05fe4ee1-db72-4a11-b29a-5a1f2209feb4"}
     */

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 7
         * username :
         * password : e10adc3949ba59abbe56e057f20f883e
         * fullName : 111
         * email : null
         * phone : 13066666661
         * type : 5
         * createUser : 1
         * createTime : 2020-11-04T14:32:23.000+0000
         * updateUser : null
         * updateTime : null
         * isDelete : null
         * lastIp : null
         * lastTime : 2020-11-14T07:18:19.000+0000
         * status : 1
         * avatar : null
         * roleId : null
         * otherId : 1
         * token : 05fe4ee1-db72-4a11-b29a-5a1f2209feb4
         */

        private String id;
        private String username;
        private String password;
        private String fullName;
        private Object email;
        private String phone;
        private int type;
        private int createUser;
        private String createTime;
        private Object updateUser;
        private Object updateTime;
        private Object isDelete;
        private Object lastIp;
        private String lastTime;
        private int status;
        private String avatar;
        private Object roleId;
        private int otherId;
        private String token;

        @Override
        public String toString() {
            return "UserBean{" +
                    "id='" + id + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", fullName='" + fullName + '\'' +
                    ", email=" + email +
                    ", phone='" + phone + '\'' +
                    ", type='" + type + '\'' +
                    ", createUser=" + createUser +
                    ", createTime='" + createTime + '\'' +
                    ", updateUser=" + updateUser +
                    ", updateTime=" + updateTime +
                    ", isDelete=" + isDelete +
                    ", lastIp=" + lastIp +
                    ", lastTime='" + lastTime + '\'' +
                    ", status=" + status +
                    ", avatar='" + avatar + '\'' +
                    ", roleId=" + roleId +
                    ", otherId=" + otherId +
                    ", token='" + token + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCreateUser() {
            return createUser;
        }

        public void setCreateUser(int createUser) {
            this.createUser = createUser;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateUser() {
            return updateUser;
        }

        public void setUpdateUser(Object updateUser) {
            this.updateUser = updateUser;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(Object isDelete) {
            this.isDelete = isDelete;
        }

        public Object getLastIp() {
            return lastIp;
        }

        public void setLastIp(Object lastIp) {
            this.lastIp = lastIp;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getRoleId() {
            return roleId;
        }

        public void setRoleId(Object roleId) {
            this.roleId = roleId;
        }

        public int getOtherId() {
            return otherId;
        }

        public void setOtherId(int otherId) {
            this.otherId = otherId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
