package com.hhy.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.LoginUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hhy.adapter.PingBiAddAdapter;
import com.hhy.bean.UserInfo;
import com.hhy.bean.User_Pingbi;
import com.jqs.servert.utils.MyApplication;
import com.yanghuan.BuildConfig;
import com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PinBiAddActivity extends AppCompatActivity {
    List<User_Pingbi> mList;
    User_Pingbi mUser_pingbi;
    PingBiAddAdapter mAdapter;
    PullToRefreshListView mPullToRefreshListView;
    RelativeLayout mRelativeLayout;
    String url;
    String biaozhi;
    int uid;
    List<String> mUidList = new ArrayList<String>();;
    TextView mTextView;
    boolean flag = false;
    FragmentManager mFragmentManager;
    FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hhy_fragment_pingbi_add);
        MyApplication myApplication = (MyApplication) getApplication();
        url = myApplication.getUrlPath();
        initView();
        initData();

        //设置刷新模式，下拉刷新，上拉加载都行
        //设置刷新模式为BOTH才可以上拉和下拉都能起作用,必须写在前面
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        //设置刷新时头部的状态（即显示的图标和文字）
        initStatus();
        setAdapter();
        setListener();
    }

    private void setListener() {
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉时执行
                new loadDataAsyn(PinBiAddActivity.this).execute();
            }
        });

        mPullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position-1;
                //获得被屏蔽人的uid
                uid = mList.get(position).getUid();

                //
                ImageView imageView = (ImageView) view.findViewById(R.id.hhy_pingbi_dagou);
                if (imageView.isShown()) {
                    imageView.setVisibility(View.GONE);
                    //mTextView.setVisibility(View.GONE);
                    mUidList.remove(uid+"");
                    Toast.makeText(PinBiAddActivity.this, mUidList.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    mTextView.setVisibility(View.VISIBLE);
                    mUidList.add(uid+"");
                    Toast.makeText(PinBiAddActivity.this, mUidList.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        //点击完成返回PingBiTaFragment页面
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String resu = gson.toJson(mUidList);
                RequestParams params = new RequestParams(url);
                //当前登陆的人的uid,现在默认为1，以后从登录界面获取
               /* params.addQueryStringParameter("loginuserid", 1+"");*/
                params.addQueryStringParameter("loginuserid", LoginUser.userid+"");
                if("pingbita".equals(biaozhi)){
                    params.addQueryStringParameter("uid",resu);
                }else if("notsee".equals(biaozhi)){
                    params.addQueryStringParameter("pingbicannot", resu);
                }else if("blacklist".equals(biaozhi)){
                   params.addQueryStringParameter("blacklist", resu);
               }
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                       /* //查询成功
                        Gson gson = new Gson();
                        UserInfo userInfo = null;
                        Type type = new TypeToken<List<UserInfo>>() {
                        }.getType();
                        List<UserInfo> lists = gson.fromJson(result, type);
                        Toast.makeText(PinBiAddActivity.this, lists.toString(), Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < lists.size(); i++) {
                            userInfo = lists.get(i);
                            if (userInfo != null) {
                                flag = true;
                            }
                            Toast.makeText(PinBiAddActivity.this, userInfo.toString(), Toast.LENGTH_SHORT).show();
                            guanZhu = new GuanZhu(userInfo.getUname(), userInfo.getUsign(), userInfo.getUrl(), 1, true);
                            mList.add(guanZhu);
                        }
                        if (flag) {
                            mAdapter.notifyDataSetChanged();
                        }*/
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
                //关闭该Activity
                finish();
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

    static class loadDataAsyn extends AsyncTask<Void, Void, String> {
        PinBiAddActivity mActivity;

        public loadDataAsyn(PinBiAddActivity activity) {
            mActivity = activity;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mActivity.initData();
            return "success";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if ("success".equals(s)) {
                //到时需从数据库中去数据
                mActivity.mAdapter.notifyDataSetChanged();//通知界面刷新
                mActivity.mPullToRefreshListView.onRefreshComplete();//表示刷新完成
            }
        }
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.hhy_pingbi_add_wancheng);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.hhy_pingbiadd_relative);
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.hhy_pingbi_add_listview);

        mFragmentManager = getSupportFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();

        //获取传过来的标志
        Intent intent = getIntent();
        biaozhi = intent.getStringExtra("biaozhi");
        Toast.makeText(PinBiAddActivity.this, biaozhi, Toast.LENGTH_SHORT).show();
    }

    private void initData() {
        mList = new ArrayList<User_Pingbi>();
        mUser_pingbi = new User_Pingbi(1,  "http://img3.imgtn.bdimg.com/it/u=2165175176,3806470859&fm=21&gp=0.jpg","uname","usign", 0);
        mList.add(mUser_pingbi);
        selectDataBase();

    }

    private void setAdapter() {

        mAdapter = new PingBiAddAdapter(mList,PinBiAddActivity.this);
        mPullToRefreshListView.setAdapter(mAdapter);
    }

    private void selectDataBase() {
        //查收查询数据库，取出数据
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("concern", "1");
        //params.addQueryStringParameter("mPath", mPath);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //查询成功
                Gson gson = new Gson();
                UserInfo userInfo = null;
                Type type = new TypeToken<List<UserInfo>>() {
                }.getType();
                List<UserInfo> lists = gson.fromJson(result, type);
                //Toast.makeText(PinBiAddActivity.this, lists.toString(), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < lists.size(); i++) {
                    userInfo = lists.get(i);
                    if (userInfo != null) {
                        flag = true;
                    }
                   // Toast.makeText(PinBiAddActivity.this, userInfo.toString(), Toast.LENGTH_SHORT).show();
                    mUser_pingbi = new User_Pingbi(userInfo.getUid(),userInfo.getUrl(),userInfo.getUname(), userInfo.getUsign(),1);
                    mList.add(mUser_pingbi);
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

}
