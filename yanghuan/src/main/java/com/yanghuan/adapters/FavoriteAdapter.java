package com.yanghuan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanghuan.R;
import com.yanghuan.Beans.FavoriteBean;

import java.util.List;

/**
 * Created by 杨欢 on 2016/5/20.
 */
public class FavoriteAdapter extends BaseAdapter {
    List<FavoriteBean> list;
    Context context;
    LayoutInflater inflater;
    ViewHolder viewHolder;

    public FavoriteAdapter(List<FavoriteBean> list, Context context) {
        this.list = list;
        this.context = context;
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
    class ViewHolder {
        ImageView imageView;
        TextView textView1,textView2;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.favorite_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.favorite_item_image);
            viewHolder.textView1 = (TextView)convertView.findViewById(R.id.favorite_item_textview1);
            viewHolder.textView2= (TextView)convertView.findViewById(R.id.favorite_item_textview2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final FavoriteBean favorite=list.get(position);
        viewHolder.imageView.setImageResource(favorite.getImage());
        viewHolder.textView1.setText(favorite.getText());
        viewHolder.textView2.setText(favorite.getContent());
        return convertView;
    }

}
