package com.example.loveuApp.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by dy on 2016/7/30.
 */
public class TextViewToDBC {

    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i< c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }if (c[i]> 65280&& c[i]< 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    public static int toSecond(String time){
        int seconds=0;

        int n1,n2,n3,n4,o1,o2,o3,o4;

        Calendar calendar = new GregorianCalendar();
        Calendar ca = Calendar.getInstance();
        String pattern = "EEE, dd MMM yyyy HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.US);
        //String dateStr = "Tue, 26 Feb 2013 09:26:57 GMT";
        try {
            Date trialTime = format.parse(time);
            calendar.setTime(trialTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        n1=calendar.get(Calendar.DATE);  o1=ca.get(Calendar.DATE);//获取日
        n2=calendar.get(Calendar.MINUTE);  o2=ca.get(Calendar.MINUTE);//分
        n3=calendar.get(Calendar.HOUR_OF_DAY);  o3=ca.get(Calendar.HOUR_OF_DAY);//小时
        n4=calendar.get(Calendar.SECOND);  o4=ca.get(Calendar.SECOND);//秒
        Log.i("n1",n1+"");
        Log.i("n2",n2+"");
        Log.i("n3",n3+"");
        Log.i("n4",n4+"");

        Log.i("o1",o1+"");
        Log.i("o2",o2+"");
        Log.i("o3",o3+"");
        Log.i("o4",o4+"");

        if((n4-o4)>0){
            seconds+=(n4-o4);
        }else{
            n2--;
            seconds+=(n4+60-o4);
        }
        Log.i("secondsiniiiiiii**************",seconds+"");
        if((n2-o2)>0){
            seconds+=((n2-o2)*60);
        }else{
            n3--;
            seconds+=((n2+60-o2)*60);
        }
        Log.i("secondsiniiiiiii**************",seconds+"");
        if((n3-o3)>0){
            seconds+=((n3-o3)*60*60);
        }else{
            n1--;
            seconds+=((n3+24-o3)*60*60);
        }
        Log.i("secondsiniiiiiii**************",seconds+"");
        if((n1-o1)>0){
            seconds+=((n1-o1)*60*60*24);
        }else{

        }
        Log.i("secondsiniiiiiii**************",seconds+"");
        return seconds;
    }

}
