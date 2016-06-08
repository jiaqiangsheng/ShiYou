package com.jqs.servert.beans;

/**
 * Created by qiangsheng on 2016/5/10.
 */
import java.io.Serializable;

public class Users implements Serializable{

    private static final long serialVersionUID = 1L;

    private int uid;
    private String upicture;

    public Users(int uid, String upicture) {
        this.uid = uid;
        this.upicture = upicture;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUpicture() {
        return upicture;
    }

    public void setUpicture(String upicture) {
        this.upicture = upicture;
    }

    @Override
    public String toString() {
        return "Users{" +
                "uid=" + uid +
                ", upicture='" + upicture + '\'' +
                '}';
    }
}
