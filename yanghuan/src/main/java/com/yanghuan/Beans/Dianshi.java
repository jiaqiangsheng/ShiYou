package com.yanghuan.Beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 杨欢 on 2016/5/25.
 */
public class Dianshi implements Serializable {
    String dsName;
    String dsImage;
    List<ShiPin> shiPinList;
    String dsTitle;
    String totalTitle;

    public Dianshi(String dsTitle, String dsImage, String dsName,List<ShiPin> shiPinList,String totalTitle) {
        this.dsTitle = dsTitle;
        this.dsImage = dsImage;
        this.dsName = dsName;
        this.shiPinList=shiPinList;
        this.totalTitle=totalTitle;
    }

    public String getTotalTitle() {
        return totalTitle;
    }

    public void setTotalTitle(String totalTitle) {
        this.totalTitle = totalTitle;
    }

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public String getDsImage() {
        return dsImage;
    }

    public void setDsImage(String dsImage) {
        this.dsImage = dsImage;
    }

    public List<ShiPin> getShiPinList() {
        return shiPinList;
    }

    public void setShiPinList(List<ShiPin> shiPinList) {
        this.shiPinList = shiPinList;
    }

    public String getDsTitle() {
        return dsTitle;
    }

    public void setDsTitle(String dsTitle) {
        this.dsTitle = dsTitle;
    }
}
