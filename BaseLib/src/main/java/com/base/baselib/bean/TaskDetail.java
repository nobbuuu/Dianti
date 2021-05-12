package com.base.baselib.bean;

import java.io.Serializable;

/**
 * 任务详情
 */
public class TaskDetail implements Serializable {

    /**
     * taskSuperviseUser : {"id":613,"taskId":316,"taskDistributeId":66,"userId":120,"longitude":"","latitude":"","signaddress":"","signdate":0,"isSign":0,"isConfirm":null,"img":"","img2":"","startdate":1605801600,"enddate":1608220800,"superviseUser":{"id":120,"phone":"18608439562","password":"68d243593159f5a044f90d9becdf7cee","encrypt":"YLyxIq","name":"向  超","sex":0,"avatar":"","address":"","lastIp":"","lastTime":0,"isReal":true,"status":1,"region1":"","region2":"","isRegion":false,"createTime":1593591515,"updateTime":1593591515,"superviseUserInfo":{"id":120,"uid":120,"realname":"向  超","mobile":"18608439562","remark":"行政","idcard":"433125197111200919","idimg1":"","idimg2":"","idimg3":"","lacard":"本科","laimg1":"","laimg2":"","laimg3":"","updateTime":1593591515,"branchoffice":"","region1":"","region2":"","autograph":""}}}
     * taskDistribution : {"id":66,"taskId":316,"dataId":46,"dataName":"吉首市满卿副食商行","dataAddress":"湖南省湘西土家族苗族自治州吉首市粮贸公司宿舍一楼6-7号门面","dataLongitude":"","dataLatitude":"","userIds":"123,120","userNames":"田  英,向  超","userPhones":"13574309268,18608439562","startdate":1605801600,"enddate":1608220800,"status":1,"categoryType":2,"taskType":1,"qualityType":1,"parentId":null,"content":"做好日常任务"}
     */

    private TaskSuperviseUserBean taskSuperviseUser;
    private TaskDistributionBean taskDistribution;

    public TaskSuperviseUserBean getTaskSuperviseUser() {
        return taskSuperviseUser;
    }

    public void setTaskSuperviseUser(TaskSuperviseUserBean taskSuperviseUser) {
        this.taskSuperviseUser = taskSuperviseUser;
    }

    public TaskDistributionBean getTaskDistribution() {
        return taskDistribution;
    }

    public void setTaskDistribution(TaskDistributionBean taskDistribution) {
        this.taskDistribution = taskDistribution;
    }

    public static class TaskSuperviseUserBean {
        /**
         * id : 613
         * taskId : 316
         * taskDistributeId : 66
         * userId : 120
         * longitude :
         * latitude :
         * signaddress :
         * signdate : 0
         * isSign : 0
         * isConfirm : null
         * img :
         * img2 :
         * startdate : 1605801600
         * enddate : 1608220800
         * superviseUser : {"id":120,"phone":"18608439562","password":"68d243593159f5a044f90d9becdf7cee","encrypt":"YLyxIq","name":"向  超","sex":0,"avatar":"","address":"","lastIp":"","lastTime":0,"isReal":true,"status":1,"region1":"","region2":"","isRegion":false,"createTime":1593591515,"updateTime":1593591515,"superviseUserInfo":{"id":120,"uid":120,"realname":"向  超","mobile":"18608439562","remark":"行政","idcard":"433125197111200919","idimg1":"","idimg2":"","idimg3":"","lacard":"本科","laimg1":"","laimg2":"","laimg3":"","updateTime":1593591515,"branchoffice":"","region1":"","region2":"","autograph":""}}
         */

        private String id;
        private String taskId;
        private String taskDistributeId;
        private String userId;
        private String longitude;
        private String latitude;
        private String signaddress;
        private long signdate;
        private String isSign;
        private Object isConfirm;
        private String img;
        private String img2;
        private long startdate;
        private long enddate;
        private SuperviseUserBean superviseUser;

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

        public String getTaskDistributeId() {
            return taskDistributeId;
        }

