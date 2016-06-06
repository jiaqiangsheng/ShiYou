package com.hhy.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.hhy.adapter.ShiYouAdapter;
import com.hhy.bean.ShiYouList;
import com.yanghuan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanhongyang on 2016/5/17.
 */
public class NearbyShouFragment extends Fragment implements View.OnClickListener{
    List<ShiYouList> mList;
    ShiYouAdapter mAdapter;
    ListView mListView;

    //切换fragment所需
    TextView mTextView;
    FragmentManager mFragmentManager;
    ShaiXuanFragment mShaiXuanFragment;
    NearbyShouFragment mShouFragment;
    FragmentTransaction mFragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hhy_nearby_content, null);

        initView(view);
        initData();
       // initListener();
        return view;
    }

   /* private void initListener() {
        String text = mTextView.getText().toString();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        if ("筛选".equals(text)) {
            // if (mShaiXuanFragment == null) {
            mShaiXuanFragment = new ShaiXuanFragment();
            mFragmentTransaction.setCustomAnimations(R.anim.shaixuan_translate, 0);
            mFragmentTransaction.replace(R.id.middle, mShaiXuanFragment);
           // mFragmentTransaction.addToBackStack(null);
        } else {
            mShouFragment = new NearbyShouFragment();
            mFragmentTransaction.replace(R.id.middle, mShouFragment);
            //设置回退
            //mFragmentTransaction.addToBackStack(null);
        }
        //提交事务
        mFragmentTransaction.commit();
    }*/


    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.nearby_listview);
        mTextView = (TextView) view.findViewById(R.id.top_textview);
        mFragmentManager = getFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        //为mTextView（筛选）添加单击事件
        mTextView.setOnClickListener(this);
    }

    private void initData() {
        mList = new ArrayList<ShiYouList>();
        ShiYouList mShiYou1 = new ShiYouList(R.drawable.icon_xiang, "陌上朝歌", "好的命运是要自己去争取的", "0.23km");
        ShiYouList mShiYou2 = new ShiYouList(R.drawable.icon_xiang, "陌上朝歌", "好的命运是要自己去争取的", "0.23km");
        ShiYouList mShiYou3 = new ShiYouList(R.drawable.icon_xiang, "陌上朝歌", "好的命运是要自己去争取的", "0.23km");
        ShiYouList mShiYou4 = new ShiYouList(R.drawable.icon_xiang, "陌上朝歌", "好不容易积累的100多首歌，手一残就没了", "0.23km");
        ShiYouList mShiYou5 = new ShiYouList(R.drawable.icon_xiang, "陌上朝歌", "好的命运是要自己去争取的", "0.23km");
        ShiYouList mShiYou6 = new ShiYouList(R.drawable.icon_xiang, "陌上朝歌", "好不容易积累的100多首歌，手一残就没了", "0.23km");
        mList.add(mShiYou1);
        mList.add(mShiYou2);
        mList.add(mShiYou3);
        mList.add(mShiYou4);
        mList.add(mShiYou5);
        mList.add(mShiYou6);

        mAdapter = new ShiYouAdapter(mList, getActivity());
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.top_textview:
                mShaiXuanFragment = new ShaiXuanFragment();
                mFragmentTransaction.setCustomAnimations(R.anim.shaixuan_translate, 0);
                mFragmentTransaction.replace(R.id.middle, mShaiXuanFragment);
                // mFragmentTransaction.addToBackStack(null);
                //提交事务
                mFragmentTransaction.commit();
                break;
            default:
                break;
        }
    }
}
