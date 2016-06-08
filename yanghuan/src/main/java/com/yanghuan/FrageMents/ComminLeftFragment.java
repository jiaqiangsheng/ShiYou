package com.yanghuan.FrageMents;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lxx.Adapter.MycontextAdapter;
import com.lxx.activity.Dynamic_Details;
import com.lxx.bean.Dynamic;
import com.lxx.bean.Lxx_BeanContext;
import com.lxx.bean.User;
import com.yanghuan.BuildConfig;
import com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 潇 on 2016/6/2.
 */
public class ComminLeftFragment extends Fragment {
    private View view;
    List<Lxx_BeanContext> contextlist;
    Lxx_BeanContext contextBean;
    List<String> imageurllist;
    ListView listView;
    MycontextAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.commitleft,null);
        initXUtils();
        initView();
        getJsonData();
        initData();
        adapter=new MycontextAdapter(getActivity(),contextlist);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Dynamic_Details.class);
                intent.putExtra("list", contextlist.get(position));
                startActivity(intent);
                // Toast.makeText(Mycontext.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void initView() {
        listView= (ListView) view.findViewById(R.id.lx_listView);
    }

    private void getJsonData() {
        Log.e("李萧萧", "" + "执行");
        RequestParams params = new RequestParams("http://10.201.1.151:8080/JavaAppWeb/selectforum");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Dynamic>>() {
                }.getType();
                List<Dynamic> list = gson.fromJson(result.toString(), type);
                for (int i = 0; i < list.size(); i++) {
                    Dynamic dynamic = list.get(i);
                    imageurllist = new ArrayList<>();
                    imageurllist.addAll(dynamic.getImageListString());
                    User user = dynamic.getUser();
                    contextBean = new Lxx_BeanContext(dynamic.getFid(),user.getName(), dynamic.getFdata(), user.getUpicture(), imageurllist, dynamic.getFcontext(), imageurllist.size(), imageurllist.size(),dynamic.getUser().getUid());
                    contextlist.add(contextBean);
                    adapter.notifyDataSetChanged();
                    Log.e("李萧萧", "" + user.getName());
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("李萧萧", "456");
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
        x.Ext.init(getActivity().getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
    private void initData() {
        contextlist=new ArrayList<>();
        imageurllist=new ArrayList<>();

    }
}
