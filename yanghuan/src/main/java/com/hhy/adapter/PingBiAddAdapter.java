package com.hhy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhy.bean.ImageViewDemo;
import com.hhy.bean.User_Pingbi;
import com.yanghuan.R;

import java.util.List;

/**
 * Created by hanhongyang on 2016/5/16.
 */
public class PingBiAddAdapter extends BaseAdapter {
    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;

    List<User_Pingbi> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    //为搜索框edittext设置动态高度
    RelativeLayout.LayoutParams layoutParams;

    ViewHolder1 holder1;
    ViewHolder3 holder3;

    public PingBiAddAdapter(List<User_Pingbi> mList, Context context) {
        this.mList = mList;
        mContext = context;

        mInflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getFlag();

    }


    @Override
    public int getViewTypeCount() {

        return 2;
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

    class ViewHolder1 {
        EditText mEditText;
        //TextView mTextView;
    }

    class ViewHolder3 {
        ImageViewDemo mSimpleDraweeView;
        TextView mTextView1;
        TextView mTextView2;
        ImageView pic;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (getItemViewType(position) == TYPE_1) {
            convertView = mInflater.inflate(R.layout.hhy_item_pingbi_add, null);
            holder1 = new ViewHolder1();
            holder1.mEditText = (EditText) convertView.findViewById(R.id.hhy_pingbi_add_editview);

            //绑定数据，此处不用，数据是在布局中写死的

        }  else if (getItemViewType(position) == TYPE_2) {
            convertView = mInflater.inflate(R.layout.hhy_item_pingbi_add2, null);
            holder3 = new ViewHolder3();
            holder3.mSimpleDraweeView = (ImageViewDemo) convertView
                    .findViewById(R.id.hhy_pingbi_simpleDraweeView);
            holder3.mTextView1 = (TextView) convertView
                    .findViewById(R.id.hhy_pingbi_name);
            holder3.mTextView2 = (TextView) convertView
                    .findViewById(R.id.hhy_pingbi_bianqian);
            holder3.pic = (ImageView) convertView.findViewById(R.id.hhy_pingbi_dagou);

            //绑定数据
            User_Pingbi guanZhu = mList.get(position);
            holder3.mSimpleDraweeView.setImageResource(R.drawable.icon_uzao);
            holder3.mTextView1.setText(guanZhu.getUname());
            holder3.mTextView2.setText(guanZhu.getUsign());
            holder3.pic.setImageResource(R.drawable.hhy_icon_dagou);
            //默认隐藏
            holder3.pic.setVisibility(View.GONE);
        }
        return convertView;
    }


}

