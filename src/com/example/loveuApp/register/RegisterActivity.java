package com.example.loveuApp.register;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.example.loveuApp.R;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient.ConnectCallback;
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

        RongIM.init(this);

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
//        Intent intent = new Intent(RegisterActivity.this, MyActivity.class);
//        startActivity(intent);
//        finish();

        String token = "aucu9trbIaknjMMFkMuu2MNCoMQxJJdg5wMHBmtyvzGteQpc4setbaH/GagQ5dUXnbfhAKVJkVE2DweekQxytw==";
        RongIM.getInstance().startConversationList(RegisterActivity.this);

        RongIM.connect(token, new ConnectCallback() {

            @Override
            public void onError(RongIMClient.ErrorCode arg0) {
                Toast.makeText(RegisterActivity.this, "connect onError", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String arg0) {
                Toast.makeText(RegisterActivity.this, "connect onSuccess", Toast.LENGTH_SHORT).show();
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
