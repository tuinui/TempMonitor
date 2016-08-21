package com.appstertech.tempmonitor.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nuimamon on 30/7/2559.
 */
public class RetrofitManager {
    private static final String BASE_URL = "http://telecorp.co.th/tmmobile/";

    public static Retrofit build() {
        return build(BASE_URL);
    }

    public static Retrofit build(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
