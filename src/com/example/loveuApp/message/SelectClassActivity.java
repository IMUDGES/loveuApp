package com.example.loveuApp.message;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.loveuApp.R;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.Service;
import com.loopj.android.http.RequestParams;

/**
 * Created by dy on 2016/9/18.
 */
public class SelectClassActivity extends Activity {

    private EditText editText1,editText2,editText3,editText4;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectclass_activity);

        init();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push();
            }
        });
    }

    public void init(){
        editText1= (EditText) findViewById(R.id.nameId);
        editText2= (EditText) findViewById(R.id.passId);
        editText3= (EditText) findViewById(R.id.classId);
        editText4= (EditText) findViewById(R.id.classnum);
        submit= (Button) findViewById(R.id.submit_class);
    }

    public void push(){
        Service service=new Service();
        RequestParams params=new RequestParams();
        params.put("","");
        params.put("","");
        params.put("","");
        params.put("","");
        params.put("","");
        service.post(getApplication(), "", params, new Listener() {
            @Override
            public void onSuccess(Object object) {

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
}
