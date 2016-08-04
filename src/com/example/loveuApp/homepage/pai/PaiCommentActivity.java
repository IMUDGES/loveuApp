package com.example.loveuApp.homepage.pai;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.paiCommentModel;
import com.example.loveuApp.bean.paiModel;
import com.example.loveuApp.homepage.pai.adapter.PaiCommentAdapter;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.model.PaiCommentModel;
import com.example.loveuApp.model.PaiModel;
import com.example.loveuApp.service.Service;
import com.example.loveuApp.service.paiCommentService;
import com.example.loveuApp.util.PhotoCut;
import com.example.loveuApp.util.TextViewToDBC;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dy on 2016/8/2.
 */
public class PaiCommentActivity extends Activity{


    private ImageView paiImageView,userImage;
    private TextView information,money,time,username,finish;
    private Button addFriend;
    private Button comment,bidding;
    private NotifyingScrollView scrollview;
    private LinearLayout layoutHead;
    private ListViewForScrollView listView;

    private static paiModel paiModels;
    private List<paiCommentModel> commentModel;
    private PaiCommentModel model;
    private CommentBack commentBack;
    private PaiCommentAdapter adapter;


    public static void setPaiModels(paiModel paiModels) {
        PaiCommentActivity.paiModels = paiModels;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paimainactivity);
        init();

        commentModel=new ArrayList<>();
        getCommentData();
        commentBack=new CommentBack() {
            @Override
            public void back() {
                adapter=new PaiCommentAdapter(PaiCommentActivity.this,commentModel);
                listView.setAdapter(adapter);
            }
        };

        layoutHead.getBackground().setAlpha(0);

        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams params = paiImageView.getLayoutParams();
        params.height =width;
        params.width =width;
        paiImageView.setLayoutParams(params);

        scrollview.smoothScrollTo(0, 0);
        scrollview.setOnScrollChangedListener(new NotifyingScrollView.OnScrollChangedListener() {

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
    }

    private void init() {
        scrollview = (NotifyingScrollView) findViewById(R.id.sv_layout);
        layoutHead = (LinearLayout) findViewById(R.id.ll_head);
        listView= (ListViewForScrollView) findViewById(R.id.pai_comment_listView);

        paiImageView= (ImageView) findViewById(R.id.pai_comment_image);
        userImage= (ImageView) findViewById(R.id.pai_comment_userImage);
        new CommentAsyncTask(paiImageView).execute(paiModels.getPaiImage());
        new CommentAsyncTask(userImage).execute(paiModels.getUserPhoto());

        username= (TextView) findViewById(R.id.pai_comment_username);
        information= (TextView) findViewById(R.id.pai_comment_infor);
        time= (TextView) findViewById(R.id.pai_comment_time);
        money= (TextView) findViewById(R.id.pai_comment_money);
        username.setText(paiModels.getNikeName());
        information.setText("        "+paiModels.getPaiInformation());
        money.setText(""+paiModels.getPaiMoney());
        new CountDownTimer(TextViewToDBC.toSecond(paiModels.getDownTime())*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                time.setText(": " + (millisUntilFinished / 1000 / 86400) + "天" +
                        "" + (millisUntilFinished / 1000 % 86400) / 3600 + "小时" + (millisUntilFinished / 1000 % 86400 % 3600) / 60 + "分"
                        + (millisUntilFinished / 1000 % 86400 % 3600 % 60) + "秒");
            }

            @Override
            public void onFinish() {
                time.setText("done!");
            }
        }.start();

        finish= (TextView) findViewById(R.id.comment_finish);
        finish.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addFriend= (Button) findViewById(R.id.pai_comment_addFriend);
        bidding= (Button) findViewById(R.id.pai_comment_want);
        bidding.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText1=new EditText(PaiCommentActivity.this);
                editText1.setTextColor(Color.parseColor("#000000"));
                editText1.setWidth(1000);
                new AlertDialog.Builder(PaiCommentActivity.this,android.R.style.Theme_DeviceDefault_InputMethod)
                        .setTitle("竞拍(输入金额)")
                        .setView(editText1)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText editText2=new EditText(PaiCommentActivity.this);
                                editText2.setTextColor(Color.parseColor("#000000"));
                                editText2.setWidth(1000);
                                new AlertDialog.Builder(PaiCommentActivity.this,android.R.style.Theme_DeviceDefault_InputMethod)
                                        .setTitle("竞拍(输入密码)")
                                        .setView(editText2)
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Iwant(editText1.getText().toString()+"",editText2.getText().toString()+"");
                                            }
                                        })
                                        .setNegativeButton("取消",null).show();
                            }
                        })
                        .setNegativeButton("取消",null).show();
            }
        });
        comment= (Button) findViewById(R.id.pai_comment_say);
        comment.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText1=new EditText(PaiCommentActivity.this);
                editText1.setTextColor(Color.parseColor("#000000"));
                editText1.setWidth(1000);
                new AlertDialog.Builder(PaiCommentActivity.this,android.R.style.Theme_DeviceDefault_InputMethod)
                        .setTitle("输入评论")
                        .setView(editText1)
                        .setPositiveButton("提交", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                CommitSay(editText1.getText().toString()+"");
                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();
            }
        });
    }

    private void CommitSay(String s) {
        String url="sendpaicomment";
        RequestParams request=new RequestParams();
        request.put("UserPhone","22222222222");
        request.put("SecretKey","11111");
        request.put("PaiId",paiModels.getPaiId());
        request.put("CommentInformation",s);
        Service service = new Service();
        service.post(this, url, request, new Listener() {
            @Override
            public void onSuccess(Object object) {
                PaiModel model=new Gson().fromJson(new String((byte[]) object),PaiModel.class);
                if(model.getState()==1){
                    getCommentData();
                    Toast.makeText(PaiCommentActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PaiCommentActivity.this, model.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(PaiCommentActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Iwant(String money, String password) {
        String url="getpai";
        RequestParams request=new RequestParams();
        request.put("UserPhone","22222222222");
        request.put("SecretKey","11111");
        request.put("PaiId",paiModels.getPaiId());
        request.put("PaiMoney",money);
        request.put("PayPassword",password);
        Service service = new Service();
        service.post(this, url, request, new Listener() {
            @Override
            public void onSuccess(Object object) {
                PaiModel model=new Gson().fromJson(new String((byte[]) object),PaiModel.class);
                if(model.getState()==1){
                    Toast.makeText(PaiCommentActivity.this, "竞拍成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PaiCommentActivity.this, model.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(PaiCommentActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getCommentData() {
        Log.i("msg","getData()");
        String url="getpaicomment";
        RequestParams params=new RequestParams();
        paiCommentService service=new paiCommentService();
        params.put("PaiId",paiModels.getPaiId());
        service.get(this, url, params, new Listener() {
            @Override
            public void onSuccess(Object object) {
                model= (PaiCommentModel) object;
                if(model.getNum()==0){
                    return;
                }
                commentModel=model.getPaicommentdata();
                commentBack.back();
            }

            @Override
            public void onFailure(String msg) {
                Toast.makeText(PaiCommentActivity.this, msg, Toast.LENGTH_SHORT).show();
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
            if(imageView==userImage){
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
