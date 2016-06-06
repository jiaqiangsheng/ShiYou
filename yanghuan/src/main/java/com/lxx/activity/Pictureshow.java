package com.lxx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.yanghuan.BuildConfig;
import com.yanghuan.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

public class Pictureshow extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictureshow);
        initXUtils();
        Intent intent = getIntent();
        String url = intent.getStringExtra("stringurl");
        imageView= (ImageView) findViewById(R.id.lxx_imageshow);
        downLoadImage(imageView,url);
    }
    private void initXUtils() {
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
    public  void  downLoadImage( ImageView imageView,String url){
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                        //设置加载过程中的图片
                        //.setLoadingDrawableId(R.drawable.aio_image_default_round)
                        //设置加载失败后的图片
                        //.setFailureDrawableId(R.drawable.aio_image_fail_round)
                        //设置使用缓存
                .setUseMemCache(true)
                        //设置支持gif
                .setIgnoreGif(false)
                        //设置显示圆形图片
                .setCircular(false)
                .setSquare(true)
                .build();
        x.image().bind(imageView, url, imageOptions);
    }
}
