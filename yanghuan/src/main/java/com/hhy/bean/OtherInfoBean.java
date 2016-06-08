package com.hhy.bean;

import java.io.Serializable;

public class OtherInfoBean implements Serializable {
    private int ltid;
    private int ltvid;
    private String wsname;
    private String wspicture;
    private String programName;
    private String programing;
    private int flag2;

    public int getLtid() {
        return ltid;
    }
    public void setLtid(int ltid) {
        this.ltid = ltid;
    }
    public int getLtvid() {
        return ltvid;
    }
    public void setLtvid(int ltvid) {
        this.ltvid = ltvid;
    }
    public String getWsname() {
        return wsname;
    }
    public void setWsname(String wsname) {
        this.wsname = wsname;
    }
    public String getWspicture() {
        return wspicture;
    }
    public void setWspicture(String wspicture) {
        this.wspicture = wspicture;
    }
    public String getProgramName() {
        return programName;
    }
    public void setProgramName(String programName) {
        this.programName = programName;
    }
    public String getPrograming() {
        return programing;
    }
    public void setPrograming(String programing) {
        this.programing = programing;
    }
    public int getFlag2() {
        return flag2;
    }
    public void setFlag2(int flag2) {
        this.flag2 = flag2;
    }
    @Override
    public String toString() {
        return "OtherInfoBean [ltid=" + ltid + ", ltvid=" + ltvid + ", wsname="
                + wsname + ", wspicture=" + wspicture + ", programName="
                + programName + ", programing=" + programing + ", flag2="
                + flag2 + "]";
    }


}
