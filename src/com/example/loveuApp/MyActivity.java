package com.example.loveuApp;

import android.os.Bundle;
import android.support.v4.app.*;
import android.widget.Toast;
import com.example.loveuApp.view.TopLinearlayout;

public class MyActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */


    private TopLinearlayout mTopLinearlayout;
    private Fragment [] mFragments;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private FragmentPagerAdapter mAdapter;

    public MyActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toast.makeText(MyActivity.this, "", Toast.LENGTH_SHORT).show();
        init();
        mTopLinearlayout.setFragmentEvent(fragmentManager, fragmentTransaction, mFragments);
    }

    private void init() {
        mTopLinearlayout = (TopLinearlayout) findViewById(R.id.id_layout);
        fragmentManager = getSupportFragmentManager();
        mFragments = new Fragment[5];

        mFragments[0] = fragmentManager.findFragmentById(R.id.main_fragment1);
        mFragments[1] = fragmentManager.findFragmentById(R.id.main_fragment2);
        mFragments[2] = fragmentManager.findFragmentById(R.id.main_fragment3);
        mFragments[3] = fragmentManager.findFragmentById(R.id.main_fragment4);
        mFragments[4] = fragmentManager.findFragmentById(R.id.main_fragment5);
        fragmentTransaction = fragmentManager.beginTransaction()
                .hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]).hide(mFragments[4]);
        fragmentTransaction.show(mFragments[0]).commit();
    }
}
