package com.hhy.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.yanghuan.R;

/**
 * Created by hanhongyang on 2016/5/23.
 */
public class hhy_EditionFragment extends Fragment {
    private Button mButton;
    private ImageView mImageView;
    FragmentManager mFragmentManager;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int id = msg.what;
           if(id == 1){
               handler.sendEmptyMessageAtTime(2,10000);
           }else if(id == 2){
                show("当前已是最新版本");
           }
        }

    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hhy_about_us,null);
        mButton = (Button) view.findViewById(R.id.hhy_gengxin);
        mImageView = (ImageView) view.findViewById(R.id.hhy_config_about_us_back);
        mFragmentManager = getFragmentManager();
        setListener();
        return view;
    }

    private void setListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show("正在检测新版本,请稍后");
                handler.sendEmptyMessage(1);
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentManager.popBackStack();
            }
        });
    }

    private void show(String text) {

        Toast.makeText(getActivity(),text,Toast.LENGTH_SHORT).show();
    }
}
