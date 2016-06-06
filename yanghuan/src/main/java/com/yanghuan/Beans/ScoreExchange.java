package com.yanghuan.Beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 杨欢 on 2016/5/26.
 */
public class ScoreExchange implements Serializable {

    List<ScoreStore> list;

    public ScoreExchange( List<ScoreStore> list) {

        this.list = list;
    }
    public List<ScoreStore> getList() {
        return list;
    }

    public void setList(List<ScoreStore> list) {
        this.list = list;
    }
}
