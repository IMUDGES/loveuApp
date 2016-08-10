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
import com.example.loveuApp.bean.giveCommentModel;
import com.example.loveuApp.util.PhotoCut;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by yangy on 2016/8/9.
 */
public class GiveCommentAdapter extends BaseAdapter {
    private List<giveCommentModel> giveCommentModels;
    private Context context;

    public void setModels(List<giveCommentModel> models) {
        this.giveCommentModels = giveCommentModels;
    }

    public GiveCommentAdapter(Context context, List<giveCommentModel> giveCommentModels) {
        this.context = context;
        this.giveCommentModels = giveCommentModels;
    }
    @Override
    public int getCount() {
        return giveCommentModels.size();
    }

    @Override
    public Object getItem(int i) {
        return giveCommentModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.givemaincommentitems,null);
            viewHolder = new ViewHolder();
            viewHolder.infor = (TextView) convertView.findViewById(R.id.givecomment_info);
            viewHolder.username = (TextView) convertView.findViewById(R.id.givecomment_name);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.givecomment_img);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.username.setText(giveCommentModels.get(i).getNickName()+"");
        viewHolder.infor.setText("    "+giveCommentModels.get(i).getCommentInformation());
        if(viewHolder.bitmap!=null){
            viewHolder.imageView.setImageBitmap(PhotoCut.toRoundBitmap(viewHolder.bitmap));
        }else{
            new photoAsyncTask(viewHolder.imageView,viewHolder).execute(giveCommentModels.get(i).getUserPhoto());
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
