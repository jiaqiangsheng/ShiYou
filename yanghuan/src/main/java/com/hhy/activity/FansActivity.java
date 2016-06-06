package com.hhy.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hhy.adapter.FansAdapter;
import com.hhy.bean.Fans;
import com.hhy.bean.UserInfo;
import com.yanghuan.BuildConfig;
import com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FansActivity extends AppCompatActivity {
    List<Fans> mList;
    PullToRefreshListView mPullToRefreshListView;
    FansAdapter mFansAdapter;
    ImageView mImageView;
    boolean flag = false;
    String url;
    Fans mFans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hhy_activity_fans);
        //初始化xUtils
        initXUtils();
        initView();
        initData();
        //设置刷新模式，下拉刷新，上拉加载都行
        //设置刷新模式为BOTH才可以上拉和下拉都能起作用,必须写在前面
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        //设置刷新时头部的状态（即显示的图标和文字）
        initStatus();
        addlistener();
        //setAdapter();

    }

    private void initXUtils() {
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

    private void initStatus() {
        //设置头部下拉刷新时的样式
        ILoadingLayout topLayout = mPullToRefreshListView.getLoadingLayoutProxy(true, false);
        topLayout.setPullLabel("下拉刷新");
        topLayout.setRefreshingLabel("正在拼命加载中...");
        topLayout.setReleaseLabel("放开刷新");

    }

    private void addlistener() {
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
                //跳转到某一粉丝的详细介绍页面上
                position = position - 1;
                int uid = mList.get(position).getOuid();
                Toast.makeText(FansActivity.this, position+"", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(FansActivity.this,OtherInfoActivity.class);
                intent.putExtra("uid",uid+"");
                startActivity(intent);
               /* Intent intent = new Intent(FansActivity.this,GuanZhuActivity.class);
                startActivity(intent);*/
            }
        });
    }

    class loadDataAsyn extends AsyncTask<Void, Void, String> {
        /* GuanZhuActivity mGuanZhuActivity;

         public loadDataAsyn(GuanZhuActivity mMainActivity){
             this.mGuanZhuActivity = mMainActivity;
         }*/
        @Override
        protected String doInBackground(Void... params) {
            //先将原来的数据清空，重新从数据库加载数据
            //mList.clear();
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
                mFansAdapter.notifyDataSetChanged();//通知界面刷新
                mPullToRefreshListView.onRefreshComplete();//表示刷新完成
            }
        }
    }


    private void initView() {
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.fans_listview);

    }

    private void initData() {
        mList = new ArrayList<Fans>();
        mFans = new Fans(1,"http","全部关注","全部关注",R.drawable.card_icon_addattention,0);
        mList.add(mFans);
        //从数据库上获取图片时用Glide进行加载
       selectDataBase();

    }

    private void selectDataBase() {
        url = "http://10.201.1.148:8888/HttpServer/HttpServer";
        //查收查询数据库，取出数据
        RequestParams params = new RequestParams(url);
        //先默认为1
        params.addQueryStringParameter("userid", 1+"");
        params.addQueryStringParameter("fans", "fans");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //查询成功
                Gson gson = new Gson();
                UserInfo userInfo = null;
                Type type = new TypeToken<List<UserInfo>>() {
                }.getType();
                List<UserInfo> lists = gson.fromJson(result, type);
                //Toast.makeText(GuanZhuActivity.this, lists.toString(), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < lists.size(); i++) {
                    userInfo = lists.get(i);
                    Toast.makeText(FansActivity.this, userInfo.getFlag1()+"", Toast.LENGTH_SHORT).show();
                    mFans = new Fans(userInfo.getUid(),userInfo.getUrl(),userInfo.getUname(),userInfo.getUsign(),userInfo.getFlag1(),1);
                    //Toast.makeText(FansActivity.this, mFans.getFla()+"", Toast.LENGTH_SHORT).show();
                    mList.add(mFans);

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

    private void setAdapter() {
        mFansAdapter = new FansAdapter(mList, FansActivity.this);
        mPullToRefreshListView.setAdapter(mFansAdapter);

    }


}

