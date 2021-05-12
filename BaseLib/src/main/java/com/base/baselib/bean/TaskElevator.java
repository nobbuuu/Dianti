package com.base.baselib.bean;

import java.io.Serializable;
import java.util.List;

public class TaskElevator implements Serializable {

    public String name;//:电梯名称
    public int total;//:所属类别总数
    public List<ElevatorInfo> list;

    @Override
    public String toString() {
        return "TaskElevator{" +
                "name='" + name + '\'' +
                ", total='" + total + '\'' +
                ", list=" + list +
                '}';
    }
}
