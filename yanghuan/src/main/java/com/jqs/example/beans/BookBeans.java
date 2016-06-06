package com.jqs.example.beans;

/**
 * Created by qiangsheng on 2016/5/23.
 */
import java.io.Serializable;

public class BookBeans implements Serializable{
    private int uid;
    private String name;
    private String hLocation;
    private String hData;
    private String upicture;
    public BookBeans( String name, String hLocation, String hData,
                      String upicture) {
        super();
        this.name = name;
        this.hLocation = hLocation;
        this.hData = hData;
        this.upicture = upicture;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getUpicture() {
        return upicture;
    }
    public void setUpicture(String upicture) {
        this.upicture = upicture;
    }

    @Override
    public String toString() {
        return "BookBeans{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", hLocation='" + hLocation + '\'' +
                ", hData='" + hData + '\'' +
                ", upicture='" + upicture + '\'' +
                '}';
    }
}
