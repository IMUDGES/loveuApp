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
import com.example.loveuApp.bean.userModel;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.userService;
import com.example.loveuApp.util.Md5;
import com.example.loveuApp.util.checkString;
import com.loopj.android.http.RequestParams;

import java.util.Timer;
import java.util.TimerTask;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private Button getCheckButton;
    private Button nextButton;
    private EditText numberEditText;
    private EditText checkCodeEditText;
    private EditText passwordEditText1;
    private EditText passwordEditText2;
    private EditText nickNameEditText;
    private int checkTime = 0;
    private int time = 60;
    private String phone;
    private boolean isGetCheckCode = false; //判断是否已经获取了验证码

    private static int BEGIN_TIME = 0;

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
        numberEditText = (EditText) getActivity().findViewById(R.id.register_phonenumber);
        checkCodeEditText = (EditText) getActivity().findViewById(R.id.register_checkcode);
        passwordEditText1 = (EditText) getActivity().findViewById(R.id.register_password1);
        passwordEditText2 = (EditText) getActivity().findViewById(R.id.register_password2);
        nickNameEditText = (EditText) getActivity().findViewById(R.id.register_nickname);
        getCheckButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        isGetCheckCode = false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_getcheckcode:
                if (checkTime == 1) {
                    Toast.makeText(getActivity(), "稍后再试", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    postPhone();
                }
                break;
            case R.id.register_next:
                if (isGetCheckCode)
                    postAll();
                else
                    Toast.makeText(getActivity(), "请先获取验证码", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void postPhone() {
        phone = numberEditText.getText().toString();
        if (phone.length() <= 0) {
            Toast.makeText(getActivity(), "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestParams requestParams = new RequestParams();
        requestParams.add("UserPhone", phone);
        userService service = new userService();
        String url = "register1";
        service.post(getActivity(), url, requestParams, new Listener() {
            @Override
            public void onSuccess(Object object) {

                userModel userModel = (com.example.loveuApp.bean.userModel) object;
                String state = userModel.getState();
                String msg = userModel.getMsg();
                if ("1".equals(state)) {
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    onGetCheckCodeSuccess();
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
        String checkCode = checkCodeEditText.getText().toString();
        String password1 = passwordEditText1.getText().toString();
        String password2 = passwordEditText2.getText().toString();
        String nickname = nickNameEditText.getText().toString();

        if (checkCode.length() <= 0 || password1.length() <= 0 || password2.length() <= 0) {
            Toast.makeText(getActivity(), "请填写完毕后再提交", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password1.equals(password2)) {
            Toast.makeText(getActivity(), "两次密码不相同，请重新输入", Toast.LENGTH_SHORT).show();
            passwordEditText1.setText("");
            passwordEditText2.setText("");
            return;
        }

        if ((!checkString.compare(password1,getActivity())) || (!checkString.compare(nickname,getActivity()))) {
            Toast.makeText(getActivity(), "...", Toast.LENGTH_SHORT).show();
            return;
        }
        String Md5_password = Md5.getMD5(password1);

        RequestParams requestParams = new RequestParams();
        requestParams.put("UserPhone", phone);
        requestParams.put("CheckCode", checkCode);
        requestParams.put("PassWord", Md5_password);
        requestParams.put("NickName", nickname);

        userService service = new userService();
        String url = "register3";
        service.post(getActivity(), url, requestParams, new Listener() {
            @Override
            public void onSuccess(Object object) {

                userModel userModel = (com.example.loveuApp.bean.userModel) object;
                String state;
                state = userModel.getState();
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
                Toast.makeText(getActivity(), "请求失败" + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    //成功获取验证码后
    private void onGetCheckCodeSuccess() {
        checkTime = 1;
        handler.sendEmptyMessageDelayed(BEGIN_TIME, 1000);
        isGetCheckCode = true;
        numberEditText.setFocusable(false);
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
