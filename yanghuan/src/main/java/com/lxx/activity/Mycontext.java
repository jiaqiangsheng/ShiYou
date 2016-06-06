package com.lxx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lxx.Adapter.MycontextAdapter;
import com.lxx.bean.Lxx_BeanContext;
import com.yanghuan.BuildConfig;
import com.yanghuan.R;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class Mycontext extends AppCompatActivity {
    List<Lxx_BeanContext> contextlist;
    Lxx_BeanContext contextBean;
    List<String> imageurllist;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);
        initXUtils();
        initView();
        initData();
        MycontextAdapter Adapter=new MycontextAdapter(Mycontext.this,contextlist);

        listView.setAdapter(Adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Mycontext.this,Dynamic_Details.class);
                intent.putExtra("list", contextlist.get(position));
                startActivity(intent);
                Toast.makeText(Mycontext.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void initXUtils() {
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
    private void initData() {

        String imageurl="http://img3.imgtn.bdimg.com/it/u=895009738,1542646259&fm=21&gp=0.jpg";
    //    imageurllist.add(imageurl);
        String contextmain="shuoshuo明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪 ";
        int a =10;
        contextBean =new Lxx_BeanContext("fdsfasdasd","5/23","http://pic13.nipic.com/20110310/6400731_100538610118_2.jpg",imageurllist,contextmain,a,a);
        contextlist.add(contextBean);
        imageurllist=new ArrayList<>();
        imageurllist.add(imageurl);
        imageurllist.add("http://h.hiphotos.baidu.com/image/pic/item/d439b6003af33a8716116e60c55c10385343b548.jpg");
        imageurllist.add("http://a.hiphotos.baidu.com/image/pic/item/8b13632762d0f7037170e83a0dfa513d2797c512.jpg");
        imageurllist.add("http://g.hiphotos.baidu.com/image/pic/item/d0c8a786c9177f3e117088eb75cf3bc79e3d568b.jpg");
        imageurllist.add("http://a.hiphotos.baidu.com/image/pic/item/d31b0ef41bd5ad6ed5ba980b84cb39dbb7fd3c8b.jpg");
        imageurllist.add("http://img.szhk.com/Image/2013/08/22/1377160001248.jpg");
        imageurllist.add("http://g.hiphotos.baidu.com/image/pic/item/f7246b600c338744159a5d9a540fd9f9d62aa0fd.jpg");
        imageurllist.add("http://www.cnnb.com.cn/pic/0/02/19/05/2190554_999846.jpg");
        imageurllist.add("http://www.yybagua.cn/uploads/allimg/141213/4-1412131ZQC25.jpg");
        imageurllist.add("http://images.china.cn/attachement/jpg/site1000/20110819/001ec949ffca0fb8413c37.jpg");
        imageurllist.add("http://images.china.cn/attachement/jpg/site1000/20110819/001ec949ffca0fb8413c37.jpg");
        contextBean =new Lxx_BeanContext("明若晓溪","5/24","http://pic13.nipic.com/20110310/6400731_100538610118_2.jpg",imageurllist,contextmain,a,a);
        contextlist.add(contextBean);


        contextlist.add(contextBean);
        contextlist.add(contextBean);
        contextlist.add(contextBean);


    }

    private void initView() {
        imageurllist=new ArrayList<>();
        contextlist=new ArrayList<>();
        listView= (ListView) findViewById(R.id.lxx_listView);
        
    }
}
