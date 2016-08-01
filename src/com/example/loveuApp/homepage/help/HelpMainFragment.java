package com.example.loveuApp.homepage.help;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import com.example.loveuApp.bean.helpModel;
import com.example.loveuApp.bean.userModel;
import com.example.loveuApp.homepage.help.adapter.HelpListAdapter;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.Service;
import com.example.loveuApp.service.helpService;
import com.example.loveuApp.service.userService;
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


public class HelpMainFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.helpmain, container, false);
        return view;
    }

    private PullToRefreshListView listView;
    private HelpListAdapter adapter;
    private List<helpModel> models;
    private List<userModel> urls;
    private OnSuccessBack back;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView= (PullToRefreshListView) getView().findViewById(R.id.helpListView);
        models=new ArrayList<>();
        urls=new ArrayList<>();

        getData();

        back=new OnSuccessBack() {
            @Override
            public void back() {
                Log.i("models",models.size()+"");
                Log.i("urls",urls.size()+"");
                listView.onRefreshComplete();
                adapter=new HelpListAdapter(getActivity(),models,urls);
                listView.setAdapter(adapter);
            }
        };
        //刷新加载
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(!isNetworkConnected(getActivity())){
                    Toast.makeText(getActivity(),"网络错误",Toast.LENGTH_SHORT).show();
                    listView.onRefreshComplete();
                }
                urls=new ArrayList<>();
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                return;
            }
        });
        //点击回调
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog(i-1);
            }
        });
    }

    /**
     * 获取数据源函数getData(),getUrls()
     * @return
     */
    private List<helpModel> getData(){
        Log.i("msg","getData()");
        String url="help";
        RequestParams params=new RequestParams();
        helpService service=new helpService();
        params.add("page","1");
        service.get(getActivity(), url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                models= (List<helpModel>) object;
                models.remove(0);
                getUrls();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });
        return models;
    }

    public List<userModel> getUrls() {
        Log.i("msg","getURLs()");
        List<String>ids=new ArrayList<>();
        List<helpModel> mmo=models;
        Log.i("msg","getData():"+mmo.size());
        for(int i=0;i<mmo.size();i++){
            ids.add(mmo.get(i).getUserId()+"");
            Log.i("msg",mmo.get(i).getUserId()+"");
        }

        String url="data";
        for (String id:ids){
            RequestParams params=new RequestParams();
            userService service=new userService();
            params.put("UserId",id);
            service.get(getActivity(), url, params, new Listener() {
                @Override
                public void onSuccess(Object object) {
                    urls.add((userModel) object);
                    Log.i("urls",urls.toString());
                    if(urls.size()==models.size())
                        back.back();
                }

                @Override
                public void onFailure(String msg) {

                }
            });
        }
        return urls;
    }

    /**
     * 回调接口
     */
    private interface OnSuccessBack{
        public void back();
    }

    /**
     * 联网判断
     * @param context
     * @return
     */
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

    /**
     * 详细信息
     * @param i
     */

    public void showDialog(int i){
        //Theme_DeviceDefault_Light_Dialog
        AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Light_Dialog);
        dialog.setTitle("金额:  "+models.get(i).getHelpMoney()+"元");
        dialog.setMessage("        "+models.get(0).getHelpInformation());
        dialog.setPositiveButton("答应他?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //agreeHelp(i);
            }
        });
        dialog.setNegativeButton("残忍拒绝", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}});
        new InnoAsyncTask(dialog).execute(urls.get(i).getUserPhoto());
    }

    private void agreeHelp(int i) {
        String url="gethelp";
        Service service=new Service();
        RequestParams request=new RequestParams();
        request.put("UserPhone","11111111111");
        request.put("SecretKey","4e91556f8169307bec052d62b9ea8606");
        request.put("HelpId",i);
        service.post(getActivity(), url, request, new Listener() {
            @Override
            public void onSuccess(Object object) {
                helpModel model=new Gson().fromJson(new String((byte[]) object),helpModel.class);
                if(1==model.getstate()){
                    Toast.makeText(getActivity(), model.getMsg(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), model.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(String msg) {
            }
        });
    }

    private class InnoAsyncTask extends AsyncTask<String,Void,Bitmap> {

        AlertDialog.Builder dialog;
        public InnoAsyncTask(AlertDialog.Builder dialog) {
            this.dialog=dialog;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url=strings[0];
            Log.i("url",url);
            Bitmap bitmap=null;
            URLConnection connection;
            InputStream inputStream;
            try {
                connection=new URL(url).openConnection();
                inputStream=connection.getInputStream();
                bitmap= BitmapFactory.decodeStream(inputStream);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Drawable drawable =new BitmapDrawable(PhotoCut.toRoundBitmap(bitmap));
            dialog.setIcon(drawable);
            dialog.show();
            super.onPostExecute(bitmap);
        }
    }
}