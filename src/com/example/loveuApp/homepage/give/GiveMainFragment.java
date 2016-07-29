package com.example.loveuApp.homepage.give;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.loveuApp.R;


public class GiveMainFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.givemain, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // mListView = (ListView) getActivity().findViewById(R.id.givefragment_listview);
       /* RequestParams params = new RequestParams();
        params.put("","");
        giveService service = new giveService();
        service.get(getActivity(), "未知", params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                data = (List<giveModel>) object;
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }
        });
        mListView.setAdapter(new GiveMainListAdapter(data,getActivity()));*/

    }
}
