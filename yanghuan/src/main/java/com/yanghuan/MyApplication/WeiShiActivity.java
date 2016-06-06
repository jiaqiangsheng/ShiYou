package com.yanghuan.MyApplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import com.yanghuan.Beans.Prochat;
import com.yanghuan.R;
import com.yanghuan.Beans.Dianshi;
import com.yanghuan.Beans.ShiPin;
import com.yanghuan.adapters.DianshiAdapter;

import org.xutils.BuildConfig;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WeiShiActivity extends AppCompatActivity {
    DianshiAdapter dianshiAdapter;
    PullToRefreshListView  listView;
    List<Dianshi> list=new ArrayList<>();
    List<ShiPin> mlist=new ArrayList<>();
    ImageView imageView;
    //图片解析
    public static final String TAG = "XUTILS";
    String mPath;
    String pic = null;
    String name = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_shi);
        initXUtils();
        initView();
        initData2();
        addListener();
        dianshiAdapter=new DianshiAdapter(list,WeiShiActivity.this);
        listView.setAdapter(dianshiAdapter);
        //设置为Both，表明：上拉和下拉同时都适用
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        //初始化刷新操作
        initRefreshListView();
        //listview的监听事件
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new loadDataAsyncTask(WeiShiActivity.this).execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new loadDataAsyncTask(WeiShiActivity.this).execute();
            }
        });
        String  activityName=getIntent().getStringExtra("1");
    }

    private void addListener() {
        Log.e("yh", "偶像电视剧");
        //get请求
        //第一步：设置访问路径以及携带数据
        RequestParams params = new RequestParams(mPath);
        params.addQueryStringParameter("a", "e");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Prochat>>() {
                }.getType();
                List<Prochat> list1 = gson.fromJson(result.toString(), type);
                Prochat prochat = null;
                for (int i = 0; i < list1.size(); i++) {
                    prochat = list1.get(i);
                    pic = prochat.getPropicture();
                    name = prochat.getProname();
                    Log.e("yh", " pic：" + pic);
                    Log.e("yh", " list：" + list1.size());
                    Log.e("yh", " pic1：" + pic);
                    ShiPin shiPin = new ShiPin(name, pic, "更新11集");
                    mlist.add(shiPin);
                }
                Dianshi dianshi = new Dianshi("更新12集", "http://o6nj6n5ea.bkt.clouddn.com/yh_dsj5.jpg", "欢乐颂", mlist, "偶像电视剧");
                list.add(dianshi);
                list.add(dianshi);
                list.add(dianshi);
                list.add(dianshi);
                list.add(dianshi);
                Log.e("yh", " list：" + list.get(0).getDsImage());
//                Log.e("yh","电视剧热聊结束");
                Log.e("list", list.size() + "");
                /*dianshiAdapter = new DianshiAdapter(list, WeiShiActivity.this);
                listView.setAdapter(dianshiAdapter);*/
                dianshiAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yh", "456");
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void initData2() {
        MyApplication application = new MyApplication();
        mPath = application.getUrl();
    }


    private void initXUtils() {
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }


   /* private void initData() {
        list=new ArrayList<>();
        mlist=new ArrayList<>();
        ShiPin shiPin=new ShiPin("神雕侠侣","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj8.jpg","更新11集");
        ShiPin shiPin1=new ShiPin("太阳的后裔","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj7.jpg","更新11集");
        mlist.add(shiPin);
        mlist.add(shiPin1);
        mlist.add(shiPin);
        mlist.add(shiPin);
        Dianshi dianshi=new Dianshi("更新12集","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj5.jpg","欢乐颂",mlist,"偶像电视剧");
        Dianshi dianshi1=new Dianshi("更新13集","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj9.jpg","欢乐颂",mlist,"偶像电视剧");
        list.add(dianshi);
        list.add(dianshi1);
        list.add(dianshi);
        list.add(dianshi);
    }*/
    /*private void initData1() {
        list=new ArrayList<>();
        mlist=new ArrayList<>();
        ShiPin shiPin=new ShiPin("一起来看流星雨","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj8.jpg","更新11集");
        ShiPin shiPin1=new ShiPin("甄嬛传","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj7.jpg","更新11集");
        mlist.add(shiPin);
        mlist.add(shiPin1);

        Dianshi dianshi=new Dianshi("更新12集","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj5.jpg","翻译官",mlist,"偶像电视剧");
        Dianshi dianshi1=new Dianshi("更新13集","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj9.jpg","翻译官",mlist,"偶像电视剧");
        list.add(dianshi);
        list.add(dianshi1);
    }*/
    //内部类：实现数据加载的耗时操作
    static class loadDataAsyncTask extends AsyncTask<Void, Void, String> {
        private WeiShiActivity activity;
        public loadDataAsyncTask(WeiShiActivity activity) {
            this.activity = activity;
        }
        @Override
        protected String doInBackground(Void... params) {
            //用一个线程来模拟刷新
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //加载数据
          /*  activity.initData1();*/
            return "success";
        }
        //对返回值进行操作
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if ("success".equals(s)) {
                //通知数据集改变，刷新页面
                activity.dianshiAdapter.notifyDataSetChanged();
                //刷新完成
                activity.listView.onRefreshComplete();
            }
        }
    }


    public void initRefreshListView() {
        ILoadingLayout startLables =listView.getLoadingLayoutProxy(true, false);
        startLables.setPullLabel("下拉刷新");
        startLables.setRefreshingLabel("正在拉");
        startLables.setReleaseLabel("放开刷新");
        ILoadingLayout endLabels = listView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉加载");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("放开加载...");
    }
    private void initView() {
        listView= (PullToRefreshListView) findViewById(R.id.activity_listview);
        imageView= (ImageView) findViewById(R.id.activity_dian_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
