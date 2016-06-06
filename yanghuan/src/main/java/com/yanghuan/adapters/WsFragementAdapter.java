package com.yanghuan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yanghuan.Beans.WeiShiBean;

import java.util.List;

/**
 * Created by 杨欢 on 2016/5/23.
 */
public class WsFragementAdapter extends BaseAdapter  {
    public static final int TYPE_0 = 0;
    public static final int TYPE_1 = 1;
    List<WeiShiBean> weiShiBeen;
    LayoutInflater inflater;
    List<WeiShiBean> list;
    View view;
    Context mContext;
   /* ViewHolder viewHolder;
    ViewHolder1 viewHolder1;*/
    WeiShiBean music;

    public WsFragementAdapter(Context mContext, List<WeiShiBean> list) {
        this.mContext = mContext;
        this.list = list;
        inflater=LayoutInflater.from(mContext);
    }



    @Override
    public int getViewTypeCount() {
        //返回每一行视图布局类型总数
        return list.size();
    }

    //判断item是否可以点击，0返回true，表示可点；1返回false，表示不可点击。
    @Override
    public boolean isEnabled(int position) {
        if (list.get(position).getItem_flag() == 0)
            return false;
        else
            return true;
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




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return convertView;
       }

    }











