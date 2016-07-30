package com.example.loveuApp.homepage.food;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.foodModel;
import com.example.loveuApp.homepage.food.adapter.FoodMainListAdapter;

import java.util.List;


public class FoodMainFragment extends Fragment{

    private ListView mListView;
    private FoodMainListAdapter mAdapter;
    private List<foodModel> data;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foodmain, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       // mListView = (ListView) getActivity().findViewById(R.id.foodfragment_listview);
       /* RequestParams params = new RequestParams();
        params.put("","");
        FoodService service = new FoodService();
        service.get(getActivity(), "未知", params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                data = (List<FoodModel>) object;
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }
        });
        mListView.setAdapter(new FoodMainListAdapter(data,getActivity()));*/

    }



}
