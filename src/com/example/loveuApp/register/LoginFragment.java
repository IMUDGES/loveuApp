package com.example.loveuApp.register;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.userModel;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.userService;
import com.example.loveuApp.util.Md5;
import com.example.loveuApp.util.PhotoCut;
import com.example.loveuApp.util.SavePhoto;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class LoginFragment extends Fragment {

    private Button loginButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView toRegister;
    private TextView toFind;
    private FLoginBtnClick fLoginBtnClick;

    public interface FLoginBtnClick {
        void onFLoginTrue();

        void onToRegisterClick();

        void onToFindClick();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        autoLogin();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof FLoginBtnClick) {
                    ((FLoginBtnClick) getActivity()).onToRegisterClick();
                }
            }
        });

        toFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() instanceof FLoginBtnClick) {
                    ((FLoginBtnClick) getActivity()).onToFindClick();
                }
            }
        });
    }

    private void autoLogin() {
        SharedPreferences sh=getActivity().getSharedPreferences("user",getActivity().MODE_PRIVATE);
        String username=sh.getString("UserPhone","");
        String password=sh.getString("PassWord","");
        String Md5_password = Md5.getMD5(password);
        RequestParams requestParams = new RequestParams();
        requestParams.add("UserPhone", username);
        requestParams.add("PassWord", Md5_password);
        userService service = new userService();
        String url = "login";
        service.post(getActivity(), url, requestParams, new Listener() {
            @Override
            public void onSuccess(Object object) {
                userModel userModel = (com.example.loveuApp.bean.userModel) object;

                if ("1".equals(userModel.getState())) {
                    SharedPreferences preferences = getActivity().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Token", userModel.getToken());
                    editor.putString("SecretKey", userModel.getSecretKey());
                    editor.commit();
                    Log.i("SecretKey",userModel.getSecretKey());
                    if (getActivity() instanceof FLoginBtnClick) {
                        ((FLoginBtnClick) getActivity()).onFLoginTrue();
                    }
                } else {
                    //Toast.makeText(getActivity(), "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), "与服务器请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        usernameEditText = (EditText) getActivity().findViewById(R.id.login_zhanghao);
        passwordEditText = (EditText) getActivity().findViewById(R.id.login_password);
        loginButton = (Button) getActivity().findViewById(R.id.login_button);
        toRegister = (TextView) getActivity().findViewById(R.id.login_register);
        toFind = (TextView) getActivity().findViewById(R.id.login_losspass);
    }

    private void login() {
        String userphone = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (userphone.length() <= 0 || password.length() <= 0) {
            Toast.makeText(getActivity(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String Md5_password = Md5.getMD5(password);
        RequestParams requestParams = new RequestParams();
        requestParams.add("UserPhone", userphone);
        requestParams.add("PassWord", Md5_password);
        userService service = new userService();
        String url = "login";
        service.post(getActivity(), url, requestParams, new Listener() {
            @Override
            public void onSuccess(Object object) {
                userModel userModel = (com.example.loveuApp.bean.userModel) object;
                String state = userModel.getState();
                String SecretKey = userModel.getSecretKey();
//                Toast.makeText(getActivity(),state,Toast.LENGTH_SHORT).show();
                if ("1".equals(state)) {
                    saveInfor(userphone, password,SecretKey,userModel.getToken());

                    new LoginAsyncTask().execute(userModel.getUserPhoto());

                    if (getActivity() instanceof FLoginBtnClick) {
                        ((FLoginBtnClick) getActivity()).onFLoginTrue();
                    }
                } else {
                    Toast.makeText(getActivity(), "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getActivity(), "与服务器请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveInfor( String phone,String password, String secret, String token) {
        SharedPreferences preferences = getActivity().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("UserPhone", phone);
        editor.putString("SecretKey", secret);
        editor.putString("PassWord", password);
        editor.putString("Token", token);
        if (!editor.commit()) {
            System.err.println("！！！写入失败！！！");
        }
    }

    private class LoginAsyncTask extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url=strings[0];
            Log.i("url",url);
            Bitmap bitmap=null;
            URLConnection connection;
            InputStream inputStream;
            try {
                connection=new URL(url).openConnection();
                inputStream=connection.getInputStream();

                bitmap= BitmapFactory.decodeStream(inputStream);

                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            SavePhoto savePhoto=new SavePhoto(bitmap, Environment.getExternalStorageDirectory().getPath(),"UserPhoto");
            savePhoto.Savephoto();
        }
    }
}
