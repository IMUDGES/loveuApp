package com.example.loveuApp.left.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.*;
import com.example.loveuApp.homepage.DetailsActivity;
import com.example.loveuApp.left.model.OnlyModel;
import com.example.loveuApp.model.HelpModel;
import com.example.loveuApp.util.PhotoCut;
import com.example.loveuApp.util.TextViewToDBC;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by caolu on 2016/8/8.
 */
public class SearchListViewAdapter extends BaseAdapter{

    private List<OnlyModel> data;
    private Context context;

    public void setObject_All(List<OnlyModel> object_All) {
        this.data = object_All;
    }

    public SearchListViewAdapter(List<OnlyModel> object_All, Context context){
        this.data=object_All;
        this.context=context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        switch(data.get(i).getKey()){
            case "food":convertView=getFoodView(i,viewHolder,convertView);break;
            case "give":convertView=getGiveView(i,viewHolder,convertView);break;
            case "help":convertView=getHelpView(i,viewHolder,convertView);break;
            case "pai":convertView=getPaiView(i,viewHolder,convertView);break;
            case "xue":convertView=getXueView(i,viewHolder,convertView);break;
            case "run":convertView=getRunView(i,viewHolder,convertView);break;
            default:;
        }
        return convertView;
    }

    private View getRunView(int i, ViewHolder viewHolder, View convertView) {
        //if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.runmainlistitem,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.runfragment_listview_name);
            viewHolder.time = (TextView) convertView.findViewById(R.id.runfragment_listview_time);
            viewHolder.info = (TextView) convertView.findViewById(R.id.runfragment_listview_info);
            viewHolder.area = (TextView) convertView.findViewById(R.id.runfragment_listview_arae);
            viewHolder.Lv = (TextView) convertView.findViewById(R.id.runfragment_listview_lv);
            viewHolder.sex = (TextView) convertView.findViewById(R.id.runfragment_listview_sex);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.runfragment_listview_img);
            //convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

        new photoAsyncTask(viewHolder.imageView).execute(((runModel)data.get(i).getObject()).getUserPhoto());
        viewHolder.name.setText(((runModel)data.get(i).getObject()).getNickName()+"");
        viewHolder.area.setText(((runModel)data.get(i).getObject()).getRunArea());
        String time = ((runModel)data.get(i).getObject()).getRunTime();
        String info = ((runModel)data.get(i).getObject()).getRunInformation();

        if (info.length()>20&&info!=null) {
            info = info.substring(0, 31);
            viewHolder.info.setText("        " + info + "...");
        }else
            viewHolder.info.setText("        " + info);
        viewHolder.time.setText(((runModel)data.get(i).getObject()).getRunTime());
        Log.i("sex",((runModel)data.get(i).getObject()).getUserSex()+"");
        if(((runModel)data.get(i).getObject()).getUserSex()==1){
            viewHolder.sex.setText("♂");
            viewHolder.sex.setBackgroundResource(R.drawable.foodlistview_sexboy);

        }else {
            viewHolder.sex.setText("♀");
            viewHolder.sex.setBackgroundResource(R.drawable.foodlistview_sexgirl);
        }
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("UserId",((runModel)data.get(i).getObject()).getUserId());
                bundle.putString("URL",((runModel)data.get(i).getObject()).getUserPhoto());
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private View getXueView(int i, ViewHolder viewHolder, View convertView) {
        //if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.xuemainlistitem,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.xuefragment_listview_name);
            viewHolder.time = (TextView) convertView.findViewById(R.id.xuefragment_listview_time);
            viewHolder.info = (TextView) convertView.findViewById(R.id.xuefragment_listview_info);
            viewHolder.area = (TextView) convertView.findViewById(R.id.xuefragment_listview_arae);
            viewHolder.Lv = (TextView) convertView.findViewById(R.id.xuefragment_listview_lv);
            viewHolder.sex = (TextView) convertView.findViewById(R.id.xuefragment_listview_sex);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.xuefragment_listview_img);
            //convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

        new photoAsyncTask(viewHolder.imageView).execute(((xueModel)data.get(i).getObject()).getUserPhoto());
        viewHolder.name.setText(((xueModel)data.get(i).getObject()).getNickName()+"");
        viewHolder.area.setText(((xueModel)data.get(i).getObject()).getXueArea());
        String time = ((xueModel)data.get(i).getObject()).getXueTime();
        String info = ((xueModel)data.get(i).getObject()).getXueInformation();

