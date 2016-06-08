package com.hhy.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hhy.activity.LoginActivity;
import com.hhy.bean.AccountManage;
import com.yanghuan.R;

import java.util.List;
import java.util.Map;

/**
 * Created by hanhongyang on 2016/5/23.
 */
public class AccountManageAdapter extends BaseAdapter {
    public static final String SAVE = "addAccount";
    public static final int TYPECOUNT = 3;
    List<AccountManage> mManageList;
    Context mContext;
    LayoutInflater mInflater;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEditor;
    boolean mflags = false;
    String uname;
    String uname2;
    int i;
    Map<String,String> map;
    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;
    public static final int TYPE_3 = 2;

    public AccountManageAdapter(List<AccountManage> manageList, Context context, boolean flags, String uname) {
        mManageList = manageList;
        mContext = context;
        mflags = flags;
        uname = uname;
        mInflater = LayoutInflater.from(mContext);
        sharedPreferences = context.getSharedPreferences(SAVE, Context.MODE_APPEND);
        mEditor = sharedPreferences.edit();
        i = 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mManageList.get(position).getFlag();
    }

    @Override
    public int getViewTypeCount() {
        return TYPECOUNT;
    }

    @Override
    public int getCount() {

        return mManageList.size();
    }

    @Override
    public Object getItem(int position) {

        return mManageList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    //设置让最后一个item不可点击，若想点击须在activity中设置点击事件
    @Override
    public boolean isEnabled(int position) {
        if(position == (mManageList.size()-1)){
            return false;
        }
        return true;
    }

    static class ViewHolder {
        SimpleDraweeView mSimpleDraweeView;
        TextView mTextView;
        ImageView mImageView1, mImageView2;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (getItemViewType(position) == TYPE_1) {
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.hhy_item_accountmanage1, null);
                viewHolder.mSimpleDraweeView = (SimpleDraweeView) convertView.findViewById(R.id.hhy_fresco1);
                viewHolder.mTextView = (TextView) convertView.findViewById(R.id.hhy_name1);
                viewHolder.mImageView1 = (ImageView) convertView.findViewById(R.id.hhy_dagou);
                viewHolder.mImageView2 = (ImageView) convertView.findViewById(R.id.hhy_delete);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //绑定数据
            AccountManage accountManage = mManageList.get(position);
            Uri uri = Uri.parse(accountManage.getHhy_accountm_pic_url());
            viewHolder.mSimpleDraweeView.setImageURI(uri);
            viewHolder.mTextView.setText(accountManage.getHhy_accountm_text());
            viewHolder.mImageView1.setImageResource(R.drawable.hhy_icon_dagou);

            //默认隐藏，在代码中根据需要再动态显示
            //需要登陆成功后取出用户名，再显示（还需要再改）
            if (viewHolder.mTextView.getText().toString().equals(mManageList.get(0).getHhy_accountm_text())) {
                viewHolder.mImageView1.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mImageView1.setVisibility(View.GONE);
            }
            viewHolder.mImageView2.setVisibility(View.GONE);
            if (mflags) {
                viewHolder.mImageView1.setVisibility(View.GONE);
                viewHolder.mImageView2.setVisibility(View.VISIBLE);
            } else {
                viewHolder.mImageView2.setVisibility(View.GONE);
                if (viewHolder.mTextView.getText().toString().equals(mManageList.get(0).getHhy_accountm_text())) {
                    viewHolder.mImageView1.setVisibility(View.VISIBLE);
                }
            }
            //添加点击事件，点击即删除某一个listview的item
            viewHolder.mImageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, position+"---123", Toast.LENGTH_SHORT).show();
                    String uname1 = mManageList.remove(position).getHhy_accountm_text();
                    Toast.makeText(mContext, uname1+"adapter", Toast.LENGTH_SHORT).show();
                    map = (Map<String, String>) sharedPreferences.getAll();
                    if (i == 0) {
                        i = map.size();
                    }
                    for (int j = 0; j < i; j++) {
                        uname2 = sharedPreferences.getString("uname" + j, "");
                        if(uname2.equals(uname1)){
                            Toast.makeText(mContext, uname2+"adapter2", Toast.LENGTH_SHORT).show();
                            mEditor.remove("uname" + j);
                            mEditor.remove("url"+j);
                            mEditor.remove("username" + j);
                            mEditor.remove("password" + j);
                            mEditor.remove("islogin" + j);
                            mEditor.remove("flag" + j);
                            mEditor.remove("a");
                            mEditor.commit();
                        }
                    }
                    //mManageList.remove(position);
                    notifyDataSetChanged();//刷新界面

                }
            });

        } else if (getItemViewType(position) == TYPE_2) {
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.hhy_item_accountmanage2, null);
                viewHolder.mSimpleDraweeView = (SimpleDraweeView) convertView.findViewById(R.id.hhy_fresco2);
                viewHolder.mTextView = (TextView) convertView.findViewById(R.id.hhy_name2);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //绑定数据
            AccountManage accountManage = mManageList.get(position);
            Uri uri = Uri.parse(accountManage.getHhy_accountm_pic_url());
            viewHolder.mSimpleDraweeView.setImageURI(uri);
            viewHolder.mTextView.setText(accountManage.getHhy_accountm_text());

        } else if(getItemViewType(position) == TYPE_3){
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.hhy_item_accountmanage3, null);
                viewHolder.mTextView = (TextView) convertView.findViewById(R.id.hhy_exit_name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //绑定数据，此处数据是写死的
            AccountManage accountManage = mManageList.get(position);
            viewHolder.mTextView.setText(accountManage.getHhy_accountm_text());

            RelativeLayout relativeLayout = (RelativeLayout) convertView.findViewById(R.id.hhy_manage_exit_relative);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,LoginActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }

        return convertView;
    }

}
