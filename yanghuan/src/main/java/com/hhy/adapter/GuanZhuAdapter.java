package com.hhy.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
import com.hhy.activity.SearchActivity;
import com.hhy.bean.GuanZhu;
import com.hhy.bean.ImageViewDemo;
=======
import com.LoginUser;
import com.hhy.activity.SearchActivity;
import com.hhy.bean.GuanZhu;
import com.hhy.bean.ImageViewDemo;
import com.jqs.servert.utils.MyApplication;
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
import com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanhongyang on 2016/5/16.
 */
public class GuanZhuAdapter extends BaseAdapter {
    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;
    public static final int TYPE_3 = 2;
<<<<<<< HEAD
=======
    public static final int TYPE_4 = 3;
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
    List<GuanZhu> mList;
    private Context mContext;
    private LayoutInflater mInflater;
    RelativeLayout relativeLayout;
    //为搜索框edittext设置动态高度
    RelativeLayout.LayoutParams layoutParams;
    ViewHolder3 holder3;
    ViewHolder1 holder1;
    ViewHolder2 holder2;
    int id;
<<<<<<< HEAD
=======
    String url;
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa

    private List<ViewHolder3> holders = new ArrayList<ViewHolder3>();//用于存放不同item行的viewHoder

    public GuanZhuAdapter(List<GuanZhu> mList, Context context, RelativeLayout relativeLayout) {
        this.mList = mList;
        mContext = context;
        this.relativeLayout = relativeLayout;
        mInflater = LayoutInflater.from(mContext);
<<<<<<< HEAD

=======
        MyApplication myApplication = (MyApplication) mContext.getApplicationContext();
        url = myApplication.getUrlPath();
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
    }

    @Override
    public boolean isEnabled(int position) {
        return mList.get(position).isB();
    }

<<<<<<< HEAD
    public static final int TYPE_COUNT = 3;
=======
    public static final int TYPE_COUNT = 4;
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getFlag();

    }


    @Override
    public int getViewTypeCount() {

<<<<<<< HEAD
        return 3;
=======
        return TYPE_COUNT;
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
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
        TextView mTextView;
    }

    class ViewHolder2 {
        TextView mTextView;
    }

    class ViewHolder3 {
        ImageViewDemo mSimpleDraweeView;
        TextView mTextView1;
        TextView mTextView2;
        ImageView pic;
        boolean flag = true;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (getItemViewType(position) == TYPE_1) {

            convertView = mInflater.inflate(R.layout.hhy_item_guanzhu_sousuo, null);
            holder1 = new ViewHolder1();
            holder1.mEditText = (EditText) convertView.findViewById(R.id.hhy_guanzhu_editview);
            holder1.mTextView = (TextView) convertView.findViewById(R.id.quxiao);
            //默认R.id.quxiao控件为不可见,并不占空间
            holder1.mTextView.setVisibility(View.GONE);

            holder1.mEditText.setClickable(true);
            //通过获取和失去焦点做相关处理
            holder1.mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {//获取焦点
                      /*  relativeLayout.setVisibility(View.GONE);
                        int newWidth = 680;
                        layoutParams = (RelativeLayout.LayoutParams) holder1.mEditText.getLayoutParams();
                        layoutParams.width = newWidth;
                        holder1.mEditText.setLayoutParams(layoutParams);
                        //设置R.id.quxiao控件可见
                        holder1.mTextView.setVisibility(View.VISIBLE);*/
                        Intent intent = new Intent(mContext, SearchActivity.class);
                        mContext.startActivity(intent);
                    }
                }
            });
           /* holder1.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    relativeLayout.setVisibility(View.VISIBLE);
                    int newWidth = 780;
                    layoutParams = (RelativeLayout.LayoutParams) holder1.mEditText.getLayoutParams();
                    layoutParams.width = newWidth;
                    holder1.mEditText.setLayoutParams(layoutParams);
                    //holder1.mEditText.layout(0,55,0,0);
                    //设置R.id.quxiao控件不可见
                    holder1.mTextView.setVisibility(View.GONE);
                }
            });*/

            //绑定数据，此处不用，数据是在布局中写死的

        } else if (getItemViewType(position) == TYPE_2) {

            convertView = mInflater.inflate(R.layout.hhy_item_guanzhu_quanbu, null);
            holder2 = new ViewHolder2();
            holder2.mTextView = (TextView) convertView
                    .findViewById(R.id.guanzhu_quanbu);


            //绑定数据，此处不用，数据实在布局中写死的
        } else if (getItemViewType(position) == TYPE_3) {

            convertView = mInflater.inflate(R.layout.hhy_item_guanzhu_list, null);
            holder3 = new ViewHolder3();
            holder3.mSimpleDraweeView = (ImageViewDemo) convertView
                    .findViewById(R.id.simpleDraweeView);
            holder3.mTextView1 = (TextView) convertView
                    .findViewById(R.id.guanzhu_name);
            holder3.mTextView2 = (TextView) convertView
                    .findViewById(R.id.guanzhu_bianqian);
            holder3.pic = (ImageView) convertView.findViewById(R.id.guanzhu);
            //***
            holder3.pic.setTag(position + "");//用于区分各个item行的右边那个图片
            holders.add(holder3);


            //绑定数据
            GuanZhu guanZhu = mList.get(position);
            Toast.makeText(mContext, guanZhu.getMflag()+"adapter", Toast.LENGTH_SHORT).show();
            holder3.mSimpleDraweeView.setImageResource(R.drawable.icon_uzao);
            holder3.mTextView1.setText(mList.get((position)).getGuanzhu_top_text());
            holder3.mTextView2.setText(mList.get((position)).getGuanzhu_below_text());
            if(guanZhu.getMflag() == 1){
                //表示在粉丝表中用户也关注了他的粉丝
                holder3.pic.setImageResource(R.drawable.card_icon_arrow);
            }else{
                holder3.pic.setImageResource(R.drawable.card_icon_attention);
            }
            holder3.pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = Integer.parseInt(v.getTag().toString());
                    index = index - 2;//因为最开始是从2开始的，不是从0
                    Toast.makeText(mContext, index + "", Toast.LENGTH_SHORT).show();
                    holder3 = holders.get(index);

                    if (holder3.flag == true) {
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                        mBuilder.setMessage("确定不再关注此人？")
                                .setNegativeButton("取消", null)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Toast.makeText(mContext,index+"",Toast.LENGTH_SHORT).show();
                                        holder3.pic.setImageResource(R.drawable.card_icon_addattention);
                                        //得到该行的uid
                                        int id = mList.get(position).getMuid();
                                        //从数据库删除不再关注的人
                                       setDataBase("deleteMcare",id,0);

                                    }
                                });
                        AlertDialog dialog = mBuilder.create();
                        dialog.show();
                        holder3.flag = false;
                    } else {
                        holder3.pic.setImageResource(R.drawable.card_icon_attention);
                        //得到该行的uid
                        id = mList.get(position).getMuid();
                        //先从粉丝表中查询一下是不是我的粉丝
                        selectDataBase(1,id);
                        holder3.flag = true;
                    }
                }

            });
