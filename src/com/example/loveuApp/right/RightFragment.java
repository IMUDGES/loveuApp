package com.example.loveuApp.right;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import com.example.loveuApp.R;


public class RightFragment extends Fragment {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment[] mFragments;
    private RadioGroup group;
    private int nowFragment = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rightmain, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFragments = new Fragment[2];
        fragmentManager = getActivity().getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.right_fragment1);
        mFragments[1] = fragmentManager.findFragmentById(R.id.right_fragment2);
        fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[1]);
        fragmentTransaction.show(mFragments[0]).commit();
        group = (RadioGroup) getActivity().findViewById(R.id.rightradiobaba);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rightradio1:
                        if (nowFragment == 1)
                            return;
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.hide(mFragments[1]);
                        fragmentTransaction.show(mFragments[0]).commit();
                        nowFragment = 1;
                        break;

                    case R.id.rightradio2:
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
