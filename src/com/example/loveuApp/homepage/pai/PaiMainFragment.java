package com.example.loveuApp.homepage.pai;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.loveuApp.bean.paiModel;
import com.example.loveuApp.homepage.pai.adapter.PaiAdapter;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.paiService;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class PaiMainFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.paimain, container, false);
        return view;
    }

    private PullToRefreshListView listView;
    private List<paiModel>paiModels;
    private int PAGEINT;
    private boolean REFU=true;
    private OnSuccessBack back;
    private PaiAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView= (PullToRefreshListView) getView().findViewById(R.id.paiListView);
        paiModels=new ArrayList<>();
        REFU=true;

        getData();
        back=new OnSuccessBack() {
            @Override
            public void Refuback() {
                listView.onRefreshComplete();
                if(REFU) {
                    Log.i("information","REFU==true");
                    adapter = new PaiAdapter(getActivity(), paiModels);
                    listView.setAdapter(adapter);
                    REFU=false;
                }else{
                    Log.i("information","REFU==false");
                    adapter.setModels(paiModels);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void Pullback() {
                Log.i("models",paiModels.size()+"");
                listView.onRefreshComplete();
                adapter.setModels(paiModels);
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
                PaiCommentActivity.setPaiModels(paiModels.get(i-1));
                startActivity(new Intent(getActivity(),PaiCommentActivity.class));
            }
        });
    }


    /**
     * 刷新获取数据源函数getData()
     * @return
     */
    private List<paiModel> getData(){
        Log.i("msg","getData()");
        String url="pai";
        RequestParams params=new RequestParams();
        paiService service=new paiService();
        params.add("page","1");
        service.get(getActivity(), url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                paiModels= (List<paiModel>) object;
                if(paiModels.get(0).getNum()==0){
                    listView.onRefreshComplete();
                    return;
                }
                paiModels.remove(0);
                back.Refuback();
            }

            @Override
            public void onFailure(String msg) {
                listView.onRefreshComplete();
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });
        return paiModels;
    }
    /**
     * pullDown()
     */
    private void pullDown() {
        String url="pai";
        RequestParams params=new RequestParams();
        paiService service=new paiService();
        params.add("page",PAGEINT+"");
        PAGEINT++;
        service.get(getActivity(), url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                if(((List<paiModel>) object).get(0).getNum()==0){
                    listView.onRefreshComplete();
                    return;
                }
                List<paiModel> mm= (List<paiModel>) object;
                mm.remove(0);
                paiModels.addAll(mm);
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
}
