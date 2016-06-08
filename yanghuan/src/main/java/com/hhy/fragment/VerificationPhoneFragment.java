package com.hhy.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yanghuan.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by hanhongyang on 2016/5/26.
 */
public class VerificationPhoneFragment extends Fragment implements View.OnClickListener {
    // 手机号输入框
    private EditText inputPhoneEt;
    // 验证码输入框
    private EditText inputCodeEt;
    // 获取验证码按钮
    private Button requestCodeBtn;
    // 下一步按钮
    private Button commitBtn;
    private ImageView mImageView;
    int i = 30;
    String phone;

    //"4a5449be48fc", "f08bd04c243dab7e41ca94e9499dec00"
    private String AppKey = "12256474442b8";
    private String AppSecret = "0c01dbd6b83ff5a968e6329a0684916a";

    View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.hhy_yanzheng_phone, null);
        // 启动短信验证sdk
        SMSSDK.initSDK(getActivity(), AppKey, AppSecret);
        init(mView);
        return mView;
    }



    private void init(View view) {
        mImageView = (ImageView) view.findViewById(R.id.hhy_verificvcation_phone_image);
        inputPhoneEt = (EditText) view.findViewById(R.id.hhy_yanzheng_editphone);
        inputCodeEt = (EditText) view.findViewById(R.id.hhy_yanzheng_editcode);
        requestCodeBtn = (Button) view.findViewById(R.id.hhy_yanzheng_requestCodeBtn);
        commitBtn = (Button) view.findViewById(R.id.hhy_next_btn);
        //添加点击事件
        mImageView.setOnClickListener(this);
        requestCodeBtn.setOnClickListener(this);
        commitBtn.setOnClickListener(this);
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        //注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    public void onClick(View view) {
        String phoneNums = inputPhoneEt.getText().toString();
        int id = view.getId();
        switch (id) {
            case R.id.hhy_verificvcation_phone_image:
                //点击了标题栏的返回小图标,返回上一个fragment,在上一个fragment里面
                // 写上setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN),这样就能实现
                //点击标题栏的返回小图标,返回上一个fragment的功能
                FragmentManager mFragmentManager;
                mFragmentManager = getFragmentManager();
                mFragmentManager.popBackStack();
                break;
            case R.id.hhy_yanzheng_requestCodeBtn:
                // 1. 通过规则判断手机号
                if (!judgePhoneNums(phoneNums)) {
                    return;
                } // 2. 通过sdk发送短信验证
                SMSSDK.getVerificationCode("86", phoneNums);

                // 3. 把按钮变成不可点击，并且显示倒计时（正在获取）
                requestCodeBtn.setClickable(false);
                requestCodeBtn.setText("重新发送(" + i + ")");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (; i > 0; i--) {
                            handler.sendEmptyMessage(-9);
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(-8);
                    }
                }).start();
                break;

            case R.id.hhy_next_btn:
                SMSSDK.submitVerificationCode("86", phoneNums, inputCodeEt
                        .getText().toString());
                //createProgressBar(getView());////*

                break;
        }
    }

    /**
     *
     */
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                requestCodeBtn.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                requestCodeBtn.setText("获取验证码");
                requestCodeBtn.setClickable(true);
                i = 30;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.e("event", "event=" + event);
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 短信注册成功后，进入绑定新手机页面
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        phone = inputPhoneEt.getText().toString();
                        Toast.makeText(getActivity(), "提交验证码成功",
                                Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), "电话号码："+phone,Toast.LENGTH_LONG).show();
                        FragmentManager mFragmentManager;
                        FragmentTransaction mTransaction;
                        mFragmentManager = getFragmentManager();
                        mTransaction = mFragmentManager.beginTransaction();
                        Hhy_BindPhoneFragment fragment = new Hhy_BindPhoneFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("key",phone);
                        fragment.setArguments(bundle);
                        mTransaction.replace(R.id.hhy_congfig_middle, fragment);
                        mTransaction.addToBackStack(null);
                        mTransaction.commit();
                        /////////////
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getActivity(), "验证码已经发送",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        ((Throwable) data).printStackTrace();
                    }
                }
            }
        }
    };

    /**
     * 判断手机号码是否合理
     *
     * @param phoneNums
     */
    private boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11)
                && isMobileNO(phoneNums)) {
            return true;
        } else if(!isMatchLength(phoneNums, 11)){
            Toast.makeText(getActivity(), "手机号码输入有误！", Toast.LENGTH_SHORT).show();
            return false;
        }else if(isMatchLength(phoneNums, 11) && !isMobileNO(phoneNums)){
            Toast.makeText(getActivity(), "此手机号码不存在！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;

    }

    /**
     * 判断一个字符串的位数
     *
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    /**
     * progressbar
     */
    private void createProgressBar(View view) {
        FrameLayout layout = (FrameLayout) view.findViewById(android.R.id.content);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        ProgressBar mProBar = new ProgressBar(getActivity());
        mProBar.setLayoutParams(layoutParams);
        mProBar.setVisibility(View.VISIBLE);
        layout.addView(mProBar);
    }

    @Override
    public void onDestroy() {
        SMSSDK.unregisterAllEventHandler();
        super.onDestroy();
    }
}
