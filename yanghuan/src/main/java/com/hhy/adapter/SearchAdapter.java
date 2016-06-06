package com.hhy.adapter;

import android.content.Context;

import com.hhy.bean.SearchBean;
import com.hhy.receiver.CommonAdapter;
import com.hhy.receiver.ViewHolder;
import com.yanghuan.R;

import java.util.List;

/**
 * Created by yetwish on 2015-05-11
 */

public class SearchAdapter extends CommonAdapter<SearchBean> {

    public SearchAdapter(Context context, List<SearchBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        holder.setImageResource(R.id.item_search_iv_icon,R.drawable.icon_xiang)
                .setText(R.id.item_search_tv_title,mData.get(position).getTitle())
                .setText(R.id.item_search_tv_content,mData.get(position).getContent());
    }
}
