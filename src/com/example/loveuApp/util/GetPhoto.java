package com.example.loveuApp.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by dy on 2016/3/14.
 */
public class GetPhoto {
    private String Path,name;

    public GetPhoto(String path, String name) {
        Path = path;
        this.name = name;
    }

    public Bitmap getphoto(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeFile(Path+"/"+name, options); //此时返回bm为空
        options.inJustDecodeBounds = false;
        //计算缩放比
        int be = (int)(options.outHeight / (float)200);
        if (be <= 0)
            be = 1;
        options.inSampleSize = be;
        //重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦
        bitmap=BitmapFactory.decodeFile(Path+"/"+name,options);
        return bitmap;
    }
}
