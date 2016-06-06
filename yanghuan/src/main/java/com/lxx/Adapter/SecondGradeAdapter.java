package com.lxx.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lxx.bean.Secondgrade_Review;
import com.yanghuan.R;

import java.util.List;

/**
 * Created by 潇 on 2016/5/25.
 */
public class SecondGradeAdapter extends BaseAdapter {
    List<Secondgrade_Review> list_second;
    LayoutInflater inflater;
    Context context;

    public SecondGradeAdapter(List<Secondgrade_Review> list_second, Context context) {
        this.list_second = list_second;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list_second.size();
    }

    @Override
    public Object getItem(int position) {
        return list_second.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class ViewHolder {

        TextView username1;
        TextView username2;
        TextView contex;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            //说明是第一次绘制整屏列表，例如1-6行
            convertView = inflater.inflate(R.layout.secondgradereview, null);
            viewHolder = new ViewHolder();
            //初始化当前行布局中的所有控件

            viewHolder.username1 = (TextView) convertView.findViewById(R.id.secondname1);
            viewHolder.username2 = (TextView) convertView.findViewById(R.id.secondname2);
            viewHolder.contex = (TextView) convertView.findViewById(R.id.secondcontext);


            //把当前的控件缓存到布局视图中
            convertView.setTag(viewHolder);

        } else {
            //说明开始上下滑动，后面的所有行布局采用第一次绘制时的缓存布局
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Secondgrade_Review secondgrade_Review = list_second.get(position);
        viewHolder.username1.setText(secondgrade_Review.getUsername1());
        viewHolder.contex.setText(secondgrade_Review.getSecondcontext());
        viewHolder.username2.setText(secondgrade_Review.getUsetname2());
        return convertView;
    }
}
