package com.yanghuan.Beans;

import java.io.Serializable;

/**
 * Created by 杨欢 on 2016/5/24.
 */
public class jiemuBean implements Serializable{
   String jiemupic;
    String jiemuname;

    public jiemuBean(String jiemupic, String jiemuname) {
        this.jiemupic = jiemupic;
        this.jiemuname = jiemuname;
    }

    public String getJiemupic() {
        return jiemupic;
    }

    public void setJiemupic(String jiemupic) {
        this.jiemupic = jiemupic;
    }

    public String getJiemuname() {
        return jiemuname;
    }

    public void setJiemuname(String jiemuname) {
        this.jiemuname = jiemuname;
    }
}
