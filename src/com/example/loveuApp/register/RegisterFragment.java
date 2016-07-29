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
import com.example.loveuApp.bean.UserModel;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.UserService;
import com.example.loveuApp.util.Md5;
import com.loopj.android.http.RequestParams;


public class RegisterFragment extends Fragment implements View.OnClickListener {
    private Button getCheckButton;
    private Button nextButton;
    private EditText numberEditext;
    private EditText checkcodeEditext;
    private EditText passwordEditext1;
    private EditText passwordEditext2;
    private int checkTime = 0;
    private int time = 60;
    private String phone;

    private static int BEGIN_TIME = 0;

    //记得要写密码等edittext带空格的情况!!!!

    public interface FRegisterClickListener {
        void onFRegisterClick();
    }

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
        numberEditext = (EditText) getActivity().findViewById(R.id.register_phonenumber);
        checkcodeEditext = (EditText) getActivity().findViewById(R.id.register_checkcode);
        passwordEditext1 = (EditText) getActivity().findViewById(R.id.register_password1);
        passwordEditext2 = (EditText) getActivity().findViewById(R.id.register_password2);
        getCheckButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_getcheckcode:

                if (checkTime == 1) {
                    Toast.makeText(getActivity(), "稍后再试", Toast.LENGTH_SHORT).show();
                   /* handler.removeMessages(BEGIN_TIME);
                    handler.sendEmptyMessageDelayed(BEGIN_TIME,1000);*/
                    return;
                } else {
                    handler.sendEmptyMessageDelayed(BEGIN_TIME, 1000);
                    checkTime = 1;
                    postPhone();
                }

                break;
            case R.id.register_next:
                postAll();
                break;
        }
    }


    private void postPhone() {
        phone = numberEditext.getText().toString();
        if (phone.length() <= 0) {
            Toast.makeText(getActivity(), "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestParams requestParams = new RequestParams();
        requestParams.add("UserPhone", phone);
        UserService service = new UserService();
        String url = "register1";
        service.post(getActivity(), url, requestParams, new Listener() {
            @Override
            public void onSuccess(Object object) {
                UserModel userModel = (UserModel) object;
                String state = userModel.getState();
                String msg = userModel.getMsg();
                if ("1".equals(state)) {
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

                    //成功
                } else {
                    Toast.makeText(getActivity(), "失败" + msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), "与服务器请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postAll() {
        String checkCode = checkcodeEditext.getText().toString();
        String password1 = passwordEditext1.getText().toString();
        String password2 = passwordEditext2.getText().toString();

        if (checkCode.length() <= 0 || password1.length() <= 0 || password2.length() <= 0) {
            Toast.makeText(getActivity(), "请填写完毕后再提交", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password1.equals(password2)) {
            Toast.makeText(getActivity(), "两次密码不相同，请重新输入", Toast.LENGTH_SHORT).show();
            passwordEditext1.setText("");
            passwordEditext2.setText("");
            return;
        }
        String Md5_password = Md5.getMD5(password1);

        RequestParams requestParams = new RequestParams();
        requestParams.put("UserPhone", phone);
        requestParams.put("CheckCode", checkCode);
        requestParams.put("PassWord", Md5_password);

        UserService service = new UserService();
        String url = "register3";
        service.post(getActivity(), url, requestParams, new Listener() {
            @Override
            public void onSuccess(Object object) {

                UserModel userModel = (UserModel) object;
                String state = userModel.getState();
                if ("1".equals(state)) {
                    Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                    if (getActivity() instanceof FRegisterClickListener) {
                        ((FRegisterClickListener) getActivity()).onFRegisterClick();
                    }
                } else {
                    Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == BEGIN_TIME) {
                getCheckButton.setText(time-- + "s后再次获取");
                handler.sendEmptyMessageDelayed(BEGIN_TIME, 1000);
                if (time == 0) {
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
