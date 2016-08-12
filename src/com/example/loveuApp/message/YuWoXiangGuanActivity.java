package com.example.loveuApp.message;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.loveuApp.R;

/**
 * Created by caolu on 2016/8/10.
 */
public class YuWoXiangGuanActivity extends FragmentActivity {

    private Fragment [] mFragments;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private int nowFragment = 0;
    private RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.yuwoxiangguan);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initFragment();
        group = (RadioGroup) findViewById(R.id.yuworadiobaba);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.yuworadio1:
                        if (nowFragment==0)
                            return;
                        else
                            fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.hide(mFragments[1])
                                .show(mFragments[0]).commit();
                        nowFragment = 0;
                        break;

                    case R.id.yuworadio2:
                        if (nowFragment == 2)
                            return;
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.hide(mFragments[0]);
                        fragmentTransaction.show(mFragments[1]).commit();
                        nowFragment = 2;
                        break;

                    default:
                        break;
                }
            }
        });

    }

    private void initFragment() {
        fragmentManager = getSupportFragmentManager();

        mFragments = new Fragment[2];
        mFragments[0] = fragmentManager.findFragmentById(R.id.yuwoxiangguan_fragment1);
        mFragments[1] = fragmentManager.findFragmentById(R.id.yuwoxiangguan_fragment2);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(mFragments[1])
                .show(mFragments[0]).commit();

    }
}
