package com.appstertech.tempmonitor.service.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nuimamon on 14/8/2559.
 */
public class TempLogGson {
/**
 * "UID": 42,
 "Code": "0001",
 "datetime": "2016-04-26 15:34:12",
 "ftempin": 3.9000000953674316,
 "ftempout": 33.799999237060547,
 "fhumi": 66.199996948242188
 */
    @SerializedName("UID")
    private int uid;
    @SerializedName("Code")
    private String code;
    @SerializedName("ftempin")
    private String tempIn;
    @SerializedName("ftempout")
    private String tempOut;
    @SerializedName("fhumi")
    private String humid;
    @SerializedName("datetime")
    private String dateTime;//2016-04-26 15:34:12"

    public TempLogGson() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTempIn() {
        return tempIn;
    }

    public void setTempIn(String tempIn) {
        this.tempIn = tempIn;
    }

    public String getTempOut() {
        return tempOut;
    }

    public void setTempOut(String tempOut) {
        this.tempOut = tempOut;
    }

    public String getHumid() {
        return humid;
    }

    public void setHumid(String humid) {
        this.humid = humid;
    }

    @Override
    public String toString() {
        return "TempLogGson{" +
                "uid=" + uid +
                ", code='" + code + '\'' +
                ", tempIn='" + tempIn + '\'' +
                ", tempOut='" + tempOut + '\'' +
                ", humid='" + humid + '\'' +
                '}';
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getDisplayDateTime(){
        //from service is 2016-04-26 15:34:12" yyyy-MM-dd HH:mm:ss
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
        Date newDate = null;
        try {
            newDate = format.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateTime;
        }

        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
        return format.format(newDate);
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
