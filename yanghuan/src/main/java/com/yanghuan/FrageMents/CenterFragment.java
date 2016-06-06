package com.yanghuan.FrageMents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanghuan.R;


/**
 * Created by mhdong on 2016/4/29.
 */
public class CenterFragment extends Fragment{

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view=inflater.inflate(R.layout.center,null);

        return view;
    }
}
