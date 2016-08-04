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
import android.widget.Toast;
import com.example.loveuApp.R;
import com.example.loveuApp.User.NoBoringActionBarActivity;
import com.example.loveuApp.listener.Listener;
import com.example.loveuApp.service.PhotoService;
import com.example.loveuApp.util.HttpFileRequest;
import com.example.loveuApp.util.PhotoCut;

import java.io.File;


public class MessageFragment extends Fragment{

    private Button btn;
    private ImageView userImg;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messagemain, container, false);

        btn = (Button) view.findViewById(R.id.to_user);

        return view;
    }

    private final String IMAGE_TYPE="image/*";
    private final int IMAGE_CODE=1;
    private String Path;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userImg = (ImageView) getActivity().findViewById(R.id.messagefragment_img);
        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(IMAGE_TYPE);
                intent.putExtra("crop", "true");    // crop=true 有这句才能出来最后的裁剪页面.
                intent.putExtra("aspectX", 1);      // 这两项为裁剪框的比例.
                intent.putExtra("aspectY", 1);
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 200);
                //输出地址
                intent.putExtra("output", Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath()+"/loveu.jpg")));
                intent.putExtra("outputFormat", "JPEG");//返回格式
                startActivityForResult(Intent.createChooser(intent, "选择图片"), IMAGE_CODE);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),NoBoringActionBarActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != getActivity().RESULT_OK) {
            Log.i("photopath","fail");
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_CODE) {
            Log.i("photopath","success");
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath()+"/loveu.jpg", options);
                    Path=Environment.getExternalStorageDirectory().getPath()+"/loveu.jpg";

                PhotoCut bitmapUtil = new PhotoCut();
                Bitmap myBitmap = bitmapUtil.toRoundBitmap(bitmap);
                if (bitmap == null)
                    Log.i("bitmap","null");
                userImg.setImageBitmap(myBitmap);
                File file = new File(Path);

                String UserPhone = "22222222222";
                String SecretKey = "11111";

                Log.i("service","begin");
                PhotoService.FileUpload(getActivity(), "userphoto", file,UserPhone,SecretKey , new Listener() {
                    @Override
                    public void onSuccess(Object object) {
                        Toast.makeText(getActivity(),"上传成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String msg) {
                        Toast.makeText(getActivity(),"上传失败",Toast.LENGTH_SHORT).show();
                    }
                });

            }catch (Exception e){
                e.getLocalizedMessage();
            }
        }
    }

}
