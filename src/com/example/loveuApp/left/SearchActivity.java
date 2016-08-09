package com.example.loveuApp.left;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.loveuApp.R;
import com.example.loveuApp.left.adapter.SearchListViewAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by caolu on 2016/8/8.
 */
public class SearchActivity extends Activity{

    private SearchListViewAdapter mAdpater;
    private String info = null;
    private PullToRefreshListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchmain);
        Bundle bundle = getIntent().getExtras();
        bundle.getString("info",info);
        mListView = (PullToRefreshListView) findViewById(R.id.leftmain_search_listview);
        Toast.makeText(getApplicationContext(),info,Toast.LENGTH_SHORT).show();
    }
}
