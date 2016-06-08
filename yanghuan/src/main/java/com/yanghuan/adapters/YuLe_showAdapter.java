package com.yanghuan.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yanghuan.Beans.JiemuShow;
import com.yanghuan.MyApplication.XiangQingActivity;
import com.yanghuan.R;

import java.util.List;

/**
 * Created by C5-0 on 2016/6/4.
 */
public class YuLe_showAdapter extends BaseAdapter {
    List<JiemuShow> list;
    Context context;
    LayoutInflater inflater;
    public YuLe_showAdapter(List<JiemuShow> jiemuShows, Context context) {
        this.list = jiemuShows;
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
        ImageView imageView;
        TextView title,xqtext;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView=inflater.inflate(R.layout.jiemu_show_item,null);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.jiemu_show_image);
            viewHolder.title= (TextView) convertView.findViewById(R.id.jiemu_show_text1);
            viewHolder.xqtext= (TextView) convertView.findViewById(R.id.jiemu_show_text2);
            //把当前的控件缓存到布局视图中
            convertView.setTag(viewHolder);
        } else{
            //说明开始上下滑动，后面的所有行布局采用第一次绘制时的缓存布局
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final  JiemuShow jiemuShow=list.get(position);
        viewHolder.title.setText(jiemuShow.getName());

        Glide.with(context)
                .load(jiemuShow.getPicture())
                .into(viewHolder.imageView);
        viewHolder.xqtext.setText(jiemuShow.getPerson());


        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        Intent intent=new Intent(context, XiangQingActivity.class);
                        context.startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(context,"2",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(context,"3",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(context,"1",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return convertView;
    }
}
