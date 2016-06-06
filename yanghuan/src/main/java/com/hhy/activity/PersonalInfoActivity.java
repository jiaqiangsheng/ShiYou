package com.hhy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxx.Utils.GetToken;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.yanghuan.R;

import org.json.JSONObject;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int CAMERA = 1;
    TextView mCancleText, mBianjiText;
    RelativeLayout mTouXiangRelative, mNiChengRelative, mStarRelative, mStarZhanKaiRelative;
    RelativeLayout mTakephotoRelative, mFromPicture, mCancle;
    RadioGroup mRadioGroup;
    RadioButton mWomanRadioButton, mManRadioButton;
    ImageView mstarIMageView;
    SimpleDraweeView mSimpleDraweeView;
    TextView mStarText, mNiChengText;
    EditText mQianMingEditText, mNiChengEditText;
    Button jinniu_roundButton, baiyang_roundButton, shuangzi_roundButton, juxie_roundButton3;
    Button shizi_roundButton3, chunv_roundButton, tianping_roundButton, tianxie_roundButton;
    Button sheshou_roundButton3, moxie_roundButton, shuiping_roundButton, shuangyu_roundButton;
    boolean flag = false, flag2 = false, flag3 = false;
    String text;
    Intent mIntent;

    Uri uri;
    String path;//图片路径
    //imageName为上传到空间中的名称，为了确保唯一，我用了("hhy"+系统当前时间)命名
    String imageName;
    GetToken mGetToken;
    String token;
    private static final String TAG = "MyText";
    String netPath = "http://o6nj6n5ea.bkt.clouddn.com";
    String name = "name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Fresco
        Fresco.initialize(PersonalInfoActivity.this);
        setContentView(R.layout.activity_personal_info);
        initView();
        setClickLinstener();

    }

    private void setClickLinstener() {
        jinniu_roundButton.setOnClickListener(this);
        baiyang_roundButton.setOnClickListener(this);
        shuangzi_roundButton.setOnClickListener(this);
        juxie_roundButton3.setOnClickListener(this);
        shizi_roundButton3.setOnClickListener(this);
        chunv_roundButton.setOnClickListener(this);
        tianping_roundButton.setOnClickListener(this);
        tianxie_roundButton.setOnClickListener(this);
        sheshou_roundButton3.setOnClickListener(this);
        moxie_roundButton.setOnClickListener(this);
        shuiping_roundButton.setOnClickListener(this);
        shuangyu_roundButton.setOnClickListener(this);
    }

    private void initView() {
        mCancleText = (TextView) findViewById(R.id.hhy_personal_cancleText);
        mCancleText.setOnClickListener(this);
        mBianjiText = (TextView) findViewById(R.id.hhy_personal_title_edit);
        mBianjiText.setOnClickListener(this);
        mStarText = (TextView) findViewById(R.id.hhy_personal_buxian_text);
        mStarText.setOnClickListener(this);
        mNiChengText = (TextView) findViewById(R.id.hhy_personal_nichengtext);
        mTouXiangRelative = (RelativeLayout) findViewById(R.id.hhy_personalinfo_touxiang_relative);
        mNiChengRelative = (RelativeLayout) findViewById(R.id.hhy_personalinfo_nicheng_relative);
        mNiChengRelative.setOnClickListener(this);
        mStarRelative = (RelativeLayout) findViewById(R.id.hhy_personal_star_relative);
        mStarRelative.setOnClickListener(this);
        mStarZhanKaiRelative = (RelativeLayout) findViewById(R.id.hhy_personal_star_zhankai);
        mRadioGroup = (RadioGroup) findViewById(R.id.hhy_personal_sex_radiogroup);
        mManRadioButton = (RadioButton) findViewById(R.id.hhy_personal_sex_radiomale);
        mWomanRadioButton = (RadioButton) findViewById(R.id.hhy_personal_sex_radiofemale);
        mstarIMageView = (ImageView) findViewById(R.id.hhy_personal_buxian_zhankaiimage);
        mstarIMageView.setOnClickListener(this);
        mSimpleDraweeView = (SimpleDraweeView) findViewById(R.id.hhy_personal_image);
        mSimpleDraweeView.setOnClickListener(this);
        mQianMingEditText = (EditText) findViewById(R.id.hhy_personal_edittext);
        mNiChengEditText = (EditText) findViewById(R.id.hhy_personal_nichengedit);
        jinniu_roundButton = (Button) findViewById(R.id.hhy_personal_star_roundButton1);
        baiyang_roundButton = (Button) findViewById(R.id.hhy_personal_star_roundButton2);
        shuangzi_roundButton = (Button) findViewById(R.id.hhy_personal_star_roundButton3);
        juxie_roundButton3 = (Button) findViewById(R.id.hhy_personal_star_roundButton4);
        shizi_roundButton3 = (Button) findViewById(R.id.hhy_personal_star_roundButton5);
        chunv_roundButton = (Button) findViewById(R.id.hhy_personal_star_roundButton6);
        tianping_roundButton = (Button) findViewById(R.id.hhy_personal_star_roundButton7);
        tianxie_roundButton = (Button) findViewById(R.id.hhy_personal_star_roundButton8);
        sheshou_roundButton3 = (Button) findViewById(R.id.hhy_personal_star_roundButton9);
        moxie_roundButton = (Button) findViewById(R.id.hhy_personal_star_roundButton10);
        shuiping_roundButton = (Button) findViewById(R.id.hhy_personal_star_roundButton11);
        shuangyu_roundButton = (Button) findViewById(R.id.hhy_personal_star_roundButton12);
        //默认隐藏圆形按钮
        yincang();


    }

    private void yincang() {
        //默认为隐藏
        mStarZhanKaiRelative.setVisibility(View.GONE);
        mNiChengEditText.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.hhy_personal_cancleText:
                //点击取消
                finish();
                break;
            case R.id.hhy_personal_title_edit:
                //点击完成，将设置的内容存到偏好设置里面
                text = mBianjiText.getText().toString();
                Toast.makeText(PersonalInfoActivity.this, text, Toast.LENGTH_SHORT).show();


                break;
            case R.id.hhy_personal_image:
                //点击更换头像
                //popwindow从下面显示
                showPopwindow();
                break;
            case R.id.hhy_personal_star_relative:
                if (!flag2) {
                    mstarIMageView.setImageResource(R.drawable.expander_open);
                    mStarZhanKaiRelative.setVisibility(View.VISIBLE);
                    flag2 = true;
                } else {
                    mstarIMageView.setImageResource(R.drawable.expander_close);
                    mStarZhanKaiRelative.setVisibility(View.GONE);
                    flag2 = false;
                }
                break;
            case R.id.hhy_personal_buxian_zhankaiimage:
                if (!flag2) {
                    mstarIMageView.setImageResource(R.drawable.expander_open);
                    mStarZhanKaiRelative.setVisibility(View.VISIBLE);
                    flag2 = true;
                } else {
                    mstarIMageView.setImageResource(R.drawable.expander_close);
                    mStarZhanKaiRelative.setVisibility(View.GONE);
                    flag2 = false;
                }
                break;
            case R.id.hhy_personal_star_roundButton1:
                //金牛座
                mStarText.setText("金牛座");
                break;
            case R.id.hhy_personal_star_roundButton2:
                //白羊座
                mStarText.setText("白羊座");
                break;
            case R.id.hhy_personal_star_roundButton3:
                //双子座
                mStarText.setText("双子座");
                break;
            case R.id.hhy_personal_star_roundButton4:
                //巨蟹座
                mStarText.setText("巨蟹座");
                break;
            case R.id.hhy_personal_star_roundButton5:
                //狮子座
                mStarText.setText("狮子座");
                break;
            case R.id.hhy_personal_star_roundButton6:
                //处女座
                mStarText.setText("处女座");
                break;
            case R.id.hhy_personal_star_roundButton7:
                //天平座
                mStarText.setText("天平座");
                break;
            case R.id.hhy_personal_star_roundButton8:
                //天蝎座
                mStarText.setText("天蝎座");
                break;
            case R.id.hhy_personal_star_roundButton9:
                //射手座
                mStarText.setText("射手座");
                break;
            case R.id.hhy_personal_star_roundButton10:
                //魔蝎座
                mStarText.setText("魔蝎座");
                break;
            case R.id.hhy_personal_star_roundButton11:
                //水瓶座
                mStarText.setText("水瓶座");
                break;
            case R.id.hhy_personal_star_roundButton12:
                //双鱼座
                mStarText.setText("双鱼座");
                break;
            default:
                break;
        }
    }

   /* private void setClickAble(boolean flag) {
        if(flag){
            //设置控件可点击
            mSimpleDraweeView.setClickable(true);
            mNiChengRelative.setClickable(true);
            mRadioGroup.setClickable(true);
            mStarRelative.setClickable(true);
            mstarIMageView.setClickable(true);
            mNiChengEditText.setClickable(true);
            mQianMingEditText.setEnabled(true);
        }else{
            //设置控件不可点击
            mSimpleDraweeView.setClickable(false);
            mNiChengRelative.setClickable(false);
            mRadioGroup.setClickable(false);
            mStarRelative.setClickable(false);
            mstarIMageView.setClickable(false);
            mQianMingEditText.setEnabled(false);
        }

    }*/


    /**
     * 显示popupWindow
     */
    private void showPopwindow() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.hhy_popwindow, null);
        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
       /* ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);*/
        window.setBackgroundDrawable(new BitmapDrawable());

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(PersonalInfoActivity.this.findViewById(R.id.hhy_personalinfo_relative),
                Gravity.BOTTOM, 0, 0);

        // 这里检验popWindow里的button是否可以点击
        mTakephotoRelative = (RelativeLayout) view.findViewById(R.id.hhy_pop_takephoto);
        mFromPicture = (RelativeLayout) view.findViewById(R.id.hhy_pop_frompicture);
        mCancle = (RelativeLayout) view.findViewById(R.id.hhy_pop_cancle);
        mTakephotoRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用系统相机
                mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(mIntent, CAMERA);
                Toast.makeText(PersonalInfoActivity.this, path, Toast.LENGTH_LONG).show();
                if (path != null) {
                    upload();
                } else {
                    Toast.makeText(PersonalInfoActivity.this, "没找到图片", Toast.LENGTH_LONG).show();
                }
            }
        });
        mFromPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent();
                mIntent.setAction(Intent.ACTION_PICK);
                mIntent.setType("image/*");
                startActivityForResult(mIntent, 1);
                setImage();
                if (path != null) {
                    upload();
                } else {
                    Toast.makeText(PersonalInfoActivity.this, "没找到图片", Toast.LENGTH_LONG).show();
                }
                //Toast.makeText(PersonalInfoActivity.this, "图库", Toast.LENGTH_SHORT).show();
            }
        });
        mCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                Toast.makeText(PersonalInfoActivity.this, "取消", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setImage() {
        Uri uri = Uri.parse(netPath + "/" + imageName);
        mSimpleDraweeView.setImageURI(uri);
    }
    public void setImage2() {
        Uri uri = Uri.parse(netPath + "/" + imageName);
        mSimpleDraweeView.setImageURI(uri);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
                Log.i("person",path);
            }
        }
    }

    public void upload() {
        UploadManager uploadManager = new UploadManager();
        mGetToken = new GetToken();
        token = mGetToken.getToken(name);
        imageName = "hhy" + "/" + System.currentTimeMillis() + ".jpg";
        uploadManager.put(path, imageName, token,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info,
                                         JSONObject response) {
                        //Log.e("ppp", "000"+imageName+"");
                        //info.statusCode 回调状态码
                        if (info.statusCode == 200) {
                            Log.i(TAG, "" + info.isOK());
                            Toast.makeText(getApplication(), "完成上传", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplication(), info.statusCode + "网络异常，上传失败", Toast.LENGTH_LONG).show();
                        }
                    }
                }, null);

    }


}
