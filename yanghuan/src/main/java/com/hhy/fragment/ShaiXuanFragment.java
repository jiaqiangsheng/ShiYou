package com.hhy.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.hhy.adapter.ShaiXuan_ShiYouAdapter;
import com.yanghuan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanhongyang on 2016/5/17.
 */
public class ShaiXuanFragment extends Fragment implements View.OnClickListener{
    List<Integer> mList;
    ShaiXuan_ShiYouAdapter mAdapter;
    ListView mListView;
    Button mButton;

    //切换fragment所需
    TextView mCancleTextView1, mWanChengTextView1;
    FragmentManager mFragmentManager;
    //ShaiXuanFragment mShaiXuanFragment;
    NearbyShouFragment mShouFragment;
    FragmentTransaction mFragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hhy_nearby_shaixuan, null);
        initView(view);
        initData();
        setAdapter();
        //initListener();
        return view;
    }

    /*private void initListener() {
        String mCancleText = mCancleTextView1.getText().toString();
       // String mWanChengText =  mWanChengTextView1.getText().toString();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        if ("取消".equals(mCancleText)) {
            mShouFragment = new NearbyShouFragment();
            mFragmentTransaction.setCustomAnimations(0,R.anim.enter_shoutranstate);
            mFragmentTransaction.replace(R.id.middle, mShouFragment);
            mFragmentTransaction.addToBackStack(null);
        } else {
           //完成，根据筛选条件查询数据库并将得到的数据一起带到mShouFragment中
        }
        //提交事务
        mFragmentTransaction.commit();
    }*/

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.shaixuan_list);
        mCancleTextView1 = (TextView) view.findViewById(R.id.cancle_shaixuan);
        mWanChengTextView1 = (TextView) view.findViewById(R.id.wancheng_shaixuan);
        mFragmentManager = getFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        //添加单击事件
        mCancleTextView1.setOnClickListener(this);
        mWanChengTextView1.setOnClickListener(this);

    }

    private void setAdapter() {
        mAdapter = new ShaiXuan_ShiYouAdapter(mList, getActivity());
        mListView.setAdapter(mAdapter);
    }

    private void initData() {
        mList = new ArrayList<Integer>();
        mList.add(1);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.cancle_shaixuan:
                mShouFragment = new NearbyShouFragment();
                mFragmentTransaction.setCustomAnimations(0,R.anim.enter_shoutranstate);
                mFragmentTransaction.replace(R.id.middle, mShouFragment);
                mFragmentTransaction.addToBackStack(null);
                //提交事务
                mFragmentTransaction.commit();
                break;
            case R.id.wancheng_shaixuan:
                //完成，根据筛选条件查询数据库并将得到的数据一起带到mShouFragment中

                break;
            default:
                break;
        }
    }
}