        public void setTaskDistributeId(String taskDistributeId) {
            this.taskDistributeId = taskDistributeId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getSignaddress() {
            return signaddress;
        }

        public void setSignaddress(String signaddress) {
            this.signaddress = signaddress;
        }

        public long getSigndate() {
            return signdate;
        }

        public void setSigndate(long signdate) {
            this.signdate = signdate;
        }

        public String getIsSign() {
            return isSign;
        }

        public void setIsSign(String isSign) {
            this.isSign = isSign;
        }

        public Object getIsConfirm() {
            return isConfirm;
        }

        public void setIsConfirm(Object isConfirm) {
            this.isConfirm = isConfirm;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg2() {
            return img2;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public long getStartdate() {
            return startdate;
        }

        public void setStartdate(long startdate) {
            this.startdate = startdate;
        }

        public long getEnddate() {
            return enddate;
        }

        public void setEnddate(long enddate) {
            this.enddate = enddate;
        }

        public SuperviseUserBean getSuperviseUser() {
            return superviseUser;
        }

        public void setSuperviseUser(SuperviseUserBean superviseUser) {
            this.superviseUser = superviseUser;
        }

        public static class SuperviseUserBean {
            /**
             * id : 120
             * phone : 18608439562
             * password : 68d243593159f5a044f90d9becdf7cee
             * encrypt : YLyxIq
             * name : 向  超
             * sex : 0
             * avatar :
             * address :
             * lastIp :
             * lastTime : 0
             * isReal : true
             * status : 1
             * region1 :
             * region2 :
             * isRegion : false
             * createTime : 1593591515
             * updateTime : 1593591515
             * superviseUserInfo : {"id":120,"uid":120,"realname":"向  超","mobile":"18608439562","remark":"行政","idcard":"433125197111200919","idimg1":"","idimg2":"","idimg3":"","lacard":"本科","laimg1":"","laimg2":"","laimg3":"","updateTime":1593591515,"branchoffice":"","region1":"","region2":"","autograph":""}
             */

            private String id;
            private String phone;
            private String password;
            private String encrypt;
            private String name;
            private String sex;
            private String avatar;
            private String address;
            private String lastIp;
            private long lastTime;
            private boolean isReal;
            private int status;
            private String region1;
            private String region2;
            private boolean isRegion;
            private long createTime;
            private long updateTime;
            private SuperviseUserInfoBean superviseUserInfo;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getEncrypt() {
                return encrypt;
            }

            public void setEncrypt(String encrypt) {
                this.encrypt = encrypt;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLastIp() {
                return lastIp;
            }

            public void setLastIp(String lastIp) {
                this.lastIp = lastIp;
            }

            public long getLastTime() {
                return lastTime;
            }

            public void setLastTime(long lastTime) {
                this.lastTime = lastTime;
            }

            public boolean isIsReal() {
                return isReal;
            }

            public void setIsReal(boolean isReal) {
                this.isReal = isReal;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getRegion1() {
                return region1;
            }

            public void setRegion1(String region1) {
                this.region1 = region1;
            }

            public String getRegion2() {
                return region2;
            }

            public void setRegion2(String region2) {
                this.region2 = region2;
            }

            public boolean isIsRegion() {
                return isRegion;
            }

            public void setIsRegion(boolean isRegion) {
                this.isRegion = isRegion;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public SuperviseUserInfoBean getSuperviseUserInfo() {
                return superviseUserInfo;
            }

            public void setSuperviseUserInfo(SuperviseUserInfoBean superviseUserInfo) {
                this.superviseUserInfo = superviseUserInfo;
            }

            public static class SuperviseUserInfoBean {
                /**
                 * id : 120
                 * uid : 120
                 * realname : 向  超
                 * mobile : 18608439562
                 * remark : 行政
                 * idcard : 433125197111200919
                 * idimg1 :
                 * idimg2 :
                 * idimg3 :
                 * lacard : 本科
                 * laimg1 :
                 * laimg2 :
                 * laimg3 :
                 * updateTime : 1593591515
                 * branchoffice :
                 * region1 :
                 * region2 :
                 * autograph :
                 */

                private String id;
                private String uid;
                private String realname;
                private String mobile;
                private String remark;
                private String idcard;
                private String idimg1;
                private String idimg2;
                private String idimg3;
                private String lacard;
                private String laimg1;
                private String laimg2;
                private String laimg3;
                private long updateTime;
                private String branchoffice;
                private String region1;
                private String region2;
                private String autograph;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getRealname() {
                    return realname;
                }

                public void setRealname(String realname) {
                    this.realname = realname;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getIdcard() {
                    return idcard;
                }

                public void setIdcard(String idcard) {
                    this.idcard = idcard;
                }

                public String getIdimg1() {
                    return idimg1;
                }

                public void setIdimg1(String idimg1) {
                    this.idimg1 = idimg1;
                }

                public String getIdimg2() {
                    return idimg2;
                }

                public void setIdimg2(String idimg2) {
                    this.idimg2 = idimg2;
                }

                public String getIdimg3() {
                    return idimg3;
                }

                public void setIdimg3(String idimg3) {
                    this.idimg3 = idimg3;
                }

                public String getLacard() {
                    return lacard;
                }

                public void setLacard(String lacard) {
                    this.lacard = lacard;
                }

                public String getLaimg1() {
                    return laimg1;
                }

                public void setLaimg1(String laimg1) {
                    this.laimg1 = laimg1;
                }

                public String getLaimg2() {
                    return laimg2;
                }

                public void setLaimg2(String laimg2) {
                    this.laimg2 = laimg2;
                }

                public String getLaimg3() {
                    return laimg3;
                }

                public void setLaimg3(String laimg3) {
                    this.laimg3 = laimg3;
                }

                public long getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(long updateTime) {
                    this.updateTime = updateTime;
                }

                public String getBranchoffice() {
                    return branchoffice;
                }

                public void setBranchoffice(String branchoffice) {
                    this.branchoffice = branchoffice;
                }

                public String getRegion1() {
                    return region1;
                }

                public void setRegion1(String region1) {
                    this.region1 = region1;
                }

                public String getRegion2() {
                    return region2;
                }

                public void setRegion2(String region2) {
                    this.region2 = region2;
                }

                public String getAutograph() {
                    return autograph;
                }

                public void setAutograph(String autograph) {
                    this.autograph = autograph;
                }
            }
        }
    }

    public static class TaskDistributionBean {
        /**
         * id : 66
         * taskId : 316
         * dataId : 46
         * dataName : 吉首市满卿副食商行
         * dataAddress : 湖南省湘西土家族苗族自治州吉首市粮贸公司宿舍一楼6-7号门面
         * dataLongitude :
         * dataLatitude :
         * userIds : 123,120
         * userNames : 田  英,向  超
         * userPhones : 13574309268,18608439562
         * startdate : 1605801600
         * enddate : 1608220800
         * status : 1
         * categoryType : 2
         * taskType : 1
         * qualityType : 1
         * parentId : null
         * content : 做好日常任务
         */

        private String id;
        private String taskId;
        private String dataId;
        private String dataName;
        private String dataAddress;
        private String dataLongitude;
        private String dataLatitude;
        private String userIds;
        private String userNames;
        private String userPhones;
        private long startdate;
        private long enddate;
        private int status;
        private int categoryType;
        private int taskType;
        private int qualityType;
        private Object parentId;
        private String content;

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

        public String getDataId() {
            return dataId;
        }

        public void setDataId(String dataId) {
            this.dataId = dataId;
        }

        public String getDataName() {
            return dataName;
        }

        public void setDataName(String dataName) {
            this.dataName = dataName;
        }

        public String getDataAddress() {
            return dataAddress;
        }

        public void setDataAddress(String dataAddress) {
            this.dataAddress = dataAddress;
        }

        public String getDataLongitude() {
            return dataLongitude;
        }

        public void setDataLongitude(String dataLongitude) {
            this.dataLongitude = dataLongitude;
        }

        public String getDataLatitude() {
            return dataLatitude;
        }

        public void setDataLatitude(String dataLatitude) {
            this.dataLatitude = dataLatitude;
        }

        public String getUserIds() {
            return userIds;
        }

        public void setUserIds(String userIds) {
            this.userIds = userIds;
        }

        public String getUserNames() {
            return userNames;
        }

        public void setUserNames(String userNames) {
            this.userNames = userNames;
        }

        public String getUserPhones() {
            return userPhones;
        }

        public void setUserPhones(String userPhones) {
            this.userPhones = userPhones;
        }

        public long getStartdate() {
            return startdate;
        }

        public void setStartdate(long startdate) {
            this.startdate = startdate;
        }

        public long getEnddate() {
            return enddate;
        }

        public void setEnddate(long enddate) {
            this.enddate = enddate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(int categoryType) {
            this.categoryType = categoryType;
        }

        public int getTaskType() {
            return taskType;
        }

        public void setTaskType(int taskType) {
            this.taskType = taskType;
        }

        public int getQualityType() {
            return qualityType;
        }

        public void setQualityType(int qualityType) {
            this.qualityType = qualityType;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
