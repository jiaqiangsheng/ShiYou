package com.hhy.bean;

import java.io.Serializable;

/**
 * Created by hanhongyang on 2016/5/18.
 */
public class ShiYouList implements Serializable {
    private int left_pic;
    private String name;
    private String biaoqian;
    private String diistance;

    public ShiYouList() {
    }

    public ShiYouList(int left_pic, String name, String biaoqian, String diistance) {
        this.left_pic = left_pic;
        this.name = name;
        this.biaoqian = biaoqian;
        this.diistance = diistance;
    }

    public int getLeft_pic() {
        return left_pic;
    }

    public void setLeft_pic(int left_pic) {
        this.left_pic = left_pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiaoqian() {
        return biaoqian;
    }

    public void setBiaoqian(String biaoqian) {
        this.biaoqian = biaoqian;
    }

    public String getDiistance() {
        return diistance;
    }

    public void setDiistance(String diistance) {
        this.diistance = diistance;
    }

    @Override
    public String toString() {
        return "ShoYouList{" +
                "left_pic=" + left_pic +
                ", name='" + name + '\'' +
                ", biaoqian='" + biaoqian + '\'' +
                ", diistance='" + diistance + '\'' +
                '}';
    }
}
