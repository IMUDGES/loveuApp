package com.example.loveuApp.service;

import android.content.Context;
import com.example.loveuApp.bean.giveCommentModel;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.util.HttpRequest;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.apache.http.Header;

/**
 * Created by dy on 2016/7/26.
 */
public class giveCommentService {

    public void get(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.get(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                giveCommentModel model=new Gson().fromJson(new String(bytes),giveCommentModel.class);
                listener.onSuccess(model);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                giveCommentModel model=new Gson().fromJson(new String(bytes),giveCommentModel.class);
                listener.onFailure(model.getMsg());
            }
        });
    }

    public void post(Context context, String url, RequestParams params, Listener listener){
        HttpRequest.post(context, url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                giveCommentModel model=new Gson().fromJson(new String(bytes),giveCommentModel.class);
                listener.onSuccess(model);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                giveCommentModel model=new Gson().fromJson(new String(bytes),giveCommentModel.class);
                listener.onFailure(model.getMsg());
            }
        });
    }
}
