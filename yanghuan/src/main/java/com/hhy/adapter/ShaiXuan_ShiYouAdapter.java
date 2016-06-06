package com.hhy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yanghuan.R;

import java.util.List;

/**
 * Created by hanhongyang on 2016/5/18.
 */
public class ShaiXuan_ShiYouAdapter extends BaseAdapter implements View.OnClickListener {
    List<Integer> mList;
    Context mContext;
    LayoutInflater mInflater;

    RadioGroup mRadioGroup;
    RadioButton mWomanRadioButton, mManRadioButton, mAllRadioButton;
    ImageView mRightImage1, mRightImage2, mRightImage3;
    RelativeLayout mAgeRelative, mHobitRelative, mStarRelative;
    Button age_roundButton1, age_roundButton2, age_roundButton3;
    TextView shaixuan_biaoqian;
    boolean flag1 = false, flag2 = false, flag3 = false;

    public ShaiXuan_ShiYouAdapter(List<Integer> list, Context context) {
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.hhy_item_shaixuan, null);
        initView(convertView);
        return convertView;
    }

    private void initView(View view) {
        mRadioGroup = (RadioGroup) view.findViewById(R.id.RadioGroup);
        mWomanRadioButton = (RadioButton) view.findViewById(R.id.woman);
        mManRadioButton = (RadioButton) view.findViewById(R.id.man);
        mAllRadioButton = (RadioButton) view.findViewById(R.id.all);
        //为单选按钮加上点击事件
        setRadioClickListener();

        //圆形按钮
        shaixuan_biaoqian = (TextView) view.findViewById(R.id.shaixuan_biaoqian);
        age_roundButton1 = (Button) view.findViewById(R.id.age_roundButton1);
        age_roundButton2 = (Button) view.findViewById(R.id.age_roundButton2);
        age_roundButton3 = (Button) view.findViewById(R.id.age_roundButton3);
        age_roundButton1.setOnClickListener(this);
        age_roundButton2.setOnClickListener(this);
        age_roundButton3.setOnClickListener(this);

        mRightImage1 = (ImageView) view.findViewById(R.id.shaixuan_right1);
        mRightImage2 = (ImageView) view.findViewById(R.id.shaixuan_right2);
        mRightImage3 = (ImageView) view.findViewById(R.id.shaixuan_right3);
        //为右边的图片加点击按钮
        mRightImage1.setOnClickListener(this);
        mRightImage2.setOnClickListener(this);
        mRightImage3.setOnClickListener(this);
        mAgeRelative = (RelativeLayout) view.findViewById(R.id.age_zhankai);
        mHobitRelative = (RelativeLayout) view.findViewById(R.id.hobit_zhankai);
        mStarRelative = (RelativeLayout) view.findViewById(R.id.star_zhankai);
        //默认隐藏圆形按钮
        yincang();
    }

    private void setRadioClickListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                //每次点击都重置RadioButton的背景颜色为原始颜色
                resetRadioBackGroundColor();
                if (mWomanRadioButton.getId() == checkedId) {
                    mWomanRadioButton.setChecked(true);
                    mWomanRadioButton.setBackgroundColor(mContext.getResources().getColor(R.color.background_radio));
                    Toast.makeText(mContext, mWomanRadioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                } else if (mManRadioButton.getId() == checkedId) {
                    Toast.makeText(mContext, mManRadioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                    mManRadioButton.setChecked(true);
                    mManRadioButton.setBackgroundColor(mContext.getResources().getColor(R.color.background_radio));
                } else {
                    Toast.makeText(mContext, mAllRadioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                    mAllRadioButton.setChecked(true);
                    mAllRadioButton.setBackgroundColor(mContext.getResources().getColor(R.color.background_radio));
                }
            }
        });

    }

    private void resetRadioBackGroundColor() {
        //重置RadioButton的背景颜色为原始颜色
        mWomanRadioButton.setBackgroundResource(R.drawable.radiobutton_style);
        mManRadioButton.setBackgroundResource(R.drawable.radiobutton_style);
        mAllRadioButton.setBackgroundResource(R.drawable.radiobutton_style);
        mWomanRadioButton.setPadding(80, 0, 0, 0);
        mManRadioButton.setPadding(80, 0, 0, 0);
        mAllRadioButton.setPadding(85, 0, 0, 0);
    }

    private void yincang() {
        mAgeRelative.setVisibility(View.GONE);
        mHobitRelative.setVisibility(View.GONE);
        mStarRelative.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.shaixuan_right1:
                if (!flag1) {
                    mRightImage1.setImageResource(R.drawable.expander_open);
                    mAgeRelative.setVisibility(View.VISIBLE);
                    flag1 = true;
                } else {
                    mRightImage1.setImageResource(R.drawable.expander_close);
                    mAgeRelative.setVisibility(View.GONE);
                    flag1 = false;
                }
                break;
            case R.id.shaixuan_right2:
                if (!flag2) {
                    mRightImage2.setImageResource(R.drawable.expander_open);
                    mHobitRelative.setVisibility(View.VISIBLE);
                    flag2 = true;
                } else {
                    mRightImage2.setImageResource(R.drawable.expander_close);
                    mHobitRelative.setVisibility(View.GONE);
                    flag2 = false;
                }
                break;
            case R.id.shaixuan_right3:
                if (!flag3) {
                    mRightImage3.setImageResource(R.drawable.expander_open);
                    mStarRelative.setVisibility(View.VISIBLE);
                    flag3 = true;
                } else {
                    mRightImage3.setImageResource(R.drawable.expander_close);
                    mStarRelative.setVisibility(View.GONE);
                    flag3 = false;
                }
                break;
            case R.id.age_roundButton1:
                String hhy_text1 = age_roundButton1.getText().toString();
                shaixuan_biaoqian.setText(hhy_text1);
                break;
            case R.id.age_roundButton2:
                String hhy_text2 = age_roundButton2.getText().toString();
                shaixuan_biaoqian.setText(hhy_text2);
                break;
            case R.id.age_roundButton3:
                String hhy_text3 = age_roundButton3.getText().toString();
                shaixuan_biaoqian.setText(hhy_text3);
                break;
            default:
                break;
        }
    }

    private void show(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

}
