package com.hhy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.hhy.bean.ImageViewDemo;
import com.hhy.bean.UserInfo;
import com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by hanhongyang on 2016/5/25.
 */
public class Tianjia_PingBi_AccountAdapter extends BaseAdapter {
    List<UserInfo> mList;
    Context mContext;
    LayoutInflater mInflater;
    String mbiaozhi;
    //biaozhi:判断是pingbiFragment还是pingbiNotSeeFragment
    public Tianjia_PingBi_AccountAdapter(List<UserInfo> list, Context context,String biaozhi) {
        mList = list;
        mContext = context;
        mbiaozhi = biaozhi;
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

    class ViewHolder {
        ImageViewDemo mImageView;
        TextView mTopTextView, mBelowTextView;
        Button mButton;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.hhy_item_pingbi_tianjia_account, null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.mImageView = (ImageViewDemo) convertView.findViewById(R.id.hhy_pingbi_simpleDrawee2);
        viewHolder.mTopTextView = (TextView) convertView.findViewById(R.id.hhy_pingbi_uname);
        viewHolder.mBelowTextView = (TextView) convertView.findViewById(R.id.hhy_pingbi1_bianqian);
        viewHolder.mButton = (Button) convertView.findViewById(R.id.hhy_pingbi_btn);

        //绑定数据
        UserInfo userInfo = mList.get(position);
        viewHolder.mImageView.setImageResource(R.drawable.icon_xiang);
        viewHolder.mTopTextView.setText(userInfo.getUname());
        viewHolder.mBelowTextView.setText(userInfo.getUsign());
        final int po = position;

        viewHolder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int uid = mList.get(po).getUid();
                mList.remove(po);
                //Toast.makeText(mContext, uid + "", Toast.LENGTH_SHORT).show();
                setDtaBase(uid);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    private void setDtaBase(int uid) {
        String url = "http://10.201.1.148:8888/HttpServer/HttpServer";
        RequestParams params = new RequestParams(url);
        params.addQueryStringParameter("myuid", 1+"");
        if ("pingbita".equals(mbiaozhi)) {
            params.addQueryStringParameter("deleteMstop", uid + "");
        } else if ("notsee".equals(mbiaozhi)) {
            params.addQueryStringParameter("deleteOstop", uid + "");
        } else {
            params.addQueryStringParameter("deleteBlacklist", uid + "");
        }
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

}
