package com.base.baselib.bean;

import java.io.Serializable;
import java.util.List;

public class ElevatorInfo implements Serializable {

    private List<ListExitBeanX> listExit;
    private List<ListElevatorBeanX> listElevator;

    public List<ListExitBeanX> getListExit() {
        return listExit;
    }

    public void setListExit(List<ListExitBeanX> listExit) {
        this.listExit = listExit;
    }

    public List<ListElevatorBeanX> getListElevator() {
        return listElevator;
    }

    public void setListElevator(List<ListElevatorBeanX> listElevator) {
        this.listElevator = listElevator;
    }

    public static class ListExitBeanX {

        /**
         * id : 6
         * categoryType : 1
         * elevatorId : 179
         * manufacturerId : 14
         * dataId : 1
         * taskDistributeId : 545
         * userId : 285
         * userName : 席小龙
         * createTime : 2021-04-04T13:37:37.000+0000
         * checkResult : null
         * processingResult : null
         * info : null
         * singer : null
         * singerPic : null
         * signDate : null
         * countNum : 1
         * htmlPic : null
         * checkData : null
         * status : 0
         * type : null
         * dtNumberno : 16D056F134C74782B4F307182729EB8D
         * dtNname : 曳引驱动乘客电梯
         * dtAddress : （乾州）吉首市乾州新区滨江路
         */

        private int id;
        private int categoryType;
        private int elevatorId;
        private int manufacturerId;
        private int dataId;
        private int taskDistributeId;
        private int userId;
        private String userName;
        private String createTime;
        private Object checkResult;
        private Object processingResult;
        private Object info;
        private Object singer;
        private Object singerPic;
        private Object signDate;
        private int countNum;
        private Object htmlPic;
        private Object checkData;
        private int status;
        private Object type;
        private String dtNumberno;
        private String dtNname;
        private String dtAddress;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCategoryType() {
            return categoryType;
        }

        public void setCategoryType(int categoryType) {
            this.categoryType = categoryType;
        }

        public int getElevatorId() {
            return elevatorId;
        }

        public void setElevatorId(int elevatorId) {
            this.elevatorId = elevatorId;
        }

        public int getManufacturerId() {
            return manufacturerId;
        }

        public void setManufacturerId(int manufacturerId) {
            this.manufacturerId = manufacturerId;
        }

        public int getDataId() {
            return dataId;
        }

        public void setDataId(int dataId) {
            this.dataId = dataId;
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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getCheckResult() {
            return checkResult;
        }

        public void setCheckResult(Object checkResult) {
            this.checkResult = checkResult;
        }

        public Object getProcessingResult() {
            return processingResult;
        }

        public void setProcessingResult(Object processingResult) {
            this.processingResult = processingResult;
        }

        public Object getInfo() {
            return info;
        }

        public void setInfo(Object info) {
            this.info = info;
        }

        public Object getSinger() {
            return singer;
        }

        public void setSinger(Object singer) {
            this.singer = singer;
        }

        public Object getSingerPic() {
            return singerPic;
        }

        public void setSingerPic(Object singerPic) {
            this.singerPic = singerPic;
        }

        public Object getSignDate() {
            return signDate;
        }

        public void setSignDate(Object signDate) {
            this.signDate = signDate;
        }

        public int getCountNum() {
            return countNum;
        }

        public void setCountNum(int countNum) {
            this.countNum = countNum;
        }

        public Object getHtmlPic() {
            return htmlPic;
        }

        public void setHtmlPic(Object htmlPic) {
            this.htmlPic = htmlPic;
        }

        public Object getCheckData() {
            return checkData;
        }

        public void setCheckData(Object checkData) {
            this.checkData = checkData;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getDtNumberno() {
            return dtNumberno;
        }

        public void setDtNumberno(String dtNumberno) {
            this.dtNumberno = dtNumberno;
        }

        public String getDtNname() {
            return dtNname;
        }

        public void setDtNname(String dtNname) {
            this.dtNname = dtNname;
        }

        public String getDtAddress() {
            return dtAddress;
        }

        public void setDtAddress(String dtAddress) {
            this.dtAddress = dtAddress;
        }
    }

    public static class ListElevatorBeanX {
        /**
         * countNum : 35
         * typeName : 曳引驱动乘客电梯
         * listElevator : [{"id":2174,"name":"曳引驱动载货电梯","code":"312043310020190010","branchoffice":"第一分局","numberno":"FBDF5FF1A064492084939B36ACD3DC7F","elevatorarea":"吉首","typeCategory":1,"typeCategoryChild":3,"typeCategoryChildLower":10,"province":"43","city":"4331","area":"433101","address":"","region1":"湘西经开区武陵山大道5号","region2":"0","modelType":"HOPE-IIG","price":0,"identificationCode":"","registerType":1,"registerStatus":2,"registerDate":"2019-06-18T08:00:00.000+0000","registerOrganization":"湘西土家族苗族自治州市场监督管理局","registerUsername":"张春蓉","registerUpdateDate":"1969-12-31T16:00:00.000+0000","registerNo":"梯12湘U00019(19)","registerCode":"31104331002017053117","registerNumber":"","registerLevel":0,"issuedCompany":"湘西土家族苗族自治州市场监督管理局","issuedType":1,"issuedDate":"2019-06-18T08:00:00.000+0000","issuedFastdate":"1969-12-31T16:00:00.000+0000","issuedEnddate":"1969-12-31T16:00:00.000+0000","issuedStatus":1,"issuedStatusUpdate":"1969-12-31T16:00:00.000+0000","issuedIsmonitor":2,"issuedIspop":2,"issuedIsplace":2,"issuedIsequipment":2,"issuedAccidenttype":0,"issuedBusinessstatus":0,"issuedBusinessstatusdate":"2017-04-12T08:00:00.000+0000","userCompayId":1,"securitySecurity":"","securityLinkname":"王刚","securityLinkmobile":"18874341787","securityUsername":"刘科建","securityUsertel":"13974377930","securityUsermobile":"13974377930","securityUseaddress":"创新创业示范园D栋2单元","securityCompayaddress":"创新创业示范园D栋2单元","securityCompaycode":"D2-15#","securityUsepalce":"创新创业示范园D栋2单元","securityUsedate":"1969-12-31T16:00:00.000+0000","manufacturerId":1,"weibaoId":2172,"createTime":"2020-06-27T23:42:34.000+0000","weibaoName":null,"compayName":"湘西自治州武陵物业管理有限公司（创新创业示范园）","elevatorBaseInfo":null,"elevatorTheme":null},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}]
         */

        private int countNum;
        private String typeName;
        private int viewType;
        private List<ListElevatorBean> listElevator;

        public int getViewType() {
            return viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }

        public int getCountNum() {
            return countNum;
        }

        public void setCountNum(int countNum) {
            this.countNum = countNum;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public List<ListElevatorBean> getListElevator() {
            return listElevator;
        }

        public void setListElevator(List<ListElevatorBean> listElevator) {
            this.listElevator = listElevator;
        }

        public static class ListElevatorBean {
            /**
             * id : 2174
             * name : 曳引驱动载货电梯
             * code : 312043310020190010
             * branchoffice : 第一分局
             * numberno : FBDF5FF1A064492084939B36ACD3DC7F
             * elevatorarea : 吉首
             * typeCategory : 1
             * typeCategoryChild : 3
             * typeCategoryChildLower : 10
             * province : 43
             * city : 4331
             * area : 433101
             * address :
             * region1 : 湘西经开区武陵山大道5号
             * region2 : 0
             * modelType : HOPE-IIG
             * price : 0
             * identificationCode :
             * registerType : 1
             * registerStatus : 2
             * registerDate : 2019-06-18T08:00:00.000+0000
             * registerOrganization : 湘西土家族苗族自治州市场监督管理局
             * registerUsername : 张春蓉
             * registerUpdateDate : 1969-12-31T16:00:00.000+0000
             * registerNo : 梯12湘U00019(19)
             * registerCode : 31104331002017053117
             * registerNumber :
             * registerLevel : 0
             * issuedCompany : 湘西土家族苗族自治州市场监督管理局
             * issuedType : 1
             * issuedDate : 2019-06-18T08:00:00.000+0000
             * issuedFastdate : 1969-12-31T16:00:00.000+0000
             * issuedEnddate : 1969-12-31T16:00:00.000+0000
             * issuedStatus : 1
             * issuedStatusUpdate : 1969-12-31T16:00:00.000+0000
             * issuedIsmonitor : 2
             * issuedIspop : 2
             * issuedIsplace : 2
             * issuedIsequipment : 2
             * issuedAccidenttype : 0
             * issuedBusinessstatus : 0
             * issuedBusinessstatusdate : 2017-04-12T08:00:00.000+0000
             * userCompayId : 1
             * securitySecurity :
             * securityLinkname : 王刚
             * securityLinkmobile : 18874341787
             * securityUsername : 刘科建
             * securityUsertel : 13974377930
             * securityUsermobile : 13974377930
             * securityUseaddress : 创新创业示范园D栋2单元
             * securityCompayaddress : 创新创业示范园D栋2单元
             * securityCompaycode : D2-15#
             * securityUsepalce : 创新创业示范园D栋2单元
             * securityUsedate : 1969-12-31T16:00:00.000+0000
             * manufacturerId : 1
             * weibaoId : 2172
             * createTime : 2020-06-27T23:42:34.000+0000
             * weibaoName : null
             * compayName : 湘西自治州武陵物业管理有限公司（创新创业示范园）
             * elevatorBaseInfo : null
             * elevatorTheme : null
             */

            private int id;
            private String name;
            private String code;
            private String branchoffice;
            private String numberno;
            private String elevatorarea;
            private int typeCategory;
            private int typeCategoryChild;
            private int typeCategoryChildLower;
            private String province;
            private String city;
            private String area;
            private String address;
            private String region1;
            private String region2;
            private String modelType;
            private int price;
            private String identificationCode;
            private int registerType;
            private int registerStatus;
            private String registerDate;
            private String registerOrganization;
            private String registerUsername;
            private String registerUpdateDate;
            private String registerNo;
            private String registerCode;
            private String registerNumber;
            private int registerLevel;
            private String issuedCompany;
            private int issuedType;
            private String issuedDate;
            private String issuedFastdate;
            private String issuedEnddate;
            private int issuedStatus;
            private String issuedStatusUpdate;
            private int issuedIsmonitor;
            private int issuedIspop;
            private int issuedIsplace;
            private int issuedIsequipment;
            private int issuedAccidenttype;
            private int issuedBusinessstatus;
            private String issuedBusinessstatusdate;
            private int userCompayId;
            private String securitySecurity;
            private String securityLinkname;
            private String securityLinkmobile;
            private String securityUsername;
            private String securityUsertel;
            private String securityUsermobile;
            private String securityUseaddress;
            private String securityCompayaddress;
            private String securityCompaycode;
            private String securityUsepalce;
            private String securityUsedate;
            private int manufacturerId;
            private int weibaoId;
            private String createTime;
            private boolean select;
            private Object weibaoName;
            private String compayName;
            private Object elevatorBaseInfo;
            private Object elevatorTheme;

            public boolean isSelect() {
                return select;
            }

            public void setSelect(boolean select) {
                this.select = select;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getBranchoffice() {
                return branchoffice;
            }

            public void setBranchoffice(String branchoffice) {
                this.branchoffice = branchoffice;
            }

            public String getNumberno() {
                return numberno;
            }

            public void setNumberno(String numberno) {
                this.numberno = numberno;
            }

            public String getElevatorarea() {
                return elevatorarea;
            }

            public void setElevatorarea(String elevatorarea) {
                this.elevatorarea = elevatorarea;
            }

            public int getTypeCategory() {
                return typeCategory;
            }

            public void setTypeCategory(int typeCategory) {
                this.typeCategory = typeCategory;
            }

            public int getTypeCategoryChild() {
                return typeCategoryChild;
            }

            public void setTypeCategoryChild(int typeCategoryChild) {
                this.typeCategoryChild = typeCategoryChild;
            }

            public int getTypeCategoryChildLower() {
                return typeCategoryChildLower;
            }

            public void setTypeCategoryChildLower(int typeCategoryChildLower) {
                this.typeCategoryChildLower = typeCategoryChildLower;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
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

            public String getModelType() {
                return modelType;
            }

            public void setModelType(String modelType) {
                this.modelType = modelType;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getIdentificationCode() {
                return identificationCode;
            }

            public void setIdentificationCode(String identificationCode) {
                this.identificationCode = identificationCode;
            }

            public int getRegisterType() {
                return registerType;
            }

            public void setRegisterType(int registerType) {
                this.registerType = registerType;
            }

            public int getRegisterStatus() {
                return registerStatus;
            }

            public void setRegisterStatus(int registerStatus) {
                this.registerStatus = registerStatus;
            }

            public String getRegisterDate() {
                return registerDate;
            }

            public void setRegisterDate(String registerDate) {
                this.registerDate = registerDate;
            }

            public String getRegisterOrganization() {
                return registerOrganization;
            }

            public void setRegisterOrganization(String registerOrganization) {
                this.registerOrganization = registerOrganization;
            }

            public String getRegisterUsername() {
                return registerUsername;
            }

            public void setRegisterUsername(String registerUsername) {
                this.registerUsername = registerUsername;
            }

            public String getRegisterUpdateDate() {
                return registerUpdateDate;
            }

            public void setRegisterUpdateDate(String registerUpdateDate) {
                this.registerUpdateDate = registerUpdateDate;
            }

            public String getRegisterNo() {
                return registerNo;
            }

            public void setRegisterNo(String registerNo) {
                this.registerNo = registerNo;
            }

            public String getRegisterCode() {
                return registerCode;
            }

            public void setRegisterCode(String registerCode) {
                this.registerCode = registerCode;
            }

            public String getRegisterNumber() {
                return registerNumber;
            }

            public void setRegisterNumber(String registerNumber) {
                this.registerNumber = registerNumber;
            }

            public int getRegisterLevel() {
                return registerLevel;
            }

            public void setRegisterLevel(int registerLevel) {
                this.registerLevel = registerLevel;
            }

            public String getIssuedCompany() {
                return issuedCompany;
            }

            public void setIssuedCompany(String issuedCompany) {
                this.issuedCompany = issuedCompany;
            }

            public int getIssuedType() {
                return issuedType;
            }

            public void setIssuedType(int issuedType) {
                this.issuedType = issuedType;
            }

            public String getIssuedDate() {
                return issuedDate;
            }

            public void setIssuedDate(String issuedDate) {
                this.issuedDate = issuedDate;
            }

            public String getIssuedFastdate() {
                return issuedFastdate;
            }

            public void setIssuedFastdate(String issuedFastdate) {
                this.issuedFastdate = issuedFastdate;
            }

            public String getIssuedEnddate() {
                return issuedEnddate;
            }

            public void setIssuedEnddate(String issuedEnddate) {
                this.issuedEnddate = issuedEnddate;
            }

            public int getIssuedStatus() {
                return issuedStatus;
            }

            public void setIssuedStatus(int issuedStatus) {
                this.issuedStatus = issuedStatus;
            }

            public String getIssuedStatusUpdate() {
                return issuedStatusUpdate;
            }

            public void setIssuedStatusUpdate(String issuedStatusUpdate) {
                this.issuedStatusUpdate = issuedStatusUpdate;
            }

            public int getIssuedIsmonitor() {
                return issuedIsmonitor;
            }

            public void setIssuedIsmonitor(int issuedIsmonitor) {
                this.issuedIsmonitor = issuedIsmonitor;
            }

            public int getIssuedIspop() {
                return issuedIspop;
            }

            public void setIssuedIspop(int issuedIspop) {
                this.issuedIspop = issuedIspop;
            }

            public int getIssuedIsplace() {
                return issuedIsplace;
            }

            public void setIssuedIsplace(int issuedIsplace) {
                this.issuedIsplace = issuedIsplace;
            }

            public int getIssuedIsequipment() {
                return issuedIsequipment;
            }

            public void setIssuedIsequipment(int issuedIsequipment) {
                this.issuedIsequipment = issuedIsequipment;
            }

            public int getIssuedAccidenttype() {
                return issuedAccidenttype;
            }

            public void setIssuedAccidenttype(int issuedAccidenttype) {
                this.issuedAccidenttype = issuedAccidenttype;
            }

            public int getIssuedBusinessstatus() {
                return issuedBusinessstatus;
            }

            public void setIssuedBusinessstatus(int issuedBusinessstatus) {
                this.issuedBusinessstatus = issuedBusinessstatus;
            }

            public String getIssuedBusinessstatusdate() {
                return issuedBusinessstatusdate;
            }

            public void setIssuedBusinessstatusdate(String issuedBusinessstatusdate) {
                this.issuedBusinessstatusdate = issuedBusinessstatusdate;
            }

            public int getUserCompayId() {
                return userCompayId;
            }

            public void setUserCompayId(int userCompayId) {
                this.userCompayId = userCompayId;
            }

            public String getSecuritySecurity() {
                return securitySecurity;
            }

            public void setSecuritySecurity(String securitySecurity) {
                this.securitySecurity = securitySecurity;
            }

            public String getSecurityLinkname() {
                return securityLinkname;
            }

            public void setSecurityLinkname(String securityLinkname) {
                this.securityLinkname = securityLinkname;
            }

            public String getSecurityLinkmobile() {
                return securityLinkmobile;
            }

            public void setSecurityLinkmobile(String securityLinkmobile) {
                this.securityLinkmobile = securityLinkmobile;
            }

            public String getSecurityUsername() {
                return securityUsername;
            }

            public void setSecurityUsername(String securityUsername) {
                this.securityUsername = securityUsername;
            }

            public String getSecurityUsertel() {
                return securityUsertel;
            }

            public void setSecurityUsertel(String securityUsertel) {
                this.securityUsertel = securityUsertel;
            }

            public String getSecurityUsermobile() {
                return securityUsermobile;
            }

            public void setSecurityUsermobile(String securityUsermobile) {
                this.securityUsermobile = securityUsermobile;
            }

            public String getSecurityUseaddress() {
                return securityUseaddress;
            }

            public void setSecurityUseaddress(String securityUseaddress) {
                this.securityUseaddress = securityUseaddress;
            }

            public String getSecurityCompayaddress() {
                return securityCompayaddress;
            }

            public void setSecurityCompayaddress(String securityCompayaddress) {
                this.securityCompayaddress = securityCompayaddress;
            }

            public String getSecurityCompaycode() {
                return securityCompaycode;
            }

            public void setSecurityCompaycode(String securityCompaycode) {
                this.securityCompaycode = securityCompaycode;
            }

            public String getSecurityUsepalce() {
                return securityUsepalce;
            }

            public void setSecurityUsepalce(String securityUsepalce) {
                this.securityUsepalce = securityUsepalce;
            }

            public String getSecurityUsedate() {
                return securityUsedate;
            }

            public void setSecurityUsedate(String securityUsedate) {
                this.securityUsedate = securityUsedate;
            }

            public int getManufacturerId() {
                return manufacturerId;
            }

            public void setManufacturerId(int manufacturerId) {
                this.manufacturerId = manufacturerId;
            }

            public int getWeibaoId() {
                return weibaoId;
            }

            public void setWeibaoId(int weibaoId) {
                this.weibaoId = weibaoId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getWeibaoName() {
                return weibaoName;
            }

            public void setWeibaoName(Object weibaoName) {
                this.weibaoName = weibaoName;
            }

            public String getCompayName() {
                return compayName;
            }

            public void setCompayName(String compayName) {
                this.compayName = compayName;
            }

            public Object getElevatorBaseInfo() {
                return elevatorBaseInfo;
            }

            public void setElevatorBaseInfo(Object elevatorBaseInfo) {
                this.elevatorBaseInfo = elevatorBaseInfo;
            }

            public Object getElevatorTheme() {
                return elevatorTheme;
            }

            public void setElevatorTheme(Object elevatorTheme) {
                this.elevatorTheme = elevatorTheme;
            }
        }
    }
}
