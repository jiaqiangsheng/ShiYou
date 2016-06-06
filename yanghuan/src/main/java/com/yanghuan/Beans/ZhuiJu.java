package com.yanghuan.Beans;

import java.io.Serializable;

/**
 * Created by C5-0 on 2016/6/5.
 */
public class ZhuiJu implements Serializable {
    String picture;
    String text1;
    String text2;
    String text3;


    public ZhuiJu() {
    }

    public ZhuiJu(String text1, String picture, String text2, String text3) {
        this.text1 = text1;
        this.picture = picture;
        this.text2 = text2;
        this.text3 = text3;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }
}
