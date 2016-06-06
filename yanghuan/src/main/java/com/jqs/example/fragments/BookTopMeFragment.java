package com.jqs.example.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yanghuan.R;


/**
 * Created by qiangsheng on 2016/5/19.
 */
public class BookTopMeFragment extends Fragment {
    ImageView mImageView;
    View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.book_me_top,null);
        mImageView= (ImageView) mView.findViewById(R.id.me_rec_back);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return mView;
    }
}
