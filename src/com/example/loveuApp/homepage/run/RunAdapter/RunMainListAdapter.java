package com.example.loveuApp.homepage.run.RunAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.runModel;
import com.example.loveuApp.homepage.DetailsActivity;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;


public class RunMainListAdapter extends BaseAdapter implements AbsListView.OnScrollListener{
    private int mStart, mEnd;
    public boolean mFirstIn;
    public static String [] URLS;
    private ImageLoader mImageLoader;
    public List<runModel> data;
    private Context mContext;
    public RunMainListAdapter(List<runModel> data, Context mContext, PullToRefreshListView listView, String [] urls) {
        this.data = data;
        this.mContext = mContext;
        if (data != null){
            Log.i("data","!null");
        }
        URLS = new String[urls.length];
        for (int i =0;i<urls.length;i++){
            URLS[i]=urls[i];
            Log.i("adapter url",URLS[i]+"");
        }
        mImageLoader = new ImageLoader(listView);
        mFirstIn = true;
        listView.setOnScrollListener(this);
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
        Log.i("num",i+"");
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.runmainlistitem,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.runfragment_listview_name);
            //viewHolder.way = (TextView) convertView.findViewById(R.id.runfragment_listview_way);
            viewHolder.time = (TextView) convertView.findViewById(R.id.runfragment_listview_time);
            viewHolder.info = (TextView) convertView.findViewById(R.id.runfragment_listview_info);
            viewHolder.arae = (TextView) convertView.findViewById(R.id.runfragment_listview_arae);
            viewHolder.Lv = (TextView) convertView.findViewById(R.id.runfragment_listview_lv);
            viewHolder.sex = (TextView) convertView.findViewById(R.id.runfragment_listview_sex);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.runfragment_listview_img);
            //viewHolder.details = (TextView) convertView.findViewById(R.id.runfragment_listview_details);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(data.get(i).getNickName()+"");
        viewHolder.arae.setText(data.get(i).getRunArea());
        String time = data.get(i).getRunTime();
        String info = data.get(i).getRunInformation();

        if (info.length()>20&&info!=null) {
            info = info.substring(0, 31);
            viewHolder.info.setText("        " + info + "...");
        }else
        viewHolder.info.setText("        " + info);
        viewHolder.time.setText(data.get(i).getRunTime());
        Log.i("sex",data.get(i).getUserSex()+"");
        if(data.get(i).getUserSex()==1){
            viewHolder.sex.setText("♂");
            viewHolder.sex.setBackgroundResource(R.drawable.foodlistview_sexboy);

        }else {
            viewHolder.sex.setText("♀");
            viewHolder.sex.setBackgroundResource(R.drawable.foodlistview_sexgirl);
        }

      viewHolder.imageView.setTag(URLS[i]+i);
       /* viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",data.get(i));
                Intent intent = new Intent(mContext,RunDetailsActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });*/
        mImageLoader.showImage(viewHolder.imageView,URLS[i]);

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("UserId",data.get(i).getUserId());
                bundle.putString("URL",data.get(i).getUserPhoto());
                Intent intent = new Intent(mContext,DetailsActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView arg0, int scrollState) {
        // TODO Auto-generated method stub

        if (scrollState == SCROLL_STATE_IDLE) {
            Log.i("下载图片","开始");
            mImageLoader.loadImages(mStart, mEnd);
        } else {
            mImageLoader.cancelAllTasks();
        }
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int arg3) {
        // TODO Auto-generated method stub
        Log.i("first",firstVisibleItem+"");
        Log.i("end",firstVisibleItem+visibleItemCount+"");
        mStart = firstVisibleItem;
        mEnd = Math.min(firstVisibleItem + visibleItemCount,data.size());

        if (mFirstIn && visibleItemCount > 0) {
            Log.i("下载图片","开始");
            mImageLoader.loadImages(mStart, mEnd);
            mFirstIn = false;
        }
    }
    public class ViewHolder{
        public TextView details;
        public TextView sex;
        public TextView Lv;
        public TextView arae;
        public TextView name;
        public TextView way;
        public TextView time;
        public TextView info;
        public ImageView imageView;
    }


}
