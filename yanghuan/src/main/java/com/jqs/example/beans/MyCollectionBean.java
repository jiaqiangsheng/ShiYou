package com.jqs.example.beans;

import java.io.Serializable;

/**
 * Created by qiangsheng on 2016/5/24.
 */
public class MyCollectionBean implements Serializable {
    //标题
    private String title;
    //简介
    private String intro;
    //内容
    private  String content;
    //图片地址
    private  String[] title_url;
    //发布时间
    private  String time;

    public MyCollectionBean(String title, String intro, String content, String[] title_url,String time) {
        this.title = title;
        this.intro = intro;
        this.content = content;
        this.title_url = title_url;
        this.time=time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getTitle_url() {
        return title_url;
    }

    public void setTitle_url(String[] title_url) {
        this.title_url = title_url;
    }
}
