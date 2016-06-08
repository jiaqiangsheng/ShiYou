package com.jqs.example.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jqs.example.adapter.MyDetailsAdapter;
import com.jqs.example.beans.Book;
import com.jqs.example.beans.BookBeans;
import com.jqs.example.beans.MyStoreItemBean;
import com.jqs.lookbook.StartActivity;
import com.jqs.servert.utils.MyApplication;
import com.yanghuan.R;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookDetailsActivity extends AppCompatActivity {
    ListView mListView;
    List<MyStoreItemBean> mList;
    MyDetailsAdapter mDetailsAdapter;
    TextView mTextView;
    MyStoreItemBean myStoreItemBean;
    ImageView mImageView;
    Button putBook,buyBook,lookBook;

    String mpath;
    List<BookBeans> list;
    List<Book> listBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        MyApplication myApplication = (MyApplication) getApplication();
        mpath = myApplication.getUrl();
        //查询数据库中是否已有这本书
        RequestParams params = new RequestParams(mpath);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //Log.e("onSuccess","成功");
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Book>>() {
                }.getType();
                listBook= gson.fromJson(result, type);
                list =listBook.get(0).getListbooBeans();
                if(list.size()!=0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (myStoreItemBean.getXsName().equals(list.get(i).getName())) {
                            //设置按钮不可点击
                            putBook.setClickable(false);
                            putBook.setText("已添加");
                        } else {
                            putBook.setClickable(true);
                            putBook.setText("添加书架");
                        }
                    }
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //Log.e("onError","失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

        //Log.e("url",mpath);
        initData();
        initView();

    }

    private void initData() {
        mList=new ArrayList<>();
        Intent intent=getIntent();
        myStoreItemBean= (MyStoreItemBean) intent.getSerializableExtra("storeBean");
        mList.add(myStoreItemBean);


    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.details_listview);
        mTextView= (TextView) findViewById(R.id.details_textview);
        mImageView= (ImageView) findViewById(R.id.details_recback);
        putBook= (Button) findViewById(R.id.details_bottom_put);
        buyBook= (Button) findViewById(R.id.details_bottom_buy);
        lookBook= (Button) findViewById(R.id.details_bottom_read);
        //返回
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mTextView.setText(myStoreItemBean.getXsName());
        mDetailsAdapter=new MyDetailsAdapter(BookDetailsActivity.this,mList);
        mListView.setAdapter(mDetailsAdapter);
    }
    //添加到书架事件
    public void DetailPut(View view) {
        Toast.makeText(BookDetailsActivity.this,"添加书架成功",Toast.LENGTH_SHORT).show();

        putBook.setClickable(false);
        putBook.setText("已添加");


        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = 12-24-c.get(Calendar.MINUTE);
        //添加书架数据库id
        //第一步：设置烦你敢问路径及携带数据
        RequestParams params = new RequestParams(mpath);
        params.addQueryStringParameter("fla","true");
        params.addQueryStringParameter("name",myStoreItemBean.getXsName());
        params.addQueryStringParameter("hLocation","还没开始阅读");
        params.addQueryStringParameter("hData",year+"年"+month+"月"+day+"日"+"  "+hour+":"+minute);
        params.addQueryStringParameter("xsPicture",myStoreItemBean.getXsPicture());

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //Log.e("onError","失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
    //购买书籍
    public void DetailBuy(View view) {
        Toast.makeText(BookDetailsActivity.this,"进入购买页",Toast.LENGTH_SHORT).show();
        //跳转购买页

    }
    //免费阅读
    public void DetailRead(View view) {
        Toast.makeText(BookDetailsActivity.this,"免费阅读开始",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(BookDetailsActivity.this, StartActivity.class);
        intent.putExtra("bookname",myStoreItemBean);
        startActivity(intent);
    }

}
