package com.lxx.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanghuan.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by 潇 on 2016/5/24.
 */
public class GridViewAdapter extends BaseAdapter{
    LayoutInflater inflater;
    Context context;
    List<String> listimage;
    int flag=0;
    public GridViewAdapter(Context context, List<String> list,int flag) {
        this.listimage = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.flag=flag;
    }

    @Override
    public int getCount() {
        return listimage.size();
    }

    @Override
    public Object getItem(int position) {
        return listimage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            //说明是第一次绘制整屏列表，例如1-6行
            convertView = inflater.inflate(R.layout.lxx_gridview, null);
            viewHolder = new ViewHolder();
            //初始化当前行布局中的所有控件

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.lxx_gridimage);
            viewHolder.textview = (TextView) convertView.findViewById(R.id.lxx_gridtext);
            //把当前的控件缓存到布局视图中
            convertView.setTag(viewHolder);

        } else {
            //说明开始上下滑动，后面的所有行布局采用第一次绘制时的缓存布局
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Log.e("grid", "" + listimage.toString()+flag+"position"+position);
        if(position==8)
            if(flag!=0){
                viewHolder.textview.setText("+"+flag);
                viewHolder.textview.setVisibility(View.VISIBLE);
                viewHolder.imageView.setAlpha(0.3f);
            }
        downLoadImage(viewHolder.imageView, listimage.get(position));



        return convertView;
    }
    private class ViewHolder {
        ImageView imageView;
        TextView textview;
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
