package com.lxx.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lxx.activity.Dynamic_Details;
import com.lxx.activity.Pictureshow;
import com.lxx.bean.Lxx_BeanContext;
import com.yanghuan.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 潇 on 2016/5/24.
 */
public class MycontextAdapter extends BaseAdapter {
    List<Lxx_BeanContext> list;

    public MycontextAdapter(Context context, List<Lxx_BeanContext> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    LayoutInflater inflater;
    Context context;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {

        TextView username;
        TextView contex;
        TextView date;
        ImageView imageView;
        GridView gridView;
        // TextView pinglun;
        // TextView zan;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            //说明是第一次绘制整屏列表，例如1-6行
            convertView = inflater.inflate(R.layout.contextlistview,null);
            viewHolder = new ViewHolder();
            //初始化当前行布局中的所有控件
            viewHolder.gridView= (GridView) convertView.findViewById(R.id.lXX_gridView);
            viewHolder.username = (TextView) convertView.findViewById(R.id.lxx_nicheng);
            viewHolder.contex = (TextView) convertView.findViewById(R.id.Lxx_contextText);
            viewHolder.date = (TextView) convertView.findViewById(R.id.Lxx_dataContext);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.Lxx_contextimageView);
            //把当前的控件缓存到布局视图中
            convertView.setTag(viewHolder);

        } else {
            //说明开始上下滑动，后面的所有行布局采用第一次绘制时的缓存布局
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Lxx_BeanContext lxx_beanContext = list.get(position);
        viewHolder.username.setText(lxx_beanContext.getUser());
        viewHolder.contex.setText(lxx_beanContext.getContent());
        viewHolder.date.setText(lxx_beanContext.getDate());
        List<String> arrayListForEveryGridView =list.get(position).getStringImage();
        if (viewHolder.gridView != null) {

            Log.e("dsa",""+arrayListForEveryGridView.toString());
            int si= arrayListForEveryGridView.size();
            if(si==1){
                viewHolder.gridView.setNumColumns(1);
            }
            else if(si==2||si==4){

                viewHolder.gridView.setNumColumns(2);
            }
            else {

                viewHolder.gridView.setNumColumns(3);
            }
            if(si>9){
                List<String> temp=new ArrayList<>();
                int flag=si-8;
                for(int i=0;i<=8;i++){
                    temp.add(arrayListForEveryGridView.get(i));
                    Log.e("sada",""+temp.toString());
                    GridViewAdapter gridViewAdapter=new GridViewAdapter(context, temp,flag);
                    viewHolder.gridView.setAdapter(gridViewAdapter);
                }

            }
            else{
                GridViewAdapter gridViewAdapter=new GridViewAdapter(context, arrayListForEveryGridView,0);
                viewHolder.gridView.setAdapter(gridViewAdapter);
            }
            viewHolder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                    Toast.makeText(context, ""+list.size(), Toast.LENGTH_SHORT).show();
                   if(list.get(position).getStringImage().size()<=9){
                       Intent intent=new Intent(context,Pictureshow.class);
                       intent.putExtra("stringurl", list.get(position).getStringImage().get(position1));

                       context.startActivity(intent);
                   }
                    else{
                       if(position1==8){
                           Intent intent=new Intent(context,Dynamic_Details.class);
                           intent.putExtra("list", list.get(position));
                           context.startActivity(intent);
                       }
                       else {
                           Intent intent=new Intent(context,Pictureshow.class);
                           intent.putExtra("stringurl", list.get(position).getStringImage().get(position1));

                           context.startActivity(intent);
                       }
                       }

                    Toast.makeText(context, ""+position1, Toast.LENGTH_SHORT).show();
                }
            });

        }
        downLoadImage(viewHolder.imageView, lxx_beanContext.getUserpicture());

        return convertView;
    }
    public  void  downLoadImage( ImageView imageView,String url){
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                .setRadius(DensityUtil.dip2px(5))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                        //设置加载过程中的图片
                //.setLoadingDrawableId(R.drawable.aio_image_default_round)
                        //设置加载失败后的图片
                //.setFailureDrawableId(R.drawable.aio_image_fail_round)
                        //设置使用缓存
                .setUseMemCache(true)
                        //设置支持gif
                .setIgnoreGif(false)
                        //设置显示圆形图片
                .setCircular(false)
                .setSquare(true)
                .build();
        x.image().bind(imageView, url, imageOptions);
    }
}
