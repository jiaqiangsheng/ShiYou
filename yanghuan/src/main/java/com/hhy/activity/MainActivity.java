package com.hhy.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.hhy.fragment.ConfigFragment;
import com.jqs.servert.utils.MyApplication;
import com.yanghuan.R;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * 项目(设置页面)
 */
public class MainActivity extends AppCompatActivity {
    public static final String MESSAGE = "messageNotification";
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    ConfigFragment mConfigFragment;
    boolean flag1 = false,flag5 = false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEditor;
    String mPath;
    public final static int NOTIFICATION_ID = "NotificationActivityDemo".hashCode();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hhy_activity_main);
        //极光推送初始化
        JPushInterface.setDebugMode(true);
        JPushInterface.init(MainActivity.this);
        MyApplication myApplication = (MyApplication) getApplication();
        mPath = myApplication.getUrlPath();
        initFlag();
        initData();

    }

    private void initData() {
        mConfigFragment = new ConfigFragment();
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        //middle部分默认显示mConfigFragment
        mFragmentTransaction.add(R.id.hhy_congfig_middle, mConfigFragment);
        mFragmentTransaction.commit();
    }


    private void initFlag() {
        sharedPreferences = getSharedPreferences(MESSAGE,MODE_APPEND);
        mEditor = sharedPreferences.edit();
        flag1 = sharedPreferences.getBoolean("flag1",false);
        flag5 = sharedPreferences.getBoolean("flag5",false);
        if(flag1){
            //表示消息通知里面的评论我的按钮之前已经被打开
            getTest(flag1,"comment");

        }

        if(flag5){
            //表示消息通知里面的评论我的按钮之前已经被打开
            getTest(flag5,"newfans");

        }
    }

    private void initNotification() {
        //当消息通知里面的相关按钮被打开时开始推送相关消息
        final NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // params
        int smallIconId = R.drawable.back;
        Bitmap largeIcon = ((BitmapDrawable) getResources().getDrawable(R.drawable.contact_0)).getBitmap();
        String info = "逢赌必输：我是通知内容";
        // action when clicked
        Intent intent = new Intent(this,FansActivity.class);
        boolean isAboveLollipop = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
        builder.setLargeIcon(largeIcon)
                .setSmallIcon(isAboveLollipop ? R.mipmap.back : R.drawable.contact_0)
                .setContentTitle("逢赌必输")
                .setContentText(info)
                .setTicker(info)
                .setContentIntent(PendingIntent.getActivity(this, 0, intent, 0));

        final Notification n = builder.getNotification();
        n.flags =Notification.FLAG_AUTO_CANCEL;//点击后取消
        n.defaults |= Notification.DEFAULT_SOUND;
        nm.notify(NOTIFICATION_ID, n);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    private void getTest(boolean flag,String biaozhi) {
        //POST请求
        //第一步：设置访问路径以及携带数据
        if(flag){
            RequestParams params = new RequestParams(mPath);
            params.addBodyParameter("biaozhi",biaozhi);
            //代表相关按钮被打开，可以发送通知了
            //第二步：开始请求，设置请求方式，同时实现回调函数
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    if("您打开了新粉丝按钮".equals(result)){
                        initNotification();
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }
    }
}
