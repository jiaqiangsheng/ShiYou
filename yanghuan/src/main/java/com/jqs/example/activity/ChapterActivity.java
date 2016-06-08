package com.jqs.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jqs.example.beans.NovelBeans;
import com.jqs.example.beans.NovelsChapter;
import com.show.api.ShowApiRequest;
import com.yanghuan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChapterActivity extends AppCompatActivity {
    protected Handler mHandler = new Handler();
    List<NovelsChapter> mChapters = new ArrayList<>();
    List<NovelBeans> mBeanses;
    ListView mListView;
    TextView mTextView;
    ImageView mImageView;
    NovelsChapter mChapter;
    NovelBeans mNovelBeans;

    //书名
    String bookName;
    //书名id数组
    List<String> listid;
    //章节数组
    List<String> list;
    boolean isflg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        mListView = (ListView) findViewById(R.id.chapter_liatview);
        mTextView = (TextView) findViewById(R.id.chapter_textview);
        mImageView= (ImageView) findViewById(R.id.chapter_recback);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list = new ArrayList<>();
        listid = new ArrayList<>();
        Intent intent = getIntent();
        bookName = intent.getStringExtra("name");
        mTextView.setText(bookName);

        new Thread() {
            //在新线程中发送网络请求
            public void run() {
                String appid = "19732";//要替换成自己的
                String secret = "0df6d056bd6e4c0fa2119349f434a3cd";//要替换成自己的
                String names = bookName + " 下载";
                Log.e("bookname", names);
                final String res2 = new ShowApiRequest("http://route.showapi.com/211-2", appid, secret)
                        .addTextPara("keyword", names)
                        .post();
                //把返回内容通过handler对象更新到界面
                mHandler.post(new Thread() {
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(res2).getJSONObject("showapi_res_body")
                                    .getJSONObject("pagebean");
                            JSONArray jsonArray = jsonObject.getJSONArray("contentlist");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject temp = new JSONObject(jsonArray.getString(i));
                                mNovelBeans = new NovelBeans(temp.getString("id"), temp.getString("author"),
                                        temp.getString("name"), temp.getString("newChapter"), temp.getString("updateTime"));
                                listid.add(mNovelBeans.getId());

                            }
                            if (listid.size() != 0) {
                                selectChapter(listid.get(0));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        }.start();

        initListener();

    }

    private void initListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bookId = mChapters.get(position).getBookId();
                String CId = mChapters.get(position).getCid();
                Intent intent = new Intent(ChapterActivity.this, ReadBookActivity.class);
                intent.putExtra("list", position);
                intent.putExtra("bookId", bookId);
                intent.putExtra("CId", CId);
                startActivity(intent);
            }
        });
    }

    public void selectChapter(final String id) {
        new Thread() {
            //在新线程中发送网络请求
            public void run() {
                String appid = "19732";//要替换成自己的
                String secret = "0df6d056bd6e4c0fa2119349f434a3cd";//要替换成自己的
                final String res = new ShowApiRequest("http://route.showapi.com/211-1", appid, secret)
                        .addTextPara("bookId", id)
                        .post();
                System.out.println(res);
                //把返回内容通过handler对象更新到界面
                mHandler.post(new Thread() {
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(res).getJSONObject("showapi_res_body")
                                    .getJSONObject("book");
                            JSONArray jsonArray = jsonObject.getJSONArray("chapterList");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject temp = new JSONObject(jsonArray.getString(i));
                                mChapter = new NovelsChapter(temp.getString("bookId"), temp.getString("cid"),
                                        temp.getString("name"));
                                mChapters.add(mChapter);
                                list.add(mChapter.getCname());
                                ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(ChapterActivity.this, android.R.layout.simple_list_item_1, list);
                                mListView.setAdapter(mAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        }.start();

    }
}
