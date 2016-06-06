package com.lxx.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.yanghuan.R;


public class StarActivity extends Activity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_star);
       // getSupportActionBar().hide();
        startMainAvtivity();
    }

    private void startMainAvtivity() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                intent=new Intent(StarActivity.this, MainActivity.class);
                startActivity(intent);
                finish();//结束本Activity
            }
        }, 3000);//设置执行时间

    }
}
