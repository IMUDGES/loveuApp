package com.example.loveuApp.updata;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import com.example.loveuApp.R;

/**
 * Created by dy on 2016/8/4.
 */
public class UpFoodActivity extends Activity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upfoodactivity);
        editText= (EditText) findViewById(R.id.upfood_write);

    }
}
