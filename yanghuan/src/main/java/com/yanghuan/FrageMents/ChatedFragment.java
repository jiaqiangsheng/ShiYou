package com.yanghuan.FrageMents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.yanghuan.MyApplication.ShiYouActivity;
import com.yanghuan.MyViews.NoScrollViewPager;
import com.yanghuan.R;
import com.yanghuan.adapters.ChatFragementAdapter;

import org.xutils.BuildConfig;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mhdong on 2016/4/29.
 */
public class ChatedFragment extends Fragment{
    private ShiYouActivity  mActivity ;;
    private View mView;
    NoScrollViewPager mViewPager;
    private List<Fragment> mList;
    private ChatFragementAdapter mAdapter;
    private FragmentManager mFragmentManager;
    JmFragement jmFragement;
    WsFragement wsFragement;
    RadioGroup mRadioGroup;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.chated,null);
        initXUtils();
        initViews();
        initListeners();
        initData();


        return mView;
    }

    private void initData() {
        mList = new ArrayList<Fragment>();
        jmFragement=new JmFragement();
         wsFragement=new WsFragement();
        mList.add(jmFragement);
        mList.add(wsFragement);


        //初始化适配器
        mFragmentManager = mActivity.getSupportFragmentManager();
        mAdapter = new ChatFragementAdapter(mFragmentManager,mList);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(mAdapter);


    }


    private void initListeners() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                resetViewPager(checkedId);
            }
        });

        //滑动viewpager的时候及时修改底部导航栏对应图标
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
           /*     //获取position位置处对应的单选按钮
                RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(position);
                //设置当前单选按钮默认选中
                radioButton.setChecked(true);*/

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


   /* private void resetRadioButton(int position) {
        //获取position位置处对应的单选按钮
        RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(position);
        //设置当前单选按钮默认选中
        radioButton.setChecked(true);

    }*/


    private void resetViewPager(int checkedId) {
        switch (checkedId){
            case R.id.bn1:
                mViewPager.setCurrentItem(0,false);
                break;
            case R.id.btn2:
                mViewPager.setCurrentItem(1,false);
                break;

        }
    }


    private void initViews() {
        mViewPager = (NoScrollViewPager)mView.findViewById(R.id.chatViewpager);
        mRadioGroup = (RadioGroup) mView.findViewById(R.id.group);
        mActivity = (ShiYouActivity) getActivity();
    }

    private void initXUtils() {
        x.Ext.init(getActivity().getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
