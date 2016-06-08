package com.yanghuan.FrageMents;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cache.ACache;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jqs.example.adapter.MyAdapter;
import com.lxx.Adapter.MynewsAdapter;
import com.lxx.activity.NewsDtails;
import com.lxx.bean.ImageSource;
import com.lxx.bean.News;
import com.yanghuan.BuildConfig;
import com.yanghuan.MyApplication.ShiYouActivity;
import com.yanghuan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 潇 on 2016/6/2.
 */
public class CommitrightFragement extends Fragment {
    int page=0;
    TextView mResultTextView;
    News news;
    ImageView imageView;
    List<Bitmap> bitmaps;
    List<String> alllistString;
    PullToRefreshListView mListView;
    List<News> list;
    List<ImageSource> listimageSource;
    ImageSource imageSource;
    PullToRefreshListView  listView;
    ACache aCache;
    MynewsAdapter myAdapter;
    private static final String PATH = "http://route.showapi.com/109-35";
    //申请的KEY

    private static final String KEY = "400237f49c264bd384a7e86c0ddd80ad";
    View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.commitright,null);
        initXUtils();
        initViews();
        initData();
        getNewsData();
        listView= (PullToRefreshListView) mView.findViewById(R.id.Dynamic_listView);
        initRefreshListView();
        myAdapter=new MynewsAdapter(getActivity(),list);

        listView.setAdapter(myAdapter);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.clear();
                page=0;
                getJSONMessage();
                Toast.makeText(getActivity(), ""+page, Toast.LENGTH_SHORT).show();
                new loadDataAsyncTask().execute();//执行下载数据
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                getJSONMessage();



                new loadDataAsyncTask().execute();
            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "4645" + position, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),NewsDtails.class);
                intent.putExtra("position", position-1);
                intent.putExtra("getallLIst", (Serializable) list);
                startActivity(intent);

                // Toast.makeText(newslist.this, ""+ list.get(position).getAllList(), Toast.LENGTH_SHORT).show();
            }
        });
        return mView;
    }
    private void getNewsData() {
        if(aCache.getAsJSONObject("cache"+1)==null){
            getJSONMessage();
        }
        else{
            if(aCache.getAsJSONObject("cache"+1)!=null){
                String result =aCache.getAsJSONObject("cache"+1).toString();
                updateTextView(result);
            }
            if(aCache.getAsJSONObject("cache"+2)!=null){
                String result =aCache.getAsJSONObject("cache"+2).toString();
                updateTextView(result);
            }

            Toast.makeText(getActivity(), "本地加载", Toast.LENGTH_SHORT).show();
        }

    }

    private void initData() {
        listimageSource=new ArrayList<>();
        alllistString=new ArrayList<>();
        list=new ArrayList<>();
        imageView= (ImageView) mView.findViewById(R.id.imageView);
        listView= (PullToRefreshListView ) mView.findViewById(R.id.listView);
        aCache = ACache.get(getActivity());

    }

    private void initXUtils() {
        x.Ext.init(getActivity().getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

    private void initViews() {


    }
    public void getJSONMessage() {
        RequestParams params = new RequestParams(PATH);


        params.addQueryStringParameter("showapi_appid",  "18937");
        params.addQueryStringParameter("showapi_sign", KEY);
        params.addQueryStringParameter("channelId", "5572a10ab3cdc86cf39001ed");
        params.addQueryStringParameter("page",  ""+(++page));
        //不支持POST方式提交
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("my", "time=" + result);
                if(page==1||page==2)
                    aCache.put("cache"+page, result);
                updateTextView(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //  Toast.makeText(newslist.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }
    private void updateTextView(String result) {
        try {
            String text = "";
            JSONObject jsonObject = new JSONObject(result);
            //返回的状态码
            int showapi_res_code = jsonObject.getInt("showapi_res_code");
            Log.e("my", "showapi_res_code=" + showapi_res_code);
            if (showapi_res_code==0) {
                //返回的结果
                JSONArray jsonArray=jsonObject.getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("contentlist");





                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    JSONArray array = jsonArray.getJSONObject(i).getJSONArray("allList");
                    alllistString=new ArrayList<>();
                    Log.e("string", "array=" + array);
                    for (int j = 0; j < array.length(); j++){


                        String string=array.getString(j);
                        Log.e("ceshi","123"+string);
                        char fir = string.charAt(0);
                        if(fir=='{'){
                        }
                        else{
                            char  fi=string.charAt(string.length()-1);
                            if(fi=='。')
                                alllistString.add(string+"\n");
                            else
                                alllistString.add(string);
                            Log.e("string", "String=" + string);
                        }
                    }

                    String desc = object.getString("desc");
                    String title =object.getString("title");
                    Log.e("my", "allList=" + title);
                    String channelId =object.getString("channelId");
                    String channelName =object.getString("channelName");
                    String source =object.getString("source");
                    String pubDate =object.getString("pubDate");
                    String link =object.getString("link");
                    JSONArray imagearray =jsonArray.getJSONObject(i).getJSONArray("imageurls");
                    Log.e("my", "imagearray=" + imagearray.length() + imagearray.toString());
                    if(imagearray.length()>0){
                        for(int j=0;j<imagearray.length();j++){
                            JSONObject imageobject = imagearray.getJSONObject(j);
                            listimageSource=new ArrayList<>();
                            if(imageobject.isNull("height")){
                                int width=imageobject.getInt("width");
                                String url=imageobject.getString("url");
                                Log.e("my", "url=" + url);
                                Log.e("my", "width=" + width);
                                imageSource= new ImageSource(url,width);
                                listimageSource.add(imageSource);
                            }
                            else
                            {
                                int height=imageobject.getInt("height");
                                int width=imageobject.getInt("width");
                                String url=imageobject.getString("url");
                                Log.e("my", "url=" + url);
                                Log.e("my", "height=" + height);
                                Log.e("my", "width=" + width);

                                imageSource= new ImageSource(url,height,width);
                                listimageSource.add(imageSource);
                            }


                        }
                        news=new News(channelId,channelName,desc,title,
                                pubDate,source,link,alllistString,listimageSource);
                        Log.e("内部", "getImageUrl=" + alllistString);
                        list.add(news);
                        if(myAdapter!=null)
                            myAdapter.notifyDataSetChanged();
                        //listimageSource.clear();
                    }



                    text=""+title+"\n";
                }
                Log.e("my", "text=" + text);
            } else {
                mResultTextView.setText("无此信息");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //内部类：实现数据加载的耗时操作
    private class loadDataAsyncTask extends AsyncTask<Void, Void, String> {
        public loadDataAsyncTask() {

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
            getJSONMessage();
            return "success";
        }
        //对返回值进行操作
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if ("success".equals(s)) {
                //通知数据集改变，刷新页面
                Log.e("my", "text=" +"刷新中");
               myAdapter.notifyDataSetChanged();
                //刷新完成
                listView.onRefreshComplete();
                Log.e("my", "text=" + "刷新完成");
            }
        }
    }


    public void initRefreshListView() {
        ILoadingLayout startLables = listView.getLoadingLayoutProxy(true, false);
        startLables.setPullLabel("下拉刷新");
        startLables.setRefreshingLabel("正在刷新");
        startLables.setReleaseLabel("放开刷新");
        ILoadingLayout endLabels = listView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉加载");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("放开加载...");
    }





}
