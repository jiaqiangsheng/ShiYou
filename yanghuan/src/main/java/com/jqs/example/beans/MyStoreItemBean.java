package com.jqs.example.beans;

import java.io.Serializable;

/**
 * Created by qiangsheng on 2016/5/18.
 */
public class MyStoreItemBean implements Serializable{
    //小说id
    private int xsid;
    //小说名
    private String xsName;
    //小说简介
    private String xsIntroduce;
    //小说作者
    private String xsAuthor;
    //兑换量
    private String xsNumber;
    //小说图片路径
    private  String xsPicture;
    //布局类型
    private int flag;
    //判断item是否可以被点击标志
    private int item_flag;

    public MyStoreItemBean(int xsid,String xsName, String xsIntroduce, String xsAuthor,
                           String xsNumber, String xsPicture, int flag,int item_flag) {
        this.xsid=xsid;
        this.xsName = xsName;
        this.xsIntroduce = xsIntroduce;
        this.xsAuthor = xsAuthor;
        this.xsNumber = xsNumber;
        this.xsPicture = xsPicture;
        this.flag = flag;
        this.item_flag=item_flag;
    }

    public int getXsid() {
        return xsid;
    }

    public void setXsid(int xsid) {
        this.xsid = xsid;
    }

    public int getItem_flag() {
        return item_flag;
    }

    public void setItem_flag(int item_flag) {
        this.item_flag = item_flag;
    }

    public String getXsName() {
        return xsName;
    }

    public void setXsName(String xsName) {
        this.xsName = xsName;
    }

    public String getXsIntroduce() {
        return xsIntroduce;
    }

    public void setXsIntroduce(String xsIntroduce) {
        this.xsIntroduce = xsIntroduce;
    }

    public String getXsAuthor() {
        return xsAuthor;
    }

    public void setXsAuthor(String xsAuthor) {
        this.xsAuthor = xsAuthor;
    }

    public String getXsNumber() {
        return xsNumber;
    }

    public void setXsNumber(String xsNumber) {
        this.xsNumber = xsNumber;
    }

    public String getXsPicture() {
        return xsPicture;
    }

    public void setXsPicture(String xsPicture) {
        this.xsPicture = xsPicture;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "MyStoreItemBean{" +
                "xsName='" + xsName + '\'' +
                ", xsIntroduce='" + xsIntroduce + '\'' +
                ", xsAuthor='" + xsAuthor + '\'' +
                ", xsNumber='" + xsNumber + '\'' +
                ", xsPicture='" + xsPicture + '\'' +
                ", flag=" + flag +
                ", item_flag=" + item_flag +
                '}';
    }
}
