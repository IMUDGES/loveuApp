package com.example.loveuApp.homepage.give;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.giveCommentData;
import com.example.loveuApp.bean.giveCommentModel;
import com.example.loveuApp.bean.giveCommentReturnModel;
import com.example.loveuApp.bean.giveModel;
import com.example.loveuApp.homepage.give.adapter.GiveCommentAdapter;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.giveCommentService;
import com.example.loveuApp.util.PhotoCut;
import com.example.loveuApp.util.ToastManager;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangy on 2016/7/30.
 */
public class GiveActivity extends Activity {
    private String commenttxt;
    private  giveModel givemodel;
    private LinearLayout layoutHead;
    private CommentBack commentBack;
    private ImageView giveimageView,giveuserimageView;
    private NotifyingScrollView scrollview;
    private TextView username, info,tip_text;
    private Button button_send;
    private Bitmap userphoto;
    private EditText editText;
    private ListViewForScrollView listView;
    private List<giveCommentModel> giveCommentModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.givemainlistitem);
        Intent intent = this.getIntent();
        givemodel=(giveModel)intent.getSerializableExtra("givemodel");
        init();
        setView();
        listView=(ListViewForScrollView)findViewById(R.id.give_comment_listView);
        giveCommentModels=new ArrayList<>();
        getCommentData();
        commentBack=new CommentBack() {
            @Override
            public void back() {
                GiveCommentAdapter  adapter=new GiveCommentAdapter(GiveActivity.this,giveCommentModels);
                listView.setAdapter(adapter);
            }
        };
    }
    private void init() {
        scrollview = (NotifyingScrollView) findViewById(R.id.give_sv_layout);
        layoutHead = (LinearLayout) findViewById(R.id.givell_head);
        giveimageView = (ImageView) findViewById(R.id.give_comment_image);
        giveuserimageView= (ImageView) findViewById(R.id.give_comment_userImage);
        username = (TextView) findViewById(R.id.give_comment_username);
        info = (TextView) findViewById(R.id.give_comment_info);
        button_send = (Button) findViewById(R.id.givecomment_sendButton);

        tip_text=(TextView)findViewById(R.id.givetip_text);


    }
    private void setView() {
        username.setText(givemodel.getNickName());
        info.setText(givemodel.getGiveInformation());
        new CommentAsyncTask(giveimageView).execute(givemodel.getGiveImage());
        new CommentAsyncTask(giveuserimageView).execute(givemodel.getUserPhoto());
        layoutHead.getBackground().setAlpha(0);

        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams params = giveimageView.getLayoutParams();
        params.height =width;
        params.width =width;
        giveimageView.setLayoutParams(params);

        scrollview.smoothScrollTo(0, 0);
        scrollview.setOnScrollChangedListener(new com.example.loveuApp.homepage.give.NotifyingScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
                //define it for scroll height
                int lHeight = (int) (width*2.5);
                if(t<=lHeight){
                    int progress = (int)(new Float(t)/new Float(lHeight)*255);
                    layoutHead.getBackground().setAlpha(progress);
                }else{
                    layoutHead.getBackground().setAlpha(255);
                }

            }
        });
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    EditText editText1=new EditText(GiveActivity.this);
                    editText1.setTextColor(Color.parseColor("#000000"));
                    editText1.setWidth(1000);
                    new AlertDialog.Builder(GiveActivity.this,android.R.style.Theme_DeviceDefault_InputMethod)
                            .setTitle("输入评论")
                            .setView(editText1)
                            .setPositiveButton("提交", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String commenttxt=editText1.getText().toString();
                                    if(commenttxt==null&&commenttxt==""){
                                        Toast.makeText(GiveActivity.this,"请输入内容！",Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        sendComment(commenttxt);}
                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                }
            }
        });
    }
    public void sendComment(String commenttxt){
        RequestParams params = new RequestParams();
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String UserPhone = preferences.getString("UserPhone","该数据未写入");
        String SecretKey = preferences.getString("SecretKey","该数据未写入");
//        Toast.makeText(getActivity().getApplicationContext(),a+"--"+b,Toast.LENGTH_SHORT).show();
        String url="getgive";
               /* params.put("UserPhone",UserPhone);
                params.put("SecretKey",SecretKey);*/
        params.put("UserPhone","22222222222");
        params.put("SecretKey","11111");
        params.put("GiveId",givemodel.getGiveId());
        params.put("CommentInformation",commenttxt);
        giveCommentService service = new giveCommentService();
        service.post(GiveActivity.this, url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                giveCommentReturnModel givecommentreturnmodel= (giveCommentReturnModel)object;
                ToastManager.toast(GiveActivity.this,givecommentreturnmodel.getMsg());
            }
            @Override
            public void onFailure(String msg) {
                Toast.makeText(GiveActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void getCommentData() {
        giveCommentService givecommentservice=new giveCommentService();
        String url="givecomment";
        RequestParams params=new RequestParams();
        params.add("GiveId",givemodel.getGiveId()+"");

        givecommentservice.get(this, url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                giveCommentData giveCommentData= (giveCommentData) object;
                giveCommentModels=giveCommentData.getGiveCommentModels();
                if(giveCommentData.getState()==0){
                    tip_text.setText(giveCommentData.getMsg());
                    return;
                }
                commentBack.back();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(GiveActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class CommentAsyncTask extends AsyncTask<String,Void,Bitmap>{
        private ImageView imageView;

        public CommentAsyncTask(ImageView imageView) {
            this.imageView = imageView;
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
            if(imageView==giveuserimageView){
                imageView.setImageBitmap(PhotoCut.toRoundBitmap(bitmap));
            }else{
                imageView.setImageBitmap(bitmap);
            }

        }
    }

    private interface CommentBack{
        public void back();
    }

}
