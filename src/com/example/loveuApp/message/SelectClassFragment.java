package com.example.loveuApp.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.loveuApp.R;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.Service;
import com.loopj.android.http.RequestParams;

/**
 * Created by dy on 2016/9/18.
 */
public class SelectClassFragment extends Fragment {

    private EditText editText1,editText2,editText3,editText4;
    private Button submit;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selectclass_fragment, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push();
            }
        });
    }

    public void init(){
        editText1= (EditText) getActivity().findViewById(R.id.nameId);
        editText2= (EditText) getActivity().findViewById(R.id.passId);
        editText3= (EditText) getActivity().findViewById(R.id.classId);
        editText4= (EditText) getActivity().findViewById(R.id.classnum);
        submit= (Button) getActivity().findViewById(R.id.submit_class);
    }

    public void push(){
        Service service=new Service();
        RequestParams params=new RequestParams();
        params.put("","");
        params.put("","");
        params.put("","");
        params.put("","");
        params.put("","");
        service.post(getActivity(), "", params, new Listener() {
            @Override
            public void onSuccess(Object object) {

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
