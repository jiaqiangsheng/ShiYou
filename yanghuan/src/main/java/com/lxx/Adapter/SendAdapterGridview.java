package com.lxx.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.yanghuan.R;

import java.util.List;

/**
 * Created by 潇 on 2016/5/28.
 */
public class SendAdapterGridview extends BaseAdapter {
    List<Bitmap> bitmapList;
    LayoutInflater inflater;
    Context context;
    public SendAdapterGridview(List<Bitmap> bitmapList, Context context) {
        this.bitmapList = bitmapList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override

    public int getCount() {
        return bitmapList.size();
    }

    @Override
    public Object getItem(int position) {
        return bitmapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class ViewHolder {

        ImageView imageView;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            //说明是第一次绘制整屏列表，例如1-6行
            convertView = inflater.inflate(R.layout.sendgridview, null);
            viewHolder = new ViewHolder();
            //初始化当前行布局中的所有控件
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.sendgridviewimage);
            //把当前的控件缓存到布局视图中
            convertView.setTag(viewHolder);

        } else {
            //说明开始上下滑动，后面的所有行布局采用第一次绘制时的缓存布局
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Bitmap bitmap=bitmapList.get(position);
        viewHolder.imageView.setImageBitmap(bitmap);


        return convertView;
    }
}
