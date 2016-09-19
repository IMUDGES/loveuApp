package com.example.loveuApp.message;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RadioGroup;
import com.example.loveuApp.R;

/**
 * Created by caolu on 2016/9/19.
 */
public class SelectMainActivity extends FragmentActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment[] mFragments;
    private RadioGroup group;
    private int nowFragment = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.selectclassactivity);
        mFragments = new Fragment[2];
        fragmentManager = getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.selectmain_fragment1);
        mFragments[1] = fragmentManager.findFragmentById(R.id.selectmain_fragment2);
        fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[1]);
        fragmentTransaction.show(mFragments[0]).commit();
        group = (RadioGroup) findViewById(R.id.selectmain_radiogroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.selectmain_bt1:
                        if (nowFragment == 1)
                            return;
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.hide(mFragments[1]);
                        fragmentTransaction.show(mFragments[0]).commit();
                        nowFragment = 1;
                        break;

                    case R.id.selectmain_bt2:
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
}
