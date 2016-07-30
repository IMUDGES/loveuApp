package com.example.loveuApp.homepage.run;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.runModel;

/**
 * Created by dy on 2016/7/29.
 */
public class RunActivity extends Activity {
    private static runModel model;
    private ImageView imageView;
    private TextView username,area,time,information;

    public static void setModel(runModel model) {
        RunActivity.model = model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runactivitymain);
        init();
        setView();
    }

    private void setView() {
        area.setText(model.getRunArea());
        time.setText(model.getRunTime());
        information.setText(model.getRunInformation());
    }

    private void init() {
        imageView= (ImageView) findViewById(R.id.run_activity_image);
        username= (TextView) findViewById(R.id.run_activity_username);
        area= (TextView) findViewById(R.id.run_activity_area);
        time= (TextView) findViewById(R.id.run_activity_time);
        information= (TextView) findViewById(R.id.run_activity_infor);
    }
}
