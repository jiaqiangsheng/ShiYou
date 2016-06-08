package com.yanghuan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yanghuan.Beans.ZhuiJu;
import com.yanghuan.R;

import java.util.List;

/**
 * Created by C5-0 on 2016/6/5.
 */
public class ZhuiJuAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<ZhuiJu> list;
    private ArrayAdapter adapter2;
    public ZhuiJuAdapter(Context context, List<ZhuiJu> list) {
        this.context = context;
        this.list = list;
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
    class ViewHolder {
        ImageView imageView;
        TextView textView1,textView2,textView3;
        Spinner spinner;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.zhuiju_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.zhuiju_image);
            viewHolder.textView1= (TextView) convertView.findViewById(R.id.zhuju_text1);
            viewHolder.textView2= (TextView) convertView.findViewById(R.id.zhuiju_text2);
            viewHolder.textView3= (TextView) convertView.findViewById(R.id.zhuiju_text3);
            viewHolder.spinner= (Spinner) convertView.findViewById(R.id.zhuiju_spinner);
            viewHolder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context,"点击了："+(position+1),Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ZhuiJu ju=list.get(position);
        viewHolder.textView1.setText(ju.getText1());
        viewHolder.textView2.setText(ju.getText2());
        viewHolder.textView3.setText(ju.getText3());
        Glide.with(context)
                .load(ju.getPicture())
                .into(viewHolder.imageView);
        return convertView;
    }
}
