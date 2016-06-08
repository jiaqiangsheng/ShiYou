package com.jqs.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jqs.example.adapter.BookFenGridAdapter;
import com.jqs.example.adapter.MyStoreAdapter;
import com.jqs.example.beans.Book;
import com.jqs.example.beans.BookNovel;
import com.jqs.example.beans.MyStoreItemBean;
import com.jqs.servert.utils.MyApplication;
import com.yanghuan.R;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookFenLeiActivity extends AppCompatActivity {
    public static final int REFRESH2 = 2;
    //控件
    ListView mListView;
    ImageView mBlackImage;
    GridView mGridView;

    //工具
    List<MyStoreItemBean> mList;
    List<String> mStrings;
    MyStoreItemBean mStoreItemBean;
    MyStoreAdapter mStoreAdapter;
    BookFenGridAdapter mFenGridAdapter;

    //
    String mpath;
    List<Book> listBook;
    List<BookNovel> mBookNovels;


    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //处理消息的方法
            //区分当前消息
            int what = msg.what;
            List<MyStoreItemBean> listMess= (List<MyStoreItemBean>) msg.obj;
            switch (what){
                case REFRESH2:
                    mStoreAdapter = new MyStoreAdapter(BookFenLeiActivity.this, listMess);
                    mStoreAdapter.notifyDataSetChanged();
                    mListView.setAdapter(mStoreAdapter);
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_fen_lei);
        mListView = (ListView) findViewById(R.id.book_fenlei_listview);
        initData();
        initView();
        initListener();
    }

    private void initView() {
        //ListView 绑定适配器
        mStoreAdapter = new MyStoreAdapter(BookFenLeiActivity.this, mList);
        mListView.setAdapter(mStoreAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyStoreItemBean myStoreItemBean = mList.get(position);
                Intent intent = new Intent(BookFenLeiActivity.this, BookDetailsActivity.class);
                intent.putExtra("storeBean", myStoreItemBean);
                startActivity(intent);
            }
        });

        //GridView 绑定适配器
        setGridView();

    }
    //模拟数据
    private void initData() {
        MyApplication myApplication = (MyApplication) getApplication();
        mpath = myApplication.getUrl();
        mList = new ArrayList<>();
        mStrings = new ArrayList<>();

        mStrings.add("言情");
        mStrings.add("古装");
        mStrings.add("婚姻职场");
        mStrings.add("仙侠");
        mStrings.add("都市");
        mStrings.add("校园");
        mStrings.add("穿越");
        mStrings.add("历史抗战");
        mStrings.add("玄幻");
        mStrings.add("名著");
        mStrings.add("其他");

        selectBook();
    }

    //设置GridView参数
    private  void setGridView(){
        mGridView = (GridView) findViewById(R.id.book_fenlei_gridview);


        int size = mStrings.size();
        int length = 100;
        //使用DisplayMetrics获取屏幕参数
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * length * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        mGridView.setLayoutParams(layoutParams); // 设置GirdView布局参数,横向布局的关键
        mGridView.setColumnWidth(itemWidth); // 设置列表项宽
        mGridView.setHorizontalSpacing(5); // 设置列表项水平间距
        mGridView.setStretchMode(GridView.NO_STRETCH);
        mGridView.setNumColumns(size); // 设置列数量=列表集合数
        mGridView.setVerticalScrollBarEnabled(false);//设置滚动条不显示
        //mListView.invalidate();

        mFenGridAdapter = new BookFenGridAdapter(BookFenLeiActivity.this, mStrings);
        mGridView.setAdapter(mFenGridAdapter);
        gridViewListener();
    }
        //GridView点击事件
    private void gridViewListener() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TextView text= (TextView) view.findViewById(R.id.book_fenlei_griditem_text);
                view.setBackgroundColor(0xe2e1df);

                mList.clear();
                //分类查询
                RequestParams params = new RequestParams(mpath);
                params.addQueryStringParameter("fla","fenlei");
                params.addQueryStringParameter("value",mStrings.get(position));
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("onSuccess","fenlei0.0成功");
                        //访问成功，参数其实就是PrintWriter写回的值
                        //把JSON格式的字符串改为Student对象
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Book>>() {
                        }.getType();
                        listBook = gson.fromJson(result, type);
                        mBookNovels=listBook.get(1).getListnoNovels();
                        for (int i = 0; i < mBookNovels.size(); i++) {
                            MyStoreItemBean s = new MyStoreItemBean(mBookNovels.get(i).getXsid(),mBookNovels.get(i).getXsname(), mBookNovels.get(i).getXsintroduce(),
                                    mBookNovels.get(i).getXsauthor(), mBookNovels.get(i).getDhnumber(),
                                    mBookNovels.get(i).getXspicture(), 1, 1);
                            mList.add(s);
                        }
                        Log.e("onSuccess","fenlei0.0成功"+mList.toString());
                        //创建一个消息对象
                        Message message = new Message();
                        //添加识别当前消息标志位
                        message.what = REFRESH2;
                        //携带数据，可以是任意的object
                        message.obj = mList;
                        //通过handler发送信息，通知主线程调用handleMessage方法来处理
                        mHandler.sendMessage(message);

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e("onError","fenlei0.0失败");
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });


            }
        });


    }

    //返回监听事件
    private void initListener() {
        mBlackImage= (ImageView) findViewById(R.id.book_fenlei_recback);
        mBlackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //访问数据库
    private void selectBook() {
        RequestParams params = new RequestParams(mpath);
        //第二步：开始请求，设置请求方式，同时实现回调函数
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("store","FenLei我成功了");
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Book>>() {
                }.getType();

                listBook = gson.fromJson(result, type);
                mBookNovels = listBook.get(1).getListnoNovels();
                for (int i = 0; i < mBookNovels.size(); i++) {
                    MyStoreItemBean s = new MyStoreItemBean(mBookNovels.get(i).getXsid(),mBookNovels.get(i).getXsname(), mBookNovels.get(i).getXsintroduce(),
                            mBookNovels.get(i).getXsauthor(), mBookNovels.get(i).getDhnumber(),
                            mBookNovels.get(i).getXspicture(), 1, 1);
                    mList.add(s);
                }
                Log.e("onSuccess","fenlei成功"+mList.toString());
                //创建一个消息对象
                Message message = new Message();
                //添加识别当前消息标志位
                message.what = REFRESH2;
                //携带数据，可以是任意的object
                message.obj = mList;
                //通过handler发送信息，通知主线程调用handleMessage方法来处理
                mHandler.sendMessage(message);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("store","FenLei我失败了");
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
