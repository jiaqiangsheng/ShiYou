package com.hhy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hhy.bean.Config;
<<<<<<< HEAD
=======
import com.jqs.servert.utils.MyApplication;
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
import com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by hanhongyang on 2016/5/16.
 */
public class MConfigAdapter extends BaseAdapter {
    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;
    public static final int TYPE_3 = 2;
    List<Config> mList;
    private Context mContext;
    private LayoutInflater mInflater;
    ViewHolder1 holder1;
    ViewHolder2 holder2;
    ViewHolder3 holder3;
    boolean flag = false;
    public static final String TAG = "XUTILS";
<<<<<<< HEAD
    String mPath;
=======
    String url;
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa

    public MConfigAdapter(List<Config> mList, Context context) {
        this.mList = mList;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
<<<<<<< HEAD

=======
        MyApplication myApplication = (MyApplication) mContext.getApplicationContext();
        url = myApplication.getUrlPath();
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
    }

    public static final int TYPE_COUNT = 3;

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getFlag();
    }

    @Override
    public int getViewTypeCount() {

        return TYPE_COUNT;
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
        ImageView left_pic;
        ImageView right_pic;
        TextView mTextView;
    }

    class ViewHolder2 {
        TextView mTextView1;
        TextView mTextView2;
        ImageView pic;

    }

    class ViewHolder3 {
        TextView mTextView;
        ImageView pic;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);
        if (type == TYPE_1) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.hhy_item_config1, null);
                holder1 = new ViewHolder1();
                holder1.left_pic = (ImageView) convertView.findViewById(R.id.left_image);
                holder1.right_pic = (ImageView) convertView.findViewById(R.id.right_image);
                holder1.mTextView = (TextView) convertView.findViewById(R.id.config_textview);
                convertView.setTag(holder1);
            } else {
                holder1 = (ViewHolder1) convertView.getTag();
            }
            //绑定数据,数据是写死的

        } else if (type == TYPE_2) {
            if (convertView == null) {
                holder2 = new ViewHolder2();
                convertView = mInflater.inflate(R.layout.hhy_item_config2, null);
                holder2.mTextView1 = (TextView) convertView
                        .findViewById(R.id.config_textview);
                holder2.mTextView2 = (TextView) convertView
                        .findViewById(R.id.bottom_text);
                holder2.pic = (ImageView) convertView.findViewById(R.id.hhy_right_image);
                convertView.setTag(holder2);
            } else {
                holder2 = (ViewHolder2) convertView.getTag();
            }

            //
            holder2.pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag){
                        holder2.pic.setImageResource(R.drawable.butn_close);
                        flag = false;
                    }else{
                        holder2.pic.setImageResource(R.drawable.butn_open);
                        flag = true;//表示推送消息按钮打开
                        getTest(flag);
                    }
                }
            });

        } else {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.hhy_item_config3, null);
                holder3 = new ViewHolder3();
                holder3.mTextView = (TextView) convertView
                        .findViewById(R.id.config_textview);
                holder3.pic = (ImageView) convertView
                        .findViewById(R.id.right_image);
                convertView.setTag(holder3);
            } else {
                holder3 = (ViewHolder3) convertView.getTag();
            }
            //绑定数据
            Config config = mList.get(position);
            holder3.mTextView.setText(config.getTextview());

        }
        return convertView;
    }

    private void getTest(boolean flag) {
        //POST请求
        //第一步：设置访问路径以及携带数据
        if(flag){
<<<<<<< HEAD
            mPath = "http://10.201.1.148:8888/HttpServer/HttpServer";
            RequestParams params = new RequestParams(mPath);
=======
            RequestParams params = new RequestParams(url);
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
            params.addQueryStringParameter("fla","1");//0：代表推送消息关闭，1：代表推送消息打开
            //第二步：开始请求，设置请求方式，同时实现回调函数
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    //访问成功，参数result其实就是PrintWriter写回的值
                    //把JSON格式的字符串改为Student对象
                    //显示服务器发来的信息
                    Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    //访问失败
                    Log.e(TAG, "错误原因：" + ex.getMessage());
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    //访问取消
                }

                @Override
                public void onFinished() {
                    //访问完成
                }
            });
        }

    }


}

