package com.jqs.lookbook.dialog;

/**
 * Created by qiangsheng on 2016/4/20.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.yanghuan.R;

public class AboutDialog extends Dialog {       //dialog对话框

    public AboutDialog(Context context) {
        super(context);
    }

    private TextView aboutTv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_dialog);

        aboutTv = (TextView) findViewById(R.id.about_tv);
    }

    public void setAboutTv(String str){
        aboutTv.setText(str);
    }
}

