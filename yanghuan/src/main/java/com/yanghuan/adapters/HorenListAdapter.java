package com.yanghuan.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yanghuan.Beans.Picture;
import com.yanghuan.R;

import java.util.List;

/**
 * Created by 杨欢 on 2016/5/24.
 */
public class HorenListAdapter extends BaseAdapter {
    private Context mContext ;
    private List<Picture> mList;
    LayoutInflater inflater;

    public HorenListAdapter(Context mContext, List<Picture> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater=LayoutInflater.from(mContext);
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView = null;
        View view  = convertView;

        if(view == null ){
            holderView = new HolderView();
            view =inflater.inflate(R.layout.match_league_round_item,null);
           holderView.title= (TextView) view.findViewById(R.id.tv_tip);
            holderView.imageView =(ImageView) view.findViewById(R.id.imageView);
            holderView.textView = (TextView) view.findViewById(R.id.textView);

            view.setTag(holderView);
        }else{
            holderView = (HolderView) view.getTag();
        }
        final Picture pic = mList.get(position);
        holderView.title.setText(pic.getTitle());
        Glide.with(mContext)
                .load(pic.getPic())
                .into(holderView.imageView);
        holderView.textView.setText(pic.getName());
        return view;
    }

    class HolderView{
        ImageView imageView;
        TextView textView,title;
    }
}


