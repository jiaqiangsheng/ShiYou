package com.yanghuan.Beans;

import java.io.Serializable;

/**
 * Created by 杨欢 on 2016/5/20.
 */
public class TaskBean implements Serializable {
    private String image;
    private String text;
    private String btn;
    private Boolean isClick;

    public TaskBean(String image, String text, String btn, Boolean isClick) {
        this.image = image;
        this.text = text;
        this.btn = btn;
        this.isClick = isClick;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

    public Boolean getClick() {
        return isClick;
    }

    public void setClick(Boolean click) {
        isClick = click;
    }
}
