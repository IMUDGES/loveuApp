package com.example.loveuApp.homepage.xue;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.foodModel;
import com.example.loveuApp.bean.xueModel;
import com.example.loveuApp.homepage.food.adapter.FoodMainListAdapter;
import com.example.loveuApp.homepage.xue.adapter.XueMainListAdapter;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.RunModel;
import com.example.loveuApp.model.XueModel;
import com.example.loveuApp.service.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class XueMainFragment extends Fragment{

    private PullToRefreshListView mListView;
    private XueMainListAdapter mAdapter;
    private List<xueModel> data;
    private String url = "http://183.175.14.250:5000/xue";
    private int page = 1;
    private boolean firstAdapter = true;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xuemain, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        firstAdapter = true;
        mListView = (PullToRefreshListView) getActivity().findViewById(R.id.xuefragment_listview);
        data = new ArrayList<>();
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (!isNetworkConnected(getActivity())) {
                    Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();
                    mListView.onRefreshComplete();
                }
                page = 1;
                new Task().execute(url);
                // mAdapter.setData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                new Task().execute(url);

            }
        });
        new Task().execute(url);
    }

    class Task extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... strings) {
            return sendGet(strings[0]);
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            if (strings == null) {
                mListView.onRefreshComplete();
                return;
            }
            if (!firstAdapter) {

                Log.i("数量",data.size()+" ");
                Log.i("数量",strings.length+" ");
                mAdapter.data = data;
                mAdapter.URLS = strings;
                mAdapter.mFirstIn = true;
                mAdapter.notifyDataSetChanged();
                mListView.onRefreshComplete();
                return;
            }
            mAdapter = new XueMainListAdapter(data, getActivity(), mListView, strings);
            mListView.setAdapter(mAdapter);
            firstAdapter = false;
            mListView.onRefreshComplete();
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    final int j = i - 1;


                    new AlertDialog.Builder(getActivity(), android.R.style.Theme_DeviceDefault_Light_Dialog)
                            .setTitle("更多信息")
                            .setMessage("        " + data.get(j).getXueInformation())
                            .setPositiveButton("确定接受", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    RequestParams params = new RequestParams();
                                    params.put("UserPhone", "11111111111");
                                    params.put("SecretKey", "b7db48afb289f63d04d8f053824955bb");
                                    params.put("FoodId", data.get(j).getXueId());
                                    Service service = new Service();
                                    service.post(getActivity(), "getxue", params, new Listener() {
                                        @Override
                                        public void onSuccess(Object object) {
                                            XueModel model = new Gson().fromJson(new String((byte[]) object), XueModel.class);
                                            ;
                                            if (model.getState() == 1) {
                                                Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
                                                new Task().execute(url);
                                                mAdapter.notifyDataSetChanged();
                                            } else
                                                Toast.makeText(getActivity(), "网络错误", Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onFailure(String msg) {
                                            Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
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


    public String[] sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?page=" + page;
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
        XueModel model = new Gson().fromJson(result,XueModel.class);
        if (model == null)
            return null;
        if (page == 1) {
            data = model.getXuedata();
            if (model.getNum() == 0)
                return null;

        }else {
            if (model.getNum() == 0)
                return null;
            data.addAll(model.getXuedata());
        }
        String[] URLS = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            URLS[i] = data.get(i).getUserPhoto();
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
