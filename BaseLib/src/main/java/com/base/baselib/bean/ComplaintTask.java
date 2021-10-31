package com.base.baselib.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ComplaintTask implements Serializable {

    public String id;// 	int 	编号id
    public String report_uid;// 	int 	监管人员用户id
    public String username;//	string 	投诉人姓名
    public String phone;// 	string ;//	投诉人电话
    public String creattime;// 	string 	日期
    public String time;// 	string 	时间
    public String week;// 	string 	星期
    public String content;// 	string 	投诉内容
    public String images;// 	string 	投诉凭证多图用,分割
    public String status;// 	string 	状态：1待指派、2待接收、3处理中、4已完成
    public String address;// 	string 	地址


    /**
     * pageNum : 0
     * pageSize : 0
     * size : 1
     * startRow : 1
     * endRow : 1
     * total : 1
     * pages : 0
     * list : [{"id":1,"taskId":1,"sid":1,"seTitle":"美丽湘西","title":"美丽湘西2021","startDate":"2021-10-15T05:02:22.000 0000","endDate":"2021-10-30T05:02:25.000 0000","content":"建设美丽湘西的内容","checkBasis":null,"resultBasis":null,"signAddress":"","userIds":"335","userNames":"杨雪露","userPhones":"15580835064","dataLongitude":"","dataLatitude":"","dataAddress":null,"status":1,"createTime":"2021-10-30T05:03:27.000 0000","acceptTime":null,"finishTime":null,"resultStatus":null,"systemScore":null,"auditScore":null,"totalScore":null,"scoreTime":null,"searchStatus":null}]
     * prePage : 0
     * nextPage : 0
     * isFirstPage : false
     * isLastPage : true
     * hasPreviousPage : false
     * hasNextPage : false
     * navigatePages : 8
     * navigatepageNums : []
     * navigateFirstPage : 0
     * navigateLastPage : 0
     * firstPage : 0
     * lastPage : 0
     */

    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private int total;
    private int pages;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int navigateFirstPage;
    private int navigateLastPage;
    private int firstPage;
    private int lastPage;
    private List<ListBean> list;
    private List<?> navigatepageNums;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<?> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<?> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public static class ListBean {
        /**
         * id : 1
         * taskId : 1
         * sid : 1
         * seTitle : 美丽湘西
         * title : 美丽湘西2021
         * startDate : 2021-10-15T05:02:22.000 0000
         * endDate : 2021-10-30T05:02:25.000 0000
         * content : 建设美丽湘西的内容
         * checkBasis : null
         * resultBasis : null
         * signAddress :
         * userIds : 335
         * userNames : 杨雪露
         * userPhones : 15580835064
         * dataLongitude :
         * dataLatitude :
         * dataAddress : null
         * status : 1
         * createTime : 2021-10-30T05:03:27.000 0000
         * acceptTime : null
         * finishTime : null
         * resultStatus : null
         * systemScore : null
         * auditScore : null
         * totalScore : null
         * scoreTime : null
         * searchStatus : null
         */

        @SerializedName("id")
        private int idX;
        private int taskId;
        private int sid;
        private String seTitle;
        private String title;
        private String startDate;
        private String endDate;
        @SerializedName("content")
        private String contentX;
        private Object checkBasis;
        private Object resultBasis;
        private String signAddress;
        private String userIds;
        private String userNames;
        private String userPhones;
        private String dataLongitude;
        private String dataLatitude;
        private Object dataAddress;
        @SerializedName("status")
        private int statusX;
        private String createTime;
        private Object acceptTime;
        private Object finishTime;
        private Object resultStatus;
        private Object systemScore;
        private Object auditScore;
        private Object totalScore;
        private Object scoreTime;
        private Object searchStatus;

        public int getIdX() {
            return idX;
        }

        public void setIdX(int idX) {
            this.idX = idX;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getSeTitle() {
            return seTitle;
        }

        public void setSeTitle(String seTitle) {
            this.seTitle = seTitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getContentX() {
            return contentX;
        }

        public void setContentX(String contentX) {
            this.contentX = contentX;
        }

        public Object getCheckBasis() {
            return checkBasis;
        }

        public void setCheckBasis(Object checkBasis) {
            this.checkBasis = checkBasis;
        }

        public Object getResultBasis() {
            return resultBasis;
        }

        public void setResultBasis(Object resultBasis) {
            this.resultBasis = resultBasis;
        }

        public String getSignAddress() {
            return signAddress;
        }

        public void setSignAddress(String signAddress) {
            this.signAddress = signAddress;
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

        public Object getDataAddress() {
            return dataAddress;
        }

        public void setDataAddress(Object dataAddress) {
            this.dataAddress = dataAddress;
        }

        public int getStatusX() {
            return statusX;
        }

        public void setStatusX(int statusX) {
            this.statusX = statusX;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getAcceptTime() {
            return acceptTime;
        }

        public void setAcceptTime(Object acceptTime) {
            this.acceptTime = acceptTime;
        }

        public Object getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(Object finishTime) {
            this.finishTime = finishTime;
        }

        public Object getResultStatus() {
            return resultStatus;
        }

        public void setResultStatus(Object resultStatus) {
            this.resultStatus = resultStatus;
        }

        public Object getSystemScore() {
            return systemScore;
        }

        public void setSystemScore(Object systemScore) {
            this.systemScore = systemScore;
        }

        public Object getAuditScore() {
            return auditScore;
        }

        public void setAuditScore(Object auditScore) {
            this.auditScore = auditScore;
        }

        public Object getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(Object totalScore) {
            this.totalScore = totalScore;
        }

        public Object getScoreTime() {
            return scoreTime;
        }

        public void setScoreTime(Object scoreTime) {
            this.scoreTime = scoreTime;
        }

        public Object getSearchStatus() {
            return searchStatus;
        }

        public void setSearchStatus(Object searchStatus) {
            this.searchStatus = searchStatus;
        }
    }
}
