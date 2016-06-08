package com.lxx.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 潇 on 2016/5/13.
 */
public class News implements Serializable {
    String channelId;
    String channelName;
    String desc;
    String title;
    String pubDate;
    String source;
    String link;

    @Override
    public String toString() {
        return "News{" +
                "allList=" + allList +
                '}';
    }

    public News(String channelId, String channelName, String desc, String title, String pubDate, String source, String link, List<String> allList, List<ImageSource> imageSourceList) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.desc = desc;
        this.title = title;
        this.pubDate = pubDate;
        this.source = source;
        this.link = link;
        this.allList = allList;
        this.imageSourceList = imageSourceList;
    }

    public List<String> getAllList() {
        return allList;
    }

    public void setAllList(List<String> allList) {
        this.allList = allList;
    }

    public List<ImageSource> getImageSourceList() {
        return imageSourceList;
    }

    public void setImageSourceList(List<ImageSource> imageSourceList) {
        this.imageSourceList = imageSourceList;
    }

    List<String> allList;
    List<ImageSource> imageSourceList;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }



    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }



    public News(String channelId, String channelName, String desc, String title, String pubDate, String source, String link, List<ImageSource> imageSourceList) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.desc = desc;
        this.title = title;
        this.pubDate = pubDate;
        this.source = source;
        this.link = link;
        this.imageSourceList = imageSourceList;
    }

    public List<ImageSource> getImageList() {
        return imageSourceList;
    }

    public void setImageList( List<ImageSource> imageSourceList) {
        this.imageSourceList = imageSourceList;
    }
}
