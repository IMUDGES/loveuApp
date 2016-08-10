package com.example.loveuApp.updata;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.loveuApp.R;
import com.example.loveuApp.homepage.HomePageFragment1;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.FoodModel;
import com.example.loveuApp.service.Service;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dy on 2016/8/4.
 */
public class UpFoodActivity extends Activity {

    private TextView finish;
    private TextView username;
    private Button send;
    private CheckBox my,aa,his;
    private EditText address,infor;
    int year,mon,day,hor,min;
    int YEAR,MON,DAY,HOR,MIN;
    private String mAddress,mInformation;
    private int STATE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upfoodactivity);

        init();

        SetCheck();

        SendMessage();

    }

    private void SendMessage() {

        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        mon=calendar.get(Calendar.MONTH)+1;
        day=calendar.get(Calendar.DAY_OF_MONTH);
        hor=calendar.get(Calendar.HOUR_OF_DAY);
        min=calendar.get(Calendar.MINUTE);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddress=address.getText().toString()+"";
                mInformation=infor.getText().toString()+"";
                new DatePickerDialog(UpFoodActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        YEAR=i;MON=i1+1;DAY=i2;
                        new TimePickerDialog(UpFoodActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                HOR=i;MIN=i1;
                                upFoodInformation();
                            }
                        }, hor, min, true).show();
                    }
                },year,calendar.get(Calendar.MONTH),day).show();
            }
        });
    }

    private void upFoodInformation() {
        if(YEAR<year||MON<mon||DAY<day){
            Toast.makeText(UpFoodActivity.this, "请输入正确时间", Toast.LENGTH_SHORT).show();
            return;
        }
        String url="creatfood";
        Service service = new Service();
        RequestParams params=new RequestParams();
        switch (STATE){
            case 1:params.put("FoodWay","我请客");break;
            case 2:params.put("FoodWay","AA");break;
            case 3:params.put("FoodWay","他请客");break;
        }
        params.put("UserPhone", HomePageFragment1.UserPhone);
        params.put("SecretKey",HomePageFragment1.SecretKey);
        params.put("FoodInformation",mInformation);
        params.put("FoodArea",mAddress);
        Calendar cal=Calendar.getInstance();
        cal.clear();
        cal.set(YEAR,MON,DAY,HOR,MIN);
        params.put("FoodTime",new SimpleDateFormat().format(cal.getTime()));//2016-03-06 12:55:21
        service.post(this, url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                FoodModel model=new Gson().fromJson(new String((byte[]) object),FoodModel.class);
                if(model.getState()==1){
                    Toast.makeText(UpFoodActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpFoodActivity.this,model.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(UpFoodActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SetCheck() {
        my.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    STATE=1;
                    aa.setChecked(false);his.setChecked(false);
                }
            }
        });
        aa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    STATE=2;
                    my.setChecked(false);his.setChecked(false);
                }
            }
        });
        his.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    STATE=3;
                    aa.setChecked(false);my.setChecked(false);
                }
            }
        });
    }

    private void init() {
        finish= (TextView) findViewById(R.id.upfood_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        username= (TextView) findViewById(R.id.upfood_username);
        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
        username.setText(sh.getString("UserName","***"));

        send= (Button) findViewById(R.id.upfood_send);

        my= (CheckBox) findViewById(R.id.upfood_myself);
        aa= (CheckBox) findViewById(R.id.upfood_aa);
        his= (CheckBox) findViewById(R.id.upfood_his);

        address= (EditText) findViewById(R.id.upfood_address);
        infor= (EditText) findViewById(R.id.upfood_infor);
    }
}
