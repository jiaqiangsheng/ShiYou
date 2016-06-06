package com.jqs.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yanghuan.R;
import com.show.api.ShowApiRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReadBookActivity extends AppCompatActivity {
    protected Handler mHandler = new Handler();
    ListView mListView;
    String content;
    List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        mListView= (ListView) findViewById(R.id.readbook_listview);
        Intent intent=getIntent();
        int postion=intent.getIntExtra("list",0);
        final String bookId=intent.getStringExtra("bookId");
        final String CId=intent.getStringExtra("CId");

        Log.e("readbook",bookId+":::"+CId);

        new Thread() {
            //在新线程中发送网络请求
            public void run() {
                String appid = "19732";//要替换成自己的
                String secret = "0df6d056bd6e4c0fa2119349f434a3cd";//要替换成自己的
                final String res = new ShowApiRequest("http://route.showapi.com/211-4", appid, secret)
                        .addTextPara("bookId", bookId)
                        .addTextPara("cid", CId)
                        .post();
                System.out.println(res);
                //把返回内容通过handler对象更新到界面
                mHandler.post(new Thread() {
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(res).getJSONObject("showapi_res_body");
                            content=jsonObject.getString("txt");
                            //------------------------------

                            int len = content.length();
                            StringBuffer stb = new StringBuffer();
                            for(int i=0; i<len-4; i++) {
                                char c = content.charAt(i);
                                char c1 = content.charAt(i+1);
                                char c2 = content.charAt(i+2);
                                char c3 = content.charAt(i+3);
                                char c4 = content.charAt(i+4);
                                stb.append(c);
                                if(c == '<'&& c1=='b'&& c2=='r' && c3=='/' && c4=='>')
                                    stb.append("\n");

                            }

                            Log.e("readbook",stb.toString());
                            //--------------------------
                            list.add(stb.toString());
                            ArrayAdapter<String> mAdapter=new  ArrayAdapter<String>(ReadBookActivity.this,android.R.layout.simple_list_item_1,list);
                            mListView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        }.start();

    }


}
