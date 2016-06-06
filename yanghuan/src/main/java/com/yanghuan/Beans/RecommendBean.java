package com.yanghuan.Beans;

import java.io.Serializable;

/**
 * Created by 杨欢 on 2016/5/14.
 */
public class RecommendBean implements Serializable {
    private String[] imageId=new String[4];
    private String title;
    private String[] content=new String[4];
    private int flag;


    public RecommendBean(String[] imageId, String title, String[] content, int flag) {
        this.imageId = imageId;
        this.title = title;
        this.content = content;
        this.flag = flag;
    }

    public String[] getImageId() {
        return imageId;
    }

    public void setImageId(String[] imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
