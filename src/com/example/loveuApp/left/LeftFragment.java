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

    private ListView mListView;

    private LeftListViewAdapter mAdapter;
    private Bitmap[] bitmaps= null;
    private int mCount = 2;
    private ImageView searchImage;
    private EditText searchinfo;
    private ArrayAdapter<String> sexadapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.leftmain, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchinfo  = (EditText) getActivity().findViewById(R.id.leftmain_searchinfo);
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
    private void doClick() {

        String info = searchinfo.getText().toString()+"";
        Log.i("info",info);
        Intent intent = new Intent(getActivity(),SearchActivity.class);
        intent.putExtra("information_sou",info);
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

}
