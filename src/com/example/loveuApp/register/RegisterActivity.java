package com.example.loveuApp.register;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.example.loveuApp.R;
import io.rong.imkit.RongIM;
import io.rong.imkit.RongIMClientWrapper;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient.ConnectCallback;
import io.rong.imlib.model.Conversation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1111 on 2016/7/28.
 */
public class RegisterActivity extends FragmentActivity
        implements LoginFragment.FLoginBtnClick, RegisterFragment.FRegisterClickListener,
        FindPasswordFragment.FFindClickListener, FindPasswordFragment2.FFind2ClickListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment[] mfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int currentapiVersion = Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.KITKAT) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        setContentView(R.layout.register_main);

        //RongIM.init(this);

        if (savedInstanceState == null) {
            init();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.register_frame, mfragment[0]).commit();
        }
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        mfragment = new Fragment[4];
        mfragment[0] = new LoginFragment();
        mfragment[1] = new RegisterFragment();
        mfragment[2] = new FindPasswordFragment();
        mfragment[3] = new FindPasswordFragment2();
    }

    //接口
    @Override
    public void onFLoginTrue() {

        SharedPreferences sh=getSharedPreferences("user",MODE_PRIVATE);
        String token = sh.getString("Token","");

        Map<String,Boolean>map=new HashMap<>();
        map.put(Conversation.ConversationType.PRIVATE.getName(), false);

        map.put(Conversation.ConversationType.GROUP.getName(), false);

        map.put(Conversation.ConversationType.DISCUSSION.getName(), false);

        map.put(Conversation.ConversationType.SYSTEM.getName(), false);

        RongIM.getInstance().startConversationList(RegisterActivity.this,map);

        Log.i("RongInformation", token);
        RongIMClient.connect(token, new ConnectCallback() {

            @Override
            public void onError(RongIMClient.ErrorCode arg0) {
                Toast.makeText(RegisterActivity.this, "connect onError", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String arg0) {
                Toast.makeText(RegisterActivity.this, "connect onSuccess", Toast.LENGTH_SHORT).show();finish();
            }

            @Override
            public void onTokenIncorrect() {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void onToRegisterClick() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(mfragment[0]).add(R.id.register_frame, mfragment[1]).addToBackStack("ToRe").commit();
    }

    @Override
    public void onToFindClick() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(mfragment[0]).add(R.id.register_frame, mfragment[2]).addToBackStack("ToFind").commit();
    }

    @Override
    public void onFRegisterClick() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(mfragment[1]).show(mfragment[0]).addToBackStack("ReBack").commit();
    }

    @Override
    public void onFFindClick() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(mfragment[2]).add(R.id.register_frame, mfragment[3]).addToBackStack("ToFind2").commit();
    }

    @Override
    public void onFFind2Click() {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(mfragment[2]).remove(mfragment[3]).show(mfragment[0]).addToBackStack("Find2Back").commit();
    }

}
