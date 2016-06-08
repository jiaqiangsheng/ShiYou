package com.jqs.example.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookPaiHangActivity extends AppCompatActivity {
    public static final int REFRESH3 = 3;
    public static final String KEY = "FL";
    //控件
    RelativeLayout mRelativeLayout;
    ListView mListView;
    TextView mTextView;
    ImageView mBlackImage, mMuchImage;
    PopupWindow mPopupWindow;

    //工具
    List<MyStoreItemBean> mList;
    List<Book> listBook;
    List<BookNovel> mBookNovels;
    MyStoreAdapter mStoreAdapter;
    String mpath;
    //窗口Popwindow里的listview
    ListView mPopListView;
    //菜单里的数据
    List<Map<String, String>> mMapList;


    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //处理消息的方法
            //区分当前消息
            int what = msg.what;
            switch (what){
                case REFRESH3:
                    List<MyStoreItemBean> listMess= (List<MyStoreItemBean>) msg.obj;
                    mStoreAdapter = new MyStoreAdapter(BookPaiHangActivity.this, listMess);
                    mStoreAdapter.notifyDataSetChanged();
                    mListView.setAdapter(mStoreAdapter);
                    break;
            }
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_pai_hang);
        initData();
        initView();
        initListener();
    }

    private void initView() {
        mRelativeLayout= (RelativeLayout) findViewById(R.id.book_paihang_top_relayout);
        mTextView= (TextView) findViewById(R.id.book_paihang_saixuan_text);
        mTextView.setText(mMapList.get(0).get(KEY));
        //ListView 绑定适配器
        mListView = (ListView) findViewById(R.id.book_paihang_listview);
        mStoreAdapter = new MyStoreAdapter(BookPaiHangActivity.this, mList);
        mListView.setAdapter(mStoreAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyStoreItemBean myStoreItemBean = mList.get(position);
                Intent intent = new Intent(BookPaiHangActivity.this, BookDetailsActivity.class);
                intent.putExtra("storeBean", myStoreItemBean);
                startActivity(intent);
            }
        });


    }

    //模拟数据
    private void initData() {
        MyApplication myApplication = (MyApplication) getApplication();
        mpath = myApplication.getUrl();
        mList = new ArrayList<>();



        mMapList = new ArrayList<>();
        HashMap<String, String> map1 = new HashMap<>();
        map1.put(KEY, "按时间");
        HashMap<String, String> map2 = new HashMap<>();
        map2.put(KEY, "按兑换量");
        HashMap<String, String> map3 = new HashMap<>();
        map3.put(KEY, "按阅读量");
        mMapList.add(map1);
        mMapList.add(map2);
        //mMapList.add(map3);

        selectBook();
    }

    //监听事件
    private void initListener() {
        mBlackImage = (ImageView) findViewById(R.id.book_paihang_recback);
        mMuchImage = (ImageView) findViewById(R.id.book_paihang_gengduo);
        //返回
        mBlackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //更多
        mMuchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPopupWindow!=null && mPopupWindow.isShowing()){
                    mPopupWindow.dismiss();
                }else {
                    View layouts=getLayoutInflater().inflate(R.layout.book_paihang_window,null);
                    mPopListView= (ListView) layouts.findViewById(R.id.book_paihang_window_list);
                    SimpleAdapter simpleAdapter=new SimpleAdapter(BookPaiHangActivity.this,
                            mMapList,R.layout.book_paihang_window_item,new String[]{KEY},new int[]{R.id.book_paihang_window_text});
                    mPopListView.setAdapter(simpleAdapter);

                    //点击内部内容触发事件
                    mPopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //实时改变顶部textview显示值
                            mTextView.setText(mMapList.get(position).get(KEY));

                            String condition="xsid";
                            switch (position){
                                case 0:
                                    condition="xsid";
                                    break;
                                case 1:
                                    condition="dhnumber";
                                    break;
                            }

                            //按条件访问数据库，获取数据
                            mList.clear();
                            //分类查询
                            RequestParams params = new RequestParams(mpath);
                            params.addQueryStringParameter("fla","paihang");
                            params.addQueryStringParameter("condition",condition);
                            x.http().get(params, new Callback.CommonCallback<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    Log.e("onSuccess","paihang0.0成功");
                                    //访问成功，参数其实就是PrintWriter写回的值
                                    //把JSON格式的字符串改为Student对象
                                    Gson gson = new Gson();
                                    Type type = new TypeToken<List<Book>>() {
                                    }.getType();
                                    listBook = gson.fromJson(result, type);
                                    mBookNovels=listBook.get(2).getListnoNovels();
                                    for (int i = 0; i < mBookNovels.size(); i++) {
                                        MyStoreItemBean s = new MyStoreItemBean(mBookNovels.get(i).getXsid(),mBookNovels.get(i).getXsname(), mBookNovels.get(i).getXsintroduce(),
                                                mBookNovels.get(i).getXsauthor(), mBookNovels.get(i).getDhnumber(),
                                                mBookNovels.get(i).getXspicture(), 1, 1);
                                        mList.add(s);
                                    }
                                    Log.e("onSuccess","paihang0.0成功"+mList.toString());
                                    //创建一个消息对象
                                    Message message = new Message();
                                    //添加识别当前消息标志位
                                    message.what = REFRESH3;
                                    //携带数据，可以是任意的object
                                    message.obj = mList;
                                    //通过handler发送信息，通知主线程调用handleMessage方法来处理
                                    mHandler.sendMessage(message);

                                }

                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {
                                    Log.e("onError","paihang0.0失败");
                                }

                                @Override
                                public void onCancelled(CancelledException cex) {

                                }

                                @Override
                                public void onFinished() {

                                }
                            });

                            //隐藏弹出窗口
                            if(mPopupWindow!=null && mPopupWindow.isShowing()){
                                mPopupWindow.dismiss();
                            }



                        }
                    });
                    //创建弹出窗口
                    // 窗口内容为layouts，里面包含一个mPopListView
                    // 窗口宽度为150
                    mPopupWindow=new PopupWindow(layouts,150, LinearLayout.LayoutParams.WRAP_CONTENT);
                    ColorDrawable cd=new ColorDrawable(0xaae2e1df);
                    mPopupWindow.setBackgroundDrawable(cd);
                    mPopupWindow.setAnimationStyle(R.style.PopupAnimation);
                    mPopupWindow.update();
                    mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
                    mPopupWindow.setTouchable(true); // 设置popupwindow可点击
                    mPopupWindow.setOutsideTouchable(true); // 设置popupwindow外部可点击
                    mPopupWindow.setFocusable(true); // 获取焦点

                    // 设置popupwindow的位置（相对image点击按钮的位置）
                    int topBarHeight = mRelativeLayout.getBottom();
                    mPopupWindow.showAsDropDown(mMuchImage, 0,
                            (topBarHeight - mMuchImage.getHeight()) / 2);

                    mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            // 如果点击了popupwindow的外部，popupwindow也会消失
                            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                                mPopupWindow.dismiss();
                                return true;
                            }
                            return false;
                        }
                    });


                }

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
                Log.e("store","paihang我成功了");
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Book>>() {
                }.getType();

                listBook = gson.fromJson(result, type);
                mBookNovels = listBook.get(2).getListnoNovels();
                for (int i = 0; i < mBookNovels.size(); i++) {
                    MyStoreItemBean s = new MyStoreItemBean(mBookNovels.get(i).getXsid(),mBookNovels.get(i).getXsname(), mBookNovels.get(i).getXsintroduce(),
                            mBookNovels.get(i).getXsauthor(), mBookNovels.get(i).getDhnumber(),
                            mBookNovels.get(i).getXspicture(), 1, 1);
                    mList.add(s);
                }
                Log.e("onSuccess","paihang成功"+mList.toString());
                //创建一个消息对象
                Message message = new Message();
                //添加识别当前消息标志位
                message.what = REFRESH3;
                //携带数据，可以是任意的object
                message.obj = mList;
                //通过handler发送信息，通知主线程调用handleMessage方法来处理
                mHandler.sendMessage(message);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("store","paihang我失败了");
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
