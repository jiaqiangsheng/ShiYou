package com.hhy.bean;

import java.io.Serializable;

/**
 * Created by hanhongyang on 2016/5/16.
 */
public class Fans implements Serializable {
    private  int ouid;
    private String tou_imageUrl;
    private String top_text;
    private String below_text;
    private int right_image;
    private int fla;//用于判断ocare(粉丝表中双方是否相互关注0：不是1：是)
    private int flag;

    public Fans() {
    }

    public Fans(int ouid,String tou_imageUrl, String top_text, String below_text,int fla, int flag) {
        this.ouid = ouid;
        this.tou_imageUrl = tou_imageUrl;
        this.top_text = top_text;
        this.below_text = below_text;
        this.fla = fla;
        this.flag = flag;
    }

    public int getOuid() {
        return ouid;
    }

    public void setOuid(int ouid) {
        this.ouid = ouid;
    }

    public String getTou_imageUrl() {
        return tou_imageUrl;
    }

    public void setTou_imageUrl(String tou_imageUrl) {
        this.tou_imageUrl = tou_imageUrl;
    }
    public String getTop_text() {
        return top_text;
    }

    public void setTop_text(String top_text) {
        this.top_text = top_text;
    }

    public String getBelow_text() {
        return below_text;
    }

    public void setBelow_text(String below_text) {
        this.below_text = below_text;
    }

    public int getRight_image() {
        return right_image;
    }

    public void setRight_image(int right_image) {
        this.right_image = right_image;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getFla() {
        return fla;
    }

    public void setFla(int fla) {
        this.fla = fla;
    }

    @Override
    public String toString() {
        return "Fans{" +
                "tou_imageUrl='" + tou_imageUrl + '\'' +
                ", top_text='" + top_text + '\'' +
                ", below_text='" + below_text + '\'' +
                ", right_image=" + right_image +
                ", flag=" + flag +
                '}';
    }
}
