package com.example.loveuApp.message;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.loveuApp.R;
import com.example.loveuApp.homepage.HomePageFragment1;
import com.example.loveuApp.message.adapter.WoFaChuAdapter;
import com.example.loveuApp.model.FoodModel;
import com.example.loveuApp.model.MineSendModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by caolu on 2016/8/10.
 */
public class YuwoFaChu extends Fragment {

    private ListView mListView;
    private WoFaChuAdapter mAdapter;
    private String url = "183.175.14.250:5000/myissue";
    private String UserPhone, SecretKey;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yuwofachu, container, false);
        mListView = (ListView) view.findViewById(R.id.yuwofachulistview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new WoFaChuAdapter(getActivity());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(getActivity(), MineFood.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), MineGive.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), MineHelp.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), MinePai.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), MineRun.class));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), MineXue.class));
                        break;
                }
            }
        });
        //new MyTaks().execute(url);

    }

    class MyTaks extends AsyncTask<String, Void, MineSendModel> {

        @Override
        protected MineSendModel doInBackground(String... strings) {
            return sendGet(strings[0]);
        }

        @Override
        protected void onPostExecute(MineSendModel model) {
            super.onPostExecute(model);
            if (model == null)
                return;

        }
    }

    public MineSendModel sendGet(String url) {
        //System.out.println("get");

        Log.i("1++++++++++++++++++++++++++", "1");
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?UserPhone=" + UserPhone + "&SecretKey=" + SecretKey;
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
        Log.i("2++++++++++++++++++++++++++", "2");
        MineSendModel model = new Gson().fromJson(result, MineSendModel.class);
        if (model == null)
            return null;
        MineSendModel data;
        data = new MineSendModel();
        data.setMyissuefood(model.getMyissuefood());
        data.setMyissuefood_overdue(model.getMyissuefood_overdue());
        data.setMyissuegive(model.getMyissuegive());
        data.setMyissuegive_overdue(model.getMyissuegive_overdue());
        data.setMyissuehelp(model.getMyissuehelp());
        data.setMyissuehelp_overdue(model.getMyissuehelp_overdue());
        data.setMyissuepai(model.getMyissuepai());
        data.setMyissuepai_overdue(model.getMyissuepai_overdue());
        data.setMyissuexue(model.getMyissuexue());
        data.setMyissuexue_overdue(model.getMyissuexue_overdue());
        data.setMyissuerun(model.getMyissuerun());
        data.setMyissuerun_overdue(model.getMyissuerun_overdue());
        if (model != null)
            Log.i("model", "!null");
        return data;
    }


    private void initInfo() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Activity.MODE_PRIVATE);
        UserPhone = sharedPreferences.getString("UserPhone", "");
        SecretKey = sharedPreferences.getString("SecretKey", "");
    }
}
