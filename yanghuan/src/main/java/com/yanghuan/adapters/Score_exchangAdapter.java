package com.yanghuan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;

import com.yanghuan.R;
import com.yanghuan.Beans.ScoreExchange;

import java.util.List;

/**
 * Created by 杨欢 on 2016/5/26.
 */
public class Score_exchangAdapter extends BaseAdapter {
    List<ScoreExchange> list;
    Context context;
    LayoutInflater inflater;
    StoryAdapter adapter;

    public Score_exchangAdapter(List<ScoreExchange> list, Context context) {
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
    private class ViewHolder {
        ImageButton button1,button2;
        GridView gridView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            //说明是第一次绘制整屏列表，例如1-6行
            convertView = inflater.inflate(R.layout.score_exange_item, null);
            viewHolder = new ViewHolder();
            viewHolder.gridView= (GridView)convertView.findViewById(R.id.score_exange_gridview);
            //把当前的控件缓存到布局视图中
            convertView.setTag(viewHolder);
        } else {
            //说明开始上下滑动，后面的所有行布局采用第一次绘制时的缓存布局
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ScoreExchange exchange=list.get(position);
//        Glide.with(context)
//                .load(exchange.getSePic())
//                .into(viewHolder.imageView);
        adapter=new StoryAdapter(context,list.get(position).getList());
        viewHolder.gridView.setAdapter(adapter);
        return convertView;
    }
}
