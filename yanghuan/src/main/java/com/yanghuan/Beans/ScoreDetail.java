package com.yanghuan.Beans;

import java.io.Serializable;

/**
 * Created by C5-0 on 2016/5/31.
 */
public class ScoreDetail implements Serializable {
    //兑换详情
    String detail;
    //兑换日期
    String date;
    //兑换时间
    String time;
    //兑换积分数
    String num;

    public ScoreDetail(String detail, String date, String time, String num) {
        this.detail = detail;
        this.date = date;
        this.time = time;
        this.num = num;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
