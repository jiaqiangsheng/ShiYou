package com.yanghuan.FrageMents;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yanghuan.Beans.Picture;
import com.yanghuan.Beans.chathistory;
import com.yanghuan.Beans.wschat;
import com.yanghuan.MyViews.HorizontalListView;
import com.yanghuan.R;
import com.yanghuan.adapters.HorenListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杨欢 on 2016/5/23.
 */
public class WsFragement extends Fragment {
    //图片解析
    public static final String TAG = "XUTILS";
    String mPath;
    String pic = null;
    String name = null;
    HorenListAdapter adapter;
    List<Picture> mlist=new ArrayList<>();;
    GridView gridView;
    View mView;
    Context mContext;
    List<Picture> list;
  /*  HorizontalListView */
   HorizontalListView listView;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext=activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.weishi,null);
        initView();
       /* initData();*/
        addListener();
        initData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                Toast.makeText(getContext(), "你点击的是第"+(arg2+1)+"张图片", Toast.LENGTH_SHORT).show();
            }
        });
        return mView;
    }

    private void addListener() {

        Log.e("yh", "聊天历史");
        mPath="http://10.201.1.3:8080/ShiYou_HttpServer2/Chathistory";
        //get请求
        //第一步：设置访问路径以及携带数据
        RequestParams params = new RequestParams(mPath);
        int id=2;
        params.addBodyParameter("ds",String.valueOf(id));
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e("yh","0000000000000");
               try {
                    JSONArray jsonArray=new JSONArray(result);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        //JSONArray wsidArray=jsonObject.getJSONArray("wsid");
                        //String wspicture=wsidArray.getJSONObject(0).getString("wspicture");
                        String wspicture=jsonObject.getString("wspicture");
                        Log.e("yh",wspicture+"wspicture");
                        //String name=wsidArray.getJSONObject(0).getString("wsname");
                        String name=jsonObject.getString("wsname");
                        Picture pic1=new Picture(name,wspicture,"");
                        mlist.add(pic1);
                        mlist.add(pic1);
                        mlist.add(pic1);


                    }
                        adapter=new HorenListAdapter(mContext,mlist);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yh", " onError：" + pic);

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

   /* private void initData() {
        MyApplication application = new MyApplication();
        mPath =application.getUrl();
    }
*/
    private void initView() {
        listView = (HorizontalListView) mView.findViewById(R.id.listView);
        gridView= (GridView) mView.findViewById(R.id.weishi_gridView);
    }

    private void initData() {
        list=new ArrayList<>();
        Picture pic1=new Picture("电视","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj7.jpg","");
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        adapter=new HorenListAdapter(mContext,list);;
        gridView.setAdapter(adapter);

    }
    /*private void initData() {
        list=new ArrayList<>();
        Picture pic1=new Picture("电视","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj7.jpg","");
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);
        list.add(pic1);

        mlist=new ArrayList<>();
        Picture pic=new Picture("电视","http://o6nj6n5ea.bkt.clouddn.com/yh_dsj8.jpg","");
        mlist.add(pic);
        mlist.add(pic);
        mlist.add(pic);
        mlist.add(pic);
        mlist.add(pic);
        mlist.add(pic);
        mlist.add(pic);
        mlist.add(pic);
        mlist.add(pic);
        mlist.add(pic);
        mlist.add(pic);
        mlist.add(pic);
        mlist.add(pic);
        mlist.add(pic);
        mlist.add(pic);
        mlist.add(pic);

        adapter=new HorenListAdapter(mContext,mlist);
        listView.setAdapter(adapter);


      int si=mlist.size();
        if(si==1){
            gridView.setNumColumns(1);
        }
        else if(si==2||si==4){

           gridView.setNumColumns(2);
        }
        else {
            gridView.setNumColumns(3);
        }

        adapter=new HorenListAdapter(mContext,list);;
        gridView.setAdapter(adapter);

    }
*/


}
