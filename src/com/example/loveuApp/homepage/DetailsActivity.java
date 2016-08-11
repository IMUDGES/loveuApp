package com.example.loveuApp.homepage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.foodModel;
import com.example.loveuApp.bean.userModel;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.OtherUserModel;
import com.example.loveuApp.service.Service;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by chaolu on 2016/8/1.
 */
public class DetailsActivity extends Activity {

    private int UserId;
    private String Url;
    private String name,phone;
    private Button addfriend;
    private int sex;
    private TextView nameText,phoneText,sexText;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.foodxuerundetails);
        init();
        initView();
        //Toast.makeText(getApplicationContext(),UserId+"   "+Url,Toast.LENGTH_SHORT).show();
       updataInfo();
        new MyTask().execute(Url);
    }

    private void init() {
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //data = (foodModel) intent.getSerializableExtra("data");
        Bundle bundle = getIntent().getExtras();
        UserId = bundle.getInt("UserId");
        Url = bundle.getString("URL");
    }

    private void initView() {
        sexText = (TextView) findViewById(R.id.fooddetails_sex);
        nameText = (TextView) findViewById(R.id.fooddetails_name);
        phoneText = (TextView) findViewById(R.id.fooddetails_phone);
        img = (ImageView) findViewById(R.id.fooddetails_img);
        addfriend = (Button) findViewById(R.id.fooddetails_addfriend);
        addfriend.setOnClickListener(new MyListener());
    }
    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Service service = new Service();
            RequestParams params = new RequestParams();
            params.put("UserPhone",HomePageFragment1.UserPhone);
            params.put("SecretKey",HomePageFragment1.SecretKey);
            params.put("BefocusonId",UserId);
            service.post(getApplicationContext(), "attention", params, new Listener() {
                @Override
                public void onSuccess(Object object) {
                    Toast.makeText(getApplicationContext(),"关注成功",Toast.LENGTH_SHORT);
                }

                @Override
                public void onFailure(String msg) {
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT);
                }
            });
        }
    }

    private void updataInfo() {
        Service service = new Service();
        RequestParams param = new RequestParams();
        param.put("UserId",UserId);
        service.get(this, "data", param, new Listener() {
            @Override
            public void onSuccess(Object object) {

                Log.i("string",new String((byte[])object));
                OtherUserModel model = new Gson().fromJson(new String((byte[])object),OtherUserModel.class);
                if (model.getState()=="0"){
                    Toast.makeText(getApplicationContext(),"不存在此id",Toast.LENGTH_SHORT).show();
                    return ;
                }
                if (model.getData().getUserSex()==0)
                    sexText.setText("女");
                else
                    sexText.setText("男");
                phoneText.setText(model.getData().getUserPhone());
                nameText.setText(model.getData().getNickName());

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    class MyTask extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            return  getBitmapFromURL(strings[0]);
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);
            img.setImageBitmap(s);
        }
    }
    private Bitmap getBitmapFromURL(String urlString) {
//        Log.i("getbitmapfromUrl", urlString);
        Bitmap bitmap = null;
        InputStream is = null;
        if (urlString == null) {
            return null;
        }
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            // Thread.sleep(1000);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        ;
        if (bitmap == null)
            Log.i("bitmap", "no");
        else Log.i("bitmap", "yes");
        return bitmap;
    }


}
