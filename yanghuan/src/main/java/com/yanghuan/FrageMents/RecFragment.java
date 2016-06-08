package com.yanghuan.FrageMents;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.yanghuan.Beans.Prochat;
import com.yanghuan.Beans.RecommendBean;
import com.yanghuan.MyApplication.MyApplication;
import com.yanghuan.R;
import com.yanghuan.adapters.ImagePaperAdapter;
import com.yanghuan.adapters.RecAdapter;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by mhdong on 2016/4/29.
 */
public class RecFragment extends Fragment {
    RecommendBean bean;
    //图片解析
    public static final String TAG = "XUTILS";
    String mPath="http://10.201.1.3:8080/ShiYou_HttpServer2/ProChat";
    PullToRefreshListView mListView;
    List<RecommendBean> mList = new ArrayList<>();
    RecAdapter recAdapter;
    View view;
    View view1;
    Context mContext;
    //图片轮播
    private LayoutInflater inflater1;
    private ViewPager mviewPager;
    //用于小圆点图片
    private List<ImageView> dotViewList;
    //用于存放轮播效果图片
    private List<ImageView> list;
    LinearLayout dotLayout;
    private int currentItem  = 0;//当前页面
    boolean isAutoPlay = true;//是否自动轮播
    private ScheduledExecutorService scheduledExecutorService;
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if(msg.what == 100){
                mviewPager.setCurrentItem(currentItem,false);
            }
        }

    };
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext=activity;
    }
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recommend, null);
        view1=inflater.inflate(R.layout.viewpager,null);
        initViews();
       /* initDatas();*/
        //初始化图片解析
        /*initData();*/
        addListener();

        //添加头部
        initListHeader(view1);
        //设置为Both，表明：上拉和下拉同时都适用
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        //初始化刷新操作
        initRefreshListView();
        //listview的监听事件
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new loadDataAsyncTask(RecFragment.this).execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new loadDataAsyncTask(RecFragment.this).execute();
            }
        });
        //广告轮播
        mviewPager = (ViewPager) view.findViewById(R.id.recommend_myviewPager);
        dotLayout = (LinearLayout)view.findViewById(R.id.recommend_dotLayout);
        //dotLayout.removeAllViews();
        initView();
        if(isAutoPlay){
            startPlay();
        }
        return view;
    }
    private void addListener() {
        Log.e("yh","卫视热聊开始");
        final String[] s= new String[4];
        final String[] n= new String[4];
        //get请求a
        //第一步：设置访问路径以及携带数据
        RequestParams params = new RequestParams(mPath);
        params.addQueryStringParameter("a","a");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Prochat>>(){}.getType();
                List<Prochat> list  = gson.fromJson(result.toString(),type);
                for(int i=0;i<4;i++){
                    Prochat prochat=list.get(i);
                    String pic = prochat.getJxprogram();
                    String name = prochat.getProname();
                    s[i]=pic;
                    n[i]=name;
                }
                bean= new RecommendBean(s,"卫视热聊",n,0);
                mList.add(bean);

                Log.e("yh","卫视热聊结束");
                Log.e("错误","ergh");
                Log.e("yh", "123");
                addListener1();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yh","456");
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
        recAdapter = new RecAdapter(getContext(), mList);
        mListView.setAdapter(recAdapter);
    }
    private void addListener1() {
        Log.e("yh","电视剧热聊开始");
        final String[] s= new String[4];
        final String[] n= new String[4];
        //get请求
        //第一步：设置访问路径以及携带数据
        RequestParams params = new RequestParams(mPath);
        params.addQueryStringParameter("a","b");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Prochat>>(){}.getType();
                List<Prochat> list  = gson.fromJson(result.toString(),type);
                Prochat prochat = null;
                for(int i=0;i<list.size();i++){
                    prochat=list.get(i);
                    String pic = prochat.getJxprogram();
                    String name = prochat.getProname();
                    s[i]=pic;
                    n[i]=name;
                }
                bean= new RecommendBean(s,"电视剧热聊",n,0);
                mList.add(bean);

                Log.e("yh","电视剧热聊结束");
                Log.e("错误","ergh");
                Log.e("yh", "123");
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yh","456");
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
                addListener2();
            }
        });
    }
    private void addListener2() {
        Log.e("yh","综艺热聊开始");
        final String[] s= new String[4];
        final String[] n= new String[4];
        //get请求
        //第一步：设置访问路径以及携带数据
        RequestParams params = new RequestParams(mPath);
        params.addQueryStringParameter("a","c");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Prochat>>(){}.getType();
                List<Prochat> list  = gson.fromJson(result.toString(),type);
                Prochat prochat = null;
                for(int i=0;i<list.size();i++){
                    prochat=list.get(i);
                    String pic = prochat.getJxprogram();
                    String name = prochat.getProname();
                    s[i]=pic;
                    n[i]=name;
                }
                bean= new RecommendBean(s,"综艺热聊",n,0);
                mList.add(bean);

                Log.e("yh","综艺热聊结束");

                Log.e("错误","ergh");
                Log.e("yh", "123");
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yh","456");
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
                addListener3();

            }
        });
    }
    private void addListener3() {
        Log.e("yh","其他热聊开始");
        final String[] s= new String[4];
        final String[] n= new String[4];

        //get请求
        //第一步：设置访问路径以及携带数据
        RequestParams params = new RequestParams(mPath);
        params.addQueryStringParameter("a","d");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Prochat>>(){}.getType();
                List<Prochat> list  = gson.fromJson(result.toString(),type);
                Prochat prochat = null;
                for(int i=0;i<list.size();i++){
                    prochat=list.get(i);
                    String pic = prochat.getJxprogram();
                    String name = prochat.getProname();
                    s[i]=pic;
                    n[i]=name;
                }
                bean= new RecommendBean(s,"其他热聊",n,0);
                mList.add(bean);
                Log.e("yh","其他热聊结束");
                Log.e("错误","ergh");
                Log.e("yh", "123");
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yh","456");
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {

            }
        });
    }


    /*private void initData() {
        MyApplication application=new MyApplication();
        mPath=application.getUrl();
    }*/
    private void initListHeader(View view1) {
        //pulltorefresh没有addHeaderView方法 ，但内部有listview
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT);
        view1.setLayoutParams(layoutParams);
        ListView lv=mListView.getRefreshableView();
        lv.addHeaderView(view1);
    }

    private void initViews() {
        mListView = (PullToRefreshListView)view.findViewById(R.id.recommend_listview);
    }

    /*private void initDatas() {
        RecommendBean recommendBean=new RecommendBean(new String[]{"http://o6nj6n5ea.bkt.clouddn.com/yh/b4.jpg"
                ,"http://o6nj6n5ea.bkt.clouddn.com/yh/b3.jpg"
                ,"http://o6nj6n5ea.bkt.clouddn.com/yh/b3.jpg"
                ,"http://o6nj6n5ea.bkt.clouddn.com/yh/b7.jpg"},"电视剧",new String[]{"1","2","3","4"},1);
        RecommendBean recommendBean1=new RecommendBean(new String[]{"http://o6nj6n5ea.bkt.clouddn.com/yh/b4.jpg"
                ,"http://o6nj6n5ea.bkt.clouddn.com/yh/b3.jpg"
                ,"http://o6nj6n5ea.bkt.clouddn.com/yh/b3.jpg"
                ,"http://o6nj6n5ea.bkt.clouddn.com/yh/b7.jpg"},"电影",new String[]{"1","2","3","4"},2);
        mList.add(recommendBean);
        mList.add(recommendBean1);

    }*/
   /* private void initData1() {
      *//*  RecommendBean recommendBean=new RecommendBean(new String[]{"http://o6nj6n5ea.bkt.clouddn.com/yh/b1.jpg",
                "http://o6nj6n5ea.bkt.clouddn.com/yh/b4.jpg",
                "http://o6nj6n5ea.bkt.clouddn.com/yh/b3.jpg",
                "http://o6nj6n5ea.bkt.clouddn.com/yh/b7.jpg"},"电影",new String[]{"5","6","7","8"},2);
        mList.add(recommendBean);*//*
    }*/



    //内部类：实现数据加载的耗时操作
    static class loadDataAsyncTask extends AsyncTask<Void, Void, String> {
        private RecFragment fragement;
        public loadDataAsyncTask(RecFragment fragement) {
            this.fragement = fragement;
        }
        @Override
        protected String doInBackground(Void... params) {
            //用一个线程来模拟刷新
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           /* //加载数据
            fragement.initData1();*/
            return "success";
        }
        //对返回值进行操作
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if ("success".equals(s)) {
                //通知数据集改变，刷新页面
                fragement.recAdapter.notifyDataSetChanged();
                //刷新完成
                fragement.mListView.onRefreshComplete();
            }
        }
    }
    public void initRefreshListView() {
        ILoadingLayout startLables = mListView.getLoadingLayoutProxy(true, false);
        startLables.setPullLabel("下拉刷新");
        startLables.setRefreshingLabel("正在拉");
        startLables.setReleaseLabel("放开刷新");
        ILoadingLayout endLabels = mListView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉加载");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("放开加载...");
    }
    //广告轮播
    public void initView(){
        dotViewList = new ArrayList<ImageView>();
        list = new ArrayList<ImageView>();
        for (int i = 0; i < 4; i++) {
            ImageView dotView = new ImageView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

            params.leftMargin = 15;//设置小圆点的外边距
            params.rightMargin = 15;

            params.height = 40;//设置小圆点的大小
            params.width = 40;

            if(i == 0){
                dotView.setBackgroundResource(R.mipmap.point_pressed);
            }else{

                dotView.setBackgroundResource(R.mipmap.point_unpressed);
            }
            dotLayout.addView(dotView, params);

            dotViewList.add(dotView);
            //上面是动态添加了四个小圆点
        }
        inflater1=LayoutInflater.from(getContext());
        ImageView img1 = (ImageView) inflater1.inflate(R.layout.scrollview_item, null);
        ImageView img2 = (ImageView) inflater1.inflate(R.layout.scrollview_item, null);
        ImageView img3 = (ImageView) inflater1.inflate(R.layout.scrollview_item, null);
        ImageView img4 = (ImageView) inflater1.inflate(R.layout.scrollview_item, null);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"第一张图片",Toast.LENGTH_LONG).show();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"第二张图片",Toast.LENGTH_LONG).show();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"第三张图片",Toast.LENGTH_LONG).show();
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"第四张图片",Toast.LENGTH_LONG).show();
            }
        });

        img1.setBackgroundResource(R.mipmap.main_img1);
        img2.setBackgroundResource(R.mipmap.main_img2);
        img3.setBackgroundResource(R.mipmap.main_img3);
        img4.setBackgroundResource(R.mipmap.main_img4);

        list.add(img1);
        list.add(img2);
        list.add(img3);
        list.add(img4);

        ImagePaperAdapter adapter = new ImagePaperAdapter((ArrayList)list);

        mviewPager.setAdapter(adapter);
        mviewPager.setCurrentItem(0);
        mviewPager.setOnPageChangeListener(new MyPageChangeListener());

    }

    /*  *
       * 开始轮播图切换
       */
    private void startPlay(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
        //根据他的参数说明，第一个参数是执行的任务，第二个参数是第一次执行的间隔，第三个参数是执行任务的周期；
    }

    /*  *
       *执行轮播图切换任务
       *
  */
    private class SlideShowTask implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (mviewPager) {
                currentItem = (currentItem+1)%list.size();
                handler.sendEmptyMessage(100);
            }
        }
    }
    /*  *
       * ViewPager的监听器
       * 当ViewPager中页面的状态发生改变时调用
       *
  */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        boolean isAutoPlay = false;
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;
                    System.out.println(" 手势滑动，空闲中");
                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;
                    System.out.println(" 界面切换中");
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (mviewPager.getCurrentItem() == mviewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        mviewPager.setCurrentItem(0);
                        System.out.println(" 滑动到最后一张");
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (mviewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        mviewPager.setCurrentItem(mviewPager.getAdapter().getCount() - 1);
                        System.out.println(" 滑动到第一张");
                    }
                    break;
            }
        }
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
        }
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            //这里面动态改变小圆点的被背景，来实现效果
            currentItem = pos;
            for(int i=0;i < dotViewList.size();i++){
                if(i == pos){
                    ((View)dotViewList.get(pos)).setBackgroundResource(R.mipmap.point_pressed);
                }else {
                    ((View)dotViewList.get(i)).setBackgroundResource(R.mipmap.point_unpressed);
                }
            }
        }
    }


}
