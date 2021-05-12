package com.base.baselib.bean;

import java.util.List;

public class PerformBean {

    /**
     * pageNum : 0
     * pageSize : 0
     * size : 1
     * startRow : 1
     * endRow : 1
     * total : 1
     * pages : 0
     * list : [{"id":1,"taskId":352,"otherId":285,"taskDistributeId":545,"dataId":1,"dataName":"电梯案件不合格","taskType":null,"city":"","nian":"","no":"12","type":1,"title":"","tiao":null,"courtName":null,"checkerName":null,"checkerPhone":null,"transferTime":null,"createTime":null,"stepNum":0,"taskUrl":"http://ydjgh5.chenhl.cn/CaseView/caselist","question":null,"tiaoText":null}]
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
         * taskId : 352
         * otherId : 285
         * taskDistributeId : 545
         * dataId : 1
         * dataName : 电梯案件不合格
         * taskType : null
         * city :
         * nian :
         * no : 12
         * type : 1
         * title :
         * tiao : null
         * courtName : null
         * checkerName : null
         * checkerPhone : null
         * transferTime : null
         * createTime : null
         * stepNum : 0
         * taskUrl : http://ydjgh5.chenhl.cn/CaseView/caselist
         * question : null
         * tiaoText : null
         */

        private int id;
        private int taskId;
        private int otherId;
        private int taskDistributeId;
        private int dataId;
        private String dataName;
        private Object taskType;
        private String city;
        private String nian;
        private String no;
        private int type;
        private String title;
        private Object tiao;
        private Object courtName;
        private Object checkerName;
        private Object checkerPhone;
        private String transferTime;
        private Object createTime;
        private int stepNum;
        private String taskUrl;
        private Object question;
        private Object tiaoText;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public int getOtherId() {
            return otherId;
        }

        public void setOtherId(int otherId) {
            this.otherId = otherId;
        }

        public int getTaskDistributeId() {
            return taskDistributeId;
        }

        public void setTaskDistributeId(int taskDistributeId) {
            this.taskDistributeId = taskDistributeId;
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

        public Object getTaskType() {
            return taskType;
        }

        public void setTaskType(Object taskType) {
            this.taskType = taskType;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getNian() {
            return nian;
        }

        public void setNian(String nian) {
            this.nian = nian;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getTiao() {
            return tiao;
        }

        public void setTiao(Object tiao) {
            this.tiao = tiao;
        }

        public Object getCourtName() {
            return courtName;
        }

        public void setCourtName(Object courtName) {
            this.courtName = courtName;
        }

        public Object getCheckerName() {
            return checkerName;
        }

        public void setCheckerName(Object checkerName) {
            this.checkerName = checkerName;
        }

        public Object getCheckerPhone() {
            return checkerPhone;
        }

        public void setCheckerPhone(Object checkerPhone) {
            this.checkerPhone = checkerPhone;
        }

        public String getTransferTime() {
            return transferTime;
        }

        public void setTransferTime(String transferTime) {
            this.transferTime = transferTime;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public int getStepNum() {
            return stepNum;
        }

        public void setStepNum(int stepNum) {
            this.stepNum = stepNum;
        }

        public String getTaskUrl() {
            return taskUrl;
        }

        public void setTaskUrl(String taskUrl) {
            this.taskUrl = taskUrl;
        }

        public Object getQuestion() {
            return question;
        }

        public void setQuestion(Object question) {
            this.question = question;
        }

        public Object getTiaoText() {
            return tiaoText;
        }

        public void setTiaoText(Object tiaoText) {
            this.tiaoText = tiaoText;
        }
    }
}
