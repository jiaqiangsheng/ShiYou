package com.yanghuan.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yanghuan.MyApplication.WeiShiActivity;
import com.yanghuan.MyApplication.DinShiActivity;
import com.yanghuan.MyApplication.OtherActivity;
import com.yanghuan.MyApplication.ZongYiActivity;
import com.yanghuan.R;
import com.yanghuan.Beans.RecommendBean;

import java.util.List;

/**
 * Created by 杨欢 on 2016/5/14.
 */
public class RecAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    Context mContext;
    List<RecommendBean> mList;
    ViewHolder viewHolder;
    RecommendBean music;



    public RecAdapter(Context mContext, List<RecommendBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
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
        ImageView imageView1,imageView2,imageView3,imageView4,imageView5;
        TextView textView1,textView2,textView3,textView4;
        TextView title;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

    if (convertView == null) {
        convertView = mInflater.inflate(R.layout.recommend_item, null);
        viewHolder = new ViewHolder();
        viewHolder.title = (TextView) convertView.findViewById(R.id.recommend_title);
        viewHolder.imageView1 = (ImageView) convertView.findViewById(R.id.recommend_imageView1);
        viewHolder.imageView2 = (ImageView) convertView.findViewById(R.id.recommend_imageView2);
        viewHolder.imageView3 = (ImageView) convertView.findViewById(R.id.recommend_imageView3);
        viewHolder.imageView4 = (ImageView) convertView.findViewById(R.id.recommend_imageView4);


        viewHolder.textView1 = (TextView) convertView.findViewById(R.id.recommend_textView1);
        viewHolder.textView2 = (TextView) convertView.findViewById(R.id.recommend_textView2);
        viewHolder.textView3 = (TextView) convertView.findViewById(R.id.recommend__textView3);
        viewHolder.textView4 = (TextView) convertView.findViewById(R.id.recommend_textView4);
        viewHolder.imageView5= (ImageView) convertView.findViewById(R.id.recommend_rightBack);

        convertView.setTag(viewHolder);
    } else {
        viewHolder = (ViewHolder) convertView.getTag();
    }
    music = mList.get(position);


    String[] content = music.getContent();
        String[] picture = music.getImageId();
    viewHolder.title.setText(music.getTitle());
        Glide.with(mContext)
                .load(picture[0])
                .into(viewHolder.imageView1);
        Glide.with(mContext)
                .load(picture[1])
                .into(viewHolder.imageView2);
        Glide.with(mContext)
                .load(picture[2])
                .into(viewHolder.imageView3);
        Glide.with(mContext)
                .load(picture[3])
                .into(viewHolder.imageView4);

    viewHolder.imageView1.requestFocus();
    viewHolder.imageView2.requestFocus();
    viewHolder.imageView3.requestFocus();
    viewHolder.imageView4.requestFocus();
    viewHolder.textView1.setText(content[0]);
    viewHolder.textView2.setText(content[1]);
    viewHolder.textView3.setText(content[2]);
    viewHolder.textView4.setText(content[3]);
        viewHolder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        show("第一张图片");
                    case 1:
                        show("第五张图片");

                }

            }
        });
       viewHolder.imageView2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               switch (position){
                   case 0:
                       show("第二张图片");
                   case 1:
                       show("第六张图片");

               }

           }
       });
        viewHolder.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        show("第三张图片");
                    case 1:
                        show("第七张图片");

                }

            }
        });
        viewHolder.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        show("第四张图片");
                    case 1:
                        show("第八张图片");

                }

            }
        });
        viewHolder.imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(mContext,WeiShiActivity.class);
                        intent.putExtra("1","RecAdapter");
                        mContext.startActivity(intent);
                        break;
                    case 1:
                        Intent intent1=new Intent(mContext,DinShiActivity.class);
                        intent1.putExtra("2","RecAdapter");
                        mContext.startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2=new Intent(mContext, ZongYiActivity.class);
                        intent2.putExtra("2","RecAdapter");
                        mContext.startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3=new Intent(mContext, OtherActivity.class);
                        intent3.putExtra("2","RecAdapter");
                        mContext.startActivity(intent3);
                        break;


                }
            }
        });
        return convertView;
    }

 /*   private void addListener() {
        viewHolder.imageView1.setOnClickListener(this);
        viewHolder.imageView2.setOnClickListener(this);
        viewHolder.imageView3.setOnClickListener(this);
        viewHolder.imageView4.setOnClickListener(this);
    }
    private void addListener2() {
        viewHolder.imageView1.setOnClickListener(this);
        viewHolder.imageView2.setOnClickListener(this);
        viewHolder.imageView3.setOnClickListener(this);
        viewHolder.imageView4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.recommend_imageView1:
                show("第一张图片");
                break;
            case R.id.recommend_imageView2:
                show("第二张图片");
                break;
            case R.id.recommend_imageView3:
                show("第三张图片");
                break;
            case R.id.recommend_imageView4:
                show("第四张图片");
                break;
        }

    }*/
    private void show(String s) {
        Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();
    }


}
