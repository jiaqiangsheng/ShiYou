package com.hhy.bean;

/**
 * bean 类
 * Created by yetwish on 2015-05-11
 */

public class SearchBean {
    private String iconUrl;
    private String title;
    private String content;


    public SearchBean(String iconUrl, String title, String content) {
        this.iconUrl = iconUrl;
        this.title = title;
        this.content = content;

    }


    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SearchBean{" +
                "iconUrl='" + iconUrl + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

