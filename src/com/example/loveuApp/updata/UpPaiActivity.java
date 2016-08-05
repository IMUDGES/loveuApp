package com.example.loveuApp.updata;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.FoodModel;
import com.example.loveuApp.service.Service;
import com.example.loveuApp.util.PhotoCut;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dy on 2016/8/5.
 */
public class UpPaiActivity extends Activity {

    private EditText title,information,money;
    private String mTitle,mInformation,mMoney;
    private ImageView image;
    int year,mon,day,hor,min;
    int YEAR,MON,DAY,HOR,MIN;
    private Button send;
    private File file = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        showImage();
        sendMessage();
    }

    private void init() {


    }

    private void sendMessage() {

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
                mInformation=information.getText().toString()+"";
                mTitle=title.getText().toString()+"";
                new DatePickerDialog(UpPaiActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        YEAR=i;MON=i1+1;DAY=i2;
                        new TimePickerDialog(UpPaiActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                HOR=i;MIN=i1;
                                upPaiInformation();
                            }
                        }, hor, min, true).show();
                    }
                },year,calendar.get(Calendar.MONTH),day).show();
            }
        });
    }

    /**
     * 上传网络
     */
    private void upPaiInformation(){
        if(YEAR<year||MON<mon||DAY<day){
            Toast.makeText(UpPaiActivity.this, "请输入正确时间", Toast.LENGTH_SHORT).show();
            return;
        }
        String url="creatpai";
        Service service = new Service();
        RequestParams params=new RequestParams();

        params.put("UserPhone","22222222222");
        params.put("SecretKey","11111");
        params.put("PaiInformation",mInformation);
        params.put("PaiTitle",mTitle);
        params.put("PaiMoney",mMoney);
        if(file==null){
            Toast.makeText(UpPaiActivity.this, "请选择图片", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            params.put("PaiImage",file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Calendar cal=Calendar.getInstance();
        cal.clear();
        cal.set(YEAR,MON,DAY,HOR,MIN);
        Log.i("information",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));
        params.put("FoodTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime()));//2016-03-06 12:55:21
        service.post(this, url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                FoodModel model=new Gson().fromJson(new String((byte[]) object),FoodModel.class);
                if(model.getState()==1){
                    Toast.makeText(UpPaiActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpPaiActivity.this,model.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(UpPaiActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 回调图片
     */
    private final String IMAGE_TYPE="image/*";
    private final int IMAGE_CODE=1;
    private String Path;
    private void showImage() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(IMAGE_TYPE);
                intent.putExtra("crop", "true");    // crop=true 有这句才能出来最后的裁剪页面.
                intent.putExtra("aspectX", 1);      // 这两项为裁剪框的比例.
                intent.putExtra("aspectY", 1);
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 200);
                //输出地址
                intent.putExtra("output", Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath()+"/loveu.jpg")));
                intent.putExtra("outputFormat", "JPEG");//返回格式
                startActivityForResult(Intent.createChooser(intent, "选择图片"), IMAGE_CODE);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != UpPaiActivity.RESULT_OK) {
            Log.i("photopath","fail");
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_CODE) {
            Log.i("photopath","success");
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+"/loveu.jpg", options);
                Path=Environment.getExternalStorageDirectory().getPath()+"/loveu.jpg";

                if (bitmap == null)
                    Log.i("bitmap","null");

                image.setImageBitmap(bitmap);
                file = new File(Path);

            }catch (Exception e){
                e.getLocalizedMessage();
            }
        }
    }
}
