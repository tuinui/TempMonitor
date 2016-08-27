package com.appstertech.tempmonitor.fcm;

import com.google.gson.annotations.SerializedName;

/**
 * Created by softbaked on 8/27/16 AD.
 */
public class MyRemoteMessageData {

    @SerializedName("type")
    private String type;
    @SerializedName("message")
    private String message;

    public MyRemoteMessageData() {
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

}
