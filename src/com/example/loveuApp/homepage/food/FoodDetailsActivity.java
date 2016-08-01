package com.example.loveuApp.homepage.food;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.foodModel;

/**
 * Created by chaolu on 2016/8/1.
 */
public class FoodDetailsActivity extends Activity {

    private foodModel data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fooddetails);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        Intent intent = this.getIntent();
        data = (foodModel) intent.getSerializableExtra("data");
        //Toast.makeText(getApplicationContext(),data.getFoodInformation(),Toast.LENGTH_SHORT).show();
    }


}
