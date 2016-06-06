package com.hhy.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhy.bean.ShiYouList;
import com.yanghuan.R;

import java.util.List;

/**
 * Created by hanhongyang on 2016/5/17.
 */
public class ShiYouAdapter extends BaseAdapter {
    List<ShiYouList> mList;
    Context mContext;
    LayoutInflater mInflater;

    public ShiYouAdapter(List<ShiYouList> list, Context context) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {

        return mList.size();
    }

    @Override
    public Object getItem(int position) {

        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    class ViewHoder {
        ImageView mLeftImage;
        TextView name;
        TextView biaoqian;
        TextView distance;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoder viewHoder;
        if (convertView == null) {
            viewHoder = new ViewHoder();
            convertView = mInflater.inflate(R.layout.hhy_item_shiyou, null);
            viewHoder.mLeftImage = (ImageView) convertView.findViewById(R.id.middle_image);
            viewHoder.name = (TextView) convertView.findViewById(R.id.middle_name);
            viewHoder.biaoqian = (TextView) convertView.findViewById(R.id.middle_bianqian);
            viewHoder.distance = (TextView) convertView.findViewById(R.id.middle_distance);
            convertView.setTag(viewHoder);
        } else {
            viewHoder = (ViewHoder) convertView.getTag();
        }

        //绑定数据
        ShiYouList shiYouList = mList.get(position);
        viewHoder.mLeftImage.setImageResource(shiYouList.getLeft_pic());
        viewHoder.name.setText(shiYouList.getName());
        viewHoder.biaoqian.setText(shiYouList.getBiaoqian());
        viewHoder.distance.setText(shiYouList.getDiistance());
        return convertView;
    }
}
