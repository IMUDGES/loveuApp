package com.example.loveuApp.util;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by dy on 2016/3/14.
 */
public class SavePhoto {

    private Bitmap bitmap;
    private String Path;
    private String PhotoName;

    public SavePhoto(Bitmap bitmap, String path, String photoName) {
        this.bitmap = bitmap;
        Path = path;
        PhotoName = photoName;
    }

    public void Savephoto(){
        File file=new File(Path,PhotoName);
        try {
            FileOutputStream out=new FileOutputStream(file);
            if(bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)){
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
