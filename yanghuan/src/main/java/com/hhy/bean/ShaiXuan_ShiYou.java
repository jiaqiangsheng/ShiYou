package com.hhy.bean;

import java.io.Serializable;

/**
 * Created by hanhongyang on 2016/5/18.
 */
public class ShaiXuan_ShiYou implements Serializable{
    private String left_text;
    private String right_text;
    private int right_pic;


    public ShaiXuan_ShiYou() {
    }

    public ShaiXuan_ShiYou(String left_text, String right_text, int right_pic) {
        this.left_text = left_text;
        this.right_text = right_text;
        this.right_pic = right_pic;

    }

    public String getLeft_text() {
        return left_text;
    }

    public void setLeft_text(String left_text) {
        this.left_text = left_text;
    }

    public String getRight_text() {
        return right_text;
    }

    public void setRight_text(String right_text) {
        this.right_text = right_text;
    }

    public int getRight_pic() {
        return right_pic;
    }

    public void setRight_pic(int right_pic) {
        this.right_pic = right_pic;
    }

    @Override
    public String toString() {
        return "ShaiXuan_ShiYou{" +
                "left_text='" + left_text + '\'' +
                ", right_text='" + right_text + '\'' +
                ", right_pic=" + right_pic +
                '}';
    }
}
