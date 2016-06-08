package com.yanghuan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yanghuan.Beans.Dianshi;
import com.yanghuan.R;

import java.util.List;

/**
 * Created by C5-0 on 2016/6/3.
 */
public class OtherAdapter extends BaseAdapter {
    List<Dianshi> list;
    Context context;
    LayoutInflater inflater;
    Dianshi_itemAdapter dianshi_itemAdapter;

    public OtherAdapter(List<Dianshi> list, Context context) {
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
        ImageView imageView;
        TextView title,totalTitle,xqtext;
        GridView gridView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            //说明是第一次绘制整屏列表，例如1-6行
            convertView = inflater.inflate(R.layout.dianshi_item, null);
            viewHolder = new ViewHolder();
            //初始化当前行布局中的所有控件
            viewHolder.totalTitle= (TextView) convertView.findViewById(R.id.dianshi_item_title);
            viewHolder.xqtext= (TextView) convertView.findViewById(R.id.xiangqing);
            viewHolder.title= (TextView) convertView.findViewById(R.id.dianshi_title);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.dianshi_image1);

            viewHolder.gridView= (GridView) convertView.findViewById(R.id.dianshi_gridview);
            if(position==0) {
                viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "点击了大图片", Toast.LENGTH_SHORT).show();
                    }
                });
                viewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context, "点击了" + (position + 1), Toast.LENGTH_SHORT).show();
                    }
                });
            }else if(position==1){
                viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "点击了第二张大图片", Toast.LENGTH_SHORT).show();
                    }
                });
                viewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context, "点击了" + (position + 4), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            //把当前的控件缓存到布局视图中
            convertView.setTag(viewHolder);
        } else {
            //说明开始上下滑动，后面的所有行布局采用第一次绘制时的缓存布局
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Dianshi dianshi=list.get(position);
        viewHolder.totalTitle.setText(dianshi.getTotalTitle());
        viewHolder.xqtext.setText(dianshi.getDsName());
      /*  //得到AssetManager
        AssetManager mgr = context.getAssets();
        //根据路径得到Typeface
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/lishu.ttf");
        //设置字体
        viewHolder.totalTitle.setTypeface(tf);*/
        viewHolder.title.setText(dianshi.getDsTitle());
        Glide.with(context)
                .load(dianshi.getDsImage())
                .into(viewHolder.imageView);

        dianshi_itemAdapter=new Dianshi_itemAdapter(list.get(position).getShiPinList(),context);
        viewHolder.gridView.setAdapter(dianshi_itemAdapter);
        return convertView;
    }
}
