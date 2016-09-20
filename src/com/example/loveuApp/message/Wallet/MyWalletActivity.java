package com.example.loveuApp.message.Wallet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.walletmodel;
import com.example.loveuApp.homepage.HomePageFragment1;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.XueModel;
import com.example.loveuApp.service.PhotoService;
import com.example.loveuApp.service.Service;
import com.example.loveuApp.util.PhotoCut;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by caolu on 2016/9/20.
 */
public class MyWalletActivity extends Activity{

    private ImageView img;
    private Button upmoneyButton,repasswordButton;
    private TextView moneyText,nameText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.mywallet_main);

        initView();
        updataInfo();
    }

    private  void updataInfo(){
        Service service = new Service();
        RequestParams params = new RequestParams();
        params.add("UserPhone",HomePageFragment1.UserPhone);
        params.add("SecretKey",HomePageFragment1.SecretKey);
        service.post(getApplicationContext(), "mydata", params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                walletmodel model = new Gson().fromJson(new String((byte[])object),walletmodel.class);
                Log.i("info",new String((byte[]) object));
                Log.i("model",model.getData().getMoney()+"");
                if (model == null){

                    return;
                }
                if (model.getState()==1) {
                    moneyText.setText("" + model.getData().getMoney()+"元");
                    nameText.setText("" + model.getData().getNickName());
                    Log.i("name",""+model.getData().getNickName());
                    new photoAsyncTask().execute(model.getData().getUserPhoto());
                }

            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(){
        img = (ImageView) findViewById(R.id.mywallet_fragment1_img);
        moneyText = (TextView) findViewById(R.id.mywallet_fragment1_money);
        nameText = (TextView) findViewById(R.id.mywallet_fragment1_name);
        upmoneyButton = (Button) findViewById(R.id.mywallet_fragment1_upmoney);
        repasswordButton = (Button) findViewById(R.id.mywallet_fragment1_repassword);

        Service service = new Service();

        upmoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText infoEidt ;
                infoEidt = new EditText(MyWalletActivity.this);
                infoEidt.setInputType(InputType.TYPE_CLASS_NUMBER);
                infoEidt.setTextColor(Color.BLACK);
                infoEidt.setMaxLines(1);
                infoEidt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
                new AlertDialog.Builder(MyWalletActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog)
                        .setTitle("充值")
                        .setMessage("输入充值金额")
                        .setView(infoEidt)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                RequestParams params = new RequestParams();
                                params.add("UserPhone", HomePageFragment1.UserPhone);
                                params.add("SecretKey", HomePageFragment1.SecretKey);
                                params.add("Money",infoEidt.getText().toString() );
                                service.post(MyWalletActivity.this, "recharge", params, new Listener() {
                                    @Override
                                    public void onSuccess(Object object) {
                                        XueModel xueModel = new Gson().fromJson(new String((byte[])object),XueModel.class);
                                        Toast.makeText(MyWalletActivity.this,xueModel.getMsg(),Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(String msg) {
                                        Toast.makeText(MyWalletActivity.this,msg,Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });

        repasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(MyWalletActivity.this,CheckActivity.class));

            }
        });
    }


    class photoAsyncTask extends AsyncTask<String,Void,Bitmap>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

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
            Log.i("post","ok");
            img.setImageBitmap(PhotoCut.toRoundBitmap(bitmap));
        }
    }

}
