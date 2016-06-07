package com.hhy.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.cache.ACache;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.hhy.bean.ImageViewDemo;
import com.lxx.Utils.GetToken;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.yanghuan.R;

import org.json.JSONObject;

import java.io.InputStream;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int CAMERA = 1;
    public static final String PERSONALINFO = "personalinfo";
    TextView mCancleText, mBianjiText;
    RelativeLayout mTouXiangRelative, mNiChengRelative, mStarRelative, mStarZhanKaiRelative;
    RelativeLayout mTakephotoRelative, mFromPicture, mCancle;
    RadioGroup mRadioGroup;
    RadioButton mWomanRadioButton, mManRadioButton;
    ImageView mstarIMageView;
    ImageViewDemo mImageViewDemo;
    /*SimpleDraweeView mSimpleDraweeView;*/
    TextView mStarText, mNiChengText;
    EditText mQianMingEditText, mNiChengEditText;
    Button jinniu_roundButton, baiyang_roundButton, shuangzi_roundButton, juxie_roundButton3;
    Button shizi_roundButton3, chunv_roundButton, tianping_roundButton, tianxie_roundButton;
    Button sheshou_roundButton3, moxie_roundButton, shuiping_roundButton, shuangyu_roundButton;
    boolean flag = false, flag2 = false;
    String text;
    String sexText;
    Intent mIntent;
    Toast mToast;

    Uri uri;
    String path;//图片路径
    //imageName为上传到空间中的名称，为了确保唯一，我用了("hhy"+系统当前时间)命名
    String imageName;
    GetToken mGetToken;
    String token;
    private static final String TAG = "MyText";
    String netPath = "http://o6nj6n5ea.bkt.clouddn.com";
    String name = "name";
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    ACache mAcache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Fresco
        Fresco.initialize(PersonalInfoActivity.this);
        setContentView(R.layout.activity_personal_info);
        initView();
        setClickLinstener();
        //从偏好设置中取出上次存入的个人信息
        initContent();

    }

    private void initContent() {
        mAcache = ACache.get(this);
        mSharedPreferences = getSharedPreferences(PERSONALINFO, MODE_APPEND);
        imageName = mSharedPreferences.getString("imagepath", "http://o6nj6n5ea.bkt.clouddn.com/hhy/a9e.9.png");
        String nicheng = mSharedPreferences.getString("nicheng", null);
        String sex = mSharedPreferences.getString("sex", "男");
        String star = mSharedPreferences.getString("star", null);
        String qianming = mSharedPreferences.getString("qianming", null);
        String flag3 = mSharedPreferences.getString("flag3","false");
        if("true".equals(flag3)) {
            Bitmap bitmap = mAcache.getAsBitmap("touxiang");
            mImageViewDemo.setImageBitmap(bitmap);
        }else{
            mImageViewDemo.setImageResource(R.drawable.ae);
        }
        if (nicheng != null) {
            mNiChengText.setVisibility(View.VISIBLE);
            mNiChengText.setText(nicheng);
        }
        if (sex != null) {
            if ("男".equals(sex)) {
                mManRadioButton.setChecked(true);
            } else {
                mWomanRadioButton.setChecked(true);
            }
        }
        if (star != null) {
            mStarText.setText(star);
        }
        if (qianming != null) {
            mQianMingEditText.setText(qianming);
        }
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
        mImageViewDemo = (ImageViewDemo) findViewById(R.id.hhy_personal_image);
        mImageViewDemo.setOnClickListener(this);
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
        mSharedPreferences = getSharedPreferences(PERSONALINFO, MODE_APPEND);
        mEditor = mSharedPreferences.edit();

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mManRadioButton.getId() == checkedId) {
                    sexText = "男";
                } else if (mWomanRadioButton.getId() == checkedId) {
                    sexText = "女";
                }
            }
        });
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
                text = mNiChengEditText.getText().toString();
                String star = mStarText.getText().toString();
                String qianming = mQianMingEditText.getText().toString();
                mEditor.putString("imagepath", imageName);
                if (text != null && !"".equals(text)) {
                    mEditor.putString("nicheng", text);
                } else {
                    mEditor.putString("nicheng", mNiChengText.getText().toString());
                }
                if (sexText != null) {
                    mEditor.putString("sex", sexText);
                } else {
                    //与登陆界面连起来之后再改，先写死
                    mEditor.putString("sex", "男");
                }
                if (star != null) {
                    mEditor.putString("star", star);
                } else {
                    mEditor.putString("star", "不限");
                }
                if (qianming != null) {
                    mEditor.putString("qianming", qianming);
                } else {
                    mEditor.putString("qianming", "");
                }
                mEditor.commit();
                finish();
                break;
            case R.id.hhy_personalinfo_nicheng_relative:
                //昵称
                mNiChengEditText.setVisibility(View.VISIBLE);
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

            }
        });
        mFromPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent();
                mIntent.setAction(Intent.ACTION_PICK);
                mIntent.setType("image/*");
                startActivityForResult(mIntent, 1);
            }
        });
        mCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
    }

    public void setImage2(Bitmap bitmap) {
        mImageViewDemo.setImageBitmap(bitmap);

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

                if (path != null) {
                    /**取得图片，图片压缩比为4*4*/
                    Bitmap bm = compressBitmap(null, null, this, data.getData(), 4, false);
                    //setImage2(bm);
                    mAcache.put("touxiang",bm);
                    //用标志表示是不是第一次缓存，true表示不是
                    mEditor.putString("flag3","true");
                    mImageViewDemo.setImageBitmap(bm);
                    upload();
                }

            }
        } else if (requestCode == CAMERA) {
            // 从拍照返回的数据
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
                Log.i("photo", path);
                show(path);
                if (path != null) {
                    /**取得图片，图片压缩比为4*4*/
                    Bitmap bm = compressBitmap(null, null, this, uri, 4, false);
                    mAcache.put("touxiang",bm);
                    //用标志表示是不是第一次缓存，true表示不是
                    mEditor.putString("flag3","true");
                    mImageViewDemo.setImageBitmap(bm);
                    upload();
                }
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
                        if (info.statusCode == 200) {
                            Log.i(TAG, "" + info.isOK());
                            Toast.makeText(getApplication(), "完成上传", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplication(), info.statusCode + "网络异常，上传失败", Toast.LENGTH_LONG).show();
                        }
                    }
                }, null);

    }

    /**图片压缩处理，size参数为压缩比，比如size为2，则压缩为1/4**/
    private Bitmap compressBitmap(String path, byte[] data, Context context, Uri uri, int size, boolean width) {
        BitmapFactory.Options options = null;
        if (size > 0) {
            BitmapFactory.Options info = new BitmapFactory.Options();
            /**如果设置true的时候，decode时候Bitmap返回的为数据将空*/
            info.inJustDecodeBounds = false;
            decodeBitmap(path, data, context, uri, info);
            int dim = info.outWidth;
            if (!width) dim = Math.max(dim, info.outHeight);
            options = new BitmapFactory.Options();
            /**把图片宽高读取放在Options里*/
            options.inSampleSize = size;
        }
        Bitmap bm = null;
        try {
            bm = decodeBitmap(path, data, context, uri, options);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

    /**把byte数据解析成图片*/
    private Bitmap decodeBitmap(String path, byte[] data, Context context, Uri uri, BitmapFactory.Options options) {
        Bitmap result = null;
        if (path != null) {
            result = BitmapFactory.decodeFile(path, options);
        }
        else if (data != null) {
            result = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        }
        else if (uri != null) {
            ContentResolver cr = context.getContentResolver();
            InputStream inputStream = null;
            try {
                inputStream = cr.openInputStream(uri);
                result = BitmapFactory.decodeStream(inputStream, null, options);
                inputStream.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public void show(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(PersonalInfoActivity.this, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }




}
