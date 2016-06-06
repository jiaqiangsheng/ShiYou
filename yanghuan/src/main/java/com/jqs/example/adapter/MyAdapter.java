package com.jqs.example.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;


/**
 * Created by qiangsheng on 2016/5/16.
 */
public class MyAdapter extends FragmentPagerAdapter{
    List<Fragment> mList;

    public MyAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        mList=list;
    }


    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
