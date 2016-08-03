package com.example.loveuApp.homepage.give;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.giveCommentData;
import com.example.loveuApp.bean.giveCommentModel;
import com.example.loveuApp.bean.giveModel;
import com.example.loveuApp.bean.userModel;
import com.example.loveuApp.homepage.give.UI.NoScrollListview;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.giveCommentService;
import com.example.loveuApp.service.userService;
import com.example.loveuApp.util.PhotoCut;
import com.example.loveuApp.util.ToastManager;
import com.loopj.android.http.RequestParams;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yangy on 2016/7/30.
 */
public class GiveActivity extends Activity {
    private String commenttxt;
    private static giveModel givemodel;
    private static userModel usermodel;
    private ImageView imageView;
    private TextView username, info,tip_text;
    private Button button_send;
    private Bitmap userphoto;
    private EditText editText;
    private NoScrollListview listView;
    private SimpleAdapter listAdapter;
    private ArrayList<HashMap<String,Object>> listItem;
    private List<giveCommentModel> giveCommentModels;
    private giveCommentService givecommentservice;
    private userModel comment_userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.givemainlistitem);
        init();
        getuserbitmap(usermodel.getUserPhoto());
        setView();
    }

    public static void setModel(giveModel givemodel, userModel usermodel) {
        GiveActivity.givemodel = givemodel;
        GiveActivity.usermodel = usermodel;

    }
    private void init() {
        imageView = (ImageView) findViewById(R.id.givefragment_listview_img);
        username = (TextView) findViewById(R.id.givefragment_listview_name);
        info = (TextView) findViewById(R.id.givefragment_listview_info);
        button_send = (Button) findViewById(R.id.givecomment_sendButton);
        editText=(EditText)findViewById(R.id.givecomment_text);
        tip_text=(TextView)findViewById(R.id.givetip_text);

    }
    private void setView() {
        imageView.setImageBitmap(userphoto);
        username.setText(usermodel.getNickName());
        info.setText(givemodel.getGiveInformation());
        listView = (NoScrollListview)this.findViewById(R.id.commentary_listView);
        listItem = new ArrayList<HashMap<String, Object>>();



        listAdapter = new SimpleAdapter(this,listItem,
                R.layout.givemaincommentitems,
                new String[]{
                        "username",

                        "list_item_comment",
                },
                new int[]{
                        R.id.givefragment_listview_name,
                        R.id.givefragment_listview_info,
                });
        givecommentservice=new giveCommentService();
        String url="http://183.175.1.250:5000/givecomment";
        RequestParams params=new RequestParams();
        params.add("GiveId",givemodel.getGiveId()+"");
        givecommentservice.get(GiveActivity.this, url,params,new Listener() {
            @Override
            public void onSuccess(Object object) {
                giveCommentData giveCommentData=(giveCommentData) object;
                if(giveCommentData.getState()==0){
                    tip_text.setText("暂时没有评论");
                }else{
                    tip_text.setVisibility(View.INVISIBLE);
                    for(int i = 0; i<giveCommentModels.size();i++){
                        giveCommentModel givecommentModel = (giveCommentModel)giveCommentModels.get(i);
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        userService userService=new userService();
                        RequestParams params_getuser=new RequestParams();
                        params_getuser.add("","");
                        userService.get(GiveActivity.this, "url", params_getuser, new Listener() {
                            @Override
                            public void onSuccess(Object object) {
                                comment_userModel=(userModel) object;
                                map.put("username",comment_userModel.getNickName());
                            }

                            @Override
                            public void onFailure(String msg) {
                                        Log.i("give评论获取用户昵称","失败");
                            }
                        });
                        map.put("list_item_comment",givecommentModel.getCommentInformation());
                        //加载图片
                        listAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                            @Override
                            public boolean setViewValue(View view, Object data, String s) {
                                if(view instanceof ImageView && data instanceof Bitmap){
                                    ImageView i = (ImageView)view;
                                    i.setImageBitmap((Bitmap) data);
                                    return true;
                                }
                                return false;
                            }
                        });
                        listItem.add(map);
                    }
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
       button_send.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               commenttxt=editText.getText().toString().trim();
               RequestParams params = new RequestParams();
               params.put("","");
               giveCommentService service = new giveCommentService();
               service.get(GiveActivity.this, "未知", params, new Listener() {
                   @Override
                   public void onSuccess(Object comments) {
                       ToastManager.toast(getApplication(),"评论成功");
                   }
                   @Override
                   public void onFailure(String msg) {
                       Toast.makeText(GiveActivity.this,msg,Toast.LENGTH_SHORT).show();
                   }
               });
           }
       });
    }
    public void getuserbitmap(String uphotoid) {
        String url_userphoto=uphotoid;
        new Thread(){
            @Override
            public void run() {
                URLConnection connection;
                InputStream inputStream;
                try {
                    connection=new URL(url_userphoto).openConnection();
                    inputStream=connection.getInputStream();
                    BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
                    userphoto= BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.run();
    }
    public class ViewHolder{
        public TextView commentname;
        public TextView commentinfo;
        public ImageView imageView;
        public Bitmap bitmap;
    }


    class photoAsyncTask extends AsyncTask<String,Void,Bitmap> {

        private ViewHolder v;
        private ImageView imageView;
        public photoAsyncTask(ImageView imageView, ViewHolder viewHolder) {
            v=viewHolder;
            this.imageView=imageView;
        }

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
            imageView.setImageBitmap(PhotoCut.toRoundBitmap(bitmap));
            v.bitmap=bitmap;
        }
    }
}
