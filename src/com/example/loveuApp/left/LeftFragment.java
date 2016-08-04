package com.example.loveuApp.left;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import com.example.loveuApp.R;

import java.io.File;


public class LeftFragment extends Fragment{
    private ViewPager mPager;
    private int mCount = 2;
    private PagerAdapter mPageAdapter;
    private ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.leftmain, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListView = (ListView) getActivity().findViewById(R.id.leftmain_listview);
        mPager = (ViewPager) getActivity().findViewById(R.id.leftmain_viewpager);
        initAdapter();
        mPager.setAdapter(mPageAdapter);
    }

    private void initAdapter() {
        mPageAdapter = new PagerAdapter() {

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ImageView)object).setImageBitmap(null);
                releaseImageViewResouce((ImageView)object);
                ((ViewPager) container).removeView((View) object); object =null;
            }

            @Override
            public int getCount() {
                return mCount;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                BitmapFactory.Options opts=new BitmapFactory.Options();
                opts.inJustDecodeBounds= false;
                Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_launcher, opts);
                ImageView view = null;
                view = new ImageView(getActivity());

                view.setImageBitmap(bitmap);
                container.addView(view);
                return view;
            }
        };
    }


    public void releaseImageViewResouce(ImageView imageView) {
        if (imageView == null) return;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap=null;
            }
        }
        System.gc();
    }

}
