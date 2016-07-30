package com.example.loveuApp.homepage.food.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.foodModel;
import com.example.loveuApp.bean.userModel;

import com.example.loveuApp.util.Tag;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * Created by caolu on 2016/7/26.
 */
public class FoodMainListAdapter extends BaseAdapter implements AbsListView.OnScrollListener{
    private int mStart, mEnd;
    private boolean mFirstIn;
    private  int [] UserIds;
    public static String [] URLS;
    private ImageLoader mImageLoader;
    private List<foodModel> data;
    private Context mContext;
    private List<userModel> modelList;
    public FoodMainListAdapter(List<foodModel> data, Context mContext, PullToRefreshListView listView,String [] urls,List<userModel> modelList) {
        this.data = data;
        this.mContext = mContext;
        this.modelList = modelList;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.foodmainlistitem,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.foodfragment_listview_name);
            viewHolder.way = (TextView) convertView.findViewById(R.id.foodfragment_listview_way);
            viewHolder.time = (TextView) convertView.findViewById(R.id.foodfragment_listview_time);
            viewHolder.info = (TextView) convertView.findViewById(R.id.foodfragment_listview_info);
            viewHolder.arae = (TextView) convertView.findViewById(R.id.foodfragment_listview_arae);
            viewHolder.Lv = (TextView) convertView.findViewById(R.id.foodfragment_listview_lv);
            viewHolder.sex = (TextView) convertView.findViewById(R.id.foodfragment_listview_sex);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.foodfragment_listview_img);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        Log.i("userids",UserIds[i]+"");
        //viewHolder.imageView.setImageResource(R.drawable.ic_launcher);
        viewHolder.name.setText(modelList.get(i).getNickName()+"");
        viewHolder.arae.setText(data.get(i).getFoodArea());
        viewHolder.way.setText(data.get(i).getFoodWay());
        if (data.get(i).getFoodWay() == "你请客"){
            viewHolder.way.setBackgroundColor(0xffe51c23);
        }else if (data.get(i).getFoodWay() == "我请客"){
            viewHolder.way.setBackgroundColor(0xff72d572);
        }

        String time = data.get(i).getFoodTime();
        String info = data.get(i).getFoodInformation();
        if (info.length()>20)
            info = info.substring(0,31);
        viewHolder.info.setText("        "+info+"...");
        viewHolder.time.setText(data.get(i).getFoodTime());
        Log.i("sex",modelList.get(i).getUserSex()+"");
        if(modelList.get(i).getUserSex()==1){
            viewHolder.sex.setText("♂");
            viewHolder.sex.setBackgroundResource(R.drawable.foodlistview_sexboy);

        }else {
            viewHolder.sex.setText("♀");
            viewHolder.sex.setBackgroundResource(R.drawable.foodlistview_sexgirl);
        }
        // loadUrlsOneByOne(i);
        Tag tag= new Tag(URLS[i],i);
        viewHolder.imageView.setTag(URLS[i]+i);
        Log.i("waiTag",tag.murl+"  "+tag.mposition);
        mImageLoader.showImage(viewHolder.imageView,URLS[i]);


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
