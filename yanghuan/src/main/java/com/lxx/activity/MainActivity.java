package com.lxx.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
=======
import com.LoginUser;
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
import com.lxx.bean.UserInfo;
import com.yanghuan.BuildConfig;
import com.yanghuan.MyApplication.ShiYouActivity;
import com.yanghuan.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

<<<<<<< HEAD
=======
/**
 * lxx的登陆界面
 */
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa

public class MainActivity extends AppCompatActivity {
    public static final String SAVE="save";
    EditText userNameEditText;
    EditText passwordEditText;
    String userNameString;
    String passwordString;
    CheckBox checkBox;
    String passwordshow=null;
    String useshow=null;
    String selectPAssword;//根据账号查找到的密码
    int m;//记录到相应的偏好设置islogin的位置
    boolean flag=true;//是否显示在记住密码列表
    int i;
    private Map<String, String> map;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String url;
    //账号登陆历史
    private static boolean isVisible=false;         //ListView是否可见
    private static boolean isIndicatorUp = false;     //指示器的方向
    public static int currentSelectedPosition=-1;
    //用于记录当前选择的ListView中的QQ联系人条目的ID，如果是-1表示没有选择任何QQ账户，注意在向
    //List中添加条目或者删除条目时都要实时更新该currentSelectedPosition
    ImageView currentUserImage;//头像显示
    ListView loginList=null;//记住账号密码
    String[] from={"userPhoto","userQQ","deletButton"};
    int[] to={R.id.login_userPhoto, R.id.login_userQQ,R.id.login_deleteButton};
    ArrayList<HashMap<String,Object>> list=null;
    ImageButton listIndicatorButton=null, deleteButtonOfEdit=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initXUtils();
        // adjustIsLogin(); //记住密码去内存中自行验证
        initViews();
        intdata();
        initListeners();
        userNameEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userNameEditText.getText().toString().equals("")) {
                    deleteButtonOfEdit.setVisibility(View.VISIBLE);
                } else {
                    deleteButtonOfEdit.setVisibility(View.GONE);
                }
            }
        });
        deleteButtonOfEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                currentUserImage.setImageResource(R.drawable.nvr);
                userNameEditText.setText("");
                passwordEditText.setText("");
                currentSelectedPosition=-1;
                deleteButtonOfEdit.setVisibility(View.GONE);

            }
        });
        if(currentSelectedPosition==-1){
            currentUserImage.setImageResource(R.drawable.nvr);
            userNameEditText.setText("");
            passwordEditText.setText("");
        } else{
            currentUserImage.setImageResource((Integer)list.get(currentSelectedPosition).get(from[0]));
            userNameEditText.setText((String)list.get(currentSelectedPosition).get(from[1]));
        }

        MyLoginlistAdapter adapter=new MyLoginlistAdapter(this, list, R.layout.layout_list_item, from, to);
        loginList.setAdapter(adapter);
        loginList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                currentUserImage.setImageResource((Integer) list.get(arg2).get(from[0]));
                userNameEditText.setText((String) list.get(arg2).get(from[1]));
                if(IsExist((String) list.get(arg2).get(from[1]))){
                    passwordEditText.setText(selectPAssword);
                }
                currentSelectedPosition = arg2;

                //相应完点击后List就消失，指示箭头反向！
                loginList.setVisibility(View.GONE);
                listIndicatorButton.setBackgroundResource(R.drawable.indicator_down);


            }


        });

        listIndicatorButton.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(isIndicatorUp){
                    isIndicatorUp=false;
                    isVisible=false;
                    listIndicatorButton.setBackgroundResource(R.drawable.indicator_down);
                    loginList.setVisibility(View.GONE);   //让ListView列表消失

                }
                else{
                    isIndicatorUp=true;
                    isVisible=true;
                    listIndicatorButton.setBackgroundResource(R.drawable.indicator_up);
                    loginList.setVisibility(View.VISIBLE);
                }
            }

        });


    }
    private void initXUtils() {
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
    private void intdata() {
        sharedPreferences=getSharedPreferences(SAVE, MODE_APPEND);
        i=0;

        url="http://10.201.1.151:8080/JavaAppWeb/httpServerLogin";
        map = (Map<String, String>) sharedPreferences.getAll();
        if (i == 0) {
            i = map.size()/4;
        }
        for(int j=0;j<i;j++){
            if(sharedPreferences.getBoolean("flag"+j,flag)){
                UserInfo user=new UserInfo(R.drawable.contact_0,sharedPreferences.getString("username"+j,""),
                        sharedPreferences.getString("password"+j,""),R.drawable.deletebutton);
                if(j==i-1){
                    Toast.makeText(MainActivity.this, "="+sharedPreferences.getString("username"+j,""),
                            Toast.LENGTH_SHORT).show();

                    passwordshow= sharedPreferences.getString("password" + j, "");
                    useshow=sharedPreferences.getString("username" + j, "");
                    userNameEditText.setText(useshow);
                    passwordEditText.setText(passwordshow);
                    addUser(user);

                }
                else{
                    addUser(user);
                }

            }

            //Toast.makeText(MainActivity.this, string,Toast.LENGTH_SHORT).show();

        }


        listIndicatorButton=(ImageButton)findViewById(R.id.qqListIndicator);


    }

    private void adjustIsLogin() {
        sharedPreferences=getSharedPreferences(SAVE,MODE_APPEND);
        boolean isLogin=sharedPreferences.getBoolean("islogin",false);
        String sharedusername=sharedPreferences.getString("username", "");
        String sharedpassword=sharedPreferences.getString("password", "");
        if(isLogin){
            userNameString=sharedPreferences.getString("username", "");

            Intent intent=new Intent(MainActivity.this,SucessActivity.class);
            intent.putExtra("name", userNameString);
            startActivity(intent);
            // finish();
        }
    }

    private void initListeners() {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                userNameString=passwordEditText.getText().toString();
                passwordString=passwordEditText.getText().toString();
             /*   if(isChecked){
                    if(userNameString.equals("123")&&passwordString.equals("123")){
                        sharedPreferences=getSharedPreferences(SAVE,MODE_PRIVATE);
                        editor=sharedPreferences.edit();
                        editor.putString("username",userNameString);
                        editor.putBoolean("islogin",true);
                        editor.commit();
                    }
                }else{
                    sharedPreferences=getSharedPreferences(SAVE,MODE_PRIVATE);
                    editor=sharedPreferences.edit();
                    editor.putString("username",userNameString);
                    editor.putBoolean("islogin",false);
                    editor.commit();
                }*/
            }
        });
    }

    private void initViews() {
        userNameEditText= (EditText) findViewById(R.id.loginusername);
        passwordEditText= (EditText) findViewById(R.id.loginpassword);
        checkBox= (CheckBox) findViewById(R.id.loginCheckbutton);
        list=new ArrayList<HashMap<String,Object>>();
        loginList= (ListView) findViewById(R.id.loginQQList);
        currentUserImage= (ImageView) findViewById(R.id.head_portrait);
        deleteButtonOfEdit=(ImageButton)findViewById(R.id.delete_button_edit);
    }

    public void Login(View view){
        if (isNetworkAvailable(MainActivity.this))
        {
            // Toast.makeText(getApplicationContext(), "当前有可用网络！", Toast.LENGTH_LONG).show();
            userNameString=userNameEditText.getText().toString();
            passwordString=passwordEditText.getText().toString();

            RequestParams params = new RequestParams(url);
            params.addQueryStringParameter("username", userNameString);
            Toast.makeText(MainActivity.this, "username"+userNameString, Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, "passwordString"+passwordString, Toast.LENGTH_SHORT).show();
            params.addQueryStringParameter("password", passwordString);
            //第二步：开始请求，设置请求方式，同时实现回调函数
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    //访问成功，参数其实就是PrintWriter写回的值
<<<<<<< HEAD

=======
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
                    if(result.toString().equals("Success")){
                        if(checkBox.isChecked()) {
                            if (!IsExist(userNameString)) {
                                sharedPreferences = getSharedPreferences(SAVE,MODE_APPEND);
                                editor = sharedPreferences.edit();

                                editor.putString("username" + i, userNameString);
                                editor.putString("password" + i, passwordString);
                                editor.putBoolean("islogin" + i, true);
                                editor.putBoolean("flag"+i,true);
                                editor.commit();
                                i++;

                            }
                            else if(IsExist(userNameString)){
                                sharedPreferences = getSharedPreferences(SAVE,MODE_APPEND);
                                editor = sharedPreferences.edit();
                                editor.putString("username" + m, userNameString);
                                editor.putString("password" + m, passwordString);
                                editor.putBoolean("islogin" + m, true);
                                editor.putBoolean("flag"+m,true);
                                editor.commit();

                            }

                        }
                        else{
                            if (IsExist(userNameString)){
                                sharedPreferences = getSharedPreferences(SAVE,MODE_APPEND);
                                editor = sharedPreferences.edit();
                                editor.putString("username"+m, userNameString);
                                editor.putString("password"+m, "");
                                editor.putBoolean("islogin" + m, false);
                                editor.putBoolean("flag"+m,true);
                                editor.commit();
                            }
                            else  {
                                sharedPreferences = getSharedPreferences(SAVE,MODE_APPEND);
                                editor = sharedPreferences.edit();
                                editor.putString("username"+i, userNameString);
                                editor.putString("password" + i, "");
                                editor.putBoolean("islogin" + i, false);
                                editor.putBoolean("flag"+i,true);
                                editor.commit();
                                i++;

                            }


                        }
<<<<<<< HEAD
=======
                        selectLoginUid(userNameString);
>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
                        String string=sharedPreferences.getString("username"+i,"");
                        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,ShiYouActivity.class);
                        intent.putExtra("name", userNameString);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(MainActivity.this, "账号密码不匹配"+result.toString(), Toast.LENGTH_SHORT).show();
                    }

                    //  textView.setText(result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    //访问失败
                    Log.e("abc", "错误原因：" + ex.getMessage());

                }

                @Override
                public void onCancelled(CancelledException cex) {
                    //访问取消
                }

                @Override
                public void onFinished() {
                    //访问完成
                }
            });

         /*   if(userNameString.equals("123")&&passwordString.equals("123")){

            sharedPreferences=getSharedPreferences(SAVE,MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.putString("username",userNameString);
                editor.putBoolean("islogin",true);
                editor.commit();
                Intent intent=new Intent(MainActivity.this,SucessActivity.class);
                intent.putExtra("name", userNameString);
                startActivity(intent);
            }else {
                sharedPreferences=getSharedPreferences(SAVE,MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.putString("username",userNameString);
                editor.putBoolean("islogin",false);
                editor.commit();
                userNameEditText.setText("");
                passwordEditText.setText("");
                userNameEditText.requestFocus();
            }*/


        }
        else
        {
            Toast.makeText(getApplicationContext(), "当前没有可用网络！", Toast.LENGTH_LONG).show();
        }

    }
