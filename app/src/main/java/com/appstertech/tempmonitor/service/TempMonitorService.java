package com.appstertech.tempmonitor.service;

import com.appstertech.tempmonitor.service.model.RefridgeGson;
import com.appstertech.tempmonitor.service.model.TempLogGson;
import com.appstertech.tempmonitor.service.model.UserGson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by nuimamon on 30/7/2559.
 */
public interface TempMonitorService {


    //http://telecorp.co.th/tmmobile/HLAuthenUserData.ashx?Username=puthinan&Password=P@ssw0rd1168
    //http://telecorp.co.th/tmmobile/HLAuthenUserData.ashx?Username=Puthinan&Password=1168&DeviceID=1231231214324sdsd&Platform=IOS
    String DEFAULT_PLATFORM = "Android";

    @GET("HLAuthenUserData.ashx?Platform=Android")
    Call<UserGson> login(@Query("Username") String username, @Query("Password") String password, @Query("DeviceID") String deviceId);

//    http://telecorp.co.th/tmmobile/HLGetRefrigerator.ashx?MSOrganisationUID=1

    @GET("HLGetRefrigerator.ashx")
    Call<List<RefridgeGson>> getRefrigerator(@Query("MSOrganisationUID") String userId);

    //http://telecorp.co.th/tmmobile/HLGetRefrigeratorByRefrigCode.ashx?RefrigeratorCode=0001&&MSOrganisationUID=1
    @GET("HLGetRefrigeratorByRefrigCode.ashx")
    Call<List<RefridgeGson>> getRefrigeratorById(@Query("RegrigeratorCode") String code, @Query("MSOrganisationUID") String userId);

    //    http://telecorp.co.th/tmmobile/HLTranctionTemplogData.ashx?RefrigeratorCode=0001
    @GET("HLTranctionTemplogData.ashx")
    Call<List<TempLogGson>> getTempLogById(@Query("RefrigeratorCode") String code);

}
