package com.lxx.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lxx.Utils.GetToken;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.yanghuan.R;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class UpPicture extends AppCompatActivity {
    private static final String TAG = "MyText";
    GetToken mGetToken;
    List<String> imageListString;
    String token;
    private Uri uri;
    String urlPath="http://o6nj6n5ea.bkt.clouddn.com";
    String path;
    //你创建的空间名
    String name = "name";


    ImageView mImageView;
    TextView mTextView;
    int id = 123;
    String imageName;
    //记录进度
    double per;
    //判断是否传完
    boolean isPaused=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uppicture);
        ininview();
    }
    //点击打开图库
    private void ininview() {
        imageListString=new ArrayList<>();
        mImageView = (ImageView) findViewById(R.id.uppictureimageView);
        mTextView= (TextView) findViewById(R.id.textview);
        mImageView.setImageResource(R.drawable.ic_fenxiang);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
    }

    public void open(View view) {
        //还原进度
        isPaused=false;
        if(path!=null) {
            //简单上传
            upload();

            //记录上传进度
            //upload2();
            mHandler.sendEmptyMessage(1);

            //setImage();
        }else{
            Toast.makeText(UpPicture.this,"没找到图片",Toast.LENGTH_LONG).show();
        }




    }
    //http://o6nj6n5ea.bkt.clouddn.com/123/1463041126076.jpg
    public  void setImage(){
        Glide.with(this)
                .load(urlPath+"/"+imageName)
                .into(mImageView);
        // Log.e("imagename",imageName+"");
    }
    public  void setText(){
        //保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        mTextView.setText(df.format(per)+"%");
    }
    /*-------------------------------------简单上传-----------------------------------------*/
    public void upload() {
        UploadManager uploadManager = new UploadManager();
        mGetToken = new GetToken();
        token = mGetToken.getToken(name);
        imageName = id + "/" + System.currentTimeMillis() + ".jpg";
        uploadManager.put(path, imageName, token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info,
                                         JSONObject response) {
                        //Log.e("ppp", "000"+imageName+"");
                        if (info.statusCode == 200) {
                            Log.e(TAG, "" + info.isOK());
                            setImage();
                            Toast.makeText(getApplication(), "完成上传", Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(getApplication(), info.statusCode + "上传失败", Toast.LENGTH_LONG).show();
                        }
                    }
                }, null);

    }



    /*--------------------------记录上传进度--------------------------------------*/

    private Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    if (!isPaused){
                        setText();
                        mHandler.sendEmptyMessageDelayed(1,500);
                    }else{
                        setText();
                    }
                    break;
                case 0:
                    break;
            }
        }
    };

    public void upload2(){
        UploadManager uploadManager = new UploadManager();
        mGetToken = new GetToken();
        token = mGetToken.getToken(name);
        imageName = id + "/" + System.currentTimeMillis() + ".jpg";
        uploadManager.put(path, imageName, token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info,
                                         JSONObject response) {
                        //Log.e("ppp", "000"+imageName+"");
                        if (info.statusCode == 200) {
                            Log.e(TAG, "" + info.isOK());
                            setImage();
                            Toast.makeText(getApplication(), "完成上传", Toast.LENGTH_LONG).show();
                        } else {

                            Toast.makeText(getApplication(), info.statusCode + "上传失败", Toast.LENGTH_LONG).show();
                        }
                    }
                    //记录上传进度
                }, new UploadOptions(null, null, false,
                        new UpProgressHandler(){
                            public void progress(String key, double percent){
                                Log.i("qiniu", key+"进度: " + percent);
                                per=percent;
                                if(per==1.0){
                                    isPaused=true;
                                }
                            }
                        }, null));

    }

    /*---------------------------------图库返回值--------------------------------------------*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                uri = data.getData();
                //这里开始的第二部分，获取图片的存储路径：
                String[] proj = {MediaStore.Images.Media.DATA};
                //好像是android多媒体数据库的封装接口，具体的看Android文档
                Cursor cursor = managedQuery(uri, proj, null, null, null);
                //按我个人理解 这个是获得用户选择的图片的索引值
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                //将光标移至开头 ，这个很重要，不小心很容易引起越界
                cursor.moveToFirst();
                //最后根据索引值获取图片路径
                path = cursor.getString(column_index);
                Log.e("path","="+path);
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

}