<<<<<<< HEAD
=======

>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
    private void addUser(UserInfo user){
        HashMap<String,Object> map=new HashMap<String,Object>();

        map.put(from[0], user.userPhoto);
        map.put(from[1], user.userQQ);
        map.put(from[2], user.deleteButtonRes);

        list.add(map);
    }

    public void zhuce(View view) {
        String APPKEY = "12256474442b8";
        String APPSECRET = "0c01dbd6b83ff5a968e6329a0684916a";
        SMSSDK.initSDK(this, APPKEY, APPSECRET, true);
        RegisterPage registerPage = new RegisterPage();
        //3>获取注册界面信息
        registerPage.setRegisterCallback(new EventHandler() {
            //事件完成后调用
            @Override
            public void afterEvent(int event, int result, Object data) {
                //判断是否已经完成
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //获取数据data
                    HashMap<String, Object> maps = (HashMap<String, Object>) data;
                    //国家信息
                    String country = (String) maps.get("country");
                    //手机号信息
                    String phone = (String) maps.get("phone");
                    //4>提交验证信息
                    submitUserInfo(country, phone);
                }
            }
        });
        //5>验证成功，显示成功
        registerPage.show(MainActivity.this);

    }


    /**
     * 为了便于在适配器中修改登录界面的Activity，这里把适配器作为
     * MainActivity的内部类，避免了使用Handler，简化代码
     * @author DragonGN
     *
     */


    public class MyLoginlistAdapter extends BaseAdapter {
        protected Context context;
        protected ArrayList<HashMap<String,Object>> list;
        protected int itemLayout;
        protected String[] from;
        protected int[] to;
        public MyLoginlistAdapter(Context context,
                                  ArrayList<HashMap<String, Object>> list, int itemLayout,
                                  String[] from, int[] to) {
            super();
            this.context = context;
            this.list = list;
            this.itemLayout = itemLayout;
            this.from = from;
            this.to = to;

        }
        @Override
        public int getCount() {
            return list.size();
        }

        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        class ViewHolder{
            public ImageView userPhoto;
            public TextView userQQ;
            public ImageButton deleteButton;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
			/*
			currentPosition=position;
			不能使用currentPosition，因为每绘制完一个Item就会更新currentPosition
			这样得到的currentPosition将始终是最后一个Item的position
			*/

            if(convertView==null){
                convertView=LayoutInflater.from(context).inflate(itemLayout, null);
                holder=new ViewHolder();
                holder.userPhoto=(ImageView)convertView.findViewById(to[0]);
                holder.userQQ=(TextView)convertView.findViewById(to[1]);
                holder.deleteButton=(ImageButton)convertView.findViewById(to[2]);
                convertView.setTag(holder);
            }
            else{
                holder=(ViewHolder)convertView.getTag();
            }

            holder.userPhoto.setBackgroundResource((Integer)list.get(position).get(from[0]));
            holder.userQQ.setText((String)list.get(position).get(from[1]));
            holder.deleteButton.setBackgroundResource((Integer)list.get(position).get(from[2]));
            holder.deleteButton.setOnClickListener(new ListOnClickListener(position));

            return convertView;
        }

        class ListOnClickListener implements View.OnClickListener {

            private int position;


            public ListOnClickListener(int position) {
                super();
                this.position = position;
            }

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Log.e("sad", "" + list.get(position).get("userQQ"));
                if(IsExist(list.get(position).get("userQQ").toString())){
                    editor = sharedPreferences.edit();
                    Log.e("sad", "" + list.get(position).get("userQQ"));
                    editor.putBoolean("flag"+m, false);
                    editor.commit();
                }
                list.remove(position);


                //如果删除的就是当前显示的账号，那么将主界面当前显示的头像设置回初始头像
                if(position==currentSelectedPosition){
                    currentUserImage.setImageResource(R.drawable.nvr);
                    userNameEditText.setText("");
                    currentSelectedPosition=-1;
                }
                else if(position<currentSelectedPosition){
                    currentSelectedPosition--;    //这里小于当前选择的position时需要进行减1操作
                }

                listIndicatorButton.setBackgroundResource(R.drawable.indicator_down);
                loginList.setVisibility(View.GONE);   //让ListView列表消失，并且让游标向下指！

                MyLoginlistAdapter.this.notifyDataSetChanged();


            }
        }
    }
    public boolean IsExist(String usename){
        for(int j=0;j<i;j++){

            if(sharedPreferences.getString("username"+j,"").equals(usename)){
                if(sharedPreferences.getBoolean("islogin"+j,false)){
                    selectPAssword=sharedPreferences.getString("password"+j,"");
                    Toast.makeText(MainActivity.this, "selsect"+selectPAssword, Toast.LENGTH_SHORT).show();
                    m=j;
                    return true;
                }
                else{
                    m=j;
                    return  true;
                }


                //Toast.makeText(MainActivity.this, selectPAssword, Toast.LENGTH_SHORT).show();


            }



        }
        return  false;
    }

    //判断网络是否连接
    public boolean isNetworkAvailable(Activity activity)
    {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int  n= 0; n < networkInfo.length; n++)
                {
                    System.out.println(n + "===状态===" + networkInfo[n].getState());
                    System.out.println(n+ "===类型===" + networkInfo[n].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[n].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void submitUserInfo(String country, String phone) {
        Random r = new Random();
        String uid = Math.abs(r.nextInt()) + "";
        String nickName = "明若潇溪";
        SMSSDK.submitUserInfo(uid, nickName, null, country, phone);
        Intent intent=new Intent(MainActivity.this,zhuce.class);
        intent.putExtra("phone", phone);
        startActivity(intent);
    }

<<<<<<< HEAD
=======
    private void selectLoginUid(String userNameString) {
        //根据用户输入的有效手机号从user表中查出对应的uid
        String urlpath = "http://10.201.1.151:8080/JavaAppWeb/selectUserId";
        RequestParams params = new RequestParams(urlpath);
        params.addBodyParameter("phone", userNameString);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //查询成功
                int uid = Integer.parseInt(result);
                LoginUser.userid = uid;//将此uid设为全局uid
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

>>>>>>> cfe8914d43a90acdaef7a5d7a1c8ac04c5b8befa
}
