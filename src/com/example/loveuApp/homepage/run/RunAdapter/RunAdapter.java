package com.example.loveuApp.homepage.run.RunAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.runModel;
import com.example.loveuApp.bean.userModel;
import com.example.loveuApp.homepage.DetailsActivity;
import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by dy on 2016/7/29.
 */
public class RunAdapter extends BaseAdapter {
    private List<runModel> runModels;
    private Context context;

    public RunAdapter(Context context, List<runModel> runModels) {
        this.context = context;
        this.runModels = runModels;
    }

    @Override
    public int getCount() {
        return runModels.size();
    }

    @Override
    public Object getItem(int i) {
        return runModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.helpmainlistitem,null);
            viewHolder = new ViewHolder();
            viewHolder.area = (TextView) convertView.findViewById(R.id.run_item_area);
            viewHolder.time = (TextView) convertView.findViewById(R.id.run_item_time);
            viewHolder.infor = (TextView) convertView.findViewById(R.id.run_item_infor);
            viewHolder.username = (TextView) convertView.findViewById(R.id.run_item_username);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.run_item_image);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.area.setText(runModels.get(i).getRunArea());
        viewHolder.infor.setText(runModels.get(i).getRunInformation());
        viewHolder.time.setText(runModels.get(i).getRunTime());
        if(viewHolder.bitmap!=null){
            viewHolder.imageView.setImageBitmap(viewHolder.bitmap);
        }else{
            new MyAsyncTask(viewHolder).execute(runModels.get(i).getUserId());
        }

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("UserId",runModels.get(i).getUserId());
                bundle.putString("URL",runModels.get(i).getUserPhoto());
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public class ViewHolder{
        public TextView area;
        public TextView infor;
        public TextView time;
        public TextView username;
        public ImageView imageView;
        public Bitmap bitmap;
    }
    private class Obj{
        public userModel md;
        public Bitmap bitmap;
    }

    class MyAsyncTask extends AsyncTask<Integer,Void,Obj>{

        private ViewHolder viewHolder;

        public MyAsyncTask( ViewHolder viewHolder) {
            this.viewHolder = viewHolder;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Obj doInBackground(Integer... integers) {
            Integer i=integers[0];
            Obj o=new Obj();
            userModel md;
            o.md=sendGet("183.175.14.250:5000/data","page="+i);
            md=o.md;
            o.bitmap=getBitmapByURL(md.getUserPhone());
            return o;
        }

        @Override
        protected void onPostExecute(Obj model) {
            super.onPostExecute(model);

            viewHolder.username.setText(model.md.getNickName());
            viewHolder.imageView.setImageBitmap(model.bitmap);
            viewHolder.bitmap=model.bitmap;
        }

        public userModel sendGet(String url, String param) {
            String result = "";
            BufferedReader in = null;
            try {
                String urlNameString = url + "?" + param;
                URL realUrl = new URL(urlNameString);
                URLConnection connection = realUrl.openConnection();
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                connection.connect();
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"gbk"));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            userModel model=new Gson().fromJson(result,userModel.class);
            return model;
        }

        public Bitmap getBitmapByURL(String url) {
            Bitmap bitmap=null;
            URLConnection connection;
            InputStream inputStream;
            try {
                connection=new URL(url).openConnection();
                inputStream=connection.getInputStream();

                BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
                bitmap= BitmapFactory.decodeStream(inputStream);

                inputStream.close();
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
}
