package com.example.loveuApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.loveuApp.bean.userModel;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.register.GuoQingZhuangBActivity;
import com.example.loveuApp.register.RegisterActivity;
import com.example.loveuApp.service.userService;
import com.example.loveuApp.util.Md5;
import com.loopj.android.http.RequestParams;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caolu on 2016/8/11.
 */
public class WelcomeActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomelayout);
        autoLogin();
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
                    SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
                    String token = sh.getString("Token","");

                    Map<String,Boolean> map=new HashMap<>();
                    map.put(Conversation.ConversationType.PRIVATE.getName(), false);
                    map.put(Conversation.ConversationType.GROUP.getName(), false);
                    map.put(Conversation.ConversationType.DISCUSSION.getName(), false);
                    map.put(Conversation.ConversationType.SYSTEM.getName(), false);

                    Log.i("RongInformation", token);
                    RongIMClient.connect(token, new RongIMClient.ConnectCallback() {

                        @Override
                        public void onError(RongIMClient.ErrorCode arg0) {
                            Toast.makeText(WelcomeActivity.this, "connect onError", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSuccess(String arg0) {
                            //Toast.makeText(WelcomeActivity.this, "connect onSuccess", Toast.LENGTH_SHORT).show();
                            RongIM.getInstance().startConversationList(WelcomeActivity.this,map);
                            finish();
                        }

                        @Override
                        public void onTokenIncorrect() {
                            // TODO Auto-generated method stub
                        }
                    });
                } else {
                    Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(String msg) {
                Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
