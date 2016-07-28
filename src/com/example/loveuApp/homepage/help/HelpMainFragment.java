package com.example.loveuApp.homepage.help;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Collection;
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
    private List<String> urls;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView= (ListView) getView().findViewById(R.id.helpListView);
        models=new ArrayList<>();
        urls=new ArrayList<>();
        adapter=new HelpListAdapter(getActivity(),getData(),getUrls());
        listView.setAdapter(adapter);

    }

    private List<helpModel> getData(){
        String url="";
        RequestParams params=new RequestParams();
        helpService service=new helpService();
        params.put("SecretKey","");
        params.put("UserPhone","");
        service.get(getActivity(), url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                models.addAll((Collection<? extends helpModel>) object);
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });
        return models;
    }

    public List<String> getUrls() {
        List<String>ids=new ArrayList<>();
        for (helpModel model:getData()) {
            ids.add(String.valueOf(model.getUserId()));
        }
        String url="";
        for (String id:ids){
            RequestParams params=new RequestParams();
            userService service=new userService();
            params.put("UserPhone","");
            params.put("UserId",id);
            service.get(getActivity(), url, params, new Listener() {
                @Override
                public void onSuccess(Object object) {
                    urls.add(((userModel)object).getUserPhoto());
                }

                @Override
                public void onFailure(String msg) {

                }
            });
        }

        return urls;
    }
}
