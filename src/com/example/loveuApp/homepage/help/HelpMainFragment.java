package com.example.loveuApp.homepage.help;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.HelpModel;
import com.example.loveuApp.bean.UserModel;
import com.example.loveuApp.homepage.help.adapter.HelpListAdapter;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.HelpService;
import com.example.loveuApp.service.UserService;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class HelpMainFragment extends Fragment implements AdapterView.OnItemClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.helpmain, container, false);
        return view;
    }

    private ListView listView;
    private HelpListAdapter adapter;
    private List<HelpModel> models;
    private List<UserModel> urls;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView= (ListView) getView().findViewById(R.id.helpListView);
        models=new ArrayList<>();
        urls=new ArrayList<>();
        adapter=new HelpListAdapter(getActivity(),getData(),getUrls());
        listView.setAdapter(adapter);

    }

    private List<HelpModel> getData(){
        String url="";
        RequestParams params=new RequestParams();
        HelpService service=new HelpService();
        params.add("page","1");
        service.get(getActivity(), url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                Toast.makeText(getActivity(), "请求", Toast.LENGTH_SHORT).show();
                models.addAll((Collection<? extends HelpModel>) object);
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });
        return models;
    }

    public List<UserModel> getUrls() {
        List<String>ids=new ArrayList<>();
        for (HelpModel model:getData()) {
            if(model.getUserId()!=null)
                ids.add(String.valueOf(model.getUserId()));
        }
        String url="";
        for (String id:ids){
            RequestParams params=new RequestParams();
            UserService service=new UserService();
            params.put("UserId",id);
            service.get(getActivity(), url, params, new Listener() {
                @Override
                public void onSuccess(Object object) {
                    urls.add((UserModel) object);
                }

                @Override
                public void onFailure(String msg) {

                }
            });
        }
        adapter.notifyDataSetChanged();
        return urls;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(getActivity(),HelpActivity.class);
        HelpActivity helpActivity=new HelpActivity();
        helpActivity.setModel(models.get(i),urls.get(i));
        startActivity(intent);
    }
}
