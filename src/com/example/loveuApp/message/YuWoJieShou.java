package com.example.loveuApp.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.loveuApp.R;

/**
 * Created by caolu on 2016/8/10.
 */
public class YuWoJieShou extends Fragment {

    private ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.yuwojieshou, container, false);
        mListView = (ListView) view.findViewById(R.id.yuwojieshoulistview);
        return view;
    }
}
