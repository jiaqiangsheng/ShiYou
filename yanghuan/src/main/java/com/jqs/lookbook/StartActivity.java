package com.jqs.lookbook;

/**
 * Created by qiangsheng on 2016/4/20.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jqs.example.beans.MyStoreItemBean;
import com.jqs.lookbook.util.IOHelper;
import com.yanghuan.R;

public class StartActivity extends Activity {
ImageView mImageView;
    MyStoreItemBean mStoreItemBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        mImageView= (ImageView) findViewById(R.id.start_activity_imageview);

        Intent intent=getIntent();
        mStoreItemBean= (MyStoreItemBean) intent.getSerializableExtra("bookname");
        String pictureUrl=  mStoreItemBean.getXsPicture();

        Glide.with(this)
                .load(pictureUrl)
                .into(mImageView);

        gotoHome();
    }

    /**
     * 启动封面后过会转向主界面
     */
    public void gotoHome(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                initBook();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent();
                intent.setClass(StartActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }

    /**
     * 将Book类初始化。
     * 因为启动界面，一般会显示几秒钟再跳转到主界面。
     * 而Book类需要从文件中提取关于书的信息，这可能需要一定时间。所以将书的初始化工作放在启动界面这里。
     * 可以节省用户等待的时间。
     */
    public void initBook(){
        IOHelper.getBook(this,mStoreItemBean);
    }
}

