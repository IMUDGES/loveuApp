package com.example.loveuApp.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.loveuApp.R;
import com.example.loveuApp.homepage.HomePageFragment1;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.XueModel;
import com.example.loveuApp.service.Service;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import org.apache.http.params.HttpParams;

/**
 * Created by caolu on 2016/9/20.
 */
public class CheckJwxtActivity extends Activity{

    private Button bt;
    private EditText usernameEidt,passwordEdit;
    private String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkjwxt_activity);

        usernameEidt = (EditText) findViewById(R.id.checkjwxt_username);
        passwordEdit = (EditText) findViewById(R.id.checkjwxt_password);
        bt = (Button) findViewById(R.id.checkjwxt_button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameEidt.getText().toString();
                password = passwordEdit.getText().toString();
                Service service = new Service();
                RequestParams params = new RequestParams();
                params.add("JwxtNumber",username);
                params.add("JwxtPassword",password);
                params.add("UserPhone", HomePageFragment1.UserPhone);
                params.add("SecretKey",HomePageFragment1.SecretKey);
                service.post(getApplicationContext(), "upjwxtservice", params, new Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        XueModel xueModel = new Gson().fromJson(new String((byte[])object),XueModel.class);
                        if (xueModel.getState()==1){
                            Toast.makeText(getApplicationContext(),"成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),xueModel.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