        if (info.length()>20&&info!=null) {
            info = info.substring(0, 31);
            viewHolder.info.setText("        " + info + "...");
        }else
            viewHolder.info.setText("        " + info);
        viewHolder.time.setText(((xueModel)data.get(i).getObject()).getXueTime());
        Log.i("sex",((xueModel)data.get(i).getObject()).getUserSex()+"");
        if(((xueModel)data.get(i).getObject()).getUserSex()==1){
            viewHolder.sex.setText("♂");
            viewHolder.sex.setBackgroundResource(R.drawable.foodlistview_sexboy);

        }else {
            viewHolder.sex.setText("♀");
            viewHolder.sex.setBackgroundResource(R.drawable.foodlistview_sexgirl);
        }

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("UserId",((xueModel)data.get(i).getObject()).getUserId());
                bundle.putString("URL",((xueModel)data.get(i).getObject()).getUserPhoto());
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    private View getPaiView(int i, ViewHolder viewHolder, View convertView) {
        //if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.paimainlistview,null);
            viewHolder = new ViewHolder();
            viewHolder.money = (TextView) convertView.findViewById(R.id.pai_item_money);
            viewHolder.time = (TextView) convertView.findViewById(R.id.pai_item_time);
            viewHolder.infor = (TextView) convertView.findViewById(R.id.pai_item_infor);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.pai_item_image);
//            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

        viewHolder.money.setText(((paiModel)data.get(i).getObject()).getPaiMoney()+"");
        viewHolder.infor.setText("    "+((paiModel)data.get(i).getObject()).getPaiInformation());
        //viewHolder.time.setText(models.get(i).getDownTime());

        final ViewHolder finalViewHolder = viewHolder;
        if(finalViewHolder.countDownTimer==null){
            Log.i("timeInformation",TextViewToDBC.toSecond(((paiModel)data.get(i).getObject()).getDownTime())+"");
            finalViewHolder.countDownTimer = new CountDownTimer(TextViewToDBC.toSecond(((paiModel)data.get(i).getObject()).getDownTime())*1000, 1000) {
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
            new photoAsyncTask(viewHolder.imageView).execute(((paiModel)data.get(i).getObject()).getPaiImage());
        }

        return convertView;
    }

    private View getHelpView(int i, ViewHolder viewHolder, View convertView) {
        //if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.helpmainlistitem,null);
            viewHolder = new ViewHolder();
            viewHolder.money = (TextView) convertView.findViewById(R.id.help_item_money);
            viewHolder.time = (TextView) convertView.findViewById(R.id.help_item_time);
            viewHolder.infor = (TextView) convertView.findViewById(R.id.help_item_infor);
            viewHolder.username = (TextView) convertView.findViewById(R.id.help_item_username);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.help_item_image);
            viewHolder.button= (Button) convertView.findViewById(R.id.help_item_addfriend);
//            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

