package com.example.loveuApp.service;

import android.content.Context;
import com.example.loveuApp.bean.UserModel;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.util.HttpRequest;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;


/**
 * Created by dy on 2016/7/26.
 */
public class UserService {

    public void get(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.get(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                UserModel model=new Gson().fromJson(new String(bytes),UserModel.class);
                listener.onSuccess(model);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                UserModel model=new Gson().fromJson(new String(bytes),UserModel.class);
                listener.onFailure(model.getMsg());
            }
        });
    }

    public void post(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.post(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                UserModel model=new Gson().fromJson(new String(bytes),UserModel.class);
                listener.onSuccess(model);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                listener.onFailure("网络请求失败");
            }
        });
    }

}
