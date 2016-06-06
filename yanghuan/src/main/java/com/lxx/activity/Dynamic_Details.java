package com.lxx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.lxx.Adapter.FirstGradeAdapter;
import com.lxx.Adapter.GridViewAdapter;
import com.lxx.bean.Firstgrade_Review;
import com.lxx.bean.Lxx_BeanContext;
import com.lxx.bean.Secondgrade_Review;
import com.lxx.myinterface.MyCallback;
import com.yanghuan.R;

import java.util.ArrayList;
import java.util.List;

public class Dynamic_Details extends AppCompatActivity {
    boolean flag=false;//是否为回复二级评论
    int localchildposition=-1;
    int localparentposition=-1;
    View mScrollView;
    String userimageurl;
    String username;
    String data;
    String context;
    String zan;
    String pinglun;
    FirstGradeAdapter myFirstAdapter;
    Lxx_BeanContext contextBean;
    ImageView pinglunimag;
    ImageView userimageview;
    TextView usernametextview;
    TextView datatextview;
    TextView contextTextview;
    GridViewAdapter gridviewAdapter;
    List<Firstgrade_Review> listfirst;
    Firstgrade_Review firstgradeReview;
    ListView listviewfirst;
    List<Secondgrade_Review> secondlist;
    List<Secondgrade_Review> secondlist2;
    Secondgrade_Review secondgradeReview;
    GridView gridview;
    EditText pingluncontext;
    String sendname=null;//回复者的名字,null表示为1集评论
    int sendposition=-1;//记录回复者的position,-1表示为一级评论
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic__details);
        initView();
        Intent intent = getIntent();
        contextBean = (Lxx_BeanContext) intent.getSerializableExtra("list");
        initdata();

        listfirst.add(firstgradeReview);
        myFirstAdapter =new FirstGradeAdapter(listfirst,Dynamic_Details.this);
        listviewfirst.setAdapter(myFirstAdapter);
        myFirstAdapter.setCallback(new MyCallback() {
            @Override
            public void Exec(int childposition,int parentposition) {
                localchildposition=childposition;
                localparentposition=parentposition;
                flag=true;
                pingluncontext.requestFocus();
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm. toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                Toast.makeText(Dynamic_Details.this, ""+flag+childposition, Toast.LENGTH_SHORT).show();
            }
        });

        listviewfirst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 sendname=listfirst.get(position).getFristUesrname();//
                 sendposition=position;
                pingluncontext.requestFocus();
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm. toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                Toast.makeText(Dynamic_Details.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        mScrollView=findViewById(R.id.scrollView);
        userimageview= (ImageView) findViewById(R.id.Lxx_contextimageView);
        usernametextview= (TextView) findViewById(R.id.lxx_nicheng);
        datatextview= (TextView) findViewById(R.id.Lxx_dataContext);
        contextTextview= (TextView) findViewById(R.id.Lxx_contextText);
        listviewfirst= (ListView) findViewById(R.id.firstlistView);
        gridview= (GridView) findViewById(R.id.first_gridview);
        pingluncontext= (EditText) findViewById(R.id.dynamicpinglun);
        pinglunimag= (ImageView) findViewById(R.id.lxx_pinglun);

    }

    private void initdata() {
        mScrollView.post(new Runnable() {
            //让scrollview跳转到顶部，必须放在runnable()方法中
            @Override
            public void run() {
                mScrollView.scrollTo(0, 0);
            }
        });
        listfirst= new ArrayList<Firstgrade_Review>();
        secondlist=new ArrayList<Secondgrade_Review>();
        int fi=contextBean.getStringImage().size();
        if(fi==1){
            gridview.setNumColumns(1);
        }
        else if(fi==2||fi==4){

            gridview.setNumColumns(2);
        }
        else {

            gridview.setNumColumns(3);
        }
        gridviewAdapter=new GridViewAdapter(Dynamic_Details.this,contextBean.getStringImage(),0);
        gridview.setAdapter(gridviewAdapter);

        userimageurl=contextBean.getUserpicture();
        username = contextBean.getUser();
        data = contextBean.getDate();
        context=contextBean.getContent();
        Toast.makeText(Dynamic_Details.this, ""+username, Toast.LENGTH_SHORT).show();
        usernametextview.setText(username);
        secondgradeReview=new Secondgrade_Review("贾强盛","李萧萧","0.0");
        secondlist.add(secondgradeReview);
        datatextview.setText(data);
        contextTextview.setText(context);
        firstgradeReview=new Firstgrade_Review("http://img1.imgtn.bdimg.com/it/u=1819655436,1406018065&fm=21&gp=0.jpg","李萧萧","5/25/2016","正文部分，一级评论内容",secondlist);

    }

    public void snedpinglun(View view) {
        String pinglunString=pingluncontext.getText().toString();
        Firstgrade_Review firstgradeReview1;
        secondlist=new ArrayList<>();
        pingluncontext.requestFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm. toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        if(sendname==null && flag==false){
            firstgradeReview1=new Firstgrade_Review("http://img1.imgtn.bdimg.com/it/u=1819655436,1406018065&fm=21&gp=0.jpg","李萧萧","5/26/2016",pinglunString,secondlist);
            listfirst.add(firstgradeReview1);

        }
        else if(sendname!=null&& flag==false){
            Toast.makeText(Dynamic_Details.this, "sendposition"+sendposition, Toast.LENGTH_SHORT).show();
            secondgradeReview=new Secondgrade_Review("我",sendname,pinglunString);
            listfirst.get(sendposition).getSecondlist().add(secondgradeReview);
            Toast.makeText(Dynamic_Details.this, "sendposition" + listfirst.get(sendposition).getSecondlist().get(0).getSecondcontext(), Toast.LENGTH_SHORT).show();
            sendname=null;
            sendposition=-1;

        }
        else if (flag==true){
            secondgradeReview=new Secondgrade_Review("我","11",pinglunString);
            listfirst.get(localparentposition).getSecondlist().add(localchildposition+1, secondgradeReview);
            flag=false;
        }
       /* List<Firstgrade_Review> listfirst2=new ArrayList<>();
        listfirst2.addAll(listfirst);
        Toast.makeText(Dynamic_Details.this, "前"+listfirst2, Toast.LENGTH_SHORT).show();
        myFirstAdapter.clearList();
        Toast.makeText(Dynamic_Details.this, "后"+listfirst2, Toast.LENGTH_SHORT).show();
        myFirstAdapter.updateList(listfirst2);*/
        myFirstAdapter.notifyDataSetChanged();





        Toast.makeText(Dynamic_Details.this, "发表成功", Toast.LENGTH_SHORT).show();
        pingluncontext.setText("");
        pingluncontext.clearFocus();
        imm.hideSoftInputFromWindow(pingluncontext.getWindowToken(),0);
        //(false);
    }

    public void pinglunfoucs(View view) {

        pingluncontext.requestFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm. toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }

}
