package com.example.loveuApp.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.example.loveuApp.R;
import com.example.loveuApp.homepage.food.FoodMainFragment;
import com.example.loveuApp.homepage.give.GiveMainFragment;
import com.example.loveuApp.homepage.help.HelpMainFragment;
import com.example.loveuApp.homepage.pai.PaiMainFragment;
import com.example.loveuApp.homepage.run.RunMainFragment;
import com.example.loveuApp.homepage.xue.XueMainFragment;
import com.example.loveuApp.view.TopHorizontalScrollView;


public class HomePageFragment extends Fragment {


    private TextView textView1;
    private TextView textView2;
    private Fragment[] mFragments;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private int now;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepagemain, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    private void initView() {
        mFragments = new Fragment[2];
        fragmentManager = getActivity().getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.homepage_fragment1);
        mFragments[1] = fragmentManager.findFragmentById(R.id.homepage_fragment2);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(mFragments[1])
                .show(mFragments[0]).commit();
        now = 0;
        textView1 = (TextView) getActivity().findViewById(R.id.homefragment_topview1);
        textView2 = (TextView) getActivity().findViewById(R.id.homefragment_topview2);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (now == 0)
                    return;
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(mFragments[1]) .show(mFragments[0]).commit();
                now = 0;
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (now == 1)
                    return;
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(mFragments[0]).show(mFragments[1]).commit();
                now = 1;
            }
        });
    }


}
