package com.jqs.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by qiangsheng on 2016/5/21.
 */
public class MyCaseReceiver extends BroadcastReceiver {
    boolean isVisibility;
    @Override
    public void onReceive(Context context, Intent intent) {
        //通过intent获取广播信息
        String action = intent.getAction();
        if (action.equals("BookTopCaseFragment")){
            boolean name =intent.getBooleanExtra("isVisibility",false);
            Log.e("broad","动态广播接收到的广播携带的数据：" + name);
            setVisibility(name);
        }
    }

    public boolean isVisibility() {
        return isVisibility;
    }

    public void setVisibility(boolean visibility) {
        isVisibility = visibility;
    }
}
