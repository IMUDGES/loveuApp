package com.example.loveuApp.message.Wallet;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.userModel;
import com.example.loveuApp.homepage.HomePageFragment1;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.XueModel;
import com.example.loveuApp.register.RegisterFragment;
import com.example.loveuApp.service.userService;
import com.example.loveuApp.util.Md5;
import com.example.loveuApp.util.checkString;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

/**
 * Created by caolu on 2016/9/20.
 */
public class CheckActivity extends Activity implements View.OnClickListener{

    private Button getCheckButton;
    private Button nextButton;
    private TextView numberEditText;
    private EditText checkCodeEditText;
    private EditText passwordEditText1;
    private EditText passwordEditText2;
    private int checkTime = 0;
    private int time = 60;
    private boolean isGetCheckCode = false; //判断是否已经获取了验证码

    private static int BEGIN_TIME = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mywallet_check);
        initView();
    }

    private void initView() {
        getCheckButton = (Button) findViewById(R.id.register_getcheckcode);
        nextButton = (Button) findViewById(R.id.register_next);
        numberEditText = (TextView) findViewById(R.id.register_phonenumber);
        checkCodeEditText = (EditText) findViewById(R.id.register_checkcode);
        passwordEditText1 = (EditText) findViewById(R.id.register_password1);
        passwordEditText2 = (EditText) findViewById(R.id.register_password2);
        getCheckButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        isGetCheckCode = false;
        numberEditText.setText(HomePageFragment1.UserPhone+"");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_getcheckcode:
                if (checkTime == 1) {
                    Toast.makeText(getApplicationContext(), "稍后再试", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    postPhone();
                }
                break;
            case R.id.register_next:
                if (isGetCheckCode)
                    postAll();
                else
                    Toast.makeText(getApplicationContext(), "请先获取验证码", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void postPhone() {

        RequestParams requestParams = new RequestParams();
        requestParams.add("UserPhone", HomePageFragment1.UserPhone);
        requestParams.add("SecretKey",HomePageFragment1.SecretKey);
        userService service = new userService();
        String url = "sendcheck";
        service.post(getApplicationContext(), url, requestParams, new Listener() {
            @Override
            public void onSuccess(Object object) {

                userModel userModel = (com.example.loveuApp.bean.userModel) object;
                String state = userModel.getState();
                String msg = userModel.getMsg();
                if ("1".equals(state)) {
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    onGetCheckCodeSuccess();
                    //成功
                } else {
                    Toast.makeText(getApplicationContext(), "失败" + msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getApplicationContext(), "与服务器请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postAll() {
        String checkCode = checkCodeEditText.getText().toString();
        String password1 = passwordEditText1.getText().toString();
        String password2 = passwordEditText2.getText().toString();

        if (checkCode.length() <= 0 || password1.length() <= 0 || password2.length() <= 0) {
            Toast.makeText(getApplicationContext(), "请填写完毕后再提交", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password1.equals(password2)) {
            Toast.makeText(getApplicationContext(), "两次密码不相同，请重新输入", Toast.LENGTH_SHORT).show();
            passwordEditText1.setText("");
            passwordEditText2.setText("");
            return;
        }

        if ((!checkString.compare(password1,getApplicationContext()))) {
            Toast.makeText(getApplicationContext(), "...", Toast.LENGTH_SHORT).show();
            return;
        }
        String md5String = Md5.getMD5(password1);
        RequestParams requestParams = new RequestParams();
        requestParams.put("UserPhone", HomePageFragment1.UserPhone);
        requestParams.put("SecretKey",HomePageFragment1.SecretKey);
        requestParams.put("CheckCode", checkCode);
        requestParams.put("PayPassword", md5String);

        userService service = new userService();
        String url = "setpsw";
        service.post(getApplicationContext(), url, requestParams, new Listener() {
            @Override
            public void onSuccess(Object object) {

                userModel userModel = (com.example.loveuApp.bean.userModel) object;
                String state;
                state = userModel.getState();
                if ("1".equals(state)) {
                    Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    if (getApplicationContext() instanceof RegisterFragment.FRegisterClickListener) {
                        ((RegisterFragment.FRegisterClickListener) getApplicationContext()).onFRegisterClick();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "修改失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getApplicationContext(), "请求失败" + msg, Toast.LENGTH_SHORT).show();
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
