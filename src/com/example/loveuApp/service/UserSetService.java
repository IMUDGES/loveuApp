package com.example.loveuApp.service;

import android.content.Context;
import android.util.Log;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.UserSetModel;
import com.example.loveuApp.util.HttpRequest;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

/**
 * Created by 1111 on 2016/8/11.
 */
public class UserSetService {
    public void get(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.get(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Log.i("json",new String(bytes));
                UserSetModel model=new Gson().fromJson(new String(bytes),UserSetModel.class);
                listener.onSuccess(model);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                UserSetModel model=new Gson().fromJson(new String(bytes),UserSetModel.class);
                listener.onFailure(model.getMsg());
            }
        });
    }

    public void post(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.post(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                UserSetModel model=new Gson().fromJson(new String(bytes),UserSetModel.class);
                listener.onSuccess(model);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                UserSetModel model=new Gson().fromJson(new String(bytes),UserSetModel.class);
//                listener.onFailure(model.getMsg());
            }
        });
    }
}
