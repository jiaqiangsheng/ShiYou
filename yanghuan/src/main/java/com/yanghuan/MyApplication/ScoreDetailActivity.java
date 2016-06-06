package com.yanghuan.MyApplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.yanghuan.Beans.ScoreDetail;
import com.yanghuan.R;
import com.yanghuan.adapters.ScoreDetailAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScoreDetailActivity extends AppCompatActivity {
    ScoreDetailAdapter adapter;
    List<ScoreDetail> list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_detail);
        initView();
        initData();
    }

    private void initData() {
        list=new ArrayList<>();
        ScoreDetail scoreDetail=new ScoreDetail("连续签到30天","2016-1-1","+5","18:20");
        list.add(scoreDetail);
        list.add(scoreDetail);
        list.add(scoreDetail);
        list.add(scoreDetail);
        list.add(scoreDetail);
        list.add(scoreDetail);
        list.add(scoreDetail);
        list.add(scoreDetail);
        list.add(scoreDetail);
        list.add(scoreDetail);
        list.add(scoreDetail);
        list.add(scoreDetail);
        adapter=new ScoreDetailAdapter(list,ScoreDetailActivity.this);
        listView.setAdapter(adapter);
    }

    private void initView() {
        listView= (ListView) findViewById(R.id.yh_scoretail_listview);
    }
}
