package com.example.loveuApp.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.message.adapter.WoFaChuAdapter;

/**
 * Created by caolu on 2016/8/10.
 */
public class YuWoJieShou extends Fragment {

    private WoFaChuAdapter mAdapter;
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yuwojieshou, container, false);
        mListView = (ListView) view.findViewById(R.id.yuwojieshoulistview);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new WoFaChuAdapter(getActivity());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(getActivity(), MineFood1.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), MineGive.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), MineHelp1.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), MinePai1.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), MineRun1.class));
                        break;
                    case 5:
                        startActivity(new Intent(getActivity(), MineXue.class));
                        break;
                }
            }
        });
        //new MyTaks().execute(url);

    }
}
