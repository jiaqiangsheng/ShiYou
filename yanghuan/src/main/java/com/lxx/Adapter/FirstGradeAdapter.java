package com.lxx.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lxx.bean.Firstgrade_Review;
import com.lxx.myinterface.MyCallback;
import com.lxx.myview.MyListView;
import com.yanghuan.R;

<<<<<<< HEAD
import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

=======
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
import java.util.List;

/**
 * Created by 潇 on 2016/5/25.
 */
public class FirstGradeAdapter extends BaseAdapter {
    List<Firstgrade_Review> list_first;
    LayoutInflater inflater;
    Context context;
    ListView list_second;
    EditText contextEditext;
    private MyCallback callback;
    public void setCallback(MyCallback callback){
        this.callback=callback;

    }

    public FirstGradeAdapter(List<Firstgrade_Review> list_first, Context context) {
        this.list_first = list_first;
        this.context = context;
        inflater = LayoutInflater.from(context);


    }
    public void clearList() {
        this.list_first.clear();
    }

    public void updateList(List<Firstgrade_Review> commentList) {
        this.list_first.addAll(commentList);
        Log.e("list_first",list_first.toString());
    }
    @Override
    public int getCount() {
        return list_first.size();
    }

    @Override
    public Object getItem(int position) {
        return list_first.get(position);
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
        MyListView secondlistview;
        // TextView pinglun;
        // TextView zan;
    }
    @Override
    public View getView(int position1, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final int positiontemp=position1;
        if (convertView == null) {
            //说明是第一次绘制整屏列表，例如1-6行
            convertView = inflater.inflate(R.layout.firstgradeview, null);
            viewHolder = new ViewHolder();
            //初始化当前行布局中的所有控件
            viewHolder.gridView= (GridView) convertView.findViewById(R.id.lXX_gridView);
            viewHolder.username = (TextView) convertView.findViewById(R.id.firstGradeName);
            viewHolder.contex = (TextView) convertView.findViewById(R.id.firstGradecontext);
            viewHolder.date = (TextView) convertView.findViewById(R.id.firstGradedata);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.Lxx_contextimageView);
            //把当前的控件缓存到布局视图中
            viewHolder.secondlistview= (MyListView) convertView.findViewById(R.id.secondlistView);
            list_second= (ListView) convertView.findViewById(R.id.secondlistView);
            convertView.setTag(viewHolder);
            contextEditext= (EditText) convertView.findViewById(R.id.dynamicpinglun);

        } else {
            //说明开始上下滑动，后面的所有行布局采用第一次绘制时的缓存布局
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Firstgrade_Review firstgradeReview = list_first.get(position1);

        viewHolder.username.setText(firstgradeReview.getFristUesrname());
        viewHolder.contex.setText(firstgradeReview.getFirstContext());
        viewHolder.date.setText(firstgradeReview.getFirstdate());
<<<<<<< HEAD
        downLoadImage(viewHolder.imageView,firstgradeReview.getFeisturlUserImage());
=======
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
       // list_second.setAdapter(null);//每次对子listview进行清空，否则会复用上次的listview

        list_second.setAdapter(new SecondGradeAdapter(firstgradeReview.getSecondlist(), context));
        list_second.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, ""+positiontemp+","+position, Toast.LENGTH_SHORT).show();
                callback.Exec(position,positiontemp);
              //  Secondgrade_Review secondReview;
                //secondReview=new Secondgrade_Review("name1","name2",contextEditext.getText().toString());
               // list_first.get(positiontemp).getSecondlist().add(position,secondReview);
            }
        });

        return convertView;
    }
<<<<<<< HEAD
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
=======
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
}
