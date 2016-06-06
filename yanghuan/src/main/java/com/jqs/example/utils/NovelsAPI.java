package com.jqs.example.utils;

import android.os.Handler;

import com.jqs.example.beans.NovelBeans;
import com.jqs.example.beans.NovelsChapter;
import com.show.api.ShowApiRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiangsheng on 2016/5/31.
 */
public class NovelsAPI {
    protected Handler mHandler = new Handler();
    NovelsChapter bookChapter;
    NovelBeans mNovelBeans;

    public List<NovelBeans> selectId(final String name) {
        final List<NovelBeans> mList=new ArrayList<>();
        new Thread() {
            //在新线程中发送网络请求
            public void run() {
                String appid = "19732";//要替换成自己的
                String secret = "0df6d056bd6e4c0fa2119349f434a3cd";//要替换成自己的
                String nameT = name +" 下载";
                final String res = new ShowApiRequest("http://route.showapi.com/211-2", appid, secret)
                        .addTextPara("keyword", nameT)
                        .post();
                System.out.println(res);
                //把返回内容通过handler对象更新到界面
                mHandler.post(new Thread() {
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(res).getJSONObject("showapi_res_body")
                                    .getJSONObject("pagebean");
                            JSONArray jsonArray = jsonObject.getJSONArray("contentlist");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject temp = new JSONObject(jsonArray.getString(i));
                                mNovelBeans = new NovelBeans(temp.getString("id"), temp.getString("author"),
                                        temp.getString("name"),temp.getString("newChapter"),temp.getString("updateTime"));
                                mList.add(mNovelBeans);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }.start();
        return  mList;
    }

    public List<NovelsChapter> selectChapter(final String bookId) {
        final List<NovelsChapter> stringList = new ArrayList<>();
        new Thread() {
            //在新线程中发送网络请求
            public void run() {
                String appid = "19732";//要替换成自己的
                String secret = "0df6d056bd6e4c0fa2119349f434a3cd";//要替换成自己的
                final String res = new ShowApiRequest("http://route.showapi.com/211-1", appid, secret)
                        .addTextPara("bookId", bookId)
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
                                bookChapter = new NovelsChapter(temp.getString("bookId"), temp.getString("cid"),
                                        temp.getString("name"));
                                stringList.add(bookChapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
            }
        }.start();
        return stringList;
    }


}
