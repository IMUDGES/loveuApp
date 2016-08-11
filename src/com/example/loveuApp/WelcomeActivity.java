package com.example.loveuApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.loveuApp.bean.userModel;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.register.GuoQingZhuangBActivity;
import com.example.loveuApp.service.userService;
import com.example.loveuApp.util.Md5;
import com.loopj.android.http.RequestParams;

/**
 * Created by caolu on 2016/8/11.
 */
public class WelcomeActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomelayout);
    }

    private void autoLogin() {
        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
        String username=sh.getString("UserPhone","");
        String password=sh.getString("PassWord","");
        String Md5_password = Md5.getMD5(password);
        RequestParams requestParams = new RequestParams();
        requestParams.add("UserPhone", username);
        requestParams.add("PassWord", Md5_password);
        userService service = new userService();
        String url = "login";
        service.post(this, url, requestParams, new Listener() {
            @Override
            public void onSuccess(Object object) {
                userModel userModel = (com.example.loveuApp.bean.userModel) object;

                if ("1".equals(userModel.getState())) {
                    SharedPreferences preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Token", userModel.getToken());
                    editor.putString("SecretKey", userModel.getSecretKey());
                    editor.commit();
                    Log.i("SecretKey",userModel.getSecretKey());
                    if (getApplicationContext() instanceof FLoginBtnClick) {
                        ((FLoginBtnClick) getApplicationContext()).onFLoginTrue();
                    }
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("MODE","startLogin");
                    Intent intent = new Intent(WelcomeActivity.this, GuoQingZhuangBActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(String msg) {
                Bundle bundle = new Bundle();
                bundle.putString("MODE","startLogin");
                Intent intent = new Intent(WelcomeActivity.this, GuoQingZhuangBActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public interface FLoginBtnClick {
        void onFLoginTrue();

        void onToRegisterClick();

        void onToFindClick();
    }
}
