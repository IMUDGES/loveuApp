package com.example.loveuApp.right;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.example.loveuApp.R;

public class ConversationActivity extends FragmentActivity {

	private TextView textView,finish;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.conversation);
		textView= (TextView) findViewById(R.id.huiha_title);
		finish= (TextView) findViewById(R.id.huihua_finish);
		finish.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		textView.setText(getIntent().getData().getQueryParameters("title")+"");
	}


}
