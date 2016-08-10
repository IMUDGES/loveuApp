package com.example.loveuApp.message;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;
import com.example.loveuApp.R;

/**
 * Created by caolu on 2016/8/10.
 */
public class YuWoXiangGuanActivity extends FragmentActivity {

    private Fragment [] mFragments;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private TextView tv1,tv2;
    private int nowFragment = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yuwoxiangguan);
        initFragment();

    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();

        mFragments = new Fragment[2];
        mFragments[0] = fragmentManager.findFragmentById(R.id.yuwoxiangguan_fragment1);
        mFragments[1] = fragmentManager.findFragmentById(R.id.yuwoxiangguan_fragment2);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(mFragments[1])
                .show(mFragments[0]).commit();
        tv1 = (TextView) findViewById(R.id.messagefragment_yuwoxiangguan_send);
        tv2 = (TextView) findViewById(R.id.messagefragment_yuwoxiangguan_get);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nowFragment==0)
                    return;
                else
                    fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(mFragments[1])
                        .show(mFragments[0]).commit();
                nowFragment = 0;
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nowFragment==1)
                    return;
                else
                    fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(mFragments[0])
                        .show(mFragments[1]).commit();
                nowFragment = 1;
            }
        });
    }
}
