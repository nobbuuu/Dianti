package com.base.baselib.bean;

import java.util.List;

public class HistotyBean {

    /**
     * totalCount : 2
     * list : [{"pointList":[{"id":16,"title":"美丽湘西","taskId":1,"taskDistributeId":5,"userId":335,"userName":"杨雪露","checkData":"UK咯哦拖","createTime":"2021-11-08","videoImg":null,"xcqzVideo":"","xcqzImg":"[{\"filePath\":\"/storage/emulated/0/vchat/image/wKgcQlz4h7CASWkHAAAhJ_1dCIQ992.bmp\",\"imgUrl\":\"https://img.znzhjh.com/627d71584f33012316be534b1344831e\"},{\"filePath\":\"/storage/emulated/0/vchat/image/wKgcQlz4iHuAFpR9AAAoelEK9zY679.bmp\",\"imgUrl\":\"https://img.znzhjh.com/1a4c77a7d65b19eff39b318fcc29d153\"},{\"filePath\":\"/storage/emulated/0/DCIM/Screenshots/Screenshot_20190628_114447.jpg\",\"imgUrl\":\"https://img.znzhjh.com/7604b753a894d1f4ef52ef5002c545f7\"},{\"filePath\":\"/storage/emulated/0/news_article/86cad047ff0dfb15b0c86c6457b18651.jpg\",\"imgUrl\":\"https://img.znzhjh.com/24fab4e1ab99c8dba8ad6c6a1235ddfe\"},{\"filePath\":\"/storage/emulated/0/news_article/35894fd197783071b75fa00e101e13c8.jpg\",\"imgUrl\":\"https://img.znzhjh.com/fbb5c86cf6e6a4500ae4d01695b6b8e8\"},{\"filePath\":\"/storage/emulated/0/news_article/85d6d4c72ef74272a16b9dc0d9835914.jpg\",\"imgUrl\":\"https://img.znzhjh.com/d372a7d35edb947e74d6f24bbb3f3a1c\"}]","htmlPic":null,"signerPic":null,"signDate":null,"searchDate":null}],"searchDate":"2021-11-08"}]
     */

    private int totalCount;
    private List<ListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * pointList : [{"id":16,"title":"美丽湘西","taskId":1,"taskDistributeId":5,"userId":335,"userName":"杨雪露","checkData":"UK咯哦拖","createTime":"2021-11-08","videoImg":null,"xcqzVideo":"","xcqzImg":"[{\"filePath\":\"/storage/emulated/0/vchat/image/wKgcQlz4h7CASWkHAAAhJ_1dCIQ992.bmp\",\"imgUrl\":\"https://img.znzhjh.com/627d71584f33012316be534b1344831e\"},{\"filePath\":\"/storage/emulated/0/vchat/image/wKgcQlz4iHuAFpR9AAAoelEK9zY679.bmp\",\"imgUrl\":\"https://img.znzhjh.com/1a4c77a7d65b19eff39b318fcc29d153\"},{\"filePath\":\"/storage/emulated/0/DCIM/Screenshots/Screenshot_20190628_114447.jpg\",\"imgUrl\":\"https://img.znzhjh.com/7604b753a894d1f4ef52ef5002c545f7\"},{\"filePath\":\"/storage/emulated/0/news_article/86cad047ff0dfb15b0c86c6457b18651.jpg\",\"imgUrl\":\"https://img.znzhjh.com/24fab4e1ab99c8dba8ad6c6a1235ddfe\"},{\"filePath\":\"/storage/emulated/0/news_article/35894fd197783071b75fa00e101e13c8.jpg\",\"imgUrl\":\"https://img.znzhjh.com/fbb5c86cf6e6a4500ae4d01695b6b8e8\"},{\"filePath\":\"/storage/emulated/0/news_article/85d6d4c72ef74272a16b9dc0d9835914.jpg\",\"imgUrl\":\"https://img.znzhjh.com/d372a7d35edb947e74d6f24bbb3f3a1c\"}]","htmlPic":null,"signerPic":null,"signDate":null,"searchDate":null}]
         * searchDate : 2021-11-08
         */

