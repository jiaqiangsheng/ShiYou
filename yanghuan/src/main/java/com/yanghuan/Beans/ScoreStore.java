package com.yanghuan.Beans;

import java.io.Serializable;

/**
 * Created by 杨欢 on 2016/5/26.
 */
public class ScoreStore implements Serializable {
    String storeName;
    String storePic;
    String storeTitle;

    public ScoreStore(String storeName, String storePic, String storeTitle) {
        this.storeName = storeName;
        this.storePic = storePic;
        this.storeTitle = storeTitle;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStorePic() {
        return storePic;
    }

    public void setStorePic(String storePic) {
        this.storePic = storePic;
    }

    public String getStoreTitle() {
        return storeTitle;
    }

    public void setStoreTitle(String storeTitle) {
        this.storeTitle = storeTitle;
    }
}
