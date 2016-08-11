package com.example.loveuApp.message;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.helpModel;
import com.example.loveuApp.homepage.help.adapter.HelpListAdapter;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.HelpModel;
import com.example.loveuApp.service.Service;
import com.example.loveuApp.service.helpService;
import com.example.loveuApp.util.PhotoCut;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caolu on 2016/8/10.
 */
public class MineHelp1 extends Activity{


    private ListView listView;
    private HelpListAdapter adapter;
    private HelpModel models;
    private List<helpModel> helpModels;
    private GoBack goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minefragment);
        listView = (ListView) findViewById(R.id.minefragment_listvew);
        helpModels=new ArrayList<>();
        getData();

        goBack=new GoBack() {
            @Override
            public void back() {
                if(helpModels.size()!=0){
                    adapter=new HelpListAdapter(MineHelp1.this,helpModels);
                    listView.setAdapter(adapter);
                }
            }
        };
    }

    /**
     * 刷新获取数据源函数getData()
     * @return
     */
    private void getData(){

        SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
        String UserPhone = sharedPreferences.getString("UserPhone", "");
        String SecretKey = sharedPreferences.getString("SecretKey", "");
        Log.i("msg","getData()");
        String url="mygethelp_notoverdue";
        RequestParams params=new RequestParams();
        helpService service=new helpService();
        params.add("UserPhone",UserPhone);
        params.add("SecretKey",SecretKey);
        service.get(MineHelp1.this, url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                models= (HelpModel) object;
                if(models.getNum()==0||models.getState()==0){
                    getData_();
                    return;
                }
                helpModels=models.getHelpdata();
                getData_();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    private void getData_(){

        SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
        String UserPhone = sharedPreferences.getString("UserPhone", "");
        String SecretKey = sharedPreferences.getString("SecretKey", "");
        Log.i("msg","getData()");
        String url="mygethelp_overdue";
        RequestParams params=new RequestParams();
        helpService service=new helpService();
        params.add("UserPhone",UserPhone);
        params.add("SecretKey",SecretKey);
        service.get(MineHelp1.this, url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                models= (HelpModel) object;
                if(models.getNum()==0||models.getState()==0){
                    goBack.back();
                    return;
                }
                if(helpModels.size()==0){
                    helpModels=models.getHelpdata();
                }else{
                    helpModels.addAll(models.getHelpdata());
                }
                goBack.back();
            }
            @Override
            public void onFailure(String msg) {
            }
        });
    }

    private interface GoBack{
        public void back();
    }
}
