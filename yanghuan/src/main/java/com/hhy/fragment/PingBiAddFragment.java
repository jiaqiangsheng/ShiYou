package com.hhy.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hhy.adapter.PingBiAddAdapter;
import com.hhy.bean.GuanZhu;
import com.hhy.bean.UserInfo;
import com.hhy.bean.User_Pingbi;
import com.yanghuan.BuildConfig;
import com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class PingBiAddFragment extends Fragment {
    List<User_Pingbi> mList;
    User_Pingbi mUser_pingbi;
    PingBiAddAdapter mAdapter;
    PullToRefreshListView mPullToRefreshListView;
    RelativeLayout mRelativeLayout;
    String url;
    GuanZhu guanZhu;
    FragmentManager mManager;
    FragmentTransaction mTransaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hhy_fragment_pingbi_add,null);
        //初始化xUtils
        initXUtils();
        initView(view);
        initData();

        //设置刷新模式，下拉刷新，上拉加载都行
        //设置刷新模式为BOTH才可以上拉和下拉都能起作用,必须写在前面
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        //设置刷新时头部的状态（即显示的图标和文字）
        initStatus();
        setAdapter();
        return view;
    }

    private void initXUtils() {
        x.Ext.init(getActivity().getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

    private void initStatus() {
        //设置头部下拉刷新时的样式
        ILoadingLayout topLayout =  mPullToRefreshListView.getLoadingLayoutProxy(true,false);
        topLayout.setPullLabel("下拉刷新");
        topLayout.setRefreshingLabel("正在拼命加载中...");
        topLayout.setReleaseLabel("放开刷新");

    }

    static class loadDataAsyn extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(5000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //mGuanZhuActivity.initData();
            return "success";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if("success".equals(s)){
                //到时需从数据库中去数据
                // mMainActivity.mArrayAdapter.notifyDataSetChanged();//通知界面刷新
                // mMainActivity.mPullToRefreshListView.onRefreshComplete();//表示刷新完成
            }
        }
    }

    private void initView(View view) {
        url = "http://10.201.1.148:8888/HttpServer/HttpServer";
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.hhy_pingbiadd_relative);
        mPullToRefreshListView = (PullToRefreshListView)view.findViewById(R.id.hhy_pingbi_add_listview);
        mPullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "123", Toast.LENGTH_SHORT).show();
            }
        });
        mManager = getFragmentManager();
        mTransaction = mManager.beginTransaction();
    }

    private void initData() {
        mList = new ArrayList<User_Pingbi>();
        mUser_pingbi = new User_Pingbi(1,  "http://img3.imgtn.bdimg.com/it/u=2165175176,3806470859&fm=21&gp=0.jpg","uname","usign", 0);
        mList.add(mUser_pingbi);
        selectDataBase();

    }

    private void setAdapter() {

        mAdapter = new PingBiAddAdapter(mList, getActivity());
        mPullToRefreshListView.setAdapter(mAdapter);
    }

    private void selectDataBase() {
        //查收查询数据库，取出数据
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("concern","concern");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //查询成功
                Gson gson = new Gson();
                UserInfo userInfo = null;
                Type type = new TypeToken<List<UserInfo>>(){}.getType();
                List<UserInfo> lists = gson.fromJson(result,type);
                Toast.makeText(getActivity(), lists.toString(), Toast.LENGTH_SHORT).show();
                for(int i=0 ;i<lists.size();i++){
                    userInfo = lists.get(i);
                    Toast.makeText(getActivity(), userInfo.toString(), Toast.LENGTH_SHORT).show();
                    mUser_pingbi = new User_Pingbi(userInfo.getUid(),userInfo.getUrl(),userInfo.getUname(),userInfo.getUsign(),1);
                    mList.add(mUser_pingbi);
                }
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
