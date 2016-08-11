package com.example.loveuApp.service;

import android.content.Context;
import android.util.Log;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.util.HttpRequest;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

/**
 * Created by dy on 2016/8/1.
 */
public class Service {

    public void get(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.get(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                listener.onSuccess(bytes);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                //Log.i("tag",new String(bytes));
                listener.onFailure("网络请求失败");
            }
        });
    }

    public void post(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.post(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                listener.onSuccess(bytes);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                //Log.i("tag",new String(bytes));
                listener.onFailure("网络请求失败");
            }
        });
    }

}
