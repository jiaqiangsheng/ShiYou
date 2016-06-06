package com.hhy.bean;

import java.io.Serializable;

/**
 * Created by hanhongyang on 2016/5/16.
 */
public class Config implements Serializable {
    private String textview;
    private int flag;


    public Config() {
    }

    public Config(String textview,int flag) {
        this.textview = textview;
        this.flag = flag;

    }

    public String getTextview() {
        return textview;
    }

    public void setTextview(String textview) {
        this.textview = textview;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Config{" +
                "textview='" + textview + '\'' +
                '}';
    }
}
