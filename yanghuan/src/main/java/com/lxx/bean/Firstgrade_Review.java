package com.lxx.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 潇 on 2016/5/25.
 */
public class Firstgrade_Review implements Serializable{
    int cid;//说说的id
    int cuid;//评论人的id

    public int getCuid() {
        return cuid;
    }

    public void setCuid(int cuid) {
        this.cuid = cuid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    String feisturlUserImage;
    String fristUesrname;
    String Firstdate;
    String firstContext;
    List<Secondgrade_Review> Secondlist;

    public Firstgrade_Review(int cid,String feisturlUserImage, String fristUesrname, String firstdate, String firstContext, List<Secondgrade_Review> secondlist, int cuid) {
        this.cid=cid;
        this.cuid=cuid;
        this.feisturlUserImage = feisturlUserImage;
        this.fristUesrname = fristUesrname;
        Firstdate = firstdate;
        this.firstContext = firstContext;
        Secondlist = secondlist;
    }

    public String getFeisturlUserImage() {
        return feisturlUserImage;
    }

    public void setFeisturlUserImage(String feisturlUserImage) {
        this.feisturlUserImage = feisturlUserImage;
    }

    public String getFristUesrname() {
        return fristUesrname;
    }

    public void setFristUesrname(String fristUesrname) {
        this.fristUesrname = fristUesrname;
    }

    public String getFirstdate() {
        return Firstdate;
    }

    public void setFirstdate(String firstdate) {
        Firstdate = firstdate;
    }

    public String getFirstContext() {
        return firstContext;
    }

    public void setFirstContext(String firstContext) {
        this.firstContext = firstContext;
    }

    public List<Secondgrade_Review> getSecondlist() {
        return Secondlist;
    }

    public void setSecondlist(List<Secondgrade_Review> secondlist) {
        Secondlist = secondlist;
    }
}
