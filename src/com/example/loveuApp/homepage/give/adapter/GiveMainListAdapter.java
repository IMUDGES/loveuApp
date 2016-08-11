package com.example.loveuApp.homepage.give.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.giveModel;
import com.example.loveuApp.util.PhotoCut;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


public class GiveMainListAdapter extends BaseAdapter{
    private List<giveModel> giveModels;
    private Context mContext;
    public GiveMainListAdapter( Context mContext,List<giveModel> giveModels) {
        Log.i("GiveMainListAdapter",giveModels.get(1).getNickName());
        this.mContext = mContext;
        this.giveModels=giveModels;
    }

    public List<giveModel> getGiveModels() {
        return giveModels;
    }

    public void setGiveModels(List<giveModel> giveModels) {
        this.giveModels = giveModels;
    }

    @Override
    public int getCount() {
        return giveModels.size();
    }

    @Override
    public Object getItem(int i) {
        return giveModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.givemainlistview,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.givemainitem_name);
            viewHolder.info = (TextView) convertView.findViewById(R.id.givemainitem_info);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.givemainitem_img);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(giveModels.get(i).getNickName());
        viewHolder.info.setText("主人留言：   "+giveModels.get(i).getGiveInformation());
        if(viewHolder.bitmap!=null){
            viewHolder.imageView.setImageBitmap(PhotoCut.toRoundBitmap(viewHolder.bitmap));
        }else{
            new photoAsyncTask(viewHolder.imageView,viewHolder).execute(giveModels.get(i).getGiveImage());
        }
        return convertView;
    }

    public class ViewHolder{
        public TextView name;
        public TextView info;
        public ImageView imageView;
        public Bitmap bitmap;
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
            Log.i("givemodel_userphoturl",url);
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
