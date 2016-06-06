package com.jqs.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.yanghuan.R;
import java.util.IdentityHashMap;
import java.util.List;

/**
 * Created by qiangsheng on 2016/5/25.
 */
public class BookFenGridAdapter extends BaseAdapter {
    List<String> mList;
    Context mContext;
    LayoutInflater mInflater;

    public BookFenGridAdapter(Context context, List<String> list) {
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

    class FenleiViewHolder{
        TextView mTextView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FenleiViewHolder fenleiViewHolder;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.book_fenlei_griditem,null);
            fenleiViewHolder=new FenleiViewHolder();
            fenleiViewHolder.mTextView= (TextView) convertView.findViewById(R.id.book_fenlei_griditem_text);
            convertView.setTag(fenleiViewHolder);
        }else {
            fenleiViewHolder= (FenleiViewHolder) convertView.getTag();
        }


        fenleiViewHolder.mTextView.setText(mList.get(position));

        return convertView;
    }
}
