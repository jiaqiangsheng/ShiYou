package com.hhy.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yanghuan.R;

import cn.smssdk.SMSSDK;


public class AccountSafeFragment extends Fragment implements View.OnClickListener {
    RelativeLayout mRelativeLayout1, mRelativeLayout2, mRelativeLayout3, mPhoneRelativeLayout;
    FragmentManager mManager;
    FragmentTransaction mTransaction;
    PingBiTaFragment mPingBiTaFragment;
    PingBiNotSeeFragment mNotSeeFragment;
    BlackListFragment mBlackListFragment;
    ImageView mImageView;
    TextView mTextView;

    private String AppKey = "12256474442b8";
    private String AppSecret = "0c01dbd6b83ff5a968e6329a0684916a";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hhy_fragment_account_safe, null);
        //初始化SMSSDK短信验证
        SMSSDK.initSDK(getActivity(), AppKey, AppSecret);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTextView = (TextView) view.findViewById(R.id.hhy_accountsafe_phone_text);
        mImageView = (ImageView) view.findViewById(R.id.hhy_safe_back_icon);
        mPhoneRelativeLayout = (RelativeLayout) view.findViewById(R.id.hhy_safe_phone);
        mRelativeLayout1 = (RelativeLayout) view.findViewById(R.id.hhy_yinsi1);
        mRelativeLayout2 = (RelativeLayout) view.findViewById(R.id.hhy_yinsi2);
        mRelativeLayout3 = (RelativeLayout) view.findViewById(R.id.hhy_heimingdan);

        mImageView.setOnClickListener(this);
        mPhoneRelativeLayout.setOnClickListener(this);
        mRelativeLayout1.setOnClickListener(this);
        mRelativeLayout2.setOnClickListener(this);
        mRelativeLayout3.setOnClickListener(this);

        mManager = getFragmentManager();
        mTransaction = mManager.beginTransaction();

        if(getArguments() != null){
            //当更换绑定的手机号后在隐私与安全页面上显示出新绑定的手机号
            String phone = (String) getArguments().get("phone");
            mTextView.setText(phone);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.hhy_safe_back_icon:
                mManager.popBackStack();
                break;
            case R.id.hhy_safe_phone:
                //跳转至输入旧手机号页面
                VerificationPhoneFragment mfragment = new VerificationPhoneFragment();
                //fragment动画必须在add,replace,remove等方法之前
                mTransaction.setCustomAnimations(
                        R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit);
                mTransaction.replace(R.id.hhy_congfig_middle, mfragment);
                mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
                break;
            case R.id.hhy_yinsi1:
                mPingBiTaFragment = new PingBiTaFragment();
                mTransaction.replace(R.id.hhy_congfig_middle, mPingBiTaFragment);
                mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
                break;
            case R.id.hhy_yinsi2:
                mNotSeeFragment = new PingBiNotSeeFragment();
                mTransaction.replace(R.id.hhy_congfig_middle, mNotSeeFragment);
                mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
                break;
            case R.id.hhy_heimingdan:
                mBlackListFragment = new BlackListFragment();
                mTransaction.replace(R.id.hhy_congfig_middle, mBlackListFragment);
                mTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
                break;
            default:
                break;
        }
    }


}
