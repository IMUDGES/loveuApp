package com.example.loveuApp.left.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.loveuApp.R;

import java.util.ArrayList;

/**
 * Created by caolu on 2016/8/4.
 */
public class LeftListViewAdapter extends BaseAdapter {
    private int viewPagerItemCount;

    private static final int VIEWPAGER_TYPE = 0;
    private static final int NOLMAL_TYPE = 1;
    private static final  int TYPE_COUNT = 2;

    private ViewPager mPager ;
    private MyPageAdapter myPageAdapter;
    private View mTopView;
    private Context mContext;
    private ArrayList<ImageView> mImages;
    private Bitmap[] bitmaps;

    public LeftListViewAdapter(Context context,int count,Bitmap[] bitmaps){
        this.mContext = context;
        this.viewPagerItemCount = count;
        this.bitmaps = new Bitmap[count];
        for (int i =0;i<count;i++){
            this.bitmaps[i] = bitmaps[i];
        }
        myPageAdapter = new MyPageAdapter();
        mImages = new ArrayList<>();
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        if (i == 0)
            return 0;
        else
            return i - 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i == 0){
            return getTopView();
        }else {
            View mView = LayoutInflater.from(mContext).inflate(R.layout.leftlistitem2, null);
            return mView;
        }

    }



    @Override
    public int getItemViewType(int position) {

        if (position == 0)
            return VIEWPAGER_TYPE;
        else
            return NOLMAL_TYPE;

    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    private View getTopView() {
        if (mTopView == null) {
            mTopView = LayoutInflater.from(mContext).inflate(
                    R.layout.leftlistitem1, null);
        }
        mPager = (ViewPager) mTopView.findViewById(R.id.leftmain_viewpager);
        for (int i =0;i<viewPagerItemCount;i++){
            ImageView img = new ImageView(mContext);
            img.setImageBitmap(bitmaps[i]);
            mImages.add(img);
        }
        mPager.setAdapter(myPageAdapter);
        mPager.setCurrentItem(0);
        return mTopView;
    }

    public class MyPageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return viewPagerItemCount;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {

            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(mImages.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImages.get(position));
            return mImages.get(position);
        }
    }

}
