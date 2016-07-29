package com.example.loveuApp.homepage.give;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.loveuApp.R;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.giveCommentService;
import com.example.loveuApp.util.ToastManager;
import com.loopj.android.http.RequestParams;

/**
 * Created by yangy on 2016/7/28.
 */
public class givemaincommentActivity extends Activity implements View.OnClickListener {
    private String commenttxt;
    private Button button_cancel;
    private Button button_send;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.givemaincomment);
        button_cancel=(Button) findViewById(R.id.cancelButton);
        button_send=(Button) findViewById(R.id.sendButton);
        editText=(EditText)findViewById(R.id.comment_text);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelButton:{
                finish();
            }
            case R.id.sendButton:{
                commenttxt=editText.getText().toString().trim();
                RequestParams params = new RequestParams();
                params.put("","");
                giveCommentService service = new giveCommentService();
                service.get(givemaincommentActivity.this, "未知", params, new Listener() {
                    @Override
                    public void onSuccess(Object comments) {
                        ToastManager.toast(getApplication(),"评论成功");
                    }
                    @Override
                    public void onFailure(String msg) {
                        Toast.makeText(givemaincommentActivity.this,msg,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}
