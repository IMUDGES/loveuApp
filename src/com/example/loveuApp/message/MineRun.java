package com.example.loveuApp.message;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.loveuApp.R;

/**
 * Created by caolu on 2016/8/10.
 */
public class MineRun extends Activity{
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minefragment);
        mListView = (ListView) findViewById(R.id.minefragment_listvew);
    }
}
