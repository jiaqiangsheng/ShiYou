package com.hhy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.yanghuan.R;

public class TongZhiActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hhy_activity_tong_zhi);

        mTextView= (TextView) findViewById(R.id.textview);
        Intent intent = getIntent();
        if (null != intent) {
           /* Bundle bundle = getIntent().getExtras();
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String content = bundle.getString(JPushInterface.EXTRA_ALERT);
            mTextView.setText("Title : " + title + "  " + "Content : " + content);*/
        }
    }
}
