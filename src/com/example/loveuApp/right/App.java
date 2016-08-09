package com.example.loveuApp.right;

import io.rong.imkit.RongIM;
import android.app.Application;

public class App extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		RongIM.init(this);
		
	}



}
