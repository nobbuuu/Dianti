package com.base.baselib.bean;

import java.io.Serializable;
import java.util.List;

public class TaskInfo implements Serializable {

    /**
     * pageNum : 0
     * pageSize : 0
     * size : 1
     * startRow : 1
     * endRow : 1
     * total : 1
     * pages : 0
     * list : [{"id":66,"taskId":316,"dataId":46,"dataName":"吉首市满卿副食商行","dataAddress":"湖南省湘西土家族苗族自治州吉首市粮贸公司宿舍一楼6-7号门面","dataLongitude":"","dataLatitude":"","userIds":"123,120","userNames":"田  英,向  超","userPhones":"13574309268,18608439562","startdate":1605801600,"enddate":1608220800,"status":1,"categoryType":2,"taskType":1,"qualityType":1,"parentId":null,"content":"做好日常任务"}]
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
    private int categoryType;
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

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

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

    public static class ListBean implements Serializable{

        /**
         * id : 537
         * taskId : 349
         * dataId : 3658
         * dataName : 吉首市伙房餐饮店
         * dataAddress : 湖南省湘西土家族苗族自治州吉首市峒河办事处新桥路（民族烟材有限公司门面）28号
         * dataLongitude : 109.747061
         * dataLatitude : 28.311926
         * userIds : 285,283
         * userNames : 席小龙,张华勇
         * userPhones : 18574320012,13700921378
         * startdate : 1614528000
         * enddate : 1619020800
         * status : 2
         * categoryType : 2
         * taskType : 2
         * qualityType : 1
         * parentId : null
         * content : 测试双随机任务，查看ios和安卓的不同，并记录到world文档
         * countNum : null
         * searchStatus : null
         * taskUrl : https://ydjgh5.znzhjh.com/task/supervise
         * dicName : SUPERVISION_POINT
         * searchParentId : null
         */

        private String id;
        private String taskId;
        private int dataId;
        private String dataName;
        private String dataAddress;
        private String dataLongitude;
        private String dataLatitude;
        private String userIds;
        private String userNames;
        private String userPhones;
        private int startdate;
        private int enddate;
        private int status;
        private int categoryType;
        private int taskType;
        private int qualityType;
        private int pointType;
        private Object parentId;
        private String content;
        private Object countNum;
        private Object searchStatus;
        private String taskUrl;
        private String dicName;
        private String checkBasis;
        private Object searchParentId;

        public int getPointType() {
            return pointType;
        }

        public void setPointType(int pointType) {
            this.pointType = pointType;
        }

        public String getCheckBasis() {
            return checkBasis;
        }

        public void setCheckBasis(String checkBasis) {
            this.checkBasis = checkBasis;
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

        public int getDataId() {
            return dataId;
        }

        public void setDataId(int dataId) {
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

        public int getStartdate() {
            return startdate;
        }

        public void setStartdate(int startdate) {
            this.startdate = startdate;
        }

        public int getEnddate() {
            return enddate;
        }

        public void setEnddate(int enddate) {
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

        public Object getCountNum() {
            return countNum;
        }

        public void setCountNum(Object countNum) {
            this.countNum = countNum;
        }

        public Object getSearchStatus() {
            return searchStatus;
        }

        public void setSearchStatus(Object searchStatus) {
            this.searchStatus = searchStatus;
        }

        public String getTaskUrl() {
            return taskUrl;
        }

        public void setTaskUrl(String taskUrl) {
            this.taskUrl = taskUrl;
        }

        public String getDicName() {
            return dicName;
        }

        public void setDicName(String dicName) {
            this.dicName = dicName;
        }

        public Object getSearchParentId() {
            return searchParentId;
        }

        public void setSearchParentId(Object searchParentId) {
            this.searchParentId = searchParentId;
        }
    }
}
