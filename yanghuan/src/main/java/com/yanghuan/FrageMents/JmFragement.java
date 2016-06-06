package com.yanghuan.FrageMents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yanghuan.MyApplication.JieMuItemActivity;
import com.yanghuan.MyApplication.ShaoErItemActivity;
import com.yanghuan.MyApplication.YuLeItemActivity;
import com.yanghuan.MyApplication.ZongYiItemActivity;
import com.yanghuan.R;
import com.yanghuan.Beans.jiemuBean;

import java.util.List;

/**
 * Created by 杨欢 on 2016/5/23.
 */
public class JmFragement extends Fragment implements View.OnClickListener{
    List<jiemuBean> list;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,
            imageView6,imageView7,imageView8,imageView9;
    TextView textView1,textView2,textView3,textView4,textView5,textView6,
            textView7,textView8,textView9;
    View mView;
    Context mContext;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext=activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.jiemu,null);
        initView();
        initData();
        addlistener();
        return mView;
    }

    private void initView() {
        imageView1 = (ImageView)mView.findViewById(R.id.jiemu_image1);
        imageView2 = (ImageView)mView.findViewById(R.id.jiemu_image2);
        imageView3= (ImageView)mView.findViewById(R.id.jiemu_image3);
        imageView4 = (ImageView)mView.findViewById(R.id.jiemu_image21);
        imageView5 = (ImageView)mView.findViewById(R.id.jiemu_image22);
        imageView6= (ImageView)mView.findViewById(R.id.jiemu_image23);
        imageView7 = (ImageView)mView.findViewById(R.id.jiemu_image31);
        imageView8 = (ImageView)mView.findViewById(R.id.jiemu_image32);
        imageView9= (ImageView)mView.findViewById(R.id.jiemu_image33);


        textView1 = (TextView)mView.findViewById(R.id.jiemu_text1);
        textView2 = (TextView)mView.findViewById(R.id.jiemu_text2);
        textView3 = (TextView)mView.findViewById(R.id.jiemu_text2);
        textView4= (TextView)mView.findViewById(R.id.jiemu_text21);
        textView5 = (TextView)mView.findViewById(R.id.jiemu_text22);
        textView6 = (TextView)mView.findViewById(R.id.jiemu_text23);
        textView7 = (TextView)mView.findViewById(R.id.jiemu_text31);
        textView8 = (TextView)mView.findViewById(R.id.jiemu_text32);
        textView9 = (TextView)mView.findViewById(R.id.jiemu_text33);

    }

    private void addlistener() {
        imageView1.setOnClickListener( this);
        imageView2.setOnClickListener( this);
        imageView3.setOnClickListener( this);
        imageView4.setOnClickListener( this);
        imageView5.setOnClickListener( this);
        imageView6.setOnClickListener( this);
        imageView7.setOnClickListener( this);
        imageView8.setOnClickListener( this);
        imageView9.setOnClickListener( this);
    }

    private void initData() {

        textView1.setText("电视剧");
        textView2.setText("电影");
        textView3.setText("综艺");
        textView4.setText("少儿");
        textView5.setText("体育");
        textView6.setText("娱乐");
        textView7.setText("跑男");
        textView8.setText("电视剧");
        textView9.setText("电影");

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.jiemu_image1:
                Intent intent=new Intent(getContext(), JieMuItemActivity.class);
                intent.putExtra("1","");
                startActivity(intent);
                break;
            case R.id.jiemu_image2:
                Intent intent2=new Intent(getContext(), ZongYiItemActivity.class);
                intent2.putExtra("2","");
                startActivity(intent2);
                break;
            case R.id.jiemu_image3:
                Intent intent3=new Intent(getContext(), YuLeItemActivity.class);
                intent3.putExtra("3","");
                startActivity(intent3);
                break;
            case R.id.jiemu_image21:
                Intent intent4=new Intent(getContext(), ShaoErItemActivity.class);
                intent4.putExtra("4","");
                startActivity(intent4);
                break;
            case R.id.jiemu_image22:
                show("电视剧");
                break;
            case R.id.jiemu_image23:
                show("电视剧");
                break;
            case R.id.jiemu_image31:
                show("电视剧");
                break;
            case R.id.jiemu_image32:
                show("电视剧");
                break;
            case R.id.jiemu_image33:
                show("电视剧");
                break;


        }

    }
    private void show(String s) {
        Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();
    }
}
