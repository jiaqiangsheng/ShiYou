package com.yanghuan.MyApplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.yanghuan.Beans.Dianshi;
import com.yanghuan.Beans.ShiPin;
import com.yanghuan.R;
import com.yanghuan.adapters.DianYingAdapter;
import com.yanghuan.adapters.OtherAdapter;

import org.xutils.BuildConfig;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class OtherActivity extends AppCompatActivity {
    OtherAdapter adapter;
    ListView listView;
    List<Dianshi> list;
    List<ShiPin> mlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        initXUtils();
        initView();
        initData();
        String  activityName=getIntent().getStringExtra("3");
    }
    private void initXUtils() {
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }


    private void initData() {
        list=new ArrayList<>();
        mlist=new ArrayList<>();
        ShiPin shiPin=new ShiPin("怦然心动","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj8.jpg","更新11集");
        ShiPin shiPin1=new ShiPin("港囧","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj7.jpg","更新15集");
        mlist.add(shiPin);
        mlist.add(shiPin1);
        mlist.add(shiPin);
        mlist.add(shiPin);
        Dianshi dianshi=new Dianshi("更新12集","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj5.jpg","澳门风云",mlist,"最新上档");
        Dianshi dianshi1=new Dianshi("更新13集","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj9.jpg","港囧",mlist,"谁的青春不迷茫");
        list.add(dianshi);
        list.add(dianshi1);
        list.add(dianshi);
        list.add(dianshi);

        adapter=new OtherAdapter(list,OtherActivity.this);
        listView.setAdapter(adapter);
    }

    private void initView() {
        listView= (ListView) findViewById(R.id.activity3_listview);


    }
}
