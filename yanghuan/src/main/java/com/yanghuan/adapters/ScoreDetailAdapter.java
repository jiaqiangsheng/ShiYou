package com.yanghuan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanghuan.Beans.ScoreDetail;
import com.yanghuan.R;

import java.util.List;

/**
 * Created by C5-0 on 2016/5/31.
 */
public class ScoreDetailAdapter extends BaseAdapter {
    List<ScoreDetail> list;
    Context context;
    LayoutInflater inflater;

    public ScoreDetailAdapter(List<ScoreDetail> list, Context context) {
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

        TextView textView1,textView2,textView3,textView4;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            //说明是第一次绘制整屏列表，例如1-6行
            convertView = inflater.inflate(R.layout.yh_scoretail_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textView1= (TextView) convertView.findViewById(R.id.yh_scoretail_text1);
            viewHolder.textView2= (TextView) convertView.findViewById(R.id.yh_scoretail_text2);
            viewHolder.textView3= (TextView) convertView.findViewById(R.id.yh_scoretail_text3);
            viewHolder.textView4= (TextView) convertView.findViewById(R.id.yh_scoretail_text4);
            //把当前的控件缓存到布局视图中
            convertView.setTag(viewHolder);
        }else {
            //说明开始上下滑动，后面的所有行布局采用第一次绘制时的缓存布局
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ScoreDetail scoreDetail=list.get(position);
        viewHolder.textView1.setText(scoreDetail.getDetail());
        viewHolder.textView2.setText(scoreDetail.getDate());
        viewHolder.textView3.setText(scoreDetail.getNum());
        viewHolder.textView4.setText(scoreDetail.getTime());
        return convertView;
    }
}
