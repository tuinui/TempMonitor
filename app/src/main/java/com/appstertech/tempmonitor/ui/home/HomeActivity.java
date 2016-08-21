package com.appstertech.tempmonitor.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.appstertech.tempmonitor.BaseActivity;
import com.appstertech.tempmonitor.LoginActivity;
import com.appstertech.tempmonitor.R;
import com.appstertech.tempmonitor.database.SharedPrefUtils;

public class HomeActivity extends BaseActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(!SharedPrefUtils.isUserLogin(this)){
            finish();
        }
        mToolbar = (Toolbar) findViewById(R.id.toolbar_home);
        mToolbar.inflateMenu(R.menu.menu_home);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.action_logout){
                    SharedPrefUtils.clearUser(HomeActivity.this);
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    finish();
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        replaceFragment(HomeFragment.newInstance());
    }

}
