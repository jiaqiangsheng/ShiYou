package com.lxx.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 潇 on 2016/5/24.
 */
public class Lxx_BeanContext implements Serializable {
    int cid;//说说id
    String user;
    int useruid;//评论人id

    public int getUseruid() {
        return useruid;
    }

    public void setUseruid(int useruid) {
        this.useruid = useruid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    String date;

    public String getUserpicture() {
        return userpicture;
    }

    public void setUserpicture(String userpicture) {
        this.userpicture = userpicture;
    }

    String userpicture;
    List<String> StringImage;
    String content;
    int zan;
    int pinglun;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getStringImage() {
        return StringImage;
    }

    public void setStringImage(List<String> stringImage) {
        StringImage = stringImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }

    public int getPinglun() {
        return pinglun;
    }

    public void setPinglun(int pinglun) {
        this.pinglun = pinglun;
    }

    public Lxx_BeanContext(int cid,String user, String date, String userpicture, List<String> stringImage, String content, int zan, int pinglun,int useruid) {
        this.cid=cid;
        this.user = user;
        this.date = date;
        this.userpicture = userpicture;
        StringImage = stringImage;
        this.content = content;
        this.zan = zan;
        this.pinglun = pinglun;
        this.useruid=useruid;
    }
}
