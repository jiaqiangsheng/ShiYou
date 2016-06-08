package com.hhy.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hhy.activity.AccountManageActivity;
import com.hhy.activity.MessageActivity;
import com.hhy.adapter.MConfigAdapter;
import com.hhy.bean.Config;
import com.yanghuan.R;

import org.xutils.x;
import java.util.ArrayList;
import java.util.List;


public class ConfigFragment extends Fragment {
    List<Config> mList;
    MConfigAdapter mAdapter;
    ListView mListView;
    Intent mIntent;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    AccountSafeFragment mSafeFragment;
    AdviceFragment mAdviceFragment;
    hhy_EditionFragment mEditionFragment;
    AlertDialog.Builder dialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hhy_fragment_config, null);
        initView(view);
        initData();
        setAdapter();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //xUtils初始化
        x.Ext.init(getActivity().getApplication());
        //极光推送初始化
        /*JPushInterface.setDebugMode(true);
        JPushInterface.init(getActivity());*/


    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.config_listview);

        mFragmentManager = getFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        //为每一个item添加点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), id + "", Toast.LENGTH_SHORT).show();
                //mImageView = (ImageView) view.findViewById(R.id.hhy_right_image);
                switch ((int) id) {
                    case 0:
                        //账号管理
                        mIntent = new Intent(getActivity(), AccountManageActivity.class);
                        startActivity(mIntent);
                        break;
                    case 1:
                        //账号安全
                        mSafeFragment = new AccountSafeFragment();
                        //fragment动画必须在add,replace,remove等方法之前
                        mFragmentTransaction.setCustomAnimations(
                                R.anim.fragment_slide_left_enter,
                                R.anim.fragment_slide_left_exit,
                                R.anim.fragment_slide_right_enter,
                                R.anim.fragment_slide_right_exit);
                        mFragmentTransaction.replace(R.id.hhy_congfig_middle, mSafeFragment);
                        mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        mFragmentTransaction.addToBackStack(null);
                        //提交事务
                        mFragmentTransaction.commit();
                        break;
                    case 2:
                        //消息通知
                        mIntent = new Intent(getActivity(), MessageActivity.class);
                        startActivity(mIntent);
                        break;
                    case 3:
                        //接收消息
                        break;
                    case 4:
                        //清空缓存
                        dialog = new AlertDialog.Builder(getActivity());
                        dialog.setTitle("提示")
                                .setIcon(R.drawable.hhy_icon)
                                .setMessage("确定清空缓存吗？")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //清空操作还没做
                                //dialog消失
                                dialog.dismiss();
                            }
                        });
                        dialog.create().show();
                        break;

                    case 5:
                        //意见反馈
                        mAdviceFragment = new AdviceFragment();
                        //fragment动画必须在add,replace,remove等方法之前
                        mFragmentTransaction.setCustomAnimations(
                                R.anim.fragment_slide_left_enter,
                                R.anim.fragment_slide_left_exit,
                                R.anim.fragment_slide_right_enter,
                                R.anim.fragment_slide_right_exit);
                        mFragmentTransaction.replace(R.id.hhy_congfig_middle, mAdviceFragment);
                        mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        mFragmentTransaction.addToBackStack(null);
                        //提交事务
                        mFragmentTransaction.commit();
                        break;
                    case 6:
                        //关于我们
                        mEditionFragment = new hhy_EditionFragment();
                        //fragment动画必须在add,replace,remove等方法之前
                        mFragmentTransaction.setCustomAnimations(
                                R.anim.fragment_slide_left_enter,
                                R.anim.fragment_slide_left_exit,
                                R.anim.fragment_slide_right_enter,
                                R.anim.fragment_slide_right_exit);
                        mFragmentTransaction.replace(R.id.hhy_congfig_middle, mEditionFragment);
                        mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        mFragmentTransaction.addToBackStack(null);
                        //提交事务
                        mFragmentTransaction.commit();
                        break;
                    default:
                        break;
                }


            }
        });
    }

    private void initData() {
        mList = new ArrayList<Config>();
        Config config1 = new Config("账号管理", 0);
        Config config2 = new Config("账号安全", 2);
        Config config3 = new Config("消息通知", 2);
        Config config4 = new Config("推送消息", 1);
        Config config5 = new Config("清空缓存", 2);
        Config config7 = new Config("意见反馈", 2);
        Config config8 = new Config("关于我们", 2);
        mList.add(config1);
        mList.add(config2);
        mList.add(config3);
        mList.add(config4);
        mList.add(config5);
        mList.add(config7);
        mList.add(config8);

    }

    private void setAdapter() {
        mAdapter = new MConfigAdapter(mList, getActivity());
        mListView.setAdapter(mAdapter);


    }


}
