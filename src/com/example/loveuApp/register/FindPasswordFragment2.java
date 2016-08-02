package com.example.loveuApp.register;

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
import com.example.loveuApp.bean.userModel;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.userService;
import com.example.loveuApp.util.Md5;
import com.example.loveuApp.util.checkString;
import com.loopj.android.http.RequestParams;

/**
 * Created by 1111 on 2016/7/30.
 */
public class FindPasswordFragment2 extends Fragment {

    private Button accompilshBtn;
    private EditText password1EditText;
    private EditText password2EditText;


    public interface FFind2ClickListener{
        void onFFind2Click();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_find2, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();

        accompilshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postPassword();
            }
        });
    }

    private void init() {
        accompilshBtn = (Button) getActivity().findViewById(R.id.find_accomplish);
        password1EditText = (EditText) getActivity().findViewById(R.id.find_password1);
        password2EditText = (EditText) getActivity().findViewById(R.id.find_password2);
    }

    private void postPassword() {
        String password1 = password1EditText.getText().toString();
        String password2 = password2EditText.getText().toString();
        String phone = FindPasswordFragment.getPhone();
        String checkCode = FindPasswordFragment.getCheckCode();

        System.out.println("---------" + phone);
        System.out.println("---------" + checkCode);
        String url = "retrieve3";

        if (password1.length() <= 0 || password2.length() <= 0) {
            Toast.makeText(getActivity(), "请填写完毕后再提交", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password1.equals(password2)) {
            Toast.makeText(getActivity(), "两次密码不相同，请重新输入", Toast.LENGTH_SHORT).show();
            password1EditText.setText("");
            password2EditText.setText("");
            return;
        }

        if (!checkString.compare(password1, getActivity())) {
            Toast.makeText(getActivity(), "不能输入非法字符", Toast.LENGTH_SHORT).show();
            return;
        }

        String Md5_password = Md5.getMD5(password1);

        RequestParams requestParams = new RequestParams();
        requestParams.put("PassWord", Md5_password);
        requestParams.put("CheckCode", checkCode);
        requestParams.put("UserPhone", phone);
        userService service = new userService();
        service.post(getActivity(), url, requestParams, new Listener() {
            @Override
            public void onSuccess(Object object) {
                userModel model = (userModel) object;
                String state = model.getState();
                if ("1".equals(state)) {
                    Toast.makeText(getActivity(), "成功", Toast.LENGTH_SHORT).show();
                    if (getActivity() instanceof FFind2ClickListener) {
                        ((FFind2ClickListener) getActivity()).onFFind2Click();
                    }
                } else {
                    System.out.println("---------" + phone);
                    System.out.println("---------" + checkCode);
                    System.out.println("----------------" + model.getMsg());
                    Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), "与服务器请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
