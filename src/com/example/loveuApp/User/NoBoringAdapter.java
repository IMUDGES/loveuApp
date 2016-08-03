package com.example.loveuApp.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.register.RegisterActivity;

/**
 * Created by 1111 on 2016/8/3.
 */
public class NoBoringAdapter extends BaseAdapter {
    private final static int BLANK_ITEM = 0;
    private final static int NORMAL_ITEM = 1;
    private final static int BUTTON_ITEM = 2;

    private Context mContext;
    private LayoutInflater inflater;
    private String[] data;

    public NoBoringAdapter(Context context, String[] data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
        mContext = context;
    }

    //三种视图
    @Override
    public int getItemViewType(int position) {
        if ("btn".equals(data[position]))//string为 btn 时 设置为BUTTON
            return BUTTON_ITEM;
        else if ("".equals(data[position]))//string为空时，设置为分隔空间
            return BLANK_ITEM;
        else//正常文字
            return NORMAL_ITEM;
    }

    @Override
    public boolean isEnabled(int position) {
        if (getItemViewType(position) == NORMAL_ITEM)
            return true;
        else
            return false;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return data[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        ViewHolder3 holder3 = null;
        int type = getItemViewType(position);

        //无convertView，需要new出各个控件
        if (view == null) {
//            Log.e("view = ", " NULL");

            //按当前所需的样式，确定new的布局
            switch (type) {
                case BLANK_ITEM:
                    view = inflater.inflate(R.layout.noboringview1, viewGroup, false);
                    holder1 = new ViewHolder1();
                    holder1.textView = (TextView) view.findViewById(R.id.noboring_view1_text);
//                    Log.e("convertView = ", "NULL TYPE_1");
                    view.setTag(holder1);
                    break;
                case NORMAL_ITEM:
                    view = inflater.inflate(R.layout.noboringview2, viewGroup, false);
                    holder2 = new ViewHolder2();
                    holder2.textView = (TextView) view.findViewById(R.id.noboring_view2_text);
//                    Log.e("convertView = ", "NULL TYPE_2");
                    view.setTag(holder2);
                    break;
                case BUTTON_ITEM:
                    view = inflater.inflate(R.layout.noboringview3, viewGroup, false);
                    holder3 = new ViewHolder3();
                    holder3.button = (Button) view.findViewById(R.id.noboring_view3_btn);
//                    Log.e("convertView = ", "NULL TYPE_3");
                    view.setTag(holder3);
                    break;
            }
        } else {
            //有convertView，按样式，取得不用的布局
            switch (type) {
                case BLANK_ITEM:
                    holder1 = (ViewHolder1) view.getTag();
//                    Log.e("convertView !!!!!!= ", "NULL TYPE_1");
                    break;
                case NORMAL_ITEM:
                    holder2 = (ViewHolder2) view.getTag();
//                    Log.e("convertView !!!!!!= ", "NULL TYPE_2");
                    break;
                case BUTTON_ITEM:
                    holder3 = (ViewHolder3) view.getTag();
//                    Log.e("convertView !!!!!!= ", "NULL TYPE_3");
                    break;
            }
        }

        //设置资源
        switch (type) {
            case BLANK_ITEM:

                break;
            case NORMAL_ITEM:
                holder2.textView.setText(data[position]);
                break;
            case BUTTON_ITEM:
                holder3.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences preferences = mContext.getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("UserPhone","");
                        editor.putString("SecretKey","");
                        editor.commit();
                        Intent intent = new Intent();
                        intent.setClass(mContext,RegisterActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);//销毁其他的Activity。
                        mContext.startActivity(intent);
                    }
                });
                break;
        }

        return view;
    }

    class ViewHolder1 {
        TextView textView;
    }

    class ViewHolder2 {
        TextView textView;
    }

    class ViewHolder3 {
        Button button;
    }
}
