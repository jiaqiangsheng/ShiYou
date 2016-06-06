package com.yanghuan.Beans;

import java.io.Serializable;

/**
 * Created by 杨欢 on 2016/5/20.
 */
public class FavoriteBean implements Serializable {
    private int image;
    private String text;
    private String content;

    public FavoriteBean(int image, String text, String content) {
        this.image = image;
        this.text = text;
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
