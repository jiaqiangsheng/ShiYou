package com.yanghuan.Beans;

import java.io.Serializable;

/**
 * Created by 杨欢 on 2016/5/25.
 */
public class JiemuShow implements Serializable {
    String name;
    String picture;
    String person;

    public JiemuShow(String name, String picture, String person) {
        this.name = name;
        this.picture = picture;
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
