package com.example.loveuApp.left;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.loveuApp.MyActivity;
import com.example.loveuApp.R;
import com.example.loveuApp.left.adapter.LeftListViewAdapter;

import java.io.File;
import java.util.ArrayList;


public class LeftFragment extends Fragment{

    //private static final String[] sex={"全部","男","女"};
    //private static final String[] way={"大一","大二","大三","大四"};
    //private Spinner setway;
   // private Spinner setsex;
    private ListView mListView;
   // private TextView sexText,wayText;
    private LeftListViewAdapter mAdapter;
    private Bitmap[] bitmaps= null;
    private int mCount = 2;
    private ImageView searchImage;
    private EditText searchinfo;
    private ArrayAdapter<String> sexadapter;
    //private int sexvalue = -1;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.leftmain, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchinfo  = (EditText) getActivity().findViewById(R.id.leftmain_searchinfo);
        //sexText = (TextView) getActivity().findViewById(R.id.leftmain_sextext);
        //wayText = (TextView) getActivity().findViewById(R.id.leftmain_waytext);
       // setsex = (Spinner) getActivity().findViewById(R.id.leftmain_sex);
       // setway = (Spinner) getActivity().findViewById(R.id.leftmain_way);
        searchImage = (ImageView) getActivity().findViewById(R.id.leftmain_search);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClick();
            }
        });
        //initSpinner();
        mListView = (ListView) getActivity().findViewById(R.id.leftmain_listview);
        /**
         * 先进行异步下载任务，之后调用以下方法
         */
        initAdapter();
        mListView.setAdapter(mAdapter);

    }

    /*private void initSpinner() {
        sexadapter = new ArrayAdapter<String>(getActivity(),R.layout.spinnertext,sex);
        //设置下拉列表的风格
        sexadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter 添加到spinner中
        setsex.setAdapter(sexadapter);

        //添加事件Spinner事件监听
        setsex.setOnItemSelectedListener(new SpinnerSelectedListener1());

        //设置默认值
        setsex.setVisibility(View.VISIBLE);
    }*/

    private void doClick() {

        String info = searchinfo.getText().toString();
        Log.i("info",info);
        if (info==null)
            return;
        Bundle bundle = new Bundle();
        bundle.putString("info",info);
        Intent intent = new Intent(getActivity(),SearchActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private void initAdapter() {
        bitmaps = new Bitmap[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        for (int i =0;i<mCount;i++) {
            bitmaps[i] = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        }
        mAdapter = new LeftListViewAdapter(getActivity(),mCount,bitmaps);
    }


    /*class SpinnerSelectedListener1 implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            if(arg2==0)
            {
                sexvalue=1;
            }
            else if (arg2 == 1){
                sexvalue = 0;
            }else
                sexvalue = -1;

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }*/

}
