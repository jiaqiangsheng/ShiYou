package com.hhy.activity;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.LoginUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hhy.adapter.SearchAdapter;
import com.hhy.bean.SearchBean;
import com.hhy.bean.UserInfo;
import com.hhy.receiver.SearchView;
import com.jqs.servert.utils.MyApplication;
import com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends Activity implements SearchView.SearchViewListener {
    String url;
    /**
     * 搜索结果列表view
     */
    private ListView lvResults;

    /**
     * 搜索view
     */
    private SearchView searchView;


    /**
     * 热搜框列表adapter
     */
    private ArrayAdapter<String> hintAdapter;

    /**
     * 自动补全列表adapter
     */
    private ArrayAdapter<String> autoCompleteAdapter;

    /**
     * 搜索结果列表adapter
     */
    private SearchAdapter resultAdapter;

    private List<SearchBean> dbData;

    /**
     * 热搜版数据
     */
    private List<String> hintData;

    /**
     * 搜索过程中自动补全数据
     */
    private List<String> autoCompleteData;

    /**
     * 搜索结果的数据
     */
    private List<SearchBean> resultData;

    /**
     * 默认提示框显示项的个数
     */
    private static int DEFAULT_HINT_SIZE = 4;

    /**
     * 提示框显示项的个数
     */
    private static int hintSize = DEFAULT_HINT_SIZE;

    /**
     * 设置提示框显示项的个数
     *
     * @param hintSize 提示框显示个数
     */
    public static void setHintSize(int hintSize) {
        SearchActivity.hintSize = hintSize;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.hhy_activity_search);
        MyApplication myApplication = (MyApplication) getApplication();
        url = myApplication.getUrlPath();
        initViews();
        initData();
    }

    /**
     * 初始化视图
     */
    private void initViews() {

        lvResults = (ListView) findViewById(R.id.main_lv_search_results);
        searchView = (SearchView) findViewById(R.id.main_search_layout);
        //设置监听
        searchView.setSearchViewListener(this);
        //设置adapter
        searchView.setTipsHintAdapter(hintAdapter);
        searchView.setAutoCompleteAdapter(autoCompleteAdapter);

        lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Toast.makeText(SearchActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //从数据库获取数据
        getDbData();
        //初始化热搜版数据
        getHintData();
        //初始化自动补全数据
        getAutoCompleteData(null);
        //初始化搜索结果数据
        getResultData(null);
    }

    /**
     * 获取db 数据
     */
    private void getDbData() {
        final int size = 10;
        dbData = new ArrayList<SearchBean>(size);
        SearchBean searchBean = null;
       /* for (int i = 0; i < size; i++) {
            dbData.add(new SearchBean("123", "android开发必备技能" + (i + 1), "Android自定义view——自定义搜索view"));
        }*/
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("concern", LoginUser.userid+"");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //查询成功
                Gson gson = new Gson();
                UserInfo userInfo = null;
                Type type = new TypeToken<List<UserInfo>>() {
                }.getType();
                List<UserInfo> lists = gson.fromJson(result, type);
                for(int i = 0; i< size; i++){
                    userInfo = lists.get(i);
                    dbData.add(new SearchBean(userInfo.getUrl(),userInfo.getUname(),userInfo.getUsign()));
                    //Toast.makeText(SearchActivity.this, dbData.toString(), Toast.LENGTH_SHORT).show();
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

    /**
     * 获取热搜版data 和adapter
     */
    private void getHintData() {
        /* hintData = new ArrayList<String>(hintSize);
        for (int i = 1; i <= hintSize; i++) {
            hintData.add("热搜版" + i + "：Android自定义View");
        }
        hintAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hintData);*/
        //查收查询数据库，取出数据
        hintData = new ArrayList<String>(hintSize);
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("conc", "concern");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //查询成功
                Gson gson = new Gson();
                UserInfo userInfo = null;
                Type type = new TypeToken<List<UserInfo>>() {
                }.getType();
                List<UserInfo> lists = gson.fromJson(result, type);
                for (int i = 0; i < hintSize; i++) {
                    userInfo = lists.get(i);
                    //Toast.makeText(SearchActivity.this, userInfo.getUname(), Toast.LENGTH_SHORT).show();
                    hintData.add(userInfo.getUname());
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
        hintAdapter = new ArrayAdapter<String>(SearchActivity.this, android.R.layout.simple_list_item_1, hintData);
    }

    /**
     * 获取自动补全data 和adapter
     */
    private void getAutoCompleteData(String text) {
        //Log.e("text:",text);
        if (autoCompleteData == null) {
            Log.e("search","初始化");
            //初始化
            autoCompleteData = new ArrayList<String>(hintSize);
        } else {
            // 根据text 获取auto data
            autoCompleteData.clear();
            for (int i = 0, count = 0; i < dbData.size()
                    && count < hintSize; i++) {
                if (dbData.get(i).getTitle().contains(text.trim())) {
                    Log.e("search",dbData.get(i).getTitle());
                    autoCompleteData.add(dbData.get(i).getTitle());
                    count++;
                }
            }
        }
        if (autoCompleteAdapter == null) {
            autoCompleteAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, autoCompleteData);
        } else {
            autoCompleteAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取搜索结果data和adapter
     */
    private void getResultData(String text) {
        if (resultData == null) {
            // 初始化
            resultData = new ArrayList<SearchBean>();
        } else {
            resultData.clear();
            for (int i = 0; i < dbData.size(); i++) {
                if (dbData.get(i).getTitle().contains(text.trim())) {
                    resultData.add(dbData.get(i));
                }
            }
        }
        if (resultAdapter == null) {
            resultAdapter = new SearchAdapter(this, resultData, R.layout.hhy_item_bean_list);
        } else {
            resultAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 当搜索框 文本改变时 触发的回调 ,更新自动补全数据
     * @param text
     */
    @Override
    public void onRefreshAutoComplete(String text) {
       // Toast.makeText(SearchActivity.this, text, Toast.LENGTH_SHORT).show();
        //更新数据
        getAutoCompleteData(text);
    }

    /**
     * 点击搜索键时edit text触发的回调
     *
     * @param text
     */
    @Override
    public void onSearch(String text) {
        //更新result数据
        getResultData(text);
        lvResults.setVisibility(View.VISIBLE);
        //第一次获取结果 还未配置适配器
        if (lvResults.getAdapter() == null) {
            //获取搜索数据 设置适配器
            lvResults.setAdapter(resultAdapter);
        } else {
            //更新搜索数据
            resultAdapter.notifyDataSetChanged();
        }
        Toast.makeText(this, "完成搜索", Toast.LENGTH_SHORT).show();


    }

}
