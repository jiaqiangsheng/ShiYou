package com.hhy.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hhy.bean.OtherInfoBean;
import com.yanghuan.R;

import java.util.List;

/**
 * Created by hanhongyang on 2016/5/30.
 */
public class OtherInfoAdapter extends BaseAdapter {
    public static final int TYPE_COUNT = 3;
    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;
    public static final int TYPE_3 = 2;
    List<OtherInfoBean> mBeanList;
    Context mContext;
    LayoutInflater mInflater;

    public OtherInfoAdapter(List<OtherInfoBean> beanList, Context context) {
        mBeanList = beanList;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return mBeanList.get(position).getFlag2();
    }

    @Override
    public int getCount() {
        return mBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        SimpleDraweeView mSimpleDraweeView;
        TextView mUnameText, mUsignText;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (getItemViewType(position) == TYPE_1) {
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.hhy_otherinfo_item_zujin3, null);
                viewHolder.mUnameText = (TextView) convertView.findViewById(R.id.otherinfo_huodong);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //绑定数据
            OtherInfoBean otherInfoBean = mBeanList.get(position);
            viewHolder.mUnameText.setText(otherInfoBean.getWsname());
        }else if (getItemViewType(position) == TYPE_2) {
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.hhy_otherinfo_item_zujin, null);
                viewHolder.mSimpleDraweeView = (SimpleDraweeView) convertView.findViewById(R.id.otherinfo_zuijin_image);
                viewHolder.mUnameText = (TextView) convertView.findViewById(R.id.otherinfo_zuijin_uname);
                viewHolder.mUsignText = (TextView) convertView.findViewById(R.id.otherinfo_zuijin_usign);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //绑定数据
            OtherInfoBean otherInfoBean = mBeanList.get(position);
            Uri uri = Uri.parse(otherInfoBean.getWspicture());
            viewHolder.mSimpleDraweeView.setImageURI(uri);
            viewHolder.mUnameText.setText(otherInfoBean.getWsname());
        } else if (getItemViewType(position) == TYPE_3) {
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.hhy_otherinfo_item_zujin2, null);
                viewHolder.mSimpleDraweeView = (SimpleDraweeView) convertView.findViewById(R.id.otherinfo_zuijin_image2);
                viewHolder.mUnameText = (TextView) convertView.findViewById(R.id.otherinfo_zuijin_uname2);
                viewHolder.mUsignText = (TextView) convertView.findViewById(R.id.otherinfo_zuijin_usign2);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //绑定数据
            OtherInfoBean otherInfoBean = mBeanList.get(position);
            Uri uri = Uri.parse(otherInfoBean.getPrograming());
            viewHolder.mSimpleDraweeView.setImageURI(uri);
            viewHolder.mUnameText.setText(otherInfoBean.getProgramName());
        }
        return convertView;
    }


}
