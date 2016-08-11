package com.example.loveuApp.updata;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.loveuApp.R;
import com.example.loveuApp.homepage.HomePageFragment;
import com.example.loveuApp.homepage.HomePageFragment1;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.FoodModel;
import com.example.loveuApp.model.HelpModel;
import com.example.loveuApp.service.Service;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dy on 2016/8/4.
 */
public class UpHelpActivity extends Activity {

    private TextView finish;
    private TextView username;
    private Button send;
    private EditText money,infor,password;
    int year,mon,day,hor,min;
    int YEAR,MON,DAY,HOR,MIN;
    private String mMoney,mInformation,mPassword;
    private int STATE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uphelpactivity);


        init();

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
                mMoney=money.getText().toString()+"";
                mInformation=infor.getText().toString()+"";
                mPassword=password.getText().toString()+"";
                new DatePickerDialog(UpHelpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        YEAR=i;MON=i1+1;DAY=i2;
                        new TimePickerDialog(UpHelpActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
            Toast.makeText(UpHelpActivity.this, "请输入正确时间", Toast.LENGTH_SHORT).show();
            return;
        }
        String url="creathelp";
        Service service = new Service();
        RequestParams params=new RequestParams();

        params.put("UserPhone", HomePageFragment1.UserPhone);
        params.put("SecretKey",HomePageFragment1.SecretKey);
        params.put("HelpInformation",mInformation);
        params.put("HelpMoney",mMoney);
        params.put("PayPassword",mPassword);
        Calendar cal=Calendar.getInstance();
        cal.clear();
        cal.set(YEAR,MON,DAY,HOR,MIN);
        params.put("DownTime",new SimpleDateFormat().format(cal.getTime()));//2016-03-06 12:55:21
        service.post(this, url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                HelpModel model=new Gson().fromJson(new String((byte[]) object),HelpModel.class);
                Log.i("information",new String((byte[]) object));
                if(model.getState()==1){
                    Toast.makeText(UpHelpActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpHelpActivity.this,model.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(UpHelpActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        finish= (TextView) findViewById(R.id.uphelp_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        username= (TextView) findViewById(R.id.uphelp_username);
        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
        username.setText(sh.getString("UserName","***"));

        send= (Button) findViewById(R.id.uphelp_send);

        money= (EditText) findViewById(R.id.uphelp_money);
        infor= (EditText) findViewById(R.id.uphelp_infor);
        password=(EditText)findViewById(R.id.uphelp_password);
    }
}
