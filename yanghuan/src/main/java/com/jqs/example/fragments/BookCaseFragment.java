package com.jqs.example.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.google.gson.reflect.TypeToken;
import com.jqs.example.adapter.MyCaseAdapter;
import com.jqs.example.beans.Book;
import com.jqs.example.beans.BookBeans;
import com.jqs.example.beans.MyCaseItemBean;
import com.jqs.servert.utils.MyApplication;
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
public class BookCaseFragment extends Fragment {

    View mView;
    Context mContext;

    Button mButton;
    ListView mListView;


    LinearLayout mLinearLayout;
    List<Book> listBook;
    List<BookBeans> list;
    List<MyCaseItemBean> mList;
    MyCaseAdapter mCaseAdapter;
    String mpath;

    //checkbox显示标志
    boolean flag = false;
    boolean name;
    //是否已经被全选
    boolean flagAll = false;


    //简单的观察者，回调函数
    public interface frashInterface {
        public abstract List<MyCaseItemBean> instantFrash();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.book_case, null);
        mLinearLayout = (LinearLayout) mView.findViewById(R.id.case_case_full_lout);
        initView();
        initData();
        Log.e("wd","oncreat也来了"+mList.size());
        mCaseAdapter = new MyCaseAdapter(mContext, mList);

        RequestParams params = new RequestParams(mpath);
        //第二步：开始请求，设置请求方式，同时实现回调函数
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Book>>() {
                }.getType();
                listBook = gson.fromJson(result, type);
                list=listBook.get(0).getListbooBeans();
                mList.clear();
                for (int i = 0; i < list.size(); i++) {
                    MyCaseItemBean m = new MyCaseItemBean(list.get(i).getName(), list.get(i).gethLocation(),
                            list.get(i).gethData(), list.get(i).getUpicture());
                    mList.add(m);
                }

                if (mList.size() == 0) {
                    mLinearLayout.setVisibility(View.GONE);
                } else {
                    mLinearLayout.setVisibility(View.VISIBLE);

                    mCaseAdapter.notifyDataSetChanged();
                    mListView.setAdapter(mCaseAdapter);
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });

        if (mList.size() == 0) {
            mLinearLayout.setVisibility(View.GONE);
        } else {
            mLinearLayout.setVisibility(View.VISIBLE);
            //initView();
            mCaseAdapter.notifyDataSetChanged();
            mListView.setAdapter(mCaseAdapter);
        }

        return mView;
    }

    //方案：把空视图和满视图放在同一个布局中，通过可见性调整
    private void initData() {
        mList = new ArrayList<>();

        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        mpath = myApplication.getUrl();
    }

    private void initView() {
        mListView = (ListView) mView.findViewById(R.id.case_full_listview);
        mButton = (Button) mView.findViewById(R.id.case_button);
        //点击进入书城button
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup mRadioGroup= (RadioGroup) getActivity().findViewById(R.id.group);
                ((RadioButton)mRadioGroup.getChildAt(1)).setChecked(true);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("wolei","我来了"+mList.size());
        mList.clear();
        RequestParams params = new RequestParams(mpath);
        //第二步：开始请求，设置请求方式，同时实现回调函数
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Book>>() {
                }.getType();

                listBook = gson.fromJson(result, type);
                list=listBook.get(0).getListbooBeans();
                for (int i = 0; i < list.size(); i++) {
                    MyCaseItemBean m = new MyCaseItemBean(list.get(i).getName(), list.get(i).gethLocation(),
                            list.get(i).gethData(), list.get(i).getUpicture());
                    mList.add(m);
                }

                if (mList.size() == 0) {
                    mLinearLayout.setVisibility(View.GONE);
                } else {
                    mLinearLayout.setVisibility(View.VISIBLE);

                    mCaseAdapter.notifyDataSetChanged();
                    mListView.setAdapter(mCaseAdapter);
                }


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

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
