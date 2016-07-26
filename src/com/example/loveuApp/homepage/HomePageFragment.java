package com.example.loveuApp.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.homepage.food.FoolMainFragment;
import com.example.loveuApp.homepage.give.GiveMainFragment;
import com.example.loveuApp.homepage.help.HelpMainFragment;
import com.example.loveuApp.homepage.pai.PaiMainFragment;
import com.example.loveuApp.homepage.run.RunMainFragment;
import com.example.loveuApp.homepage.xue.XueMainFragment;
import com.example.loveuApp.view.TopHorizontalScrollView;


public class HomePageFragment extends Fragment{

    private ViewPager mPager;
    private Fragment [] mFragments;
    private FragmentPagerAdapter mAdapter;
    private TopHorizontalScrollView mHorizontalScrollView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepagemain, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        mPager.setAdapter(mAdapter);
        mHorizontalScrollView.setViewPager(mPager,0,getActivity());

    }

    private void initView() {
        mHorizontalScrollView = (TopHorizontalScrollView) getActivity().findViewById(R.id.homefragment_scrollview);
        textView1 = (TextView) getActivity().findViewById(R.id.homefragment_scorllViewTv1);
        textView2 = (TextView) getActivity().findViewById(R.id.homefragment_scorllViewTv2);
        textView3 = (TextView) getActivity().findViewById(R.id.homefragment_scorllViewTv3);
        textView4 = (TextView) getActivity().findViewById(R.id.homefragment_scorllViewTv4);
        textView5 = (TextView) getActivity().findViewById(R.id.homefragment_scorllViewTv5);
        textView6 = (TextView) getActivity().findViewById(R.id.homefragment_scorllViewTv6);
        textView1.setWidth(getScreemWidth() / 3);
        textView2.setWidth(getScreemWidth() / 3);
        textView3.setWidth(getScreemWidth() / 3);
        textView4.setWidth(getScreemWidth() / 3);
        textView5.setWidth(getScreemWidth() / 3);
        textView6.setWidth(getScreemWidth() / 3);
        mFragments = new Fragment[6];
        mFragments[0] = new FoolMainFragment();
        mFragments[1] = new GiveMainFragment();
        mFragments[2] = new HelpMainFragment();
        mFragments[3] = new PaiMainFragment();
        mFragments[4] = new RunMainFragment();
        mFragments[5] = new XueMainFragment();
        mPager = (ViewPager) getActivity().findViewById(R.id.homefragment_viewpager);
        mAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }
        };
    }

    private int getScreemWidth() {

        WindowManager wmManager = (WindowManager) getActivity()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wmManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
