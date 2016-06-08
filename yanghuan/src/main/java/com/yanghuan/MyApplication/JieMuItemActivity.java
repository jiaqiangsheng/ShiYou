package com.yanghuan.MyApplication;

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
import com.yanghuan.Beans.Program;
import com.yanghuan.Beans.Program2;
import com.yanghuan.R;
import com.yanghuan.Beans.JiemuShow;
import com.yanghuan.adapters.JieMu_showAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JieMuItemActivity extends AppCompatActivity {
    JieMu_showAdapter adapter;
   PullToRefreshListView listView;
    List<JiemuShow> mlist;
    ImageView imageView;
    //图片解析
    public static final String TAG = "XUTILS";
    String mPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jie_mu_item);
        initView();
        //initData();
       addListener1();
        //设置为Both，表明：上拉和下拉同时都适用
       listView.setMode(PullToRefreshBase.Mode.BOTH);
        //初始化刷新操作
        initRefreshListView();

        String activityName = getIntent().getStringExtra("1");
    }

    private void initRefreshListView() {
        ILoadingLayout startLables = listView.getLoadingLayoutProxy(true, false);
        startLables.setPullLabel("下拉刷新");
        startLables.setRefreshingLabel("正在拉");
        startLables.setReleaseLabel("放开刷新");
        ILoadingLayout endLabels = listView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉加载");
        endLabels.setRefreshingLabel("正在载入...");
        endLabels.setReleaseLabel("放开加载...");
    }

    private void addListener1() {
        Log.e("yh", "节目单");
        mPath = "http://10.201.1.3:8080/ShiYou_HttpServer2/ProgramServlet";
        //post请求
        //第一步：设置访问路径以及携带数据
        RequestParams params = new RequestParams(mPath);
        params.addBodyParameter("type", "a");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
              try {
                    JSONArray jsonArray=new JSONArray(result);
                    JSONArray list=jsonArray.getJSONObject(0).getJSONArray("list");
                    for(int i=0;i<list.length();i++){
                        JSONObject json=list.getJSONObject(i);
                        if(json.getString("programType").equals("a")){
                            String name=json.getString("programing");
                            String picture=json.getString("programName");
                            String person=json.getString("person");
                            JiemuShow jiemuShow = new JiemuShow( picture,name, "演员：" + person);
                            mlist.add(jiemuShow);
                        }
                    }
                    adapter = new JieMu_showAdapter(mlist, JieMuItemActivity.this);
                    listView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
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
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new loadDataAsyncTask(JieMuItemActivity.this).execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new loadDataAsyncTask(JieMuItemActivity.this).execute();
            }
        });
    }
    //内部类：实现数据加载的耗时操作
    static class loadDataAsyncTask extends AsyncTask<Void, Void, String> {
        private JieMuItemActivity activity;
        public loadDataAsyncTask(JieMuItemActivity activity) {
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
               activity.adapter.notifyDataSetChanged();
                //刷新完成
                activity.listView.onRefreshComplete();
            }
        }
    }

    private void addListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(JieMuItemActivity.this, "欢迎进入" + (position) + "聊天室", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        listView = (PullToRefreshListView) findViewById(R.id.activity_jieitem_listview);
        mlist = new ArrayList<>();
        imageView = (ImageView) findViewById(R.id.activity_jiemu_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
