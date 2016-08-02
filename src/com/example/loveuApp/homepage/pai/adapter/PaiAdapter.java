package com.example.loveuApp.homepage.pai.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.paiModel;
import com.example.loveuApp.util.TextViewToDBC;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

/**
 * Created by dy on 2016/8/2.
 */
public class PaiAdapter extends BaseAdapter {

    private List<paiModel> models;
    private Context context;

    public void setModels(List<paiModel> models) {
        this.models = models;
    }

    public PaiAdapter(Context context, List<paiModel> models) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.paimainlistview,null);
            viewHolder = new ViewHolder();
            viewHolder.money = (TextView) convertView.findViewById(R.id.pai_item_money);
            viewHolder.time = (TextView) convertView.findViewById(R.id.pai_item_time);
            viewHolder.infor = (TextView) convertView.findViewById(R.id.pai_item_infor);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.pai_item_image);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.money.setText(models.get(i).getPaiMoney()+"");
        viewHolder.infor.setText("    "+models.get(i).getPaiInformation());
        //viewHolder.time.setText(models.get(i).getDownTime());

        final ViewHolder finalViewHolder = viewHolder;
        if(finalViewHolder.countDownTimer==null){
            Log.i("timeInformation",TextViewToDBC.toSecond(models.get(i).getDownTime())+"");
            finalViewHolder.countDownTimer = new CountDownTimer(TextViewToDBC.toSecond(models.get(i).getDownTime())*1000, 1000) {
                public void onTick(long millisUntilFinished) {
                    finalViewHolder.time.setText(": " + (millisUntilFinished/1000/86400)+"天" +
                            ""+(millisUntilFinished/1000%86400)/3600+"小时"+(millisUntilFinished/1000%86400%3600)/60+"分"
                            +(millisUntilFinished/1000%86400%3600%60)+"秒");
                }
                public void onFinish() {
                    finalViewHolder.time.setText("done!");
                }
            };
            finalViewHolder.countDownTimer.start();
        }

        if(viewHolder.bitmap!=null){
            viewHolder.imageView.setImageBitmap(viewHolder.bitmap);
        }else{
            new photoAsyncTask(viewHolder.imageView,viewHolder).execute(models.get(i).getPaiImage());
        }

        return convertView;
    }

    private class ViewHolder{
        public CountDownTimer countDownTimer;
        public TextView money;
        public TextView infor;
        public TextView time;
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
            imageView.setImageBitmap(bitmap);
            v.bitmap=bitmap;
        }
    }
}