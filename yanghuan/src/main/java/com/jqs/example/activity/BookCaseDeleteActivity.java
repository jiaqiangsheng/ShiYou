package com.jqs.example.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jqs.example.adapter.CaseDeleteAdapter;
import com.jqs.example.beans.Book;
import com.jqs.example.beans.BookBeans;
import com.jqs.example.beans.MyCaseItemBean;
import com.jqs.example.fragments.BookCaseFragment;
import com.jqs.servert.utils.MyApplication;
import  com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookCaseDeleteActivity extends AppCompatActivity{
    MyCaseItemBean mCaseItemBean;
    List<MyCaseItemBean> mList;
    CaseDeleteAdapter mCaseDeleteAdapter;

    ListView mListView;
    Button checkAll, delete;
    ImageView mImageView;

    //记录删除位置
    List<Integer> dels;

    List<Book> listBook;
    List<BookBeans> list;
    //标志位
    boolean flagAll = false;
    String mpath;
    //回调函数
    BookCaseFragment mBookCaseFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_case_delete);
        initData();
        initView();

    }

    private void initData() {
        mList = new ArrayList<>();

        MyApplication myApplication = (MyApplication) getApplication();
        mpath = myApplication.getUrl();

        RequestParams params = new RequestParams(mpath);
        //第二步：开始请求，设置请求方式，同时实现回调函数
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Book>>() {
                }.getType();
                listBook = gson.fromJson(result, type);
                list=listBook.get(0).getListbooBeans();
                for (int i = 0; i < list.size(); i++) {
                    MyCaseItemBean m = new MyCaseItemBean(list.get(i).getName(), list.get(i).gethLocation(),
                            list.get(i).gethData(), list.get(i).getUpicture());
                    //Log.e("Liat", "__" + list.get(i).getName() + list.get(i).gethLocation() + list.get(i).gethData() + list.get(i).getUpicture());
                    mList.add(m);
                }
                if (mList.size() != 0) {
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setCheckFlag(false);
                    }
                }
                initView();
                //Log.e("onSuccess", "list" + mList.size() + "------" + mList.toString());

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
        if (mList.size() != 0) {
            for (int i = 0; i < mList.size(); i++) {
                mList.get(i).setCheckFlag(false);
            }
        }
    }


    private void initView() {
        mListView = (ListView) findViewById(R.id.case_delete_listview);
        checkAll = (Button) findViewById(R.id.case_delete_bottom_btn1);
        delete = (Button) findViewById(R.id.case_delete_bottom_btn2);

        mImageView = (ImageView) findViewById(R.id.case_delete_recback);

        //checkAll按钮点击事件（全选）
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flagAll) {
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setCheckFlag(true);
                        checkAll.setText("取消全选");
                        flagAll = true;
                    }
                } else {
                    for (int i = 0; i < mList.size(); i++) {
                        mList.get(i).setCheckFlag(false);
                        checkAll.setText("全选");
                        flagAll = false;
                    }
                }
                mCaseDeleteAdapter = new CaseDeleteAdapter(BookCaseDeleteActivity.this, mList);
                mListView.setAdapter(mCaseDeleteAdapter);

            }
        });
        //delete按钮点击事件（删除）
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mList.size(); i++) {

                    if (mList.get(i).isCheckFlag()) {
                        Toast.makeText(BookCaseDeleteActivity.this, "" + i, Toast.LENGTH_SHORT).show();
                        //dels.add(i);
                        RequestParams params = new RequestParams(mpath);
                        params.addQueryStringParameter("fla", "delete");
                        params.addQueryStringParameter("name", mList.get(i).getXsName());
                        x.http().get(params, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Log.e("onSuccess", "成功");
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                Log.e("onError", "失败");
                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });
                        mList.remove(i);
                        i--;
                    }
                }
                mCaseDeleteAdapter = new CaseDeleteAdapter(BookCaseDeleteActivity.this, mList);
                mCaseDeleteAdapter.notifyDataSetChanged();
                mListView.setAdapter(mCaseDeleteAdapter);

            }
        });
        //返回
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        mCaseDeleteAdapter = new CaseDeleteAdapter(BookCaseDeleteActivity.this, mList);
        mListView.setAdapter(mCaseDeleteAdapter);
    }


}
