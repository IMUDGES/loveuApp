package com.example.loveuApp.message;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.example.loveuApp.R;

/**
 * Created by caolu on 2016/8/10.
 */
public class YuWoXiangGuanActivity extends FragmentActivity {

    private Fragment [] mFragments;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yuwoxiangguan);
        initFragment();

    }

    private void initFragment() {
        mFragments = new Fragment[2];
    }
}
