package com.yanghuan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanghuan.R;
import com.yanghuan.Beans.ScoreStore;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by 杨欢 on 2016/5/26.
 */
public class StoryAdapter extends BaseAdapter {
    List<ScoreStore> list;
    LayoutInflater inflater;
    Context context;

    public StoryAdapter(Context context, List<ScoreStore> list) {
        this.context = context;
        this.list = list;
       inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder {
        ImageView imageView;
        TextView textView,title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            //说明是第一次绘制整屏列表，例如1-6行
            convertView = inflater.inflate(R.layout.store_item_jifen, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.store_item_image);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.store_item_title);
        viewHolder.title= (TextView) convertView.findViewById(R.id.store_item_text);
            //把当前的控件缓存到布局视图中
            convertView.setTag(viewHolder);
        }else {
            //说明开始上下滑动，后面的所有行布局采用第一次绘制时的缓存布局
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ScoreStore scoreStore=list.get(position);
        downLoadImage(viewHolder.imageView,scoreStore.getStorePic());
        viewHolder.textView.setText(scoreStore.getStoreTitle());
        viewHolder.title.setText(scoreStore.getStoreName());
            return convertView;
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
