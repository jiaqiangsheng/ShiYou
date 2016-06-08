package com.yanghuan.MyApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
<<<<<<< HEAD
=======
import android.widget.ImageView;
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hhy.activity.FansActivity;
import com.hhy.activity.GuanZhuActivity;
import com.hhy.activity.MainActivity;
import com.hhy.activity.PersonalInfoActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yanghuan.FrageMents.ChatedFragment;
import com.yanghuan.FrageMents.CominicationFragment;
import com.yanghuan.FrageMents.ReadStoryFragment;
import com.yanghuan.FrageMents.RecFragment;
import com.yanghuan.FrageMents.TopFragment;
import com.yanghuan.MyViews.MoreWindow;
import com.yanghuan.MyViews.NoScrollViewPager;
import com.yanghuan.R;
import com.yanghuan.adapters.TotalMyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShiYouActivity extends AppCompatActivity implements View.OnClickListener {
    RadioGroup mRadioGroup;
    MoreWindow mMoreWindow;
    NoScrollViewPager  mViewPager;
    List<Fragment> mList;
    TopFragment mTopFragment;
    RecFragment mRecFragment;
    ChatedFragment mChatedFragment;
  /*  CenterFragment mReadFragment;*/
    CominicationFragment mBookFragment;
    ReadStoryFragment mCenterFragment;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    TotalMyAdapter mAdapter;
    //侧滑
    SlidingMenu menu;
    View  v=getCurrentFocus();
    Intent mIntent;
<<<<<<< HEAD
    TextView guanzhuview,bottomView1, bottomView2, bottomView3, bottomView4, bottomView5, bottomView6;
=======
    ImageView mTouXiangImage;
    TextView guanzhuview,fansview,bottomView1, bottomView2, bottomView3, bottomView4, bottomView5, bottomView6;
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_you);
       /* LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(R.layout.activity_shi_you,null);*/
        initViews();
        initListeners();
        initData();
        //侧滑
        initMenu();
        initMenuviews();
        addMenuListeners();
        Intent intentBack=getIntent();



    }
    private void initMenuviews() {
<<<<<<< HEAD
=======
        mTouXiangImage = (ImageView) findViewById(R.id.slidingmenu_aboveImageView);
        fansview = (TextView) findViewById(R.id.slidinemenu_middleTextView22);
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
        guanzhuview = (TextView) findViewById(R.id.slidingmenu_middleTextView12);
        bottomView1= (TextView) findViewById(R.id.slidingmenu_bottomTextView1);
        bottomView2= (TextView) findViewById(R.id.slidingmenu_bottomTextView2);
        bottomView3= (TextView) findViewById(R.id.slidingmenu_bottomTextView3);
        bottomView4= (TextView) findViewById(R.id.slidingmenu_bottomTextView4);
        bottomView5= (TextView) findViewById(R.id.slidingmenu_bottomTextView5);
        bottomView6= (TextView) findViewById(R.id.sldingmenu_bottomTextView6);

    }

    private void addMenuListeners() {
<<<<<<< HEAD
=======
        mTouXiangImage.setOnClickListener(this);
        fansview.setOnClickListener(this);
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
        guanzhuview.setOnClickListener(this);
        bottomView1.setOnClickListener(this);
        bottomView2.setOnClickListener(this);
        bottomView3.setOnClickListener(this);
        bottomView4.setOnClickListener(this);
        bottomView5.setOnClickListener(this);
        bottomView6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.slidingmenu_aboveImageView:
                mIntent = new Intent(ShiYouActivity.this,PersonalInfoActivity.class);
                startActivity(mIntent);
                break;
            case R.id.slidingmenu_middleTextView12:
                mIntent = new Intent(ShiYouActivity.this,GuanZhuActivity.class);
                startActivity(mIntent);
                break;
            case R.id.slidinemenu_middleTextView22:
                mIntent = new Intent(ShiYouActivity.this,FansActivity.class);
                startActivity(mIntent);
                break;
            case R.id.slidingmenu_bottomTextView1:
                Intent intent=new Intent(ShiYouActivity.this,MyTaskActivity.class);
                startActivity(intent);
                break;
            case R.id.slidingmenu_bottomTextView2:
                show("我的任务2");
                break;
            case R.id.slidingmenu_bottomTextView3:
               /* Intent intent1=new Intent(ShiYouActivity.this,MyFavoriteActivity.class);
                startActivity(intent1);*/
                break;
            case R.id.slidingmenu_bottomTextView4:
                Intent intent1=new Intent(ShiYouActivity.this,ZhuiJuActivity.class);
                startActivity(intent1);
                break;
            case R.id.sldingmenu_bottomTextView6:
                show("我的任务6");
                //MainActivity是设置的主页面
                Intent intent6=new Intent(ShiYouActivity.this,MainActivity.class);
                startActivity(intent6);
                break;
            case R.id.slidingmenu_bottomImageView5:
                show("我的任务5");
                break;
            default:
                break;
        }

    }

    private void show(String s) {
        Toast.makeText(ShiYouActivity.this,s,Toast.LENGTH_SHORT).show();
    }

    private void initData() {
        mList = new ArrayList<Fragment>();

        mRecFragment = new RecFragment();
        mChatedFragment = new ChatedFragment();
      /*  mReadFragment = new CenterFragment();*/
        mBookFragment = new CominicationFragment();
        mCenterFragment=new ReadStoryFragment();



        mList.add(mRecFragment);
        mList.add(mChatedFragment);
  /*      mList.add(mReadFragment);*/
        mList.add(mBookFragment);
        mList.add(mCenterFragment);

        //初始化适配器
        mFragmentManager = getSupportFragmentManager();
        mAdapter = new TotalMyAdapter(mFragmentManager,mList);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(mAdapter);
        //
        mFragmentTransaction=mFragmentManager.beginTransaction();
//        //mFragmentTransaction.add(R.id.shi_you_top,mTopFragment);
        mFragmentTransaction.commit();

    }

    private void initViews() {
        mViewPager = (NoScrollViewPager) findViewById(R.id.shi_you_middle);
        mRadioGroup = (RadioGroup) findViewById(R.id.tatal_group);
    }

    private void initListeners() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.e("yang","checkid1:"+checkedId);
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
                //根据当前位置设置默认选中单选按钮
                if(position>1) {
                    resetRadioButton(position+1);
                }else {
                    resetRadioButton(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void resetViewPager(int checkedId) {
        Log.e("yang","checkid2:"+checkedId);
        switch (checkedId){
            case R.id.total_recommend:
                mViewPager.setCurrentItem(0,false);
                break;
            case R.id.total_chated:
                mViewPager.setCurrentItem(1,false);
                break;
             case R.id.total_center:
                 RadioButton radioButton= (RadioButton) findViewById(R.id.total_center);
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMoreWindow(v);
                    }
                });
                 break;
            case R.id.total_read:
                Log.e("yang","checkid3:"+R.id.total_read);
                mViewPager.setCurrentItem(2,false);
                Log.e("yang","1111");
              break;
            case R.id.total_book:
                Log.e("yang","checkid4:"+ R.id.total_book);
                mViewPager.setCurrentItem(3,false);
                Log.e("yang","2222");
                break;
        }
    }

   private void showMoreWindow(View view) {
        if (null == mMoreWindow) {
            mMoreWindow = new MoreWindow(ShiYouActivity.this);
            mMoreWindow.init();
        }

        mMoreWindow.showMoreWindow(view,100);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void resetRadioButton(int position) {
        //获取position位置处对应的单选按钮
        RadioButton radioButton = (RadioButton) mRadioGroup.getChildAt(position);
        //设置当前单选按钮默认选中
        radioButton.setChecked(true);

    }

    private void initMenu() {
        menu=new SlidingMenu(this);

        //设置侧滑菜单的位置，可选值LEFT , RIGHT , LEFT_RIGHT （两边都有菜单时设置）
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式，可选只MARGIN , CONTENT
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        // 设置SlidingMenu离屏幕的偏移量 或者 设置宽度menu.setBehindWidth()
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //根据dimension资源文件的ID来设置阴影的宽度
        menu.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
        //根据资源文件ID来设置滑动菜单的阴影效果
        //menu.setShadowDrawable(R.drawable.p12);
        //设置渐变度
        menu.setFadeDegree(0.55f);
        //设置布局
        menu.setMenu(R.layout.slidingmenu);
        menu.attachToActivity(this,SlidingMenu.SLIDING_WINDOW);

    }


}
