package com.base.baselib.bean;

import java.util.List;

/**
 * 主页菜单
 */
public class Menu {


    /**
     * titleName : 移动监督管理
     * list : [{"menuSon":[{"id":1,"parentId":12,"typeId":1,"imgsrc":"http://qjezfefhb.hn-bkt.clouddn.com/sjzf_icon.png","title":"待办任务","num":null},{"id":2,"parentId":12,"typeId":1,"imgsrc":"http://qjezfefhb.hn-bkt.clouddn.com/fjrw_icon.png","title":"复检任务","num":null},{"id":3,"parentId":12,"typeId":1,"imgsrc":"http://qjezfefhb.hn-bkt.clouddn.com/tszf_icon.png","title":"投诉待办任务","num":null},{"id":4,"parentId":12,"typeId":1,"imgsrc":"http://qjezfefhb.hn-bkt.clouddn.com/rcxg_icon.png","title":"未读消息","num":null}],"title":"今日任务"},{"menuSon":[{"id":5,"parentId":13,"typeId":1,"imgsrc":"http://qjezfefhb.hn-bkt.clouddn.com/dsh_icon.png","title":"待复检任务","num":null},{"id":6,"parentId":13,"typeId":1,"imgsrc":"http://qjezfefhb.hn-bkt.clouddn.com/ywc_icon.png","title":"已完成任务","num":null}],"title":"其他任务"}]
     * headerTitle : 今日任务
     */

    private String titleName;
    private String headerTitle;
    private List<ListBean> list;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * menuSon : [{"id":1,"parentId":12,"typeId":1,"imgsrc":"http://qjezfefhb.hn-bkt.clouddn.com/sjzf_icon.png","title":"待办任务","num":null},{"id":2,"parentId":12,"typeId":1,"imgsrc":"http://qjezfefhb.hn-bkt.clouddn.com/fjrw_icon.png","title":"复检任务","num":null},{"id":3,"parentId":12,"typeId":1,"imgsrc":"http://qjezfefhb.hn-bkt.clouddn.com/tszf_icon.png","title":"投诉待办任务","num":null},{"id":4,"parentId":12,"typeId":1,"imgsrc":"http://qjezfefhb.hn-bkt.clouddn.com/rcxg_icon.png","title":"未读消息","num":null}]
         * title : 今日任务
         */

        private String title;
        private List<MenuSonBean> menuSon;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<MenuSonBean> getMenuSon() {
            return menuSon;
        }

        public void setMenuSon(List<MenuSonBean> menuSon) {
            this.menuSon = menuSon;
        }

        public static class MenuSonBean {
            /**
             * id : 1
             * parentId : 12
             * typeId : 1
             * imgsrc : http://qjezfefhb.hn-bkt.clouddn.com/sjzf_icon.png
             * title : 待办任务
             * num : null
             */

            private String id;
            private int parentId;
            private int typeId;
            private String imgsrc;
            private String title;
            private String url;
            private int num;
            private int check;

            public int getCheck() {
                return check;
            }

            public void setCheck(int check) {
                this.check = check;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }
        }
    }
}
