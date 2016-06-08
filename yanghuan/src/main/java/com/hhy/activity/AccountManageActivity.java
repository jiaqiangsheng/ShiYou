package com.hhy.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hhy.adapter.AccountManageAdapter;
import com.hhy.bean.AccountManage;
import com.hhy.bean.UserInfo;
import com.yanghuan.BuildConfig;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountManageActivity extends AppCompatActivity {
   /* public static final String SAVE = "addAccount";
    List<AccountManage> mManageList;
    AccountManageAdapter mManageAdapter;
    ListView mListView;
    TextView mTextView;
    ImageView mImageView;
    boolean flag = false;
    boolean flag2 = false;
    Intent mIntent;
    AccountManage accountManage;
    String uname;
    SharedPreferences sharedPreferences;
    int i;
    Map<String, String> map;
    String url;
    List<String> mPhoneList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Fresco
        Fresco.initialize(AccountManageActivity.this);
        setContentView(R.layout.hhy_activity_account_manage);
        initXUtils();
        initView();
        initData();
        setAdapter();
        setLinstener();
    }

    private void initXUtils() {
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

    private void setLinstener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(AccountManageActivity.this,position+"mListView", Toast.LENGTH_SHORT).show();
                String text = mManageList.get(position).getHhy_accountm_text();
                if ("添加账号".equals(text)) {
                    //跳转至添加账户界面,并将用户名及密码传过来
                    flag2 = true;
                    mIntent = new Intent(AccountManageActivity.this, AddAccountActivity.class);
                    startActivity(mIntent);

                    //此处可以成功
                    //Toast.makeText(AccountManageActivity.this, position + " 添加账号", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mTextView.getText().toString();
                if ("编辑".equals(text)) {
                    flag = true;
                    setAdapter();
                    mTextView.setText("完成");
                } else {
                    flag = false;
                    setAdapter();
                    mTextView.setText("编辑");
                }
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回上一个界面
                AccountManageActivity.this.finish();
            }
        });


    }

    private void initView() {
        url = "http://10.201.1.148:8888/HttpServer/HttpServer";

        mImageView = (ImageView) findViewById(R.id.hhy_manage_back_image);
        mListView = (ListView) findViewById(R.id.hhy_account_manage);
        mTextView = (TextView) findViewById(R.id.hhy_accountM_bianji);
    }

    private void initData() {
        mManageList = new ArrayList<AccountManage>();
      *//*  Intent intent = getIntent();
        url = intent.getStringExtra("url");
        uname = intent.getStringExtra("uname");
        String flag = intent.getStringExtra("fla");
        Toast.makeText(AccountManageActivity.this, url+uname, Toast.LENGTH_SHORT).show();
        if(url != null && uname != null){
            accountManage = new AccountManage(url,uname, 0);
            mManageList.add(accountManage);
        }*//*
        //
        sharedPreferences = getSharedPreferences(SAVE, MODE_APPEND);
        i = 0;

        map = (Map<String, String>) sharedPreferences.getAll();
        //Toast.makeText(AccountManageActivity.this, map.size()+"偏好设置", Toast.LENGTH_SHORT).show();
        if (i == 0) {
            i = map.size();
        }
        mPhoneList = new ArrayList<String>();
        //int a = sharedPreferences.getInt("a", 0);
        //a = a + 1;
        for (int j = 0; j <  i; j++) {
            uname = sharedPreferences.getString("uname" + j, "");
            url = sharedPreferences.getString("url" + j, "");
            //Toast.makeText(AccountManageActivity.this, uname+"偏好设置", Toast.LENGTH_SHORT).show();
            if (!"".equals(uname) && !"".equals(url)) {
                accountManage = new AccountManage(url, uname, 0);
                mManageList.add(accountManage);
            }

        }

        AccountManage accountManage1 = new AccountManage("http://o6nj6n5ea.bkt.clouddn.com/hhy/icon_xiang.jpg", "漩涡鸣人", 0);
        AccountManage accountManage2 = new AccountManage("http://o6nj6n5ea.bkt.clouddn.com/hhy/lag.png", "添加账号", 1);
        AccountManage accountManage3 = new AccountManage("http://o6nj6n5ea.bkt.clouddn.com/hhy/icon_xiang.jpg", "退出当前帐号", 2);

        mManageList.add(accountManage1);
        mManageList.add(accountManage2);
        mManageList.add(accountManage3);
        //selectDataBase();
    }

    private void setAdapter() {
        mManageAdapter = new AccountManageAdapter(mManageList, AccountManageActivity.this, flag, uname);
        mListView.setAdapter(mManageAdapter);
    }

    private void selectDataBase() {
        RequestParams params = new RequestParams(url);
        Gson gson = new Gson();
        String mPhoneListString = gson.toJson(mPhoneList);
        params.addQueryStringParameter("mPhoneList", mPhoneListString);
        final AccountManage accountManaget = null;
        final AccountManage accountManagef = null;

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //打印日志
                Log.i("Account", "成功");
                Gson gson = new Gson();
                UserInfo userInfo = null;
                Type type = new TypeToken<List<UserInfo>>() {
                }.getType();
                List<UserInfo> lists = gson.fromJson(result, type);
                //Toast.makeText(AccountManageActivity.this, lists.toString(), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < lists.size(); i++) {
                    userInfo = lists.get(i);
                    //Toast.makeText(AccountManageActivity.this, userInfo.toString(), Toast.LENGTH_SHORT).show();
                    accountManage = new AccountManage(userInfo.getUrl(), userInfo.getUname(), 0);
                    mManageList.add(accountManage);
                }
               *//* mManageList.add(accountManaget);
                mManageList.add(accountManagef);*//*

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
*/
}
