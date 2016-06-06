package com.yanghuan.MyApplication;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by 杨欢 on 2016/5/25.
 */
public class MyApplication extends Application {
    private String url = "http://10.201.1.3:8080/ShiYou_HttpServer2/ProChat";
    //private String url1 = "http://10.201.1.3:8080/ShiYou_HttpServer2/Chathistory";
    //Application的onCreate早于所有的Activity的onCreate方法
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        // 是否输出debug日志, 开启debug会影响性能.
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
    public String getUrl() {
        return url;
    }
    /*public String getUrl1() {
        return url1;
    }*/


}
