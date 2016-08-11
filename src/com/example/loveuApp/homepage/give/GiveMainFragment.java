package com.example.loveuApp.homepage.give;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.example.loveuApp.bean.giveData;
import com.example.loveuApp.bean.giveModel;
import com.example.loveuApp.homepage.give.adapter.GiveMainListAdapter;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.giveService;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;


public class GiveMainFragment extends Fragment implements AdapterView.OnItemClickListener{
    private PullToRefreshListView listView;
    private GiveMainListAdapter adapter;
    private giveData givedata;
    private boolean REFU=true;
    private OnSuccessBack successbBack;
    private List<giveModel> giveModels;
    private int page=1;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.givemain, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView= (PullToRefreshListView) getView().findViewById(R.id.giveListView);
        giveModels=new ArrayList<>();
        getGiveData();
        REFU=true;
        successbBack=  new OnSuccessBack() {
            @Override
            public void Refuback() {
                listView.onRefreshComplete();
                if(REFU) {
                    Log.i("information","REFU==true");
                    adapter = new GiveMainListAdapter(getActivity(), givedata.getGiveModels());
                    listView.setAdapter(adapter);
                    REFU=false;
                }else{
                    Log.i("information","REFU==false");
                    adapter.setGiveModels(giveModels);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void Pullback() {
                Log.i("models",givedata.getGiveModels().size()+"");
                listView.onRefreshComplete();
                adapter.setGiveModels(giveModels);
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
                page=1;
                getGiveData();
                giveModels=givedata.getGiveModels();
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),GiveActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("givemodel", giveModels.get(i-1));
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
                Log.i("msg",i+"");
            }
        });
    }
    public void getGiveData(){
        String url="give";
        RequestParams params=new RequestParams();
        params.put("page",page+"");
        giveService service=new giveService();
        System.out.println(url);
        service.get(getActivity(), url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                givedata= (giveData) object;
                Log.i("givemodelsnick:",givedata.getGiveModels().get(1).getNickName());
                giveModels=givedata.getGiveModels();
                successbBack.Refuback();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(getActivity(),GiveActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("givemodel", givedata.getGiveModels().get(i));
        intent.putExtras(bundle);
        startActivity(intent);
    }
    /**
     * pullDown()
     */
    private void pullDown() {
        String url="give";
        RequestParams params=new RequestParams();
        giveService service=new giveService();
        page++;
        params.add("page",page+"");

        service.get(getActivity(), url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                if(((giveData)object).getNum()==0){
                    listView.onRefreshComplete();
                    return;
                }

                List<giveModel> mm= ((giveData) object).getGiveModels();
                givedata.getGiveModels().addAll(mm);
                successbBack.Pullback();
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
