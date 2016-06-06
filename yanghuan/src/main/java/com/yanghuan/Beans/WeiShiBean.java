package com.yanghuan.Beans;

import java.io.Serializable;

/**
 * Created by 杨欢 on 2016/5/23.
 */
public class WeiShiBean implements Serializable{
    //頂部頭
    String title;
    //視頻名称
    String[] jmcontent=new String[3];
    //图片
    String[] jmpicture=new String[3];

    //判断item是否可以被点击标志
    private int item_flag;
   private int flag;

    public WeiShiBean(String title, String[] jmcontent, String[] jmpicture, int item_flag, int flag) {
        this.title = title;
        this.jmcontent = jmcontent;
        this.jmpicture = jmpicture;
        this.item_flag = item_flag;
        this.flag = flag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getJmcontent() {
        return jmcontent;
    }

    public void setJmcontent(String[] jmcontent) {
        this.jmcontent = jmcontent;
    }

    public String[] getJmpicture() {
        return jmpicture;
    }

    public void setJmpicture(String[] jmpicture) {
        this.jmpicture = jmpicture;
    }

    public int getItem_flag() {
        return item_flag;
    }

    public void setItem_flag(int item_flag) {
        this.item_flag = item_flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
