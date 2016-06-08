package com.hhy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by hanhongyang on 2016/5/23.
 */
public class AdviceFragment extends Fragment implements View.OnClickListener {
    public static final int REQUEST_CODE = 1;

    private TextView mTextView;
    private Button mButton;
    private EditText mEditText;
    FragmentTransaction mFragmentTransaction;
    ConfigFragment mConfigFragment;
    boolean flag = false;
    public static final String TAG = "XUTILS";
    String mPath;
    String text;
    FragmentManager mFragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hhy_advicefankui, null);
        initView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //xUtils初始化
        x.Ext.init(getActivity().getApplication());
    }

    private void initView(View view) {
        mTextView = (TextView) view.findViewById(R.id.hhy_cancle);
        mEditText = (EditText) view.findViewById(R.id.hhy_edittext);
        mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(300)});
        mButton = (Button) view.findViewById(R.id.hhy_advice_fasong);
        mFragmentManager = getFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        //添加监听事件
        mTextView.setOnClickListener(this);
        mButton.setOnClickListener(this);
        mEditText.addTextChangedListener(watcher);
    }

   /* private void ifEditText() {
        text =  mEditText.getText().toString();
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        if ("".equals(text)){
           // flag = false;

        }else{
            mButton.setEnabled(true);
            //flag = true;
        }
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.hhy_cancle:
                //点击取消意见反馈，返回上一个fragment
                mFragmentManager.popBackStack();
                break;
            case R.id.hhy_advice_fasong:
                //点击发送按钮IF
                text = mEditText.getText().toString();
                if (!"".equals(text)) {
                    getTest(text);
                }
                break;
            default:
                break;
        }
    }

    //为mEditText添加内容监听事件
    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // 当内容改变时
            if (!"".equals(s)) {
                mButton.setEnabled(true);
            } else {
                mButton.setEnabled(false);
            }

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
            if (!"".equals(s)) {
                mButton.setEnabled(true);
            } else {
                mButton.setEnabled(false);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {
            // 当内容改变后
            if (!"".equals(s)) {
                mButton.setEnabled(true);
            } else {
                mButton.setEnabled(false);
            }

        }
    };


    public void getTest(String text) {
        mPath = "http://10.201.1.148:8888/HttpServer/HttpServer";
        RequestParams params = new RequestParams(mPath);
        params.addQueryStringParameter("myuidadvice", 1+"");
        params.addQueryStringParameter("adviceContent", text);
        //第二步：开始请求，设置请求方式，同时实现回调函数
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //访问成功，参数result其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                //显示服务器发来的信息
                if("意见发送成功".equals(result)){
                    Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mFragmentManager.popBackStack();



            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //访问失败
                Log.e(TAG, "错误原因：" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                //访问取消
            }

            @Override
            public void onFinished() {
                //访问完成
            }
        });

    }
}
