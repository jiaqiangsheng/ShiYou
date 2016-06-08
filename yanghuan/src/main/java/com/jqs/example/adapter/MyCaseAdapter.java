package com.jqs.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jqs.example.beans.MyCaseItemBean;
import com.yanghuan.R;

import java.util.List;

/**
 * Created by qiangsheng on 2016/5/18.
 */
public class MyCaseAdapter extends BaseAdapter{
List<MyCaseItemBean> mList;
    Context mContext;
    LayoutInflater mInflater;
    //是否显示或隐藏checkbox
    //private boolean mflag;

    public MyCaseAdapter(Context context, List<MyCaseItemBean> list) {
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

    class CaseViewHolder{
        ImageView mImageView;
        TextView mTextView1,mTextView2,mTextView3;
        //CheckBox mCheckBox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CaseViewHolder caseViewHolder;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.case_item,null);
            caseViewHolder=new CaseViewHolder();
            caseViewHolder.mImageView= (ImageView) convertView.findViewById(R.id.case_item_imageview);
            caseViewHolder.mTextView1= (TextView) convertView.findViewById(R.id.case_item_textview1);
            caseViewHolder.mTextView2= (TextView) convertView.findViewById(R.id.case_item_textview2);
            caseViewHolder.mTextView3= (TextView) convertView.findViewById(R.id.case_item_textview3);
           // caseViewHolder.mCheckBox= (CheckBox) convertView.findViewById(R.id.case_item_checkboc);
            convertView.setTag(caseViewHolder);


        }else{
            caseViewHolder= (CaseViewHolder) convertView.getTag();
        }
            final MyCaseItemBean myCaseItemBean=mList.get(position);
        Glide.with(mContext)
                .load(myCaseItemBean.getXsPicture())
                .into(caseViewHolder.mImageView);
        caseViewHolder.mTextView1.setText(myCaseItemBean.getXsName());
        caseViewHolder.mTextView2.setText(myCaseItemBean.gethLocation());
        caseViewHolder.mTextView3.setText(myCaseItemBean.gethData());

      /*  if(mflag){
                caseViewHolder.mCheckBox.setVisibility(View.VISIBLE);
            caseViewHolder.mCheckBox.setChecked(mList.get(position).isCheckFlag());
            notifyDataSetChanged();
        }else {
            caseViewHolder.mCheckBox.setVisibility(View.INVISIBLE);
            for(int i=0;i<mList.size();i++){
                mList.get(i).setCheckFlag(false);
            }
            notifyDataSetChanged();
        }
*/
        return convertView;
    }
}
