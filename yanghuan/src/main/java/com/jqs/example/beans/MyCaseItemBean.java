package com.jqs.example.beans;

import java.io.Serializable;

/**
 * Created by qiangsheng on 2016/5/18.
 */
public class MyCaseItemBean implements Serializable {
    //小说名
    private String xsName;
    //上次阅读位置
    private  String hLocation;
    //上次阅读时间
    private String hData;
    //小说图片路径
    private  String xsPicture;
    //checkbox是否选中标志位
    private boolean checkFlag=false;

    public MyCaseItemBean(String xsName, String hLocation, String hData, String xsPicture) {
        this.xsName = xsName;
        this.hLocation = hLocation;
        this.hData = hData;
        this.xsPicture = xsPicture;

    }

    public boolean isCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getXsName() {
        return xsName;
    }

    public void setXsName(String xsName) {
        this.xsName = xsName;
    }

    public String gethLocation() {
        return hLocation;
    }

    public void sethLocation(String hLocation) {
        this.hLocation = hLocation;
    }

    public String gethData() {
        return hData;
    }

    public void sethData(String hData) {
        this.hData = hData;
    }

    public String getXsPicture() {
        return xsPicture;
    }

    public void setXsPicture(String xsPicture) {
        this.xsPicture = xsPicture;
    }

    @Override
    public String toString() {
        return "MyCaseItemBean{" +
                "xsName='" + xsName + '\'' +
                ", hLocation='" + hLocation + '\'' +
                ", hData='" + hData + '\'' +
                ", xsPicture='" + xsPicture + '\'' +
                '}';
    }
}
