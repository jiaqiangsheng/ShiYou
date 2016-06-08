package com.lxx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxx.Adapter.MynewsAdapter;
import com.lxx.bean.News;
import com.yanghuan.R;

import java.util.List;

public class NewsDtails extends AppCompatActivity {
    TextView textViewtitle;

    TextView textViewContext;
    ImageView dtailsimage;
    MynewsAdapter myAdapter;
    List<News> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_dtails);
        initView();
    }

    private void initView() {
        textViewtitle= (TextView) findViewById(R.id.dtailstitle);
        textViewContext= (TextView) findViewById(R.id.dtailscontex);
        dtailsimage= (ImageView) findViewById(R.id.dtailsimageView);
        myAdapter=new MynewsAdapter();

        int position= (int) this.getIntent().getSerializableExtra("position");
        List<News> list = (List<News>)this.getIntent().getSerializableExtra("getallLIst");
        textViewContext.setText(list.get(position).getAllList().toString());
        textViewtitle.setText(list.get(position).getTitle());
        myAdapter.downLoadImage(dtailsimage,list.get(position).getImageList().get(0).getImageUrl());


    }
}
