package com.hhy.bean;

import java.io.Serializable;

/**
 * Created by hanhongyang on 2016/5/23.
 */
public class AccountManage implements Serializable {
    private String hhy_accountm_pic_url;
    private String hhy_accountm_text;
    /*private int hhy_accountm_rightpic;*/
    private int flag;//用于区分布局类型


    public AccountManage() {
    }

    public AccountManage(String hhy_accountm_pic, String hhy_accountm_text,  int flag) {
        this.hhy_accountm_pic_url = hhy_accountm_pic;
        this.hhy_accountm_text = hhy_accountm_text;
        /*this.hhy_accountm_rightpic = hhy_accountm_rightpic;*/
        this.flag = flag;
        //this.biaozhi = biaozhi;
    }

    public String getHhy_accountm_pic_url() {
        return hhy_accountm_pic_url;
    }

    public void setHhy_accountm_pic_url(String hhy_accountm_pic) {
        this.hhy_accountm_pic_url = hhy_accountm_pic;
    }

    public String getHhy_accountm_text() {
        return hhy_accountm_text;
    }

    public void setHhy_accountm_text(String hhy_accountm_text) {
        this.hhy_accountm_text = hhy_accountm_text;
    }

 /*   public int getHhy_accountm_rightpic() {
        return hhy_accountm_rightpic;
    }

    public void setHhy_accountm_rightpic(int hhy_accountm_rightpic) {
        this.hhy_accountm_rightpic = hhy_accountm_rightpic;
    }*/

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

   /* public boolean isBiaozhi() {
        return biaozhi;
    }

    public void setBiaozhi(boolean biaozhi) {
        this.biaozhi = biaozhi;
    }*/


}
