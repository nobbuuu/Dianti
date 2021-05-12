package com.base.baselib.bean;

import java.io.File;

public class AppImage {
    public String name;
    public String desc;

    public String fileName;

    public File imageFile;

    @Override
    public String toString() {
        return "AppImage{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", fileName='" + fileName + '\'' +
                ", imageFile=" + imageFile +
                '}';
    }
}
