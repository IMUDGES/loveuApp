package com.example.loveuApp.homepage.help;

import android.content.Intent;
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
import com.example.loveuApp.service.helpService;
import com.example.loveuApp.service.userService;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;


public class HelpMainFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.helpmain, container, false);
        return view;
    }

    private ListView listView;
    private HelpListAdapter adapter;
    private List<helpModel> models;
    private List<userModel> urls;
    private OnSuccessBack back;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView= (ListView) getView().findViewById(R.id.helpListView);
        models=new ArrayList<>();
        urls=new ArrayList<>();

        getData();

        back=new OnSuccessBack() {
            @Override
            public void back() {
                Log.i("models",models.size()+"");
                Log.i("urls",urls.size()+"");
                adapter=new HelpListAdapter(getActivity(),models,urls);
                listView.setAdapter(adapter);
            }
        };
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(),HelpActivity.class);
                HelpActivity.setModel(models.get(i),urls.get(i));
                getActivity().startActivity(intent);
                Log.i("msg",i+"");
            }
        });
    }

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

    private interface OnSuccessBack{
        public void back();
    }
}