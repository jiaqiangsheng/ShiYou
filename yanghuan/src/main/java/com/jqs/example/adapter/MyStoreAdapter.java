package com.jqs.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jqs.example.activity.BookFenLeiActivity;
import com.jqs.example.activity.BookPaiHangActivity;
import com.jqs.example.beans.MyStoreItemBean;
import com.jqs.example.utils.AutoPlayViewPager;
import com.yanghuan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiangsheng on 2016/5/18.
 */
public class MyStoreAdapter extends BaseAdapter {
    public static final int TYPE_0 = 0;
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    public static final int TYPE_3 = 3;

    List<MyStoreItemBean> mList;
    Context mContext;
    LayoutInflater mInflater;

    List<Integer> list;

    public MyStoreAdapter(Context context, List<MyStoreItemBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getItemViewType(int position) {
        //此方法返回当前数据行的视图类型
        return mList.get(position).getFlag();
    }

    @Override
    public int getViewTypeCount() {
        //返回每一行视图布局类型总数
        return 4;
    }

    //判断item是否可以点击，0返回true，表示可点；1返回false，表示不可点击。
    @Override
    public boolean isEnabled(int position) {
        if (mList.get(position).getItem_flag() == 0)
            return false;
        else
            return true;
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


    class ViewHolder {
        ImageView mImageView;
        TextView mTextView1, mTextView2, mTextView3, mTextView4;
    }

    class ViewHolder0 {
        TextView mV0TextView;
    }
    class ViewHolder02{
        RelativeLayout mRelativeLayout1,mRelativeLayout2,mRelativeLayout3,mRelativeLayout4;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //三种布局类型判断
        if (getItemViewType(position) == TYPE_1) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.store_item, null);
                viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.store_item_imageview);
                viewHolder.mTextView1 = (TextView) convertView.findViewById(R.id.store_item_text1);
                viewHolder.mTextView2 = (TextView) convertView.findViewById(R.id.store_item_text2);
                viewHolder.mTextView3 = (TextView) convertView.findViewById(R.id.store_item_text3);
                viewHolder.mTextView4 = (TextView) convertView.findViewById(R.id.store_item_text4);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            final MyStoreItemBean myStoreItemBean = mList.get(position);
            Glide.with(mContext)
                    .load(myStoreItemBean.getXsPicture())
                    .into(viewHolder.mImageView);
            viewHolder.mTextView1.setText(myStoreItemBean.getXsName());
            viewHolder.mTextView2.setText(myStoreItemBean.getXsIntroduce());
            viewHolder.mTextView3.setText(myStoreItemBean.getXsAuthor());
            viewHolder.mTextView4.setText(myStoreItemBean.getXsNumber());
        } else if (getItemViewType(position) == TYPE_0) {
            ViewHolder0 viewHolder0 = new ViewHolder0();
            convertView = mInflater.inflate(R.layout.store_item1, null);
            viewHolder0.mV0TextView = (TextView) convertView.findViewById(R.id.store_item1_textview);
            //得到AssetManager
            AssetManager mgr = mContext.getAssets();
            //根据路径得到Typeface
            Typeface tf = Typeface.createFromAsset(mgr, "fonts/lishu.ttf");
            //设置字体
            viewHolder0.mV0TextView.setTypeface(tf);


        } else if (getItemViewType(position) == TYPE_2) {
            ViewHolder02 viewHolder02;
                viewHolder02=new ViewHolder02();
                convertView = mInflater.inflate(R.layout.store_item2, null);
                viewHolder02.mRelativeLayout1= (RelativeLayout) convertView.findViewById(R.id.store_item2_r1);
                viewHolder02.mRelativeLayout2= (RelativeLayout) convertView.findViewById(R.id.store_item2_r2);
                viewHolder02.mRelativeLayout3= (RelativeLayout) convertView.findViewById(R.id.store_item2_r3);
                viewHolder02.mRelativeLayout4= (RelativeLayout) convertView.findViewById(R.id.store_item2_r4);
            //跳转分类显示界面
            viewHolder02.mRelativeLayout1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent=new Intent(mContext, BookFenLeiActivity.class);
                    mContext.startActivity(intent);
                }
            });
            //跳转排行榜显示界面
            viewHolder02.mRelativeLayout2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent=new Intent(mContext, BookPaiHangActivity.class);
                    mContext.startActivity(intent);
                }
            });
            //跳转界面

            //跳转免费图书界面



        } else if (getItemViewType(position) == TYPE_3) {
            convertView = mInflater.inflate(R.layout.store_item3, null);
            AutoPlayViewPager mViewPager= (AutoPlayViewPager) convertView.findViewById(R.id.store_item3_viewpager);
            list=new ArrayList<>();
            list.add(R.drawable.a);
            list.add(R.drawable.b);
            list.add(R.drawable.c);

            BannerAdapter bannerAdapter = new BannerAdapter(mContext);
            bannerAdapter.update(list);
            mViewPager.setAdapter(bannerAdapter);
            mViewPager.start();

        }else{
            convertView = mInflater.inflate(R.layout.store_item, null);
        }
        return convertView;
    }
}
