package com.example.loveuApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by dy on 2016/8/9.
 */
public class MainActivity extends Activity {

    private String token = "aucu9trbIaknjMMFkMuu2MNCoMQxJJdg5wMHBmtyvzGteQpc4setbaH/GagQ5dUXnbfhAKVJkVE2DweekQxytw==";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_main_activity);

        RongIM.init(this);

        this.findViewById(android.R.id.text1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button", Toast.LENGTH_SHORT).show();

                RongIM.connect(token, new RongIMClient.ConnectCallback() {

                    @Override
                    public void onError(RongIMClient.ErrorCode arg0) {
                        Toast.makeText(MainActivity.this, "connect onError", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String arg0) {
                        Toast.makeText(MainActivity.this, "connect onSuccess", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onTokenIncorrect() {
                        // TODO Auto-generated method stub
                    }
                });

            }
        });

        this.findViewById(android.R.id.button1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RongIM.getInstance().startConversationList(MainActivity.this);
            }
        });


        this.findViewById(android.R.id.button2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RongIM.getInstance().startConversation(MainActivity.this, Conversation.ConversationType.PRIVATE, "2", "21212");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
