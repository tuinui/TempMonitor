package com.appstertech.tempmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.appstertech.tempmonitor.database.SharedPrefUtils;
import com.appstertech.tempmonitor.ui.home.HomeFragment;
import com.google.firebase.iid.FirebaseInstanceId;

public class HomeActivity extends BaseActivity {
    private Toolbar mToolbar;
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (!SharedPrefUtils.isUserLogin(this)) {
            finish();
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar_home);
        mToolbar.inflateMenu(R.menu.menu_home);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_logout) {
                    SharedPrefUtils.clearUser(HomeActivity.this);
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    finish();
                    startActivity(intent);
                    return true;
                }

                /*else if (item.getItemId() == R.id.action_log_firebase_token) {
                    Log.i(TAG, "Token : " + FirebaseInstanceId.getInstance().getToken());
                }*/
                return false;
            }
        });
        replaceFragment(HomeFragment.newInstance());
    }

}
