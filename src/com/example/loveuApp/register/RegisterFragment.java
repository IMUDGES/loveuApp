package com.example.loveuApp.register;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.loveuApp.R;


public class RegisterFragment extends Fragment implements View.OnClickListener{
    private Button getCheckButton;
    private Button nextButton;
    private EditText numberEditext;
    private EditText checkcodeEditext;
    private  int checkTime = 0;
    private  int time = 60;

    private static int BEGIN_TIME = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        getCheckButton = (Button) getActivity().findViewById(R.id.register_getcheckcode);
        nextButton = (Button) getActivity().findViewById(R.id.register_next);
        numberEditext  = (EditText) getActivity().findViewById(R.id.register_phonenumber);
        checkcodeEditext = (EditText) getActivity().findViewById(R.id.register_checkcode);
        getCheckButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_getcheckcode:

                if (checkTime == 1) {
                    Toast.makeText(getActivity(),"稍后再试",Toast.LENGTH_SHORT).show();
                   /* handler.removeMessages(BEGIN_TIME);
                    handler.sendEmptyMessageDelayed(BEGIN_TIME,1000);*/
                    return;
                }else {
                    handler.sendEmptyMessageDelayed(BEGIN_TIME,1000);
                    checkTime = 1;
                }

                break;
            case R.id.register_next:

                break;
        }
    }
    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
          if (msg.what == BEGIN_TIME){
              getCheckButton.setText(time--+"s后再次获取");
              handler.sendEmptyMessageDelayed(BEGIN_TIME,1000);
              if (time==0){
                  time = 60;
                  checkTime = 0;
                  getCheckButton.setText("获取验证码");
                  handler.removeMessages(BEGIN_TIME);
              }
          }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
       handler.removeMessages(BEGIN_TIME);
    }
}
