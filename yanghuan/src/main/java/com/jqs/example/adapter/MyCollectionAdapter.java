package com.jqs.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jqs.example.beans.MyCollectionBean;
import com.yanghuan.R;

import java.util.List;

/**
 * Created by qiangsheng on 2016/5/24.
 */
public class MyCollectionAdapter extends BaseAdapter {
    List<MyCollectionBean> mList;
    Context mContext;
    LayoutInflater mInflater;

    public MyCollectionAdapter(Context context, List<MyCollectionBean> list) {
        mContext = context;
        mList = list;
        mInflater=LayoutInflater.from(mContext);
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

    class CollectionViewHolder{
        ImageView mImageView;
        TextView mTitle,mIntro,mTime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CollectionViewHolder collectionViewHolder;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.collect_item1,null);
            collectionViewHolder=new CollectionViewHolder();
            collectionViewHolder.mImageView= (ImageView) convertView.findViewById(R.id.collection_item1_imageview);
            collectionViewHolder.mTitle= (TextView) convertView.findViewById(R.id.collection_item1_title);
            collectionViewHolder.mIntro= (TextView) convertView.findViewById(R.id.collection_item1_intro);
            collectionViewHolder.mTime= (TextView) convertView.findViewById(R.id.collection_item1_time);
            convertView.setTag(collectionViewHolder);
        }else {
            collectionViewHolder= (CollectionViewHolder) convertView.getTag();
        }
        final MyCollectionBean myCollectionBean=mList.get(position);
        Glide.with(mContext)
                .load(myCollectionBean.getTitle_url()[0])
                .into(collectionViewHolder.mImageView);

        collectionViewHolder.mTitle.setText(myCollectionBean.getTitle());
        collectionViewHolder.mIntro.setText(myCollectionBean.getIntro());
        collectionViewHolder.mTime.setText(myCollectionBean.getTime());

        return convertView;
    }
}
