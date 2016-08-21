package com.appstertech.tempmonitor.fcm;


import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by nuimamon on 17/8/2559.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "Message";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Toast.makeText(this,remoteMessage.toString(),Toast.LENGTH_LONG).show();
    }
}
