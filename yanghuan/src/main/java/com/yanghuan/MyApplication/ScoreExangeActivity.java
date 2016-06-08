package com.yanghuan.MyApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.yanghuan.R;
import com.yanghuan.Beans.ScoreExchange;
import com.yanghuan.Beans.ScoreStore;
import com.yanghuan.adapters.Score_exchangAdapter;

import org.xutils.BuildConfig;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class ScoreExangeActivity extends AppCompatActivity implements View.OnClickListener {
    Score_exchangAdapter adapter;
    ListView listView;
    List<ScoreStore> list;
    List<ScoreExchange> mlist;
    View vHead;
    ImageButton bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_exange);
        initXUtils();
        initViews();
        initData();
        Intent intent=getIntent();
    }

    private void initXUtils() {
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

    private void initViews() {
        listView= (ListView) findViewById(R.id.activity_score_listview);
        vHead= View.inflate(this,R.layout.headlist,null);
        bt1= (ImageButton) vHead.findViewById(R.id.score_exange_bt1);
        bt2= (ImageButton) vHead.findViewById(R.id.score_exange_bt2);
        addListener();
        listView.addHeaderView(vHead);
    }

    private void addListener() {
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.score_exange_bt1:
                Toast.makeText(this,"1",Toast.LENGTH_LONG).show();
                break;
            case R.id.score_exange_bt2:
                Toast.makeText(this,"2",Toast.LENGTH_LONG).show();
                break;
        }

    }

    private void initData() {
        list=new ArrayList<>();
        mlist=new ArrayList<>();
        ScoreStore store=new ScoreStore("伪装者","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj8.jpg","30积分");
        list.add(store);
        list.add(store);
        list.add(store);
        list.add(store);
        list.add(store);
        list.add(store);
        ScoreExchange exchange=new ScoreExchange(list);
        mlist.add(exchange);
        mlist.add(exchange);
        mlist.add(exchange);
        mlist.add(exchange);
        adapter=new Score_exchangAdapter(mlist,ScoreExangeActivity.this);
        listView.setAdapter(adapter);
    }


}
