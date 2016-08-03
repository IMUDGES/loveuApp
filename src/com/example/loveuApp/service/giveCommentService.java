package com.example.loveuApp.service;

import android.content.Context;
import com.example.loveuApp.bean.giveCommentData;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.util.HttpRequest;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;


public class giveCommentService {

    public void get(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.get(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                giveCommentData giveCommentData=new Gson().fromJson(new String(bytes),giveCommentData.class);
                listener.onSuccess(giveCommentData);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                giveCommentData giveCommentData=new Gson().fromJson(new String(bytes),giveCommentData.class);
                listener.onFailure(giveCommentData.getMsg());
            }
        });
    }

    public void post(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.post(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                giveCommentData giveCommentData=new Gson().fromJson(new String(bytes),giveCommentData.class);
                listener.onSuccess(giveCommentData);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                giveCommentData giveCommentData=new Gson().fromJson(new String(bytes),giveCommentData.class);
                listener.onFailure(giveCommentData.getMsg());
            }
        });
    }
}
