package com.yanghuan.MyApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.yanghuan.R;
import com.yanghuan.Beans.TaskBean;
import com.yanghuan.adapters.MyTaskAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyTaskActivity extends AppCompatActivity {
   MyTaskAdapter myTaskAdapter;
    List<TaskBean> taskList;
    ListView listView;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);
        initViews();
        initData();
        myTaskAdapter=new MyTaskAdapter(taskList,MyTaskActivity.this);
        listView.setAdapter(myTaskAdapter);
        addListener();



    }
      private void initViews() {
        listView= (ListView) findViewById(R.id.my_task_listview);
        imageView= (ImageView) findViewById(R.id.my_task_back);
    }

    private void initData() {
        taskList=new ArrayList<>();
        TaskBean taskBean1=new TaskBean("http://o6nj6n5ea.bkt.clouddn.com/qunliao.png","上传头像可以获得10个积分","已领奖",true);
        TaskBean taskBean2=new TaskBean("http://o6nj6n5ea.bkt.clouddn.com/qunliao.png","在任意聊天室聊天超过半小时可获得20积分","群聊室",true);
        TaskBean taskBean3=new TaskBean("http://o6nj6n5ea.bkt.clouddn.com/qunliao.png","上传头像可以获得10个积分","已领奖",true);
        TaskBean taskBean4=new TaskBean("http://o6nj6n5ea.bkt.clouddn.com/qunliao.png","关注5个朋友可获得20个积分","搜视友",false);
        TaskBean taskBean5=new TaskBean("http://o6nj6n5ea.bkt.clouddn.com/qunliao.png","每天首帖可获得5积分","发帖",false);
        TaskBean taskBean6=new TaskBean("http://o6nj6n5ea.bkt.clouddn.com/qunliao.png","每日满5次评论可获得15积分","去动态",false);
        taskList.add(taskBean1);
        taskList.add(taskBean2);
        taskList.add(taskBean3);
        taskList.add(taskBean4);
        taskList.add(taskBean5);
        taskList.add(taskBean6);

    }

    private void addListener() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack=new Intent(MyTaskActivity.this,ShiYouActivity.class);
            }
        });

    }


}
