package com.yanghuan.FrageMents;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lxx.Adapter.MycontextAdapter;
import com.lxx.activity.Dynamic_Details;
import com.lxx.bean.Dynamic;
import com.lxx.bean.Lxx_BeanContext;
import com.lxx.bean.User;
import com.yanghuan.BuildConfig;
import com.yanghuan.MyApplication.ShiYouActivity;
import com.yanghuan.MyViews.NoScrollViewPager;
import com.yanghuan.R;
import com.yanghuan.adapters.CommitFragementAdapter;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by mhdong on 2016/4/29.
 */
public class CominicationFragment extends Fragment {
    NoScrollViewPager mViewPager;
    RadioGroup mRadioGroup;
    private ShiYouActivity  mActivity ;
    ComminLeftFragment leftFragment;
    CommitrightFragement commitrightFragement;
    private List<Fragment> mList;
    CommitFragementAdapter adapter1;
    private FragmentManager mFragmentManager;
    private View mView;

/*
    */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mView=inflater.inflate(R.layout.communication, null);
        initXUtils();
        initView();
        initListeners();
        initData1();


       /* getJsonData();*/

        /*initData();*/
/*

        adapter=new MycontextAdapter(getActivity(),contextlist);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Dynamic_Details.class);
                intent.putExtra("list", contextlist.get(position));
                startActivity(intent);
               // Toast.makeText(Mycontext.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.bn1:
                       // RadioButton radioButton = (RadioButton)findViewById(R.id.bn1);
                      //  radioButton.setChecked(true);
                       // Toast.makeText(Mycontext.this, "123", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn2:
                       // radioButton = (RadioButton) findViewById(R.id.btn2);
                       // radioButton.setChecked(true);
                       // Toast.makeText(Mycontext.this, "456", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
*/

        return mView;


    }

    private void initData1() {
        mList = new ArrayList<Fragment>();
        leftFragment=new ComminLeftFragment();
        commitrightFragement=new CommitrightFragement();
         mList.add(leftFragment);
        mList.add(commitrightFragement);

        //初始化适配器
        mFragmentManager = mActivity.getSupportFragmentManager();
        adapter1 = new CommitFragementAdapter (mFragmentManager,mList);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(adapter1);

    }

   /* private void getJsonData() {
        Log.e("李萧萧", "" + "执行");
        RequestParams params = new RequestParams("http://10.201.1.151:8080/JavaAppWeb/selectforum");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //访问成功，参数其实就是PrintWriter写回的值
                //把JSON格式的字符串改为Student对象
                Gson gson = new Gson();
                Type type = new TypeToken<List<Dynamic>>() {
                }.getType();
                List<Dynamic> list = gson.fromJson(result.toString(), type);
                for (int i = 0; i < list.size(); i++) {
                    Dynamic dynamic = list.get(i);
                    imageurllist = new ArrayList<>();
                    imageurllist.addAll(dynamic.getImageListString());
                    User user = dynamic.getUser();
                    contextBean = new Lxx_BeanContext(user.getName(), dynamic.getFdata(), user.getUpicture(), imageurllist, dynamic.getFcontext(), imageurllist.size(), imageurllist.size());
                    contextlist.add(contextBean);
                    adapter.notifyDataSetChanged();
                    Log.e("李萧萧", "" + user.getName());
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

    }*/
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

    private void initXUtils() {
        x.Ext.init(getActivity().getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
    /*private void initData() {

        String imageurl="http://img3.imgtn.bdimg.com/it/u=895009738,1542646259&fm=21&gp=0.jpg";
        //    imageurllist.add(imageurl);
        String contextmain="shuoshuo明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪明若晓溪 ";
        int a =10;
        contextBean =new Lxx_BeanContext("fdsfasdasd","5/23","http://pic13.nipic.com/20110310/6400731_100538610118_2.jpg",imageurllist,contextmain,a,a);
        contextlist.add(contextBean);
       *//* imageurllist=new ArrayList<>();
        imageurllist.add(imageurl);
        imageurllist.add("http://h.hiphotos.baidu.com/image/pic/item/d439b6003af33a8716116e60c55c10385343b548.jpg");
        imageurllist.add("http://a.hiphotos.baidu.com/image/pic/item/8b13632762d0f7037170e83a0dfa513d2797c512.jpg");
        imageurllist.add("http://g.hiphotos.baidu.com/image/pic/item/d0c8a786c9177f3e117088eb75cf3bc79e3d568b.jpg");
        imageurllist.add("http://a.hiphotos.baidu.com/image/pic/item/d31b0ef41bd5ad6ed5ba980b84cb39dbb7fd3c8b.jpg");
        imageurllist.add("http://img.szhk.com/Image/2013/08/22/1377160001248.jpg");
        imageurllist.add("http://g.hiphotos.baidu.com/image/pic/item/f7246b600c338744159a5d9a540fd9f9d62aa0fd.jpg");
        imageurllist.add("http://www.cnnb.com.cn/pic/0/02/19/05/2190554_999846.jpg");
        imageurllist.add("http://www.yybagua.cn/uploads/allimg/141213/4-1412131ZQC25.jpg");
        imageurllist.add("http://images.china.cn/attachement/jpg/site1000/20110819/001ec949ffca0fb8413c37.jpg");
        imageurllist.add("http://images.china.cn/attachement/jpg/site1000/20110819/001ec949ffca0fb8413c37.jpg");
        contextBean =new Lxx_BeanContext("明若晓溪","5/24","http://pic13.nipic.com/20110310/6400731_100538610118_2.jpg",imageurllist,contextmain,a,a);
        contextlist.add(contextBean);


        contextlist.add(contextBean);
        contextlist.add(contextBean);
        contextlist.add(contextBean);*//*


    }*/
    private void initView() {
        mViewPager = (NoScrollViewPager)mView.findViewById(R.id.Lxx_Viewpager);
        mRadioGroup = (RadioGroup) mView.findViewById(R.id.actionbar_group);
        mActivity = (ShiYouActivity) getActivity();
    }
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
}
