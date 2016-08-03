package com.example.loveuApp.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.util.HttpFileRequest;
import com.example.loveuApp.util.HttpRequest;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by caolu on 2016/8/3.
 */
public class PhotoService extends  HttpFileRequest{

    public static void FileUpload(Context context, String url,File file,String UserPhone,String SecretKey, Listener listener)throws FileNotFoundException {
        RequestParams params=new RequestParams();
        params.put("photo",file);
        params.put("UserPhone",UserPhone);
        params.put("SecretKey",SecretKey);
        Log.i("message",UserPhone);
        Log.i("message",SecretKey);
        Log.i("message",file.getPath());
        if (file.exists() && file.length() > 0) {
            // 上传文件
            HttpRequest.post(context,url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    listener.onSuccess(responseBody);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    listener.onFailure(String.valueOf(responseBody));
                }
            });
        } else {
            listener.onFailure((file+"不存在"));
        }
    }
}

