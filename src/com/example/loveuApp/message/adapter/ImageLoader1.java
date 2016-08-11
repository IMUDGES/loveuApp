package com.example.loveuApp.message.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import com.example.loveuApp.R;
import com.example.loveuApp.homepage.food.adapter.FoodMainListAdapter;
import com.example.loveuApp.util.PhotoCut;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by caolu on 2016/7/28.
 */
public class ImageLoader1 {

    private LruCache<String, Bitmap> mCache;
    private Set<MyAsyncTask> mTask;

    private ListView mListView;


    ImageLoader1(ListView listView) {
        mListView = listView;

        mTask = new HashSet<MyAsyncTask>();
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        mCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    public void addBitmapToCache(String url, Bitmap bitmap) {
        if (getBitmapFromCache(url) == null) {
            mCache.put(url, bitmap);
        }
    }

    public Bitmap getBitmapFromCache(String url) {
        if (url == null)
            return null;
        return mCache.get(url);
    }

    public void showImage(ImageView imageView, String url) {
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null) {
            imageView.setImageResource(R.drawable.ic_launcher);

        } else {
            imageView.setImageDrawable(null);
            imageView.setImageBitmap(PhotoCut.toRoundBitmap(bitmap));
        }

    }

    public Bitmap getBitmapFromURL(String urlString) {
//        Log.i("getbitmapfromUrl", urlString);
        Bitmap bitmap = null;
        InputStream is = null;
        if (urlString == null) {
            return null;
        }
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            // Thread.sleep(1000);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        ;
        if (bitmap == null)
            Log.i("bitmap", "no");
        else Log.i("bitmap", "yes");
        return bitmap;
    }

    public void loadImages(int start, int end) {
        for (int i = start; i < end; i++) {
            String url = FoodAdapter1.URLS[i];
            Bitmap bitmap = getBitmapFromCache(url);
            if (bitmap == null) {
                MyAsyncTask task = new MyAsyncTask(url,i);
                task.execute(url);
                mTask.add(task);
            } else {
                ImageView imageView = (ImageView) mListView.findViewWithTag(url+i);
               // imageView.setImageBitmap(bitmap);
            }

        }
    }


    private class MyAsyncTask extends AsyncTask<String, Void, Bitmap> {

        private String murl;
        private int mposition;

        //		private ImageView mImageView;
        public MyAsyncTask(String url,int position) {
//			mImageView = imageView;
            mposition = position;
            murl = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            // TODO Auto-generated method stub
            Log.i("doinbackground", "yes");
            String url = params[0];
            Bitmap bitmap = getBitmapFromURL(url);
            if (bitmap != null) {
                Log.i("bitmap", "yes");
                addBitmapToCache(url, bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            // TODO Auto-generated method stub
            super.onPostExecute(bitmap);
            Log.i("background ok", "ok");
            Log.i("mposition",mposition+"");
            ImageView imageView = (ImageView) mListView.findViewWithTag(murl+mposition);
            Log.i("url+i",murl+mposition);
            if (imageView==null)
            Log.i("imgview","null");
            if (imageView != null && bitmap != null) {
                Log.i("find", "ok");
                imageView.setImageBitmap(PhotoCut.toRoundBitmap(bitmap));
            }else Log.i("imageVIew","null");
            mTask.remove(this);
        }
    }


    public void cancelAllTasks() {
        // TODO Auto-generated method stub
        if (mTask != null) {
            for (MyAsyncTask task : mTask) {
                task.cancel(false);
            }
        }
    }
}
