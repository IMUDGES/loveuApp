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
import com.loopj.android.http.RequestParams;

/**
 * Created by 1111 on 2016/7/30.
 */
public class FindPasswordFragment extends Fragment {

    private EditText phoneEditText;
    private EditText CodeEditText;
    private Button getCodeBtn;
    private Button nextBtn;
    private int checkTime = 0;
    private int time = 60;
    private static String phone;
    private static String checkCode;
    private boolean isGetCheckCode = false; //判断是否已经获取了验证码

    private static int BEGIN_TIME = 0;

    public interface FFindClickListener {
        void onFFindClick();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_find, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        phoneEditText = (EditText) getActivity().findViewById(R.id.find_phone);
        CodeEditText = (EditText) getActivity().findViewById(R.id.find_checkcode);
        getCodeBtn = (Button) getActivity().findViewById(R.id.find_getcheckcode);
        nextBtn = (Button) getActivity().findViewById(R.id.find_next);

        phone = "";
        checkCode = "";

        isGetCheckCode = false;
        getCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkTime == 1) {
                    Toast.makeText(getActivity(), "稍后再试", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    postPhone();
                }
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGetCheckCode)
                    postAll();
                else
                    Toast.makeText(getActivity(), "请先获取验证码", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postPhone() {
        String url = "retrieve1";
        phone = phoneEditText.getText().toString();

        if (phone.length() <= 0) {
            Toast.makeText(getActivity(), "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

//        Toast.makeText(getActivity(), "start", Toast.LENGTH_SHORT).show();
        RequestParams requestParams = new RequestParams();
        requestParams.add("UserPhone", phone);
        userService service = new userService();
        service.post(getActivity(), url, requestParams, new Listener() {
            @Override
            public void onSuccess(Object object) {
                userModel model = (userModel) object;
                String state = model.getState();
                if ("1".equals(state)) {
                    onGetCheckCodeSuccess();
//                    Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), "与服务器请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postAll() {
        checkCode = CodeEditText.getText().toString();
        String url = "retrieve2";

        if (checkCode.length() <= 0) {
            Toast.makeText(getActivity(), "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        System.out.println("-------------yan zheng ma"+checkCode);
        RequestParams requestParams = new RequestParams();
        requestParams.add("CheckCode", checkCode);
        requestParams.add("UserPhone", phone);
        userService service = new userService();
        service.post(getActivity(), url, requestParams, new Listener() {
            @Override
            public void onSuccess(Object object) {
                userModel model = (userModel) object;
                String state = model.getState();
                if ("1".equals(state)) {
//                    Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
                    if (getActivity() instanceof FFindClickListener) {
                        ((FFindClickListener) getActivity()).onFFindClick();
                    }
                } else {
                    System.out.println("----------------"+model.getMsg());
                    Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), "与服务器请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //成功获取验证码后
    private void onGetCheckCodeSuccess() {
        checkTime = 1;
        handler.sendEmptyMessageDelayed(BEGIN_TIME, 1000);
        isGetCheckCode = true;
        phoneEditText.setFocusable(false);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == BEGIN_TIME) {
                getCodeBtn.setText(time-- + "s后再次获取");
                handler.sendEmptyMessageDelayed(BEGIN_TIME, 1000);
                if (time == 0) {
                    time = 60;
                    checkTime = 0;
                    getCodeBtn.setText("获取验证码");
                    handler.removeMessages(BEGIN_TIME);
                }
            }
        }
    };

    public static String getPhone() {
        return phone;
    }

    public static String getCheckCode() {
        return checkCode;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeMessages(BEGIN_TIME);
    }
}
