package com.example.loveuApp.homepage.run;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.runModel;
import com.example.loveuApp.homepage.run.RunAdapter.RunAdapter;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.runService;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;


public class RunMainFragment extends Fragment implements AdapterView.OnItemClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.runmain, container, false);
        return view;
    }

    private ListView listView;
    private RunAdapter adapter;
    private List<runModel> runModels;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView= (ListView) getView().findViewById(R.id.runListView);
        runModels=new ArrayList<>();
//        adapter=new RunAdapter(getActivity(),getRunModels());
//        listView.setAdapter(adapter);
    }

    public List<runModel> getRunModels() {
        String url="";
        RequestParams params=new RequestParams();
        params.put("page","");
        runService service=new runService();
        service.get(getActivity(), url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                runModels= (List<runModel>) object;
                if("0".equals(runModels.get(0).getstate())){
                    return;
                }else{
                    runModels.remove(0);
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
        return runModels;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(getActivity(),RunActivity.class);
        RunActivity.setModel(runModels.get(i));
        startActivity(intent);
    }
}
