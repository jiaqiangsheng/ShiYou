package com.lxx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.LoginUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hhy.activity.AddAccountActivity;
import com.lxx.Adapter.FirstGradeAdapter;
import com.lxx.Adapter.GridViewAdapter;
import com.lxx.Utils.ActivityForResultUtil;
import com.lxx.bean.CommentBean;
import com.lxx.bean.Firstgrade_Review;
import com.lxx.bean.Lxx_BeanContext;
import com.lxx.bean.ReplyBean;
import com.lxx.bean.Secondgrade_Review;
import com.lxx.bean.SelectCommentBean;
import com.lxx.myinterface.MyCallback;
import com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Dynamic_Details extends AppCompatActivity {
    boolean flag=false;//是否为回复二级评论
    int localchildposition=-1;
    int localparentposition=-1;

    int Commentid;//一级评论的id
    int commentuid;//一级评论人的id

    List<SelectCommentBean> list;//取得到的评论集合
    View mScrollView;
    String userimageurl;
    String username;
    String data;
    String context;
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
        getJsonData();

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
                Commentid=listfirst.get(position).getCid();
                Toast.makeText(Dynamic_Details.this, "Commentid"+Commentid, Toast.LENGTH_SHORT).show();
                commentuid=listfirst.get(position).getCuid();
                Toast.makeText(Dynamic_Details.this, "Commentid"+commentuid, Toast.LENGTH_SHORT).show();
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
        datatextview.setText(data);
        contextTextview.setText(context);


    }

    public void snedpinglun(View view) {
        final String pinglunString=pingluncontext.getText().toString();
        secondlist=new ArrayList<>();
        pingluncontext.requestFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm. toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        if(sendname==null && flag==false){
            //插入一级评论
             CommentBean commentBean=new CommentBean(contextBean.getCid(), contextBean.getUseruid(),LoginUser.userid,pinglunString);
           // Log.e("李萧萧", "" + "执行" + contextBean.getCid());
            RequestParams params = new RequestParams(ActivityForResultUtil.insertComment);
            Gson gson = new Gson();
            String result=gson.toJson(commentBean);
            params.addQueryStringParameter("result", result);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    int i=Integer.parseInt(result);
                    Firstgrade_Review firstgradeReview1;
                    Toast.makeText(Dynamic_Details.this, "李萧萧"+i+"", Toast.LENGTH_SHORT).show();
                    firstgradeReview1=new Firstgrade_Review(contextBean.getCid(),LoginUser.userimageUrl,LoginUser.username,"5/26/2016",pinglunString,secondlist,i);
                    listfirst.add(firstgradeReview1);
                    myFirstAdapter.notifyDataSetChanged();
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.e("李萧萧", "456");
                }

                @Override
                public void onCancelled(CancelledException cex) {
                }

                @Override
                public void onFinished() {
                }
            });

        }
        else if(sendname!=null&& flag==false){
            //插入二级评论的第一条评论
            secondgradeReview=new Secondgrade_Review(LoginUser.userid,Commentid,commentuid,LoginUser.username,sendname,pinglunString,0);
            listfirst.get(sendposition).getSecondlist().add(secondgradeReview);
            Toast.makeText(Dynamic_Details.this, "sendposition" + listfirst.get(sendposition).getSecondlist().get(0).getSecondcontext(), Toast.LENGTH_SHORT).show();
            sendname=null;
            sendposition=-1;


            ReplyBean replyBean=new ReplyBean(LoginUser.userid,commentuid,Commentid,pinglunString,"");
            //插入到数据库 表reply
            RequestParams params = new RequestParams(ActivityForResultUtil.insertReply);
            Gson gson = new Gson();
            String result=gson.toJson(replyBean);
            params.addQueryStringParameter("result", result);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.e("李萧萧", "" + "执行" + contextBean.getCid());
                    //访问成功，参数其实就是PrintWriter写回的值
                    //把JSON格式的字符串改为Student对象
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<SelectCommentBean>>() {
                    }.getType();
                    ReplyBean replyBean=new ReplyBean(LoginUser.userid,commentuid,Commentid,pinglunString,"");

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.e("李萧萧", "456");
                }

                @Override
                public void onCancelled(CancelledException cex) {
                }

                @Override
                public void onFinished() {
                }
            });

        }
        else if (flag==true){
            //二级评论间插入
            secondgradeReview=new Secondgrade_Review(0,0,0,LoginUser.username,listfirst.get(localparentposition).getSecondlist().get(localchildposition).getUsername1(),pinglunString,0);
            listfirst.get(localparentposition).getSecondlist().add(localchildposition+1, secondgradeReview);
            flag=false;

            commentuid=listfirst.get(localparentposition).getSecondlist().get(localchildposition).getRuid();
            Commentid=listfirst.get(localparentposition).getSecondlist().get(localchildposition).getRcid();
            ReplyBean replyBean=new ReplyBean(LoginUser.userid,commentuid,Commentid,pinglunString,"");
            //插入到数据库 表reply
            RequestParams params = new RequestParams(ActivityForResultUtil.insertReply);
            Gson gson = new Gson();
            String result=gson.toJson(replyBean);
            params.addQueryStringParameter("result", result);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.e("李萧萧", "" + "执行" + contextBean.getCid());
                    //访问成功，参数其实就是PrintWriter写回的值
                    //把JSON格式的字符串改为Student对象
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<SelectCommentBean>>() {
                    }.getType();
                    ReplyBean replyBean=new ReplyBean(LoginUser.userid,commentuid,Commentid,pinglunString,"");

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.e("李萧萧", "456");
                }

                @Override
                public void onCancelled(CancelledException cex) {
                }

                @Override
                public void onFinished() {
                }
            });


        }
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
    private void getJsonData() {
        list=new ArrayList<>();
        Log.e("李萧萧", "" + "执行" + contextBean.getCid());
        RequestParams params = new RequestParams(ActivityForResultUtil.SelectComment);
        params.addQueryStringParameter("result",""+contextBean.getCid() );
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("李萧萧", "" + "执行" + result);
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象

                Gson gson = new Gson();
                Type type = new TypeToken<List<SelectCommentBean>>() {
                }.getType();
                list = gson.fromJson(result.toString(), type);
                for (int j = 0; j < list.size(); j++) {
                    secondlist=new ArrayList<Secondgrade_Review>();
                    SelectCommentBean selectCommentBean=list.get(j);
                    for (int i = 0; i < selectCommentBean.getListSelectReplyBeans().size(); i++) {
                        secondgradeReview = new Secondgrade_Review(LoginUser.userid,selectCommentBean.getCuid(),selectCommentBean.getCid(),contextBean.getUser(), selectCommentBean.getUname(), selectCommentBean.getListSelectReplyBeans().get(i).getCcontext(),selectCommentBean.getCpid());
                        secondlist.add(secondgradeReview);
                    }
                    firstgradeReview = new Firstgrade_Review(selectCommentBean.getCid(), selectCommentBean.getUpicture(), selectCommentBean.getUname(), selectCommentBean.getCdata(), selectCommentBean.getCcontext(), secondlist,selectCommentBean.getCuid());
                    listfirst.add(firstgradeReview);
                    myFirstAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("李萧萧", "456");
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });

    }

}
