package com.hhy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hhy.activity.PinBiAddActivity;
import com.hhy.adapter.Tianjia_PingBi_AccountAdapter;
import com.hhy.bean.UserInfo;
import com.yanghuan.BuildConfig;
import com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class PingBiTaFragment extends Fragment {
    RelativeLayout mRelativeLayout;
    Intent mIntent;
    FragmentManager mFragmentManager;
    FragmentTransaction mTransaction;
    PingBiAddFragment mAddFragment;
    List<UserInfo> mUserInfoList;
    String url;
    List<UserInfo> list;
    UserInfo mUserInfo;
    UserInfo userInfo;
    boolean b= false;
    Tianjia_PingBi_AccountAdapter mAdapter;
    ListView mListView;
    ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hhy_fragment_ping_bi_ta,null);
        //初始化xUtils
        initXUtils();
        initView(view);
        initData();
        return view;
    }

    private void initXUtils() {
        x.Ext.init(getActivity().getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

    private void initData() {
        url = "http://10.201.1.148:8888/HttpServer/HttpServer";
        mUserInfoList = new ArrayList<UserInfo>();
        selectDataBase();

    }

    private void setAdapter() {
        mAdapter = new Tianjia_PingBi_AccountAdapter(mUserInfoList,getActivity(),"pingbita");
        mListView.setAdapter(mAdapter);

    }

    private void selectDataBase() {
        //查收查询数据库，取出数据
        RequestParams params = new RequestParams(url);
        //查询已经屏蔽掉不再看他的动态的账户
        params.addQueryStringParameter("mYuid", 1+"");
        params.addQueryStringParameter("selectPath", "selectPath");
        //params.addQueryStringParameter("mPath", mPath);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //成功

                Gson gson = new Gson();
                Type type = new TypeToken<List<UserInfo>>(){}.getType();
                list = gson.fromJson(result,type);
                for(int i = 0;i<list.size();i++){
                    mUserInfo = list.get(i);
                    if(mUserInfo != null){
                        b = true;
                    }
                    Toast.makeText(getActivity(), mUserInfo.toString(), Toast.LENGTH_SHORT).show();
                    userInfo = new UserInfo(mUserInfo.getUid(),mUserInfo.getUrl(),mUserInfo.getUname(),mUserInfo.getUsign());
                    mUserInfoList.add(userInfo);
                }
                setAdapter();

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

    private void initView(View view) {
        mFragmentManager = getFragmentManager();
        mTransaction = mFragmentManager.beginTransaction();
        mAddFragment = new PingBiAddFragment();
        mImageView = (ImageView) view.findViewById(R.id.hhy_pingbita_back_image);
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.hhy_pingbita_relative);
        mListView = (ListView) view.findViewById(R.id.hhy_pingbi_others_listview);
        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至页面
                mIntent = new Intent(getActivity(), PinBiAddActivity.class);
                mIntent.putExtra("biaozhi","pingbita");
                startActivity(mIntent);
            }
        });
        //返回上一个fragment
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentManager.popBackStack();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        setAdapter();
    }
}
