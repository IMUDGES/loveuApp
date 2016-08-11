package com.example.loveuApp.message.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.helpModel;
import com.example.loveuApp.util.PhotoCut;
import com.example.loveuApp.util.TextViewToDBC;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by dy on 2016/7/28.
 */
public class HelpAdapter extends BaseAdapter {

    private List<helpModel> models;
    private Context context;

    public void setModels(List<helpModel> models) {
        this.models = models;
    }

    public HelpAdapter(Context context, List<helpModel> models) {
        this.context = context;
        this.models = models;
    }
    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int i) {
        return models.get(i);
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
            viewHolder.money = (TextView) convertView.findViewById(R.id.help_item_money);
            viewHolder.time = (TextView) convertView.findViewById(R.id.help_item_time);
            viewHolder.infor = (TextView) convertView.findViewById(R.id.help_item_infor);
            viewHolder.username = (TextView) convertView.findViewById(R.id.help_item_username);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.help_item_image);
            viewHolder.button= (Button) convertView.findViewById(R.id.help_item_addfriend);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.money.setText(models.get(i).getHelpMoney()+"");
        viewHolder.infor.setText("    "+models.get(i).getHelpInformation());
        viewHolder.time.setText(models.get(i).getDownTime());
        viewHolder.username.setText(models.get(i).getNickName());
        if(viewHolder.bitmap!=null){
            viewHolder.imageView.setImageBitmap(PhotoCut.toRoundBitmap(viewHolder.bitmap));
        }else{
            new photoAsyncTask(viewHolder.imageView,viewHolder).execute(models.get(i).getUserPhoto());
        }
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (finalViewHolder.button.getText()+""){
                    case "+":
                        finalViewHolder.infor.setSingleLine(false);
                        finalViewHolder.infor.setText("    "+TextViewToDBC.ToDBC(models.get(i).getHelpInformation()));
                        finalViewHolder.button.setText("×");
                        break;
                    case "×":
                        finalViewHolder.infor.setSingleLine(true);
                        finalViewHolder.infor.setText("    "+TextViewToDBC.ToDBC(models.get(i).getHelpInformation()));
                        finalViewHolder.button.setText("+");
                        break;
                }
            }
        });
        return convertView;
    }

    public class ViewHolder{
        public TextView money;
        public TextView infor;
        public TextView time;
        public TextView username;
        public ImageView imageView;
        public Bitmap bitmap;
        public Button button;
    }

    class photoAsyncTask extends AsyncTask<String,Void,Bitmap>{

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
