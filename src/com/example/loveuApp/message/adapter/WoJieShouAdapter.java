package com.example.loveuApp.message.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.loveuApp.R;

/**
 * Created by caolu on 2016/8/10.
 */
public class WoJieShouAdapter extends BaseAdapter{

    private Context mContext;

    public WoJieShouAdapter(Context context) {
        mContext = context;
    }

    private int itemNum = 6;


    @Override
    public int getCount() {
        return itemNum;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.mineitem, null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.mine_text);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }if (i==0)
            viewHolder.mTextView.setText("请客");
        else if (i==1)
            viewHolder.mTextView.setText("赠送");
        else if (i==2)
            viewHolder.mTextView.setText("帮助");
        else if (i==3)
            viewHolder.mTextView.setText("拍卖");
        else if (i==4)
            viewHolder.mTextView.setText("跑步");
        else if (i==5)
            viewHolder.mTextView.setText("学习");
        return convertView;
    }
    public class ViewHolder {
        public TextView mTextView;
    }
}
