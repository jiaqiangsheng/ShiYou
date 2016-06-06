package com.yanghuan.MyApplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.yanghuan.Beans.ZhuiJu;
import com.yanghuan.R;
import com.yanghuan.adapters.ZhuiJuAdapter;

import java.util.ArrayList;
import java.util.List;

public class ZhuiJuActivity extends AppCompatActivity {
    ListView listView;
    ZhuiJuAdapter adapter;
    List<ZhuiJu> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhui_ju);
        initViews();
        initData();
    }

    private void initData() {
        ZhuiJu ju=new ZhuiJu();
        ju.setPicture("http://o6nj6n5ea.bkt.clouddn.com/yh_dsj8.jpg");
        ju.setText1("欢乐喜剧人");
        ju.setText2("更新至11集");
        ju.setText3("每周六播出");
        list.add(ju);
        list.add(ju);
        list.add(ju);
        list.add(ju);
        list.add(ju);
        list.add(ju);
        list.add(ju);
        list.add(ju);
        list.add(ju);
        list.add(ju);


        adapter=new ZhuiJuAdapter(ZhuiJuActivity.this,list);
        listView.setAdapter(adapter);

    }

    private void initViews() {
        listView= (ListView) findViewById(R.id.zhuiju_listview);

    }
}
