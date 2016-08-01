package com.example.loveuApp.homepage.food;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.foodModel;
import com.example.loveuApp.bean.userModel;
import com.example.loveuApp.homepage.food.adapter.FoodMainListAdapter;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.register.GuoQingZhuangBActivity;
import com.example.loveuApp.service.Service;
import com.example.loveuApp.service.foodService;
import com.example.loveuApp.service.userService;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FoodMainFragment extends Fragment{

    private PullToRefreshListView mListView;
    private FoodMainListAdapter mAdapter;
    private List<foodModel> data;
    private int page = 1;
    private String url = "http://183.175.14.250:5000/data";
    private List<userModel> modelList;
    private boolean firstAdapter = true;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foodmain, container, false);
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = (PullToRefreshListView) getActivity().findViewById(R.id.foodfragment_listview);
        modelList = new ArrayList<>();
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(!isNetworkConnected(getActivity())){
                    Toast.makeText(getActivity(),"网络错误",Toast.LENGTH_SHORT).show();
                    mListView.onRefreshComplete();
                }
                getData();
               // mAdapter.setData();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                return;
            }
        });
        getData();
    }

    private void getData(){
        RequestParams params = new RequestParams();
        params.put("page",page+"");
        foodService service = new foodService();
        service.get(getActivity(), "food", params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                data = (List<foodModel>) object;
                for (int i =0;i<data.size();i++){
                    Log.i("data url",data.get(i).getUserId()+"");
                }
                data.remove(0);
                new Task().execute(url);
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }
        });

    }
    class Task extends  AsyncTask<String,Void,String[]>{
        @Override
        protected String[] doInBackground(String... strings) {
            return sendGet(strings[0]);
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);

            if (!firstAdapter){
                Toast.makeText(getActivity(),"刷新",Toast.LENGTH_SHORT).show();
                //mAdapter.setData(data,modelList);
               String[] URLS = new String[modelList.size()];
                for (int i =0;i<modelList.size();i++){
                    URLS[i]=modelList.get(i).getUserPhoto();
                    Log.i("adapter url",URLS[i]+"");
                }
                //mAdapter.modelList.clear();
                mAdapter.modelList = modelList;
                mAdapter.data = data;
                mAdapter.URLS = URLS;
                Log.i("size",mAdapter.modelList.size()+"");
                mAdapter.notifyDataSetChanged();
                mListView.onRefreshComplete();
                return ;
            }
            mAdapter = new FoodMainListAdapter(data,getActivity(),mListView,strings,modelList);
            mListView.setAdapter(mAdapter);
            firstAdapter = false;
            mListView.onRefreshComplete();
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        final int j = i-1;


                    new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Light_Dialog)
                            .setTitle("更多信息")
                            .setMessage("        "+data.get(j).getFoodInformation())
                            .setPositiveButton("确定接受", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    RequestParams params = new RequestParams();
                                    params.put("UserPhone","11111111111");
                                    params.put("SecretKey","b7db48afb289f63d04d8f053824955bb");
                                    params.put("FoodId", data.get(j).getFoodId());
                                    Service service = new Service();
                                    service.get(getActivity(), "getfood", params, new Listener() {
                                        @Override
                                        public void onSuccess(Object object) {
                                             foodModel model=new Gson().fromJson(new String(( byte[])object),foodModel.class);;
                                            if (model.getstate()==1){
                                                Toast.makeText(getActivity(),"成功",Toast.LENGTH_SHORT).show();
                                            }else
                                                Toast.makeText(getActivity(),"网络错误",Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onFailure(String msg) {
                                            Toast.makeText(getActivity(),""+msg,Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .setIcon(R.drawable.empty_drafts)
                            .show();
                }
            });
        }
    }

    public  String[] sendGet(String url) {
        //System.out.println("get");
        String [] URLS = new String[data.size()];
        for (int i =0;i<data.size();i++) {
            String result = "";
            BufferedReader in = null;

            try {
                String urlNameString = url + "?UserId=" + data.get(i).getUserId();
                URL realUrl = new URL(urlNameString);
                // 打开和URL之间的连接
                URLConnection connection = realUrl.openConnection();
                // 设置通用的请求属性
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 建立实际的连接
                connection.connect();
                // 获取所有响应头字段
                Map<String, List<String>> map = connection.getHeaderFields();
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                System.out.println("发送GET请求出现异常！" + e);
                e.printStackTrace();
            }
            // 使用finally块来关闭输入流
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            userModel model = new Gson().fromJson(result, userModel.class);
            // Log.i("model+++++++++++++++++++",model.getUserPhoto());
            modelList.add(model);
            URLS[i] = model.getUserPhoto();
        }
        return URLS;
    }
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}


