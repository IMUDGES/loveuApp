package com.example.loveuApp.homepage.help;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.HelpModel;
import com.example.loveuApp.bean.UserModel;

/**
 * Created by dy on 2016/7/29.
 */
public class HelpActivity extends Activity{

    private HelpModel helpmodel;
    private UserModel userModel;
    private ImageView imageView;
    private TextView username,money,time,information;


    public void setModel(HelpModel helpmodel, UserModel userModel) {
        this.helpmodel = helpmodel;
        this.userModel = userModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helpactivitymain);
        init();
    }

    private void init() {
        imageView= (ImageView) findViewById(R.id.help_activity_image);
        username= (TextView) findViewById(R.id.help_activity_username);
        money= (TextView) findViewById(R.id.help_activity_money);
        time= (TextView) findViewById(R.id.help_activity_time);
        information= (TextView) findViewById(R.id.help_activity_infor);
    }
}
