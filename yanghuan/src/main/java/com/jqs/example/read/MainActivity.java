package com.jqs.example.read;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;


import com.jqs.example.adapter.MyAdapter;
import com.jqs.example.fragments.BookCaseFragment;
import com.jqs.example.fragments.BookCollextionFragment;
import com.jqs.example.fragments.BookMeFragment;
import com.jqs.example.fragments.BookStoreFragment;
import com.jqs.example.fragments.BookTopCaseFragment;
import com.jqs.example.fragments.BookTopCollectionFragment;
import com.jqs.example.fragments.BookTopMeFragment;
import com.jqs.example.fragments.BookTopStoreFragment;
import com.yanghuan.R;
import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity {
    List<Fragment> mList;
    MyAdapter mAdapter;
    //控件
    ViewPager mViewPager;
    RadioGroup mRadioGroup;
    //fragment
    BookCaseFragment mBookCaseFragment;
    BookCollextionFragment mBookCollextionFragment;
    BookMeFragment mBookMeFragment;
    BookStoreFragment mBookStoreFragment;

    BookTopStoreFragment mBookTopStoreFragment;
    BookTopCaseFragment mBookTopCaseFragment;
    BookTopCollectionFragment mBookTopCollectionFragment;
    BookTopMeFragment mBookTopMeFragment;

    FragmentManager mFragmentManager;
    FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_book);
        initView();
        initData();
        initListen();
    }

    //初始化控件
    private void initView() {
        mViewPager= (ViewPager) findViewById(R.id.readbook_center);
        mRadioGroup= (RadioGroup) findViewById(R.id.group);
    }

    //初始化fragment
    private void initData() {
        mList=new ArrayList<>();

        mBookCaseFragment =new BookCaseFragment();
        mBookTopStoreFragment =new BookTopStoreFragment();
        mBookCollextionFragment=new BookCollextionFragment();
        mBookMeFragment=new BookMeFragment();
        mBookStoreFragment=new BookStoreFragment();

        mList.add(mBookCaseFragment);
        mList.add(mBookStoreFragment);
        mList.add(mBookCollextionFragment);
        mList.add(mBookMeFragment);

        //初始化适配器
        mFragmentManager=getSupportFragmentManager();
        mAdapter=new MyAdapter(mFragmentManager,mList);
        mViewPager.setAdapter(mAdapter);
        //默认显示第二个pager
        mViewPager.setCurrentItem(1);

        mTransaction=mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.readbook_top, mBookTopStoreFragment);
        mTransaction.commit();
    }
    //监听事件
    private void initListen() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                resetViewPager(checkedId);
            }
        });

    }
    //ViewPager监听事件
    private void resetViewPager(int checkId){
        switch (checkId){
            case R.id.r1:
                mViewPager.setCurrentItem(0,false);
               // mFragmentManager=getSupportFragmentManager();
                mTransaction=mFragmentManager.beginTransaction();
                mBookTopCaseFragment=new BookTopCaseFragment();
                mTransaction.replace(R.id.readbook_top, mBookTopCaseFragment);
                mTransaction.commit();
                break;
            case R.id.r2:
                mViewPager.setCurrentItem(1,false);
              //  mFragmentManager=getSupportFragmentManager();
                mTransaction=mFragmentManager.beginTransaction();
                mTransaction.replace(R.id.readbook_top, mBookTopStoreFragment);
                mTransaction.commit();
                break;
            case R.id.r3:
                mViewPager.setCurrentItem(2,false);
                mTransaction=mFragmentManager.beginTransaction();
                mBookTopCollectionFragment=new BookTopCollectionFragment();
                mTransaction.replace(R.id.readbook_top, mBookTopCollectionFragment);
                mTransaction.commit();
                break;
            case R.id.r4:
                mViewPager.setCurrentItem(3,false);
                mTransaction=mFragmentManager.beginTransaction();
                mBookTopMeFragment = new BookTopMeFragment();
                mTransaction.replace(R.id.readbook_top, mBookTopMeFragment);
                mTransaction.commit();
                break;
        }
    }

}
