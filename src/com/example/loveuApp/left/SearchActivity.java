package com.example.loveuApp.left;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.*;
import com.example.loveuApp.left.adapter.SearchListViewAdapter;
import com.example.loveuApp.left.model.AllModel;
import com.example.loveuApp.left.model.OnlyModel;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.Service;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caolu on 2016/8/8.
 */
public class SearchActivity extends Activity{

    private PullToRefreshListView listview;
    private AllModel model;
    private List<OnlyModel> object_All;
    private CallBack call;
    private SearchListViewAdapter adapter;
    private boolean state=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchmain);


        init();
        call=new CallBack() {
            @Override
            public void back() {
                addData();
                if(!state){
                    state=true;
                    adapter=new SearchListViewAdapter(object_All,getApplicationContext());
                    listview.setAdapter(adapter);
                    listview.onRefreshComplete();
                }else{
                    adapter.notifyDataSetChanged();
                    listview.onRefreshComplete();
                }
            }
        };

        getData();
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                listview.onRefreshComplete();
            }
        });
    }

    private void init() {
        listview= (PullToRefreshListView) findViewById(R.id.leftmain_search_listview);
    }

    public void getData(){
        Service service=new Service();
        RequestParams params=new RequestParams();
        Intent intent=getIntent();
        Log.i("information", intent.getStringExtra("information_sou")+"");
        params.put("Message",intent.getStringExtra("information_sou")+"");
        service.get(getApplicationContext(), "searchall", params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                String result=new String((byte[]) object);
                model=new Gson().fromJson(result,AllModel.class);
                call.back();
            }

            @Override
            public void onFailure(String msg) {
                listview.onRefreshComplete();
                Toast.makeText(SearchActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addData(){
        object_All=new ArrayList<>();
        /**
         * 处理Json串
         */
        if(model.getFood().getNum()!=null){
            List<foodModel> foodlist=model.getFood().getFoodlist();
            for(foodModel model:foodlist){
                OnlyModel only=new OnlyModel();
                only.setKey("food");
                only.setObject(model);
                object_All.add(only);
            }
        }
        if(model.getGive().getNum()!=null){
            List<giveModel> givelist=model.getGive().getGivelist();
            for(giveModel model:givelist){
                OnlyModel only=new OnlyModel();
                only.setKey("give");
                only.setObject(model);
                object_All.add(only);
            }
        }
        if(model.getHelp().getNum()!=null){
            List<helpModel> helplist=model.getHelp().getHelplist();
            for(helpModel model:helplist){
                OnlyModel only=new OnlyModel();
                only.setKey("help");
                only.setObject(model);
                object_All.add(only);
            }
        }
        if(model.getPai().getNum()!=null){
            List<paiModel> pailist=model.getPai().getPailist();
            for(paiModel model:pailist){
                OnlyModel only=new OnlyModel();
                only.setKey("pai");
                only.setObject(model);
                object_All.add(only);
            }
        }
        if(model.getRun().getNum()!=null){
            List<runModel> runlist=model.getRun().getRunlist();
            for(runModel model:runlist){
                OnlyModel only=new OnlyModel();
                only.setKey("run");
                only.setObject(model);
                object_All.add(only);
            }
        }
        if(model.getXue().getNum()!=null){
            List<xueModel> xuelist=model.getXue().getXuelist();
            for(xueModel model:xuelist){
                OnlyModel only=new OnlyModel();
                only.setKey("xue");
                only.setObject(model);
                object_All.add(only);
            }
        }
    }

    private interface CallBack{
        public void back();
    }
}
