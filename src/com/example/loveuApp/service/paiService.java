package com.example.loveuApp.service;

import android.content.Context;
import android.util.Log;
import com.example.loveuApp.bean.paiModel;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.PaiModel;
import com.example.loveuApp.util.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dy on 2016/7/26.
 */
public class paiService {

    public void get(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.get(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Log.i("paiInformation",new String(bytes));
                PaiModel models=new Gson().fromJson(new String(bytes),PaiModel.class);
                listener.onSuccess(models);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                //Log.i("service",new String(bytes));
                listener.onFailure("网络请求失败");
            }
        });
    }

    public void post(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.post(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                PaiModel models=new Gson().fromJson(new String(bytes),PaiModel.class);
                listener.onSuccess(models);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.i("service",new String(bytes));
                listener.onFailure("网络请求失败");
            }
        });
    }

}
