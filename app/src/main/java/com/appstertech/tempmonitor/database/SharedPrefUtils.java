package com.appstertech.tempmonitor.database;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.appstertech.tempmonitor.service.model.UserGson;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by nuimamon on 30/7/2559.
 */
public class SharedPrefUtils {

    private static final String SHARED_PREF_NAME_USER_DATA = "SHARED_PREF_NAME_USER_DATA";
    private static final String SHARED_PREF_KEY_USER_GSON = "SHARED_PREF_KEY_USER_GSON";

    public static void saveUser(Context context, UserGson user) {
        Gson gson = new Gson();
        String userString = gson.toJson(user, UserGson.class);
        getUserSharedPref(context).edit().putString(SHARED_PREF_KEY_USER_GSON, userString).apply();
    }

    public static void clearUser(Context context){
        getUserSharedPref(context).edit().putString(SHARED_PREF_KEY_USER_GSON, null).apply();
    }

    public static SharedPreferences getUserSharedPref(Context context) {
        return context.getSharedPreferences(SHARED_PREF_NAME_USER_DATA, Context.MODE_PRIVATE);
    }

    public static UserGson getUser(Context context) {
        String userGsonString = getUserSharedPref(context).getString(SHARED_PREF_KEY_USER_GSON,"");
        if(TextUtils.isEmpty(userGsonString)){
           return null;
        }
        Gson userGson = new Gson();
        return userGson.fromJson(userGsonString,UserGson.class);
    }

    public static boolean isUserLogin(Context context){
        return getUser(context) != null;
    }
}
