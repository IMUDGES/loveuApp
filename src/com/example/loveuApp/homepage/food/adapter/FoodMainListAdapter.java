package com.example.loveuApp.homepage.food.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.foodModel;

import java.util.List;

/**
 * Created by caolu on 2016/7/26.
 */
public class FoodMainListAdapter extends BaseAdapter{
    public FoodMainListAdapter(List<foodModel> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    private List<foodModel> data;
    private Context mContext;

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
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.foodmainlistitem,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.foodfragment_listview_name);
            viewHolder.way = (TextView) convertView.findViewById(R.id.foodfragment_listview_way);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.foodfragment_listview_img);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(data.get(i).getFoodId());
        viewHolder.way.setText(data.get(i).getFoodWay());


        return null;
    }

    public class ViewHolder{
        public TextView name;
        public TextView way;
        public ImageView imageView;
    }
}
