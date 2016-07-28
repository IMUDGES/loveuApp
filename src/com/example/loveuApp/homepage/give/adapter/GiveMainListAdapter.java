package com.example.loveuApp.homepage.give.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.bean.giveModel;

import java.util.List;

/**
 * Created by caolu on 2016/7/26.
 */
public class GiveMainListAdapter extends BaseAdapter{
    public GiveMainListAdapter(List<giveModel> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    private List<giveModel> data;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.givemainlistitem,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.givefragment_listview_name);
            viewHolder.info = (TextView) convertView.findViewById(R.id.givefragment_listview_info);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.givefragment_listview_img);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(data.get(i).getGiveId());
        viewHolder.info.setText(data.get(i).getGiveInformation());


        return null;
    }

    public class ViewHolder{
        public TextView name;
        public TextView info;
        public ImageView imageView;
    }
}
