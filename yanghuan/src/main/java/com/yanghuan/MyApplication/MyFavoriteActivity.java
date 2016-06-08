package com.yanghuan.MyApplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.yanghuan.R;
import com.yanghuan.Beans.FavoriteBean;
import com.yanghuan.adapters.FavoriteAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyFavoriteActivity extends AppCompatActivity {
    FavoriteAdapter favoriteAdapter;
    List<FavoriteBean> list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_favorite);
        initViews();
        initData();
        String name=getIntent().getStringExtra("2");
    }

    private void initViews() {
        listView= (ListView) findViewById(R.id.my_favorite_listview);

    }

    private void initData() {
        list=new ArrayList<>();
        list.add(new FavoriteBean(R.mipmap.a1,"3月24号","东方卫视"));
        list.add(new FavoriteBean(R.mipmap.a3,"3月25号","湖南卫视"));
        list.add(new FavoriteBean(R.mipmap.a4,"3月26号","山东卫视"));
        list.add(new FavoriteBean(R.mipmap.b3,"3月27号","江苏卫视"));
        list.add(new FavoriteBean(R.mipmap.a1,"3月24号","东方卫视"));
        list.add(new FavoriteBean(R.mipmap.a3,"3月25号","湖南卫视"));
        list.add(new FavoriteBean(R.mipmap.a4,"3月26号","山东卫视"));
        list.add(new FavoriteBean(R.mipmap.b3,"3月27号","江苏卫视"));
        favoriteAdapter=new FavoriteAdapter(list,MyFavoriteActivity.this);
        listView.setAdapter(favoriteAdapter);

    }
}
