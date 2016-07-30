package com.example.loveuApp.service;

import android.content.Context;
import com.example.loveuApp.bean.xueModel;
import com.example.loveuApp.listener.Listener;
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
public class xueService {

    public void get(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.get(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                List<xueModel> models=new Gson().fromJson(new String(bytes),new TypeToken<LinkedList<xueModel>>(){}.getType());
                listener.onSuccess(models);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                xueModel model=new Gson().fromJson(new String(bytes),xueModel.class);
                listener.onFailure(model.getMsg());
            }
        });
    }

    public void post(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.post(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                List<xueModel> models=new Gson().fromJson(new String(bytes),new TypeToken<LinkedList<xueModel>>(){}.getType());
                listener.onSuccess(models);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                xueModel model=new Gson().fromJson(new String(bytes),xueModel.class);
                listener.onFailure(model.getMsg());
            }
        });
    }

}
