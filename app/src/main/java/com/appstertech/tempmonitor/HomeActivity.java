package com.appstertech.tempmonitor;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.appstertech.tempmonitor.database.SharedPrefUtils;
import com.appstertech.tempmonitor.service.RetrofitManager;
import com.appstertech.tempmonitor.service.TempMonitorService;
import com.appstertech.tempmonitor.ui.home.HomeFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                    requestLogout(HomeActivity.this);
                    return true;
                }
                return false;
            }
        });
        replaceFragment(HomeFragment.newInstance());
    }

    private void requestLogout(final Context context) {
        if (null == SharedPrefUtils.getUser(context)) {
            return;
        }

        final ProgressDialog dialog = ProgressDialog.show(context, "", "Loading", true, true);
        RetrofitManager.build().create(TempMonitorService.class)
                .logout(SharedPrefUtils.getUser(context).getUsername())
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        dialog.dismiss();
                        if (response.isSuccessful()) {
                            SharedPrefUtils.clearUser(HomeActivity.this);
                            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                            finish();
                            startActivity(intent);
                        } else {
                            Toast.makeText(context, "not Successful", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(context, "Failure", Toast.LENGTH_LONG).show();
                    }
                });
    }


}
