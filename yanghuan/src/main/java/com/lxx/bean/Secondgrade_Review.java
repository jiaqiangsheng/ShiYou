package com.lxx.bean;

import java.io.Serializable;

/**
 * Created by 潇 on 2016/5/25.
 */
public class Secondgrade_Review implements Serializable {
    int ruid;//回复人的id
    int rqid;//发表评论人的id
    int rcid;//评论id,区分哪条评论
    int shuoshuoid;
    String username1;
    String usetname2;

    public int getShuoshuoid() {
        return shuoshuoid;
    }

    public void setShuoshuoid(int shuoshuoid) {
        this.shuoshuoid = shuoshuoid;
    }

    public Secondgrade_Review(int ruid, int rqid, int rcid, String username1, String usetname2, String secondcontext,int shuoshuoid) {
        this.ruid = ruid;
        this.rqid = rqid;
        this.rcid = rcid;
        this.username1 = username1;
        this.usetname2 = usetname2;
        this.secondcontext = secondcontext;
        this.shuoshuoid=shuoshuoid;

    }

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public int getRqid() {
        return rqid;
    }

    public void setRqid(int rqid) {
        this.rqid = rqid;
    }

    public int getRcid() {
        return rcid;
    }

    public void setRcid(int rcid) {
        this.rcid = rcid;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getUsetname2() {
        return usetname2;
    }

    public void setUsetname2(String usetname2) {
        this.usetname2 = usetname2;
    }

    public String getSecondcontext() {
        return secondcontext;
    }

    public void setSecondcontext(String secondcontext) {
        this.secondcontext = secondcontext;
    }

    String secondcontext;
}