package com.example.loveuApp.service;

import android.content.Context;
import com.example.loveuApp.bean.giveData;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.util.HttpRequest;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

/**
 * Created by dy on 2016/7/26.
 */
public class giveService {

    public void get(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.get(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                giveData giveDatas=new Gson().fromJson(new String(bytes),giveData.class);
                listener.onSuccess(giveDatas);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                //giveData giveDatas=new Gson().fromJson(new String(bytes),giveData.class);
                listener.onFailure("网络请求失败");
            }
        });
    }

    public void post(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.post(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                giveData giveDatas=new Gson().fromJson(new String(bytes),giveData.class);
                listener.onSuccess(giveDatas);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                giveData giveDatas=new Gson().fromJson(new String(bytes),giveData.class);
                listener.onFailure(giveDatas.getMsg());
            }
        });
    }

}
