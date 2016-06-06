package com.yanghuan.Beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by C5-0 on 2016/6/6.
 */
public class History implements Serializable {
    //历史图片
    String image;
    //历史时间
    Date date;
    //卫视名字
    String name;

    public History() {
    }

    public History(String image, Date date, String name) {
        this.image = image;
        this.date = date;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
