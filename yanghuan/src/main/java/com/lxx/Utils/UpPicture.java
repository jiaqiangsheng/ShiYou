package com.lxx.Utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lxx.Utils.GetToken;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 潇 on 2016/6/3.
 */
public class UpPicture {
    private static final String TAG = "MyText";
    GetToken mGetToken;
    String token;
    private Uri uri;
    String urlPath="http://o6nj6n5ea.bkt.clouddn.com";
    //你创建的空间名
    String name = "name";
    List<String> list;
    ImageView mImageView;
    TextView mTextView;
    //记录进度
    double per;
    //判断是否传完
    boolean isPaused=false;
    public List<String> upload(List<String> list,List<String> namelist) {
        final List<String> pathlist=new ArrayList<>();
        UploadManager uploadManager = new UploadManager();
        mGetToken = new GetToken();
        token = mGetToken.getToken(name);
        for(int i=0;i<list.size();i++){
            uploadManager.put(list.get(i), namelist.get(i), token,
                    new UpCompletionHandler() {
                        @Override
                        public void complete(String key, ResponseInfo info,
                                             JSONObject response) {
                            //Log.e("ppp", "000"+imageName+"");
                            if (info.statusCode == 200) {
                                Log.e(TAG, "" + info.isOK());
                                Log.e("path","等待");

                            } else {

                                Log.e("path","上传失败");
                            }
                        }
                    }, null);

        }
        return pathlist;
    }
}

