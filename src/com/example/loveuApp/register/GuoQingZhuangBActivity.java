package com.example.loveuApp.register;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import com.example.loveuApp.R;

/**
 * Created by caolu on 2016/7/26.
 */
public class GuoQingZhuangBActivity extends FragmentActivity {

    private ViewPager mPager;
    private Fragment[] mFragments;
    private FragmentPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chaoluactivity);
        mPager = (ViewPager) findViewById(R.id.chaolupager);
        initView();
        mPager.setAdapter(mAdapter);
    }

    private void initView() {
        mFragments = new Fragment[2];
        mFragments[0] = new LoginFragment();
        mFragments[1] = new RegisterFragment();
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
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
}
