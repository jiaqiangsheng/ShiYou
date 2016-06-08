package com.lxx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yanghuan.R;

public class SucessActivity extends AppCompatActivity {
    TextView mTextView;
    String mName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess);
    }
    private void initData() {
        Intent intent = getIntent();
        mName = intent.getStringExtra("name");
    }

    private void initViews() {
        mTextView = (TextView) findViewById(R.id.text);
        mTextView.setText(mName);
    }
}
