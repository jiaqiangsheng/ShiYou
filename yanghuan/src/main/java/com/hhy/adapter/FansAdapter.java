package com.hhy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.LoginUser;
import com.bumptech.glide.Glide;
import com.hhy.bean.Fans;
import com.jqs.servert.utils.MyApplication;
import com.yanghuan.R;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanhongyang on 2016/5/16.
 */
public class FansAdapter extends BaseAdapter {
    public static final int TYPE_COUNT = 3;
    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;
    public static final int TYPE_3 = 2;
    List<Fans> mList;
    private List<ViewHolder2> holders = new ArrayList<ViewHolder2>();//用于存放不同item行的viewHoder
    Context mContext;
    LayoutInflater mInflater;
    //boolean flag = false;
    String url;

    ImageView mImageView;
    ViewHolder1 viewHolder1;
    ViewHolder2 viewHolder2;

    public FansAdapter(List<Fans> list, Context context) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        MyApplication myApplication = (MyApplication) mContext.getApplicationContext();
        url = myApplication.getUrlPath();
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getFlag();
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

    class ViewHolder2 {
        ImageView tou_image;
        TextView top_text;
        TextView below_text;
        ImageView right_image;
        TextView right_text;
        boolean flag = true;

    }

    class ViewHolder1 {
        TextView text;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (getItemViewType(position) == TYPE_1) {
            if (convertView == null) {
                viewHolder1 = new ViewHolder1();
                convertView = mInflater.inflate(R.layout.hhy_item_fans2, null);
                viewHolder1.text = (TextView) convertView.findViewById(R.id.hhy_fans_quanbu);
                convertView.setTag(R.string.message, viewHolder1);
            } else {
                viewHolder1 = (ViewHolder1) convertView.getTag(R.string.message);
            }


        } else if (getItemViewType(position) == TYPE_2) {
            if (convertView == null) {
                viewHolder2 = new ViewHolder2();
                convertView = mInflater.inflate(R.layout.hhy_item_fans, null);
                viewHolder2.tou_image = (ImageView) convertView.findViewById(R.id.fans_touxiang);
                viewHolder2.top_text = (TextView) convertView.findViewById(R.id.fans_top_text);
                viewHolder2.below_text = (TextView) convertView.findViewById(R.id.fans_below_text);
                viewHolder2.right_image = (ImageView) convertView.findViewById(R.id.fans_right_image);
                viewHolder2.right_text = (TextView) convertView.findViewById(R.id.fans_right_text);

                //***
                viewHolder2.right_image.setTag(position + "");//用于区分各个item行的右边那个图片
                holders.add(viewHolder2);
                convertView.setTag(R.string.app_name, viewHolder2);
            } else {
                viewHolder2 = (ViewHolder2) convertView.getTag(R.string.app_name);
            }
            //绑定数据
            final Fans fans = mList.get(position);
            Toast.makeText(mContext, fans.getFla() + "", Toast.LENGTH_SHORT).show();
            Glide.with(mContext).load(fans.getTou_imageUrl()).into(viewHolder2.tou_image);
            viewHolder2.top_text.setText(fans.getTop_text());
            viewHolder2.below_text.setText(fans.getBelow_text());

            if (fans.getFla() == 1) {
                viewHolder2.right_image.setImageResource(R.drawable.card_icon_attention);
                viewHolder2.right_text.setText("已关注");
                //insertGuanZhuDataBase(fans.get);
            } else {
                viewHolder2.right_image.setImageResource(R.drawable.card_icon_addattention);
                viewHolder2.right_text.setText("加关注");
            }
            viewHolder2.right_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = Integer.parseInt(v.getTag().toString());
                    index = index - 1;
                    viewHolder2 = holders.get(index);
                    viewHolder2.right_image.setImageResource(R.drawable.card_icon_attention);
                    viewHolder2.right_text.setText("已关注");
                    //得到该行的uid
                    int ouid = mList.get(position).getOuid();
                    Toast.makeText(mContext, ouid + "", Toast.LENGTH_SHORT).show();
                    //将选中的粉丝加入到关注表中，右边图标改为互相关注的图标
                    insertDataBase2(ouid);
                    //更新粉丝表中的flag标志
                    insertDataBase(ouid);
                }

            });
        }else if (getItemViewType(position) == TYPE_3){
            convertView = mInflater.inflate(R.layout.hhy_item_fans3, null);
        }
        return convertView;
    }

    private void insertDataBase(int ouid) {
        RequestParams params = new RequestParams(url);
        //还必须得到我的uid，一并插入，这里先假定我的uid是1（
        // 因为没有和登陆连起来，所以无法动态获得uid）
        params.addBodyParameter("userId", LoginUser.userid+ "");
        params.addBodyParameter("insertOcare", ouid + "");

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                //Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
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

    private void insertDataBase2(int ouid) {
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("useId", LoginUser.userid+ "");
        params.addBodyParameter("insertmcare", ouid + "");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                //Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
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
