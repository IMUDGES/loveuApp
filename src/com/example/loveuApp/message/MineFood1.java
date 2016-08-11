package com.example.loveuApp.message;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.foodModel;
import com.example.loveuApp.message.adapter.FoodAdapter;
import com.example.loveuApp.model.FoodModel;
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
public class MineFood1 extends Activity {

    private ListView mListView;
    private FoodAdapter mAdapter;
    private List<foodModel> data;
    private String url = "";
    private int firstnum = 0;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minefragment);
        Log.i("in", "in");
        tv = (TextView) findViewById(R.id.food_title);
        tv.setText("我接受的");
        initInfo();
        mListView = (ListView) findViewById(R.id.minefragment_listvew);
        new MyTask().execute("");
    }

    class MyTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... strings) {
            sendGet("http://183.175.14.250:5000/mygetfood_notoverdue", 1);
            sendGet("http://183.175.14.250:5000/mygetfood_overdue", 2);
            if (data == null) {
                Log.i("data", "null");
                return null;
            } else
                Log.i("data", "!null");
            String[] URLS = new String[data.size()];
            Log.i("size", data.size() + "");
            for (int i = 0; i < data.size(); i++) {

                URLS[i] = data.get(i).getUserPhoto();
                Log.i("url", URLS[i]);
            }

            return URLS;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            if (strings == null)
                return;
            mAdapter = new FoodAdapter(data, getApplicationContext(), firstnum, strings, mListView);
            Log.i("firstnum",""+firstnum);
            mListView.setAdapter(mAdapter);
        }
    }

    public void sendGet(String url, int i) {
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
        FoodModel model = new Gson().fromJson(result, FoodModel.class);
        Log.i("model", result);
        if (model == null) {
            Log.i("model", "null");
        }
        if (model.getNum() == 0)
            return;

        if (i == 1) {
            data = model.getFooddata();
        } else
            data.addAll(model.getFooddata());
        if (data == null)
            firstnum += 0;
        else
            firstnum += data.size();
        Log.i("datasize",data.size()+"");

    }


    private String UserPhone, SecretKey;

    private void initInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Activity.MODE_PRIVATE);
        UserPhone = sharedPreferences.getString("UserPhone", "");
        SecretKey = sharedPreferences.getString("SecretKey", "");
    }
}
