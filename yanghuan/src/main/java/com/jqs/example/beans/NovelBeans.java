package com.jqs.example.beans;

import java.io.Serializable;

/**
 * Created by qiangsheng on 2016/5/31.
 */
public class NovelBeans implements Serializable{
    private String id;
    private String author;
    private String name;
    private String newchapter;
    private String updateTime;

    public NovelBeans(String id, String author, String name, String newchapter, String updateTime) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.newchapter = newchapter;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewchapter() {
        return newchapter;
    }

    public void setNewchapter(String newchapter) {
        this.newchapter = newchapter;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
