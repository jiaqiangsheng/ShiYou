package com.jqs.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jqs.example.activity.ChapterActivity;
import com.jqs.example.beans.MyStoreItemBean;
import com.yanghuan.R;

import java.util.List;

/**
 * Created by qiangsheng on 2016/5/19.
 */
public class MyDetailsAdapter extends BaseAdapter{
    protected Handler mHandler = new Handler();
    List<MyStoreItemBean> mList;
    Context mContext;
    LayoutInflater mInflater;

    public MyDetailsAdapter(Context context, List<MyStoreItemBean> list) {
        mContext = context;
        mList = list;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
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
    class DetailsViewHolder{
        ImageView mImageView;
        TextView mTextView1, mTextView2, mTextView3, mTextView4,mTextView;
        RelativeLayout mLayout;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetailsViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new DetailsViewHolder();
            convertView = mInflater.inflate(R.layout.details_item1, null);
            viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.details_item1_imageview);
            viewHolder.mTextView1 = (TextView) convertView.findViewById(R.id.details_item1_text1);
            viewHolder.mTextView2 = (TextView) convertView.findViewById(R.id.details_item1_text2);
            viewHolder.mTextView3 = (TextView) convertView.findViewById(R.id.details_item1_text3);
            viewHolder.mTextView4 = (TextView) convertView.findViewById(R.id.details_item1_text4);
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.details_item1_textview);
            viewHolder.mLayout= (RelativeLayout) convertView.findViewById(R.id.details_item_relayout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DetailsViewHolder) convertView.getTag();
        }

        final MyStoreItemBean myStoreItemBean = mList.get(position);
        Glide.with(mContext)
                .load(myStoreItemBean.getXsPicture())
                .into(viewHolder.mImageView);
        viewHolder.mTextView1.setText(myStoreItemBean.getXsName());
        viewHolder.mTextView2.setText("150书币");
        viewHolder.mTextView3.setText(myStoreItemBean.getXsAuthor());
        viewHolder.mTextView4.setText(myStoreItemBean.getXsNumber()+"");
        viewHolder.mTextView.setText(myStoreItemBean.getXsIntroduce());

        viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,ChapterActivity.class);
                intent.putExtra("name",myStoreItemBean.getXsName());
                mContext.startActivity(intent);
            }
        });



        return convertView;
    }
}
