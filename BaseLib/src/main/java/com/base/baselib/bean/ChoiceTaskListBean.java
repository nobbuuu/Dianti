package com.base.baselib.bean;

import java.util.List;

public class ChoiceTaskListBean {

    /**
     * xcqzImg : null
     * list : [{"categoryType":1,"otherId":296,"dataId":90,"taskUrl":"http://ydjgh5.chenhl.cn/task/supervise","name":"特殊食品检查要点表","pointType":1,"id":675,"qualityType":1,"dicName":"SUPERVISION_COLD_POINT","taskId":440,"status":0},{"categoryType":1,"otherId":296,"dataId":90,"taskUrl":"http://ydjgh5.chenhl.cn/task/supervise","name":"特殊场所检查项目表","pointType":2,"id":675,"qualityType":1,"dicName":"SUPERVISION_SPECIAL_POINT","taskId":440,"status":0},{"categoryType":1,"otherId":296,"dataId":90,"taskUrl":"http://ydjgh5.chenhl.cn/task/supervise","name":"普通食品经营检查要点表","pointType":3,"id":675,"qualityType":1,"dicName":"SUPERVISION_COMMON_POINT","taskId":440,"status":0},{"categoryType":1,"otherId":296,"dataId":90,"taskUrl":"http://ydjgh5.chenhl.cn/task/supervise","name":"餐饮服务日常监督检查要点表","pointType":4,"id":675,"qualityType":1,"dicName":"SUPERVISION_POINT","taskId":440,"status":0}]
     */

    private String xcqzImg;
    private List<ListBean> list;

    public String getXcqzImg() {
        return xcqzImg;
    }

    public void setXcqzImg(String xcqzImg) {
        this.xcqzImg = xcqzImg;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * categoryType : 1
         * otherId : 296
         * dataId : 90
         * taskUrl : http://ydjgh5.chenhl.cn/task/supervise
         * name : 特殊食品检查要点表
         * pointType : 1
         * id : 675
         * qualityType : 1
         * dicName : SUPERVISION_COLD_POINT
         * taskId : 440
         * status : 0
         */

        private int categoryType;
        private int otherId;
        private int dataId;
        private String taskUrl;
        private String name;
        private int pointType;
        private int id;
        private int qualityType;
        private String dicName;
        private int taskId;
        private int status;

        public int getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(int categoryType) {
            this.categoryType = categoryType;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPointType() {
            return pointType;
        }

        public void setPointType(int pointType) {
            this.pointType = pointType;
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

        public String getDicName() {
            return dicName;
        }

        public void setDicName(String dicName) {
            this.dicName = dicName;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
