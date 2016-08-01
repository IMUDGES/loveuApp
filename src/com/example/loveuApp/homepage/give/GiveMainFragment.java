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
import com.example.loveuApp.R;
import com.example.loveuApp.bean.giveModel;
import com.example.loveuApp.bean.userModel;
import com.example.loveuApp.homepage.give.adapter.GiveMainListAdapter;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.giveService;
import com.example.loveuApp.service.userService;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;


public class GiveMainFragment extends Fragment implements AdapterView.OnItemClickListener{
    private PullToRefreshListView listView;
    private GiveMainListAdapter adapter;
    private List<giveModel> giveModels;
    private List<userModel> users;
    private OnSuccessBack successbBack;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.givemain, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView= (PullToRefreshListView) getView().findViewById(R.id.giveListView);
//        getGiveModels();
//        getUsers();
        successbBack=new OnSuccessBack() {
            @Override
            public void successbBack() {
                Log.i("models",giveModels.size()+"");
                Log.i("urls",users.size()+"");
                adapter=new GiveMainListAdapter(getActivity(),giveModels,users);
                listView.setAdapter(adapter);
            }
        };
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),GiveActivity.class);
                GiveActivity.setModel(giveModels.get(i),users.get(i));
                getActivity().startActivity(intent);
                Log.i("msg",i+"");
            }
        });
    }
    public void getGiveModels(){
        String url="";
        RequestParams params=new RequestParams();
        params.put("page","");
        giveService service=new giveService();
        service.get(getActivity(), url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                giveModels= (List<giveModel>) object;
                if("0".equals(giveModels.get(0).getState())){
                    return ;
                }else{
                    giveModels.remove(0);
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
    public void getUsers() {

        List<String>ids=new ArrayList<>();
        for (giveModel model:giveModels) {
            if(model.getUserId()!=null)
                ids.add(String.valueOf(model.getUserId()));
        }
        String url="data";
        for (String id:ids){
            RequestParams params=new RequestParams();
            userService service=new userService();
            params.put("UserId",id);
            service.get(getActivity(), url, params, new Listener() {
                @Override
                public void onSuccess(Object object) {
                    users.add((userModel) object);
                    Log.i("urls",users.toString());
                    if(users.size()==giveModels.size())
                        successbBack.successbBack();
                }
                @Override
                public void onFailure(String msg) {

                }
            });
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(getActivity(),GiveActivity.class);
        GiveActivity.setModel(giveModels.get(i),users.get(i));
        startActivity(intent);
    }
    private interface OnSuccessBack{
        public void successbBack();
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