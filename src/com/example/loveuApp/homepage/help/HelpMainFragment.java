package com.example.loveuApp.homepage.help;

import android.app.AlertDialog;

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

import com.example.loveuApp.homepage.HomePageFragment1;
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


public class HelpMainFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.helpmain, container, false);
        return view;
    }

    private PullToRefreshListView listView;
    private HelpListAdapter adapter;
    private HelpModel models;
    private List<helpModel> helpModels;
    private OnSuccessBack back;

    private int PAGEINT=2;
    private boolean REFU=true;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        REFU=true;
        listView= (PullToRefreshListView) getView().findViewById(R.id.helpListView);
        helpModels=new ArrayList<>();

        getData();

        back=new OnSuccessBack() {
            @Override
            public void Refuback() {
                listView.onRefreshComplete();
                if(REFU) {
                    Log.i("information","REFU==true");
                    adapter = new HelpListAdapter(getActivity(), helpModels);
                    listView.setAdapter(adapter);
                    REFU=false;
                }else{
                    Log.i("information","REFU==false");
                    adapter.setModels(helpModels);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void Pullback() {
                Log.i("models",helpModels.size()+"");
                listView.onRefreshComplete();
                adapter.setModels(helpModels);
                adapter.notifyDataSetChanged();
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
                PAGEINT=2;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(!isNetworkConnected(getActivity())){
                    Toast.makeText(getActivity(),"网络错误",Toast.LENGTH_SHORT).show();
                    listView.onRefreshComplete();
                }
                pullDown();
            }
        });
        //点击回调
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("i",i+"");
                showDialog(i-1);
            }
        });
    }



    /**
     * 刷新获取数据源函数getData()
     * @return
     */
    private HelpModel getData(){
        Log.i("msg","getData()");
        String url="help";
        RequestParams params=new RequestParams();
        helpService service=new helpService();
        params.add("page","1");
        service.get(getActivity(), url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                models= (HelpModel) object;
                if(models.getNum()==0){
                    listView.onRefreshComplete();
                    return;
                }
                helpModels=models.getHelpdata();
                back.Refuback();
            }

            @Override
            public void onFailure(String msg) {

                listView.onRefreshComplete();
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });
        return models;
    }

    /**
     * pullDown()
     */
    private void pullDown() {
        String url="help";
        RequestParams params=new RequestParams();
        helpService service=new helpService();
        params.add("page",PAGEINT+"");
        PAGEINT++;
        service.get(getActivity(), url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                if(((HelpModel)object).getNum()==0){
                    listView.onRefreshComplete();
                    return;
                }
                List<helpModel> mm= ((HelpModel) object).getHelpdata();
                helpModels.addAll(mm);
                back.Pullback();
            }

            @Override
            public void onFailure(String msg) {
                listView.onRefreshComplete();
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 回调接口
     */
    private interface OnSuccessBack{
        public void Refuback();
        public void Pullback();
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
     * @param k
     */

    public void showDialog(int k){
        //Theme_DeviceDefault_Light_Dialog
        AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity(),android.R.style.Theme_DeviceDefault_Light_Dialog);
        dialog.setTitle("金额:  "+helpModels.get(k).getHelpMoney()+"元");
        dialog.setMessage("        "+helpModels.get(k).getHelpInformation());
        dialog.setPositiveButton("答应他?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                agreeHelp(k);
            }
        });
        dialog.setNegativeButton("残忍拒绝", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}});
        new InnoAsyncTask(dialog).execute(helpModels.get(k).getUserPhoto());
    }

    private void agreeHelp(int i) {
        String url="gethelp";
        Service service=new Service();
        RequestParams request=new RequestParams();
        request.put("UserPhone", HomePageFragment1.UserPhone);
        request.put("SecretKey",HomePageFragment1.SecretKey);
        request.put("HelpId",helpModels.get(i).getHelpId());
        service.post(getActivity(), url, request, new Listener() {
            @Override
            public void onSuccess(Object object) {
                HelpModel model=new Gson().fromJson(new String((byte[]) object),HelpModel.class);
                if(1==model.getState()){
                    Toast.makeText(getActivity(), model.getMsg(), Toast.LENGTH_SHORT).show();
                    getData();
                }else{
                    Toast.makeText(getActivity(), model.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
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