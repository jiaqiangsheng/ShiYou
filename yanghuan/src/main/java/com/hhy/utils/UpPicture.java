package com.hhy.utils;


import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.lxx.Utils.GetToken;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

/**
 * Created by 潇 on 2016/6/3.
 */
public class UpPicture {
    private static final String TAG = "MyText";
    GetToken mGetToken;
    String token;
    private Uri uri;
    //你的外联域名
    String urlPath = "http://o6nj6n5ea.bkt.clouddn.com";
    //你创建的空间名
    String name = "name";
    ImageView mImageView;
    //imageName为上传到空间中的名称，为了确保唯一，我用了(id+系统当前时间)命名
    String imageName;
    String path;
    Context mContext;

    public UpPicture(Context mContext) {
        this.mContext = mContext;
    }

    public void upload() {
        UploadManager uploadManager = new UploadManager();
        mGetToken = new GetToken();
        token = mGetToken.getToken(name);
        imageName = "hhy" + "/" + System.currentTimeMillis() + ".jpg";
        if (path != null) {
            uploadManager.put(path, imageName, token,
                    new UpCompletionHandler() {
                        @Override
                        public void complete(String key, ResponseInfo info,
                                             JSONObject response) {
                            //Log.e("ppp", "000"+imageName+"");
                            //info.statusCode 回调状态码
                            if (info.statusCode == 200) {
                                Log.e(TAG, "" + info.isOK());
                                Toast.makeText(mContext, "完成上传", Toast.LENGTH_LONG).show();
                            } else {

                                Toast.makeText(mContext, info.statusCode + "上传失败", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, null);

        }

    }
}

