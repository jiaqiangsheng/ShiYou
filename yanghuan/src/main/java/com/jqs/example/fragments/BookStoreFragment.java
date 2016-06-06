package com.jqs.example.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.jqs.example.activity.BookDetailsActivity;
import com.jqs.example.adapter.MyStoreAdapter;
import com.jqs.example.beans.Book;
import com.jqs.example.beans.BookNovel;
import com.jqs.example.beans.MyStoreItemBean;
import com.jqs.servert.utils.MyApplication;
import com.jqs.view.XListView;
import com.yanghuan.R;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by qiangsheng on 2016/5/16.
 */
public class BookStoreFragment extends Fragment implements XListView.IXListViewListener {
    public static final int REFRESH = 1;
    //ListView mListView;
    private XListView mListView;

    View mView;
    MyStoreAdapter mStoreAdapter;
    Context mContext;
    List<MyStoreItemBean> mList;

    //服务器路径
    String mpath;
    //标志第一行数据id
    int id;

    int n = 0, m = 4;

    List<Book> listBook=new ArrayList<>();
    List<BookNovel> listNovel, listNovels;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //处理消息的方法
            //区分当前消息
            int what = msg.what;
            switch (what) {
                case REFRESH:
                    List<MyStoreItemBean> listMess = (List<MyStoreItemBean>) msg.obj;
                    mStoreAdapter = new MyStoreAdapter(mContext, listMess);
                    mStoreAdapter.notifyDataSetChanged();
                    mListView.setAdapter(mStoreAdapter);
                    break;
            }
        }
    };


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.book_store, null);
        initData();
        initView();

        return mView;
    }

    private void initView() {
        mListView = (XListView) mView.findViewById(R.id.listview_store);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        mStoreAdapter = new MyStoreAdapter(mContext, mList);
        mListView.setAdapter(mStoreAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != mList.size() + 1) {
                    MyStoreItemBean myStoreItemBean = mList.get(position - 1);
                    Intent intent = new Intent(mContext, BookDetailsActivity.class);
                    intent.putExtra("storeBean", myStoreItemBean);
                    startActivity(intent);
                }
            }
        });
    }

    //刷新结束
    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    private void initData() {
        mList = new ArrayList<>();
        MyApplication myApplication = (MyApplication) mContext.getApplicationContext();
        mpath = myApplication.getUrl();
        //最后两个参数：第一个是item布局类型参数（共三种布局类型），第二个是判断item是否可以被点击（0不可点，1可点击）
        MyStoreItemBean m = new MyStoreItemBean(0,"琅琊榜1", "宫廷智斗大戏", "不知道", "50000", "http://o6nj6n5ea.bkt.clouddn.com/langyabang.jpg", 3, 0);
        MyStoreItemBean m0 = new MyStoreItemBean(0,"琅琊榜2", "宫廷智斗大戏", "不知道", "50000", "http://o6nj6n5ea.bkt.clouddn.com/langyabang.jpg", 2, 0);
        MyStoreItemBean m1 = new MyStoreItemBean(0,"琅琊榜2", "宫廷智斗大戏", "不知道", "50000", "http://o6nj6n5ea.bkt.clouddn.com/langyabang.jpg", 0, 0);

        mList.add(m);
        mList.add(m0);
        mList.add(m1);
        //查询小说数据库
        selectBook();

    }
    private void selectBook() {
        Log.e("texting",""+"我到了这个方法");
        RequestParams params = new RequestParams(mpath);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("texting","我到了onSuccess方法 : " + result);
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Book>>() {
                }.getType();
                listBook = gson.fromJson(result, type);
                listNovel = listBook.get(0).getListnoNovels();
                Log.e("texting","listNovel:"+listNovel.toString()+"");
                for (int i = 0; i < listNovel.size(); i++) {
                    MyStoreItemBean s = new MyStoreItemBean(listNovel.get(i).getXsid(),listNovel.get(i).getXsname(), listNovel.get(i).getXsintroduce(),
                            listNovel.get(i).getXsauthor(), listNovel.get(i).getDhnumber(),
                            listNovel.get(i).getXspicture(), 1, 1);

                    mList.add(s);
                }
                Log.e("texting", mList.toString());
                //  mStoreAdapter = new MyStoreAdapter(getActivity(), mList);
                mStoreAdapter.notifyDataSetChanged();
                //  mListView.setAdapter(mStoreAdapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                Log.e("texting","我失败了");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });

    }
    private void selectBook(int i, int j) {
        RequestParams params = new RequestParams(mpath);
        //第二步：开始请求，设置请求方式，同时实现回调函数
        params.addQueryStringParameter("fla1", "store");
        params.addQueryStringParameter("i",i+"");
        params.addQueryStringParameter("j",j+"");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Book>>() {
                }.getType();
                listBook = gson.fromJson(result, type);
                listNovel = listBook.get(0).getListnoNovels();
                Log.e("text",""+listNovel.toString()+"");
                for (int i = 0; i < listNovel.size(); i++) {
                    MyStoreItemBean s = new MyStoreItemBean(listNovel.get(i).getXsid(),listNovel.get(i).getXsname(), listNovel.get(i).getXsintroduce(),
                            listNovel.get(i).getXsauthor(), listNovel.get(i).getDhnumber(),
                            listNovel.get(i).getXspicture(), 1, 1);

                    mList.add(s);
                }
                Log.e("sheng", mList.toString());
                /*//创建一个消息对象
                Message message = new Message();
                //添加识别当前消息标志位
                message.what = REFRESH;
                //携带数据，可以是任意的object
                message.obj = mList;
                //通过handler发送信息，通知主线程调用handleMessage方法来处理
                mHandler.sendMessage(message);*/
                mStoreAdapter = new MyStoreAdapter(getActivity(), mList);
                mStoreAdapter.notifyDataSetChanged();
                mListView.setAdapter(mStoreAdapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
//                Log.e("store","BookStore我失败了");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });

    }

    private void selectBookTop() {
        Log.e("onRec", "id==" + id);
        id=mList.get(3).getXsid();
        if (id == 0) {
            selectBook(0, 4);
        } else {
            RequestParams params = new RequestParams(mpath);
            params.addQueryStringParameter("fla", "store_onRefresh");
            params.addQueryStringParameter("store_id", id + "");
            //第二步：开始请求，设置请求方式，同时实现回调函数
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Book>>() {
                    }.getType();
                    List<Book> listBook = gson.fromJson(result, type);
                    listNovels = listBook.get(3).getListnoNovels();
                    Toast.makeText(mContext, "" + listNovels.size(), Toast.LENGTH_SHORT).show();
                    int n = 3;
                    if (listNovels.size() != 0) {
                        for (int i = 0; i < listNovels.size(); i++) {
                            MyStoreItemBean s = new MyStoreItemBean(listNovels.get(i).getXsid(),listNovels.get(i).getXsname(), listNovels.get(i).getXsintroduce(),
                                    listNovels.get(i).getXsauthor(), listNovels.get(i).getDhnumber(),
                                    listNovels.get(i).getXspicture(), 1, 1);
                            mList.add(n++, s);
                        }
                        id = listNovels.get(0).getXsid();
                    }
                    Log.e("sheng222", mList.toString());
                    mStoreAdapter.notifyDataSetChanged();

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    ex.printStackTrace();
//                Log.e("store","BookStore我失败了");
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

    //下拉刷新
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                selectBookTop();
                onLoad();
            }
        }, 2000);
    }

    //上拉加载
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //selectBook();
                n = n + 4;
                m = m + 4;
                Toast.makeText(mContext, "上拉加载"+n+":"+m, Toast.LENGTH_LONG).show();
                selectBook(n, m);
                mStoreAdapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }


}
