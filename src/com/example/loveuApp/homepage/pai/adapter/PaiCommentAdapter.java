package com.example.loveuApp.homepage.pai.adapter;

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
import com.example.loveuApp.bean.paiCommentModel;
import com.example.loveuApp.util.PhotoCut;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by dy on 2016/8/3.
 */
public class PaiCommentAdapter extends BaseAdapter {

    private List<paiCommentModel> models;
    private Context context;

    public void setModels(List<paiCommentModel> models) {
        this.models = models;
    }

    public PaiCommentAdapter(Context context, List<paiCommentModel> models) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.pai_comment_item_listview,null);
            viewHolder = new ViewHolder();
            viewHolder.infor = (TextView) convertView.findViewById(R.id.comment_item_infor);
            viewHolder.username = (TextView) convertView.findViewById(R.id.comment_item_username);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.comment_item_userPhoto);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.username.setText(models.get(i).getNickName()+"");
        viewHolder.infor.setText("    "+models.get(i).getCommentInformation());
        if(viewHolder.bitmap!=null){
            viewHolder.imageView.setImageBitmap(PhotoCut.toRoundBitmap(viewHolder.bitmap));
        }else{
            new photoAsyncTask(viewHolder.imageView,viewHolder).execute(models.get(i).getUserPhoto());
        }

        return convertView;
    }

    private class ViewHolder{
        public TextView infor;
        public TextView username;
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
