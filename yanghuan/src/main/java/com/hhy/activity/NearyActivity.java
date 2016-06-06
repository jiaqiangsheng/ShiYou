package com.hhy.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hhy.fragment.NearbyShouFragment;
import com.yanghuan.R;

public class NearyActivity extends AppCompatActivity {

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    NearbyShouFragment shouFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hhy_activity_neary);

        initData();
    }

    private void initData() {
        shouFragment = new NearbyShouFragment();
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        //middle部分默认显示shouFragment
        mFragmentTransaction.add(R.id.middle, shouFragment);
        mFragmentTransaction.commit();

    }


}
