package com.example.loveuApp.updata;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.loveuApp.R;


public class UpdataFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_activty, container, false);
        return view;
    }

    ImageView mImageView;
    View mEyepetizerMenuView;
    FrameLayout frameLayout;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        frameLayout = (FrameLayout) getActivity().findViewById(R.id.view_layout);
        mImageView = (ImageView) getActivity().findViewById(R.id.iv_action_menu);


        mEyepetizerMenuView = LayoutInflater.from(getActivity()).inflate(R.layout.setting_view, null);
        frameLayout.addView(mEyepetizerMenuView);

        TextView tv1= (TextView) mEyepetizerMenuView.findViewById(R.id.upfood);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), UpFoodActivity.class));
            }
        });
        TextView tv2= (TextView) mEyepetizerMenuView.findViewById(R.id.upgave);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), UpFoodActivity.class));
            }
        });
        TextView tv3= (TextView) mEyepetizerMenuView.findViewById(R.id.uphelp);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), UpHelpActivity.class));
            }
        });
        TextView tv4= (TextView) mEyepetizerMenuView.findViewById(R.id.uppai);
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), UpPaiActivity.class));
            }
        });
        TextView tv5= (TextView) mEyepetizerMenuView.findViewById(R.id.uprun);
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), UpRunActivity.class));
            }
        });
        TextView tv6= (TextView) mEyepetizerMenuView.findViewById(R.id.upxue);
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), UpXueActivity.class));
            }
        });

        new EyepetizerMenuAnimation.EyepetizerMenuBuilder(getActivity(), mEyepetizerMenuView, mImageView).build();
    }


}
