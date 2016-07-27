package com.example.loveuApp.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.loveuApp.R;


public class LoginFragment extends Fragment{

    private Button loginButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                        登陆
                 */
            }
        });
    }

    private void initView() {
        usernameEditText = (EditText) getActivity().findViewById(R.id.login_zhanghao);
        passwordEditText = (EditText) getActivity().findViewById(R.id.login_mima);
        loginButton = (Button) getActivity().findViewById(R.id.login_button);
    }

}