        private String searchDate;
        private List<PointListBean> pointList;

        public String getSearchDate() {
            return searchDate;
        }

        public void setSearchDate(String searchDate) {
            this.searchDate = searchDate;
        }

        public List<PointListBean> getPointList() {
            return pointList;
        }

        public void setPointList(List<PointListBean> pointList) {
            this.pointList = pointList;
        }

        public static class PointListBean {
            /**
             * id : 16
             * title : 美丽湘西
             * taskId : 1
             * taskDistributeId : 5
             * userId : 335
             * userName : 杨雪露
             * checkData : UK咯哦拖
             * createTime : 2021-11-08
             * videoImg : null
             * xcqzVideo :
             * xcqzImg : [{"filePath":"/storage/emulated/0/vchat/image/wKgcQlz4h7CASWkHAAAhJ_1dCIQ992.bmp","imgUrl":"https://img.znzhjh.com/627d71584f33012316be534b1344831e"},{"filePath":"/storage/emulated/0/vchat/image/wKgcQlz4iHuAFpR9AAAoelEK9zY679.bmp","imgUrl":"https://img.znzhjh.com/1a4c77a7d65b19eff39b318fcc29d153"},{"filePath":"/storage/emulated/0/DCIM/Screenshots/Screenshot_20190628_114447.jpg","imgUrl":"https://img.znzhjh.com/7604b753a894d1f4ef52ef5002c545f7"},{"filePath":"/storage/emulated/0/news_article/86cad047ff0dfb15b0c86c6457b18651.jpg","imgUrl":"https://img.znzhjh.com/24fab4e1ab99c8dba8ad6c6a1235ddfe"},{"filePath":"/storage/emulated/0/news_article/35894fd197783071b75fa00e101e13c8.jpg","imgUrl":"https://img.znzhjh.com/fbb5c86cf6e6a4500ae4d01695b6b8e8"},{"filePath":"/storage/emulated/0/news_article/85d6d4c72ef74272a16b9dc0d9835914.jpg","imgUrl":"https://img.znzhjh.com/d372a7d35edb947e74d6f24bbb3f3a1c"}]
             * htmlPic : null
             * signerPic : null
             * signDate : null
             * searchDate : null
             */

            private int id;
            private String title;
            private int taskId;
            private int taskDistributeId;
            private int userId;
            private String userName;
            private String checkData;
            private String createTime;
            private Object videoImg;
            private String xcqzVideo;
            private String xcqzImg;
            private Object htmlPic;
            private Object signerPic;
            private Object signDate;
            private Object searchDate;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getTaskId() {
                return taskId;
            }

            public void setTaskId(int taskId) {
                this.taskId = taskId;
            }

            public int getTaskDistributeId() {
                return taskDistributeId;
            }

            public void setTaskDistributeId(int taskDistributeId) {
                this.taskDistributeId = taskDistributeId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getCheckData() {
                return checkData;
            }

            public void setCheckData(String checkData) {
                this.checkData = checkData;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getVideoImg() {
                return videoImg;
            }

            public void setVideoImg(Object videoImg) {
                this.videoImg = videoImg;
            }

            public String getXcqzVideo() {
                return xcqzVideo;
            }

            public void setXcqzVideo(String xcqzVideo) {
                this.xcqzVideo = xcqzVideo;
            }

            public String getXcqzImg() {
                return xcqzImg;
            }

            public void setXcqzImg(String xcqzImg) {
                this.xcqzImg = xcqzImg;
            }

            public Object getHtmlPic() {
                return htmlPic;
            }

            public void setHtmlPic(Object htmlPic) {
                this.htmlPic = htmlPic;
            }

            public Object getSignerPic() {
                return signerPic;
            }

            public void setSignerPic(Object signerPic) {
                this.signerPic = signerPic;
            }

            public Object getSignDate() {
                return signDate;
            }

            public void setSignDate(Object signDate) {
                this.signDate = signDate;
            }

            public Object getSearchDate() {
                return searchDate;
            }

            public void setSearchDate(Object searchDate) {
                this.searchDate = searchDate;
            }
        }
    }
}
