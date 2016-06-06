package com.lxx.bean;

import java.io.Serializable;

/**
 * Created by 潇 on 2016/5/25.
 */
public class Secondgrade_Review implements Serializable {
    String username1;
    String usetname2;
    String secondcontext;

    public Secondgrade_Review(String username1, String usetname2, String secondcontext) {
        this.username1 = username1;
        this.usetname2 = usetname2;
        this.secondcontext = secondcontext;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getUsetname2() {
        return usetname2;
    }

    public void setUsetname2(String usetname2) {
        this.usetname2 = usetname2;
    }

    public String getSecondcontext() {
        return secondcontext;
    }

    public void setSecondcontext(String secondcontext) {
        this.secondcontext = secondcontext;
    }
}
