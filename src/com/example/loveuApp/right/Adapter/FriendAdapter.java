package com.example.loveuApp.right.Adapter;

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
import com.example.loveuApp.bean.userModel;
import com.example.loveuApp.util.PhotoCut;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by dy on 2016/8/11.
 */
public class FriendAdapter extends BaseAdapter {

    private Context context;
    private List<userModel> userModels;

    public FriendAdapter(Context context,List<userModel> userModels){
        this.context=context;
        this.userModels=userModels;
    }


    @Override
    public int getCount() {
        return userModels.size();
    }

    @Override
    public Object getItem(int i) {
        return userModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.friend_list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.username = (TextView) convertView.findViewById(R.id.friend_item_username);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.friend_item_image);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.username.setText(userModels.get(i).getNickName());
        new photoAsyncTask(viewHolder).execute(userModels.get(i).getUserPhoto());
        return convertView;
    }

    public class ViewHolder{
        public TextView username;
        public ImageView imageView;
    }

    class photoAsyncTask extends AsyncTask<String,Void,Bitmap> {

        private ViewHolder v;
        public photoAsyncTask( ViewHolder viewHolder) {
            v=viewHolder;
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
            v.imageView.setImageBitmap(PhotoCut.toRoundBitmap(bitmap));
        }
    }
}
