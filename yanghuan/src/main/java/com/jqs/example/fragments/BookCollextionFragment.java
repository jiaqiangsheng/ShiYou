package com.jqs.example.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.jqs.example.adapter.MyCollectionAdapter;
import com.jqs.example.beans.MyCollectionBean;
import com.jqs.view.XListView;
import com.yanghuan.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiangsheng on 2016/5/16.
 */
public class BookCollextionFragment extends Fragment implements XListView.IXListViewListener {
    View mView;
    Context mContext;
    MyCollectionAdapter mCollectionAdapter;

    List<MyCollectionBean> mList;

    XListView mListView;
    ImageView mImageView;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext=activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.book_collection, null);
        initData();
        initView();

        return mView;
    }

    private void initData() {
        mList = new ArrayList<>();
        MyCollectionBean m1 = new MyCollectionBean("一个小孩", "这是一个很感人的故事,在那个隔壁有一个神秘的老王", "...", new String[]{"http://o6nj6n5ea.bkt.clouddn.com/jia/i.jpg"}, "2016-5-24 18:36");
        MyCollectionBean m2 = new MyCollectionBean("二个小孩", "这是一个很感人的故事,", "...", new String[]{"http://o6nj6n5ea.bkt.clouddn.com/jia/k.jpg"}, "2016-5-24 17:36");
        MyCollectionBean m3 = new MyCollectionBean("三个小孩", "这是一个很感人的故事,", "...", new String[]{"http://o6nj6n5ea.bkt.clouddn.com/jia/i.jpg"}, "2016-5-24 16:36");
        MyCollectionBean m4 = new MyCollectionBean("四个小孩", "这是一个很感人的故事,在那个隔壁有一个神秘的老王", "...", new String[]{"http://o6nj6n5ea.bkt.clouddn.com/jia/k.jpg"}, "2016-5-24 16:00");
        MyCollectionBean m5 = new MyCollectionBean("五个小孩", "这是一个很感人的故事,", "...", new String[]{"http://o6nj6n5ea.bkt.clouddn.com/jia/i.jpg"}, "2016-5-24 15:30");
        MyCollectionBean m6 = new MyCollectionBean("六个小孩", "这是一个很感人的故事,在那个隔壁有一个神秘的老王", "...", new String[]{"http://o6nj6n5ea.bkt.clouddn.com/jia/k.jpg"}, "2016-5-24 15:00");
        MyCollectionBean m7 = new MyCollectionBean("七个小孩", "这是一个很感人的故事,", "...", new String[]{"http://o6nj6n5ea.bkt.clouddn.com/jia/i.jpg"}, "2016-5-24 14:36");
        MyCollectionBean m8 = new MyCollectionBean("八个小孩", "这是一个很感人的故事,", "...", new String[]{"http://o6nj6n5ea.bkt.clouddn.com/jia/k.jpg"}, "2016-5-24 13:36");
        MyCollectionBean m9 = new MyCollectionBean("一个老王", "这是一个很感人的故事,", "...", new String[]{"http://o6nj6n5ea.bkt.clouddn.com/jia/i.jpg"}, "2016-5-24 12:36");

        mList.add(m1);
        mList.add(m2);
        mList.add(m3);
        mList.add(m4);
        mList.add(m5);
        mList.add(m6);
        mList.add(m7);
        mList.add(m8);
        mList.add(m9);
    }

    private void initView() {
        mListView = (XListView) mView.findViewById(R.id.book_collection_listview);
        mImageView= (ImageView) mView.findViewById(R.id.book_collection_imageview);

        //XListView滚动事件
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState){
                    //滚动停止时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        //判断ListView是否在顶部
                        if (mListView.getFirstVisiblePosition() == 0) {
                            mImageView.setVisibility(View.GONE);
                        } else {
                            mImageView.setVisibility(View.VISIBLE);
                        }
                        break;
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                mImageView.setVisibility(View.GONE);
            }
        });

        //返回顶部按钮
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //不带滚动动画的返回顶部的两种方式
                //mListView.setSelection(0);
                //mListView.setSelectionAfterHeaderView();
                //带滚动动画的返回顶部
                mListView.smoothScrollToPosition(0);
            }
        });

        mCollectionAdapter=new MyCollectionAdapter(mContext,mList);
        mListView.setAdapter(mCollectionAdapter);
    }
    //下拉刷新
    @Override
    public void onRefresh() {


    }
    //上拉加载
    @Override
    public void onLoadMore() {


    }
}