        viewHolder.money.setText(((helpModel)data.get(i).getObject()).getHelpMoney()+"");
        viewHolder.infor.setText("    "+((helpModel)data.get(i).getObject()).getHelpInformation());
        viewHolder.time.setText(((helpModel)data.get(i).getObject()).getDownTime());
        viewHolder.username.setText(((helpModel)data.get(i).getObject()).getNickName());
        if(viewHolder.bitmap!=null){
            viewHolder.imageView.setImageBitmap(PhotoCut.toRoundBitmap(viewHolder.bitmap));
        }else{
            new photoAsyncTask(viewHolder.imageView).execute(((helpModel)data.get(i).getObject()).getUserPhoto());
        }
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (finalViewHolder.button.getText()+""){
                    case "+":
                        finalViewHolder.infor.setSingleLine(false);
                        finalViewHolder.infor.setText("    "+ TextViewToDBC.ToDBC(((helpModel)data.get(i).getObject()).getHelpInformation()));
                        finalViewHolder.button.setText("×");
                        break;
                    case "×":
                        finalViewHolder.infor.setSingleLine(true);
                        finalViewHolder.infor.setText("    "+TextViewToDBC.ToDBC(((helpModel)data.get(i).getObject()).getHelpInformation()));
                        finalViewHolder.button.setText("+");
                        break;
                }
            }
        });

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("UserId",((helpModel)data.get(i).getObject()).getUserId());
                bundle.putString("URL",((helpModel)data.get(i).getObject()).getUserPhoto());
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private View getGiveView(int i, ViewHolder viewHolder, View convertView) {
        //if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.givemainlistview,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.givemainitem_name);
            viewHolder.info = (TextView) convertView.findViewById(R.id.givemainitem_info);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.givemainitem_img);
            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

        viewHolder.name.setText(((giveModel)data.get(i).getObject()).getNickName());
        viewHolder.info.setText("主人留言：   "+((giveModel)data.get(i).getObject()).getGiveInformation());
        if(viewHolder.bitmap!=null){
            viewHolder.imageView.setImageBitmap(PhotoCut.toRoundBitmap(viewHolder.bitmap));
        }else{
            new photoAsyncTask(viewHolder.imageView).execute(((giveModel)data.get(i).getObject()).getGiveImage());
        }
        return convertView;
    }

    private View getFoodView(int i,ViewHolder viewHolder,View convertView){
        //if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.foodmainlistitem,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.foodfragment_listview_name);
            viewHolder.way = (TextView) convertView.findViewById(R.id.foodfragment_listview_way);
            viewHolder.time = (TextView) convertView.findViewById(R.id.foodfragment_listview_time);
            viewHolder.info = (TextView) convertView.findViewById(R.id.foodfragment_listview_info);
            viewHolder.area = (TextView) convertView.findViewById(R.id.foodfragment_listview_arae);
            viewHolder.Lv = (TextView) convertView.findViewById(R.id.foodfragment_listview_lv);
            viewHolder.sex = (TextView) convertView.findViewById(R.id.foodfragment_listview_sex);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.foodfragment_listview_img);
            //viewHolder.details = (TextView) convertView.findViewById(R.id.foodfragment_listview_details);
//            convertView.setTag(viewHolder);
//        }else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

        new photoAsyncTask(viewHolder.imageView).execute(((foodModel)data.get(i).getObject()).getUserPhoto());
        viewHolder.name.setText(((foodModel)data.get(i).getObject()).getNickName()+"");
        viewHolder.area.setText(((foodModel)data.get(i).getObject()).getFoodArea());
        viewHolder.way.setText(((foodModel)data.get(i).getObject()).getFoodWay());
        if (((foodModel)data.get(i).getObject()).getFoodWay() == "你请客"){
            viewHolder.way.setBackgroundColor(0xffe51c23);
        }else if (((foodModel)data.get(i).getObject()).getFoodWay() == "我请客"){
            viewHolder.way.setBackgroundColor(0xff72d572);
        }

        String time = ((foodModel)data.get(i).getObject()).getFoodTime();
        String info = ((foodModel)data.get(i).getObject()).getFoodInformation();

        if (info.length()>20&&info!=null) {
            info = info.substring(0, 31);
            viewHolder.info.setText("        " + info + "...");
        }else
            viewHolder.info.setText("        " + info);
        viewHolder.time.setText(((foodModel)data.get(i).getObject()).getFoodTime());
        Log.i("sex",((foodModel)data.get(i).getObject()).getUserSex()+"");
        if(((foodModel)data.get(i).getObject()).getUserSex()==1){
            viewHolder.sex.setText("♂");
            viewHolder.sex.setBackgroundResource(R.drawable.foodlistview_sexboy);
        }else {
            viewHolder.sex.setText("♀");
            viewHolder.sex.setBackgroundResource(R.drawable.foodlistview_sexgirl);
        }
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                // bundle.putSerializable("data",data.get(i));
//                bundle.putInt("UserId",((foodModel)data.get(i).getObject()).getUserId());
//                bundle.putString("URL",((foodModel)data.get(i).getObject()).getUserPhoto());
//                Intent intent = new Intent(context,DetailsActivity.class);
//                intent.putExtras(bundle);
//                context.startActivity(intent);
            }
        });
        return convertView;
    }

    //缓存类
    private class ViewHolder{
        public CountDownTimer countDownTimer;
        public TextView money,name,way,info,sex,Lv,area;
        public TextView infor;
        public TextView time;
        public TextView title;
        public TextView username;
        public ImageView imageView;
        public Button button;
        public Bitmap bitmap;
    }

    class photoAsyncTask extends AsyncTask<String,Void,Bitmap> {

        private ImageView imageView;
        public photoAsyncTask(ImageView imageView) {
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
        }
    }
}
