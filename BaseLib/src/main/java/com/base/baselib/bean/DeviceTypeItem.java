package com.base.baselib.bean;

public class DeviceTypeItem {

    /**
     * id : 56
     * name : SPECIAL_EQUIPMENT
     * description : 特种设备现场安全监督检查记录（电梯）
     * listDataDictionary : null
     */

    private int id;
    private String name;
    private String description;
    private Object listDataDictionary;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getListDataDictionary() {
        return listDataDictionary;
    }

    public void setListDataDictionary(Object listDataDictionary) {
        this.listDataDictionary = listDataDictionary;
    }
}
