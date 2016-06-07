package com.hhy.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.LoginUser;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.hhy.adapter.OtherInfoAdapter;
import com.hhy.bean.OtherInfoBean;
import com.hhy.bean.UserInfo;
import com.jqs.servert.utils.MyApplication;
import com.yanghuan.BuildConfig;
import com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class OtherInfoActivity extends AppCompatActivity implements View.OnClickListener{
    SimpleDraweeView mSimpleDraweeView;//头像
    ImageView mImageView;
    Button mJiaguanzhuButton,mSiXinButton;
    TextView mUnameText, mSexText, mStarText, mAddressText, mCareText, mCareNumberText, mFansText, mFansNumberText;
    String url;
    Intent mIntent;
    String uid;
    List<OtherInfoBean> mUserInfoList;
    OtherInfoAdapter mInfoAdapter;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Fresco初始化
        Fresco.initialize(OtherInfoActivity.this);
        setContentView(R.layout.hhy_activity_other_info);
        MyApplication myApplication = (MyApplication) getApplication();
        url = myApplication.getUrlPath();
        initView();
        initData();

    }
    private void initView() {
        mListView = (ListView) findViewById(R.id.otherinfo_listview);
        mUserInfoList = new ArrayList<OtherInfoBean>();
        mJiaguanzhuButton = (Button) findViewById(R.id.otherinfo_jiaguanzhu);
        mSiXinButton = (Button) findViewById(R.id.otherinfo_sixin);
        mImageView = (ImageView) findViewById(R.id.otherinfo_icon);
        mSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.otherinfo_touxiang);
        mUnameText = (TextView) findViewById(R.id.otherinfo_name);
        mSexText = (TextView) findViewById(R.id.otherinfo_age);
        mStarText = (TextView) findViewById(R.id.otherinfo_star);
        mAddressText = (TextView) findViewById(R.id.otherinfo_address);
        mCareText = (TextView) findViewById(R.id.otherinfo_guanzhu);
        mCareNumberText = (TextView) findViewById(R.id.otherinfo__guanzhu_number);
        mFansText = (TextView) findViewById(R.id.otherinfo_fans);
        mFansNumberText = (TextView) findViewById(R.id.otherinfo_fans_number);

        mJiaguanzhuButton.setOnClickListener(this);
        mSiXinButton.setOnClickListener(this);

    }

    private void initData() {
        mIntent = getIntent();
        uid = mIntent.getStringExtra("uid");
        selectDataBase();
    }

    private void selectDataBase() {
        //Toast.makeText(OtherInfoActivity.this, uid+"-other", Toast.LENGTH_LONG).show();
        RequestParams params = new RequestParams(url);
        if(uid == null){
           return;
        }else{
            //根据uid查询出他人的信息
            params.addBodyParameter("otherinfo",uid);
            //登陆人的uid待完善
            /*params.addBodyParameter("MyUid2", 1+"");*/
            params.addBodyParameter("MyUid2", LoginUser.userid+"");
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                UserInfo userInfo = null;
                userInfo = gson.fromJson(result,new UserInfo().getClass());
                mUnameText.setText(userInfo.getUname());
                mSexText.setText(userInfo.getUsex());
                mStarText.setText(userInfo.getStar());
                mAddressText.setText(userInfo.getUaddress());
                Uri uri = Uri.parse(userInfo.getUrl());
                mSimpleDraweeView.setImageURI(uri);
                if("男".equals(userInfo.getUsex())){
                    mImageView.setImageResource(R.drawable.sex_man);
                }
                if(userInfo.getMcareNumber() == 0){
                    mCareNumberText.setText(0+"");
                }else{
                    mCareNumberText.setText(userInfo.getMcareNumber()+"");
                }
                if(userInfo.getOcareNumber() == 0){
                    mFansNumberText.setText(0+"");
                }else{
                    mFansNumberText.setText(userInfo.getOcareNumber()+"");
                }
                if("true".equals(userInfo.getFlag())){
                    mJiaguanzhuButton.setText("已关注");
                    mJiaguanzhuButton.setClickable(false);
                    mJiaguanzhuButton.setTextColor(0xFFFFFFFF);
                }
                OtherInfoBean otherInfoBean = new OtherInfoBean();
                otherInfoBean.setFlag2(0);
                otherInfoBean.setWsname("最近活动");
                mUserInfoList.add(otherInfoBean);
                for(int i = 0;i<userInfo.getBeanList().size();i++){
                    mUserInfoList.add(userInfo.getBeanList().get(i));
                }

                mInfoAdapter = new OtherInfoAdapter(mUserInfoList,OtherInfoActivity.this);
                mListView.setAdapter(mInfoAdapter);

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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.otherinfo_jiaguanzhu:
                //点击+关注按钮
                insertDataBase(uid);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                selectDataBase(uid);
                break;
            case R.id.otherinfo_sixin:
                //跳转到私信页面
                break;
            default:
                break;
        }
    }

    private void insertDataBase(String uid) {
        //向关注表中通过uid添加数据
        //查收查询数据库，取出数据
        RequestParams params = new RequestParams(url);
        //当前登陆人的uid,先假设都为1，以后从登陆上取得//待完善
       /* params.addBodyParameter("MyUid", 1+"");*/
        params.addBodyParameter("MyUid", LoginUser.userid+"");
        //当前登陆人要关注的人的uid
        params.addBodyParameter("insertMcareByUid",uid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if("true".equals(result)){
                    mJiaguanzhuButton.setText("已关注");
                    mJiaguanzhuButton.setClickable(false);
                    mJiaguanzhuButton.setTextColor(0xFFFFFFFF);
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

    private void selectDataBase(String uid) {
        //查询粉丝数量
        String url = "http://10.201.1.148:8888/HttpServer/HttpServer";
        //查收查询数据库，取出数据
        RequestParams params = new RequestParams(url);
        //当前登陆人要关注的人的uid
        params.addBodyParameter("otherUid",uid);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(OtherInfoActivity.this, result, Toast.LENGTH_SHORT).show();
                if("0".equals(result)){
                    mFansNumberText.setText("0");
                }else{
                    mFansNumberText.setText(result);
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
