package com.example.loveuApp.register;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;
import com.example.loveuApp.R;

/**
 * Created by caolu on 2016/7/26.
 */
public class GuoQingZhuangBActivity extends FragmentActivity {

    private String mode;
    private ViewPager mPager;
    private Fragment[] mFragments;
    private FragmentPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chaoluactivity);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        mPager = (ViewPager) findViewById(R.id.chaolupager);
        initView();
        mPager.setAdapter(mAdapter);
        checkWhere();
    }

    private void checkWhere() {
        Bundle bundle = getIntent().getExtras();
        mode = bundle.getString("MODE","");
        if (mode == "startLogin"){
            mPager.setCurrentItem(0);
        }
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
