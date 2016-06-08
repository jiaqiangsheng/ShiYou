package com.yanghuan.adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yanghuan.R;
import com.yanghuan.Beans.TaskBean;

import java.util.List;

/**
 * Created by 杨欢 on 2016/5/20.
 */
public class MyTaskAdapter extends BaseAdapter{
    LayoutInflater inflater;
    List<TaskBean> list;
    Context context;
    ImageView imageView1;
    TextView textView1;
    Button title;

    //弹框

    PopupWindow popupWindow;
    int width,height;
    View view,view1;
    public MyTaskAdapter(List<TaskBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

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




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.task_item, null);
        imageView1 = (ImageView) convertView.findViewById(R.id.task_item_image1);
           textView1 = (TextView) convertView.findViewById(R.id.task_item_text1);
           title= (Button) convertView.findViewById(R.id.task_item_btn1);

        final TaskBean task1=list.get(position);
        title.setText(task1.getBtn());
        Glide.with(context)
                .load(task1.getImage())
                .into(imageView1);

        textView1.setText(task1.getText());
        if(task1.getClick()) {
            addListener();
        }
        view1=convertView;
        return convertView;

    }
    private void addListener() {
        initPop();

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(view1,500, 200);
                popupWindow.update();
            }
        });

    }
    private void initPop() {
        //inflater=LayoutInflater.from(context);
        width= LinearLayout.LayoutParams.WRAP_CONTENT;
        height= LinearLayout.LayoutParams.WRAP_CONTENT;
        view=inflater.inflate(R.layout.popwindow_item,null);
      /* view.setVisibility(8);*/
        popupWindow=new PopupWindow(view,width,height,false);
        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
       // popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);

    }
}
