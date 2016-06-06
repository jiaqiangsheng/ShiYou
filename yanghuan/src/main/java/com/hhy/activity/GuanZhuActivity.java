package com.hhy.activity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cache.ACache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hhy.adapter.GuanZhuAdapter;
import com.hhy.bean.GuanZhu;
import com.hhy.bean.UserInfo;
import com.yanghuan.BuildConfig;
import com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GuanZhuActivity extends AppCompatActivity {
    List<GuanZhu> mList;
    GuanZhuAdapter mGuanZhuAdapter;
    PullToRefreshListView mPullToRefreshListView;
    RelativeLayout mRelativeLayout;
    String url;
    GuanZhu guanZhu;
    EditText mEditText;
    Intent mIntent;
    //使用缓存
    ACache mACache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hhy_activity_guan_zhu);
        //初始化xUtils
        initXUtils();
        initView();
        initData();
       // setAdapter();
        //设置刷新模式，下拉刷新，上拉加载都行
        //设置刷新模式为BOTH才可以上拉和下拉都能起作用,必须写在前面
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        //设置刷新时头部的状态（即显示的图标和文字）
        initStatus();
        setListener();
    }

    private void setListener() {
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉时执行
                new loadDataAsyn().execute();
            }
        });

        mPullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //得到该行的uid
                position = position-1;//******(此处需特别小心)
                int uid = mList.get(position).getMuid();
                Toast.makeText(GuanZhuActivity.this, uid+"-guanzhu", Toast.LENGTH_LONG).show();
                mIntent = new Intent(GuanZhuActivity.this,OtherInfoActivity.class);
                mIntent.putExtra("uid",uid+"");
                startActivity(mIntent);
            }
        });
    }

    private void initStatus() {
        //设置头部下拉刷新时的样式
        ILoadingLayout topLayout = mPullToRefreshListView.getLoadingLayoutProxy(true, false);
        topLayout.setPullLabel("下拉刷新");
        topLayout.setRefreshingLabel("正在拼命加载中...");
        topLayout.setReleaseLabel("放开刷新");

    }

    class loadDataAsyn extends AsyncTask<Void, Void, String> {
        /* GuanZhuActivity mGuanZhuActivity;

         public loadDataAsyn(GuanZhuActivity mMainActivity){
             this.mGuanZhuActivity = mMainActivity;
         }*/
        @Override
        protected String doInBackground(Void... params) {
            //先将原来的数据清空，重新从数据库加载数据
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            initData();
            //重新从数据库取数据，若有新数据就会被加在进来
            Log.i("muid",mList.toString());
            return "success";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if ("success".equals(s)) {
                //到时需从数据库中去数据
                //setAdapter();
                mGuanZhuAdapter.notifyDataSetChanged();//通知界面刷新
                mPullToRefreshListView.onRefreshComplete();//表示刷新完成
            }
        }
    }

    private void initView() {
        mACache = ACache.get(GuanZhuActivity.this);
        mEditText = (EditText) findViewById(R.id.hhy_guanzhu_editview);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.guanzhu_listview);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.guanzhu_relative);
    }

    private void initData() {
        mList = new ArrayList<GuanZhu>();
        GuanZhu guanZhu1 = new GuanZhu(0, "酷宝宝有时很任性", "手一残100首歌就没了", "http://img3.imgtn.bdimg.com/it/u=2165175176,3806470859&fm=21&gp=0.jpg", 0,0, false);
        GuanZhu guanZhu2 = new GuanZhu(1, "酷宝宝有时很任性", "手一残100首歌就没了", "http://img3.imgtn.bdimg.com/it/u=2165175176,3806470859&fm=21&gp=0.jpg", 1,0, false);
        mList.add(guanZhu1);
        mList.add(guanZhu2);
        selectDataBase();
    }

    private void setAdapter() {

        mGuanZhuAdapter = new GuanZhuAdapter(mList, GuanZhuActivity.this, mRelativeLayout);
        mPullToRefreshListView.setAdapter(mGuanZhuAdapter);

    }

    private void selectDataBase() {
        url = "http://10.201.1.148:8888/HttpServer/HttpServer";
        //查收查询数据库，取出数据
        RequestParams params = new RequestParams(url);
        //1：是当前用户的uid，需要在用户登陆时获取他的uid，然后再传过来
        params.addQueryStringParameter("concern", "1");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                mACache.put("carelist",result);
                //查询成功
                Gson gson = new Gson();
                UserInfo userInfo = null;
                Type type = new TypeToken<List<UserInfo>>() {
                }.getType();
                List<UserInfo> lists = gson.fromJson(result, type);

                //Toast.makeText(GuanZhuActivity.this, lists.toString(), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < lists.size(); i++) {
                    userInfo = lists.get(i);
                    Toast.makeText(GuanZhuActivity.this, userInfo.getFlag1()+"", Toast.LENGTH_SHORT).show();
                    guanZhu = new GuanZhu(userInfo.getUid(), userInfo.getUname(), userInfo.getUsign(), userInfo.getUrl(), 2,userInfo.getFlag1(), true);
                    mList.add(guanZhu);
                }
                setAdapter();
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

    private void initXUtils() {
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
