package com.appstertech.tempmonitor.fcm;


import android.util.Log;

import com.appstertech.tempmonitor.database.SharedPrefUtils;
import com.appstertech.tempmonitor.service.RetrofitManager;
import com.appstertech.tempmonitor.service.TempMonitorService;
import com.appstertech.tempmonitor.service.model.UserGson;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nuimamon on 17/8/2559.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     * <p/>
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param deviceId The new token.
     */
    private void sendRegistrationToServer(String deviceId) {
        //TODO implement registeration device id to server
        UserGson user = SharedPrefUtils.getUser(this);
        if (null != user) {
            RetrofitManager.build().create(TempMonitorService.class)
                    .refreshDeviceId(user.getUserId(), deviceId)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
        }
    }
}
