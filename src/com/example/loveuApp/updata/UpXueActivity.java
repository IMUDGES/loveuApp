package com.example.loveuApp.updata;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.loveuApp.R;
import com.example.loveuApp.homepage.HomePageFragment1;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.FoodModel;
import com.example.loveuApp.model.XueModel;
import com.example.loveuApp.service.Service;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by caolu on 2016/8/8.
 */
public class UpXueActivity extends Activity{

    private EditText address;
    private EditText info;
    private Button upButton;
    private TextView finishButton;;
    private int year,mon,day,hor,min;
    private  int YEAR,MON,DAY,HOR,MIN;
    private String mAddress,mInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upxueactivity);
        initView();
        doClick();
    }

    private void doClick() {
      finishButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              finish();
          }
      });
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        mon=calendar.get(Calendar.MONTH)+1;
        day=calendar.get(Calendar.DAY_OF_MONTH);
        hor=calendar.get(Calendar.HOUR_OF_DAY);
        min=calendar.get(Calendar.MINUTE);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddress=address.getText().toString()+"";
                mInformation=info.getText().toString()+"";
                new DatePickerDialog(UpXueActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        YEAR=i;MON=i1+1;DAY=i2;
                        new TimePickerDialog(UpXueActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                HOR=i;MIN=i1;
                                upXueInformation();
                            }
                        }, hor, min, true).show();
                    }
                },year,calendar.get(Calendar.MONTH),day).show();
            }
        });
    }

    private void upXueInformation() {
        if(YEAR<year||MON<mon||DAY<day){
            Toast.makeText(UpXueActivity.this, "请输入正确时间", Toast.LENGTH_SHORT).show();
            return;
        }
        String url="creatxue";
        Service service = new Service();
        RequestParams params=new RequestParams();

        params.put("UserPhone", HomePageFragment1.UserPhone);
        params.put("SecretKey",HomePageFragment1.SecretKey);
        params.put("XueInformation",mInformation);
        params.put("XueArea",mAddress);
        Calendar cal=Calendar.getInstance();
        cal.clear();
        cal.set(YEAR,MON,DAY,HOR,MIN);
        params.put("XueTime",new SimpleDateFormat().format(cal.getTime()));//2016-03-06 12:55:21
        service.post(this, url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                XueModel model=new Gson().fromJson(new String((byte[]) object),XueModel.class);
                Log.i("TOOO",new String((byte[]) object));
                if(model.getState()==1){
                    Toast.makeText(UpXueActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpXueActivity.this,model.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(UpXueActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        address = (EditText) findViewById(R.id.upxue_address);
        info = (EditText) findViewById(R.id.upxue_infor);
        upButton = (Button) findViewById(R.id.upxue_send);
        finishButton = (TextView) findViewById(R.id.upxue_finish);
    }

}
