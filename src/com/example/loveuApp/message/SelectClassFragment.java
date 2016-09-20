package com.example.loveuApp.message;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.loveuApp.R;
import com.example.loveuApp.homepage.HomePageFragment1;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.XueModel;
import com.example.loveuApp.service.Service;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

/**
 * Created by dy on 2016/9/18.
 */
public class SelectClassFragment extends Fragment {

    private EditText /* editText1,editText2,*/editText3,editText4;
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
      //  editText1= (EditText) getActivity().findViewById(R.id.nameId);
       // editText2= (EditText) getActivity().findViewById(R.id.passId);
        editText3= (EditText) getActivity().findViewById(R.id.classId);
        editText4= (EditText) getActivity().findViewById(R.id.classnum);
        submit= (Button) getActivity().findViewById(R.id.submit_class);
    }

    public void push(){
        String kxu = editText3.getText().toString();
        String kming = editText4.getText().toString();
        Service service=new Service();
        RequestParams params=new RequestParams();
        params.put("UserPhone", HomePageFragment1.UserPhone);
        params.put("SecretKey", HomePageFragment1.SecretKey);
        params.put("ClassOrder",kxu);
        params.put("ClassNumber",kming);
       // params.put("","");
        service.post(getActivity(), "selectclass", params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                XueModel model = new Gson().fromJson(new String((byte[])object),XueModel.class);
                if (model.getState()=='1'){
                    Toast.makeText(getActivity(),"成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(),""+msg,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
