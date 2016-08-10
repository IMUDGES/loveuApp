package com.example.loveuApp.message;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.loveuApp.R;
import com.example.loveuApp.User.NoBoringActionBarActivity;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.PhotoService;
import com.example.loveuApp.util.GetPhoto;
import com.example.loveuApp.util.HttpFileRequest;
import com.example.loveuApp.util.PhotoCut;

import java.io.File;


public class MessageFragment extends Fragment implements View.OnClickListener{

    private ImageView userImg;
    private LinearLayout myinfo;
    private LinearLayout mywallet;
    private LinearLayout relatedtome;
    private LinearLayout aboutour;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messagemain, container, false);

        userImg = (ImageView) view.findViewById(R.id.messagefragment_img);
        setimg();
        myinfo = (LinearLayout) view.findViewById(R.id.messagefragment_mymessage);
        mywallet = (LinearLayout) view.findViewById(R.id.messagefragment_mywallet);
        relatedtome = (LinearLayout) view.findViewById(R.id.messagefragment_relatedtome);
        aboutour = (LinearLayout) view.findViewById(R.id.messagefragment_aboutour);
        myinfo.setOnClickListener(this);
        mywallet.setOnClickListener(this);
        relatedtome.setOnClickListener(this);
        aboutour.setOnClickListener(this);
        return view;
    }

    private void setimg() {
        GetPhoto getPhoto = new GetPhoto(Environment.getExternalStorageDirectory().getPath(), "UserPhoto");
        Bitmap bitmap = getPhoto.getphoto();
        if (bitmap != null)
            userImg.setImageBitmap(bitmap);
        else
            userImg.setBackgroundResource(R.drawable.ic_launcher);
    }

    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 1;
    private String Path;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.messagefragment_mymessage:
                Intent intent = new Intent(getActivity(), NoBoringActionBarActivity.class);
                startActivity(intent);
                break;
            case R.id.messagefragment_mywallet:;
            case R.id.messagefragment_relatedtome:
                startActivity(new Intent(getActivity(),YuWoXiangGuanActivity.class));
            case R.id.messagefragment_aboutour:;
        }
    }
}