<<<<<<< HEAD
=======
        }else if(getItemViewType(position) == TYPE_4) {
            convertView = mInflater.inflate(R.layout.hhy_item_guanzhu_4, null);
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
        }
        return convertView;
    }

    private void setDataBase(String biaozhi,int muid,int mflag) {
        //当不再关注某人时，从数据库将此人删除
<<<<<<< HEAD
        String url = "http://10.201.1.148:8888/HttpServer/HttpServer";
        RequestParams params = new RequestParams(url);
        if("deleteMcare".equals(biaozhi)){
            params.addBodyParameter("deleteMcare1",1+"");
            params.addBodyParameter("deleteMcare",muid+"");
        }else if("insertMcare".equals(biaozhi)){
            //还必须得到我的uid，一并插入，这里先假定我的uid是1（因为没有和登陆连起来，所以无法动态获得uid）
            params.addBodyParameter("insertMcare1",1+"");
=======
        RequestParams params = new RequestParams(url);
        if("deleteMcare".equals(biaozhi)){
            params.addBodyParameter("deleteMcare1", LoginUser.userid+"");
            params.addBodyParameter("deleteMcare",muid+"");
        }else if("insertMcare".equals(biaozhi)){
            //还必须得到我的uid，一并插入，这里先假定我的uid是1（因为没有和登陆连起来，所以无法动态获得uid）
            params.addBodyParameter("insertMcare1",LoginUser.userid+"");
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
            params.addBodyParameter("insertMcare",muid+"");
            params.addBodyParameter("mflag",mflag+"");
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(mContext,result, Toast.LENGTH_SHORT).show();
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

    private void  selectDataBase(int myuid,int muid) {
<<<<<<< HEAD
        String url = "http://10.201.1.148:8888/HttpServer/HttpServer";
        RequestParams params = new RequestParams(url);
        //还必须得到我的uid，一并插入，这里先假定我的uid是1（因为没有和登陆连起来，所以无法动态获得uid）
        params.addBodyParameter("userd",1+"");
=======
        RequestParams params = new RequestParams(url);
        //还必须得到我的uid，一并插入，这里先假定我的uid是1（因为没有和登陆连起来，所以无法动态获得uid）
        params.addBodyParameter("userd",LoginUser.userid+"");
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
        params.addBodyParameter("fan",muid+"");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if("true".equals(result)){
                    //是我的粉丝
                    setDataBase("insertMcare",id,1);
                }else{
                    //不是我的粉丝
                    setDataBase("insertMcare",id,0);
                }
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

