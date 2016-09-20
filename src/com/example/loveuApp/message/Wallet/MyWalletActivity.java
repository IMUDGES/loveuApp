package com.example.loveuApp.message.Wallet;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

/**
 * Created by caolu on 2016/9/20.
 */
public class MyWalletActivity extends FragmentActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment[] mFragments;
    private int nowFragment = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
