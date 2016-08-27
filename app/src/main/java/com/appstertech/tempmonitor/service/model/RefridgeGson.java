package com.appstertech.tempmonitor.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nuimamon on 31/7/2559.
 */
public class RefridgeGson implements Parcelable {
    //    "Code": "0001",
//            "RefrigeratorName": "RFM-CHM-002",
//            "RefrigeratorDescription": "ใช้ในการดเก็บน้ำยาเครื่อง Automade",
//            "Contact": "Chanyuth Pongkun",
//            "TypeUID": 1,
//            "LocationUID": 1,
//            "LocationName": "Floor 9",
//            "MSOrganisationUID": 1,
//            "TypeName": "2 to 8 celsius",
//            "Maxrang": "8   ",
//            "MaxrangOut": "100",
//            "Minrang": "2",
//            "MinrangOut": "-100",
//            "Image": "U3lzdGVtLkJ5dGVbXQ==",
//            "ImageString": "http://telecorp.co.th/tmmobile/HLGetimagebyRefrigerator.ashx?RefrigeratorCode=0001&&MSOrganisationUID=1",
//            "RefrigeratorModel": "SDC-1500AY",
//            "RefrigeratorBand": "Sanden Intercool",
//            "RefrigeratorSN": "SDC1500103A-0511-00234",
//            "RefrigeratorIDCode": "RFM-CHM-002",
//            "RefrigeratorHNNo": "N/A",
//            "RefrigeratorTempSetpoint": "4",
//            "RefrigeratorAccuracy": "6",
//            "RefrigeratorUseRangefrom": "2",
//            "RefrigeratorUseRangeTo": "8",
//            "RefrigeratorOpenDoorTime": "4",
//            "RefrigeratorPMCALInterval": "5",
//            "tempin": "9.10",
//            "tempout": "30.80",
//            "humi": "52.00",
//            "StatusOpenDoors": "Close",
//            "StatusRefrig": "",
//            "LinkWeb": "http://www.telecorp.co.th/HM/login/Loadlogin"
    @SerializedName("Code")
    private String code;
    @SerializedName("RefrigeratorName")
    private String refrigeratorName;
    @SerializedName("RefrigeratorDescription")
    private String refrigeratorDescription;
    @SerializedName("Contact")
    private String contact;
    @SerializedName("TypeUID")
    private String typeUID;
    @SerializedName("LocationUID")
    private String locationUID;
    @SerializedName("LocationName")
    private String locationName;
    @SerializedName("MSOrganisationUID")
    private String msOrganisationUID;
    @SerializedName("TypeName")
    private String typeName;
    @SerializedName("Maxrang")
    private String maxRang;
    @SerializedName("MaxrangOut")
    private String maxRangOut;
    @SerializedName("Minrang")
    private String minRang;
    @SerializedName("MinrangOut")
    private String minRangOut;
    @SerializedName("Image")
    private String image;
    @SerializedName("ImageString")
    private String imageString;
    @SerializedName("RefrigeratorModel")
    private String refrigeratorModel;
    @SerializedName("RefrigeratorBand")
    private String refrigeratorBand;
    @SerializedName("RefrigeratorSN")
    private String refrigeratorSN;
    @SerializedName("RefrigeratorIDCode")
    private String refrigeratorIDCode;
    @SerializedName("RefrigeratorHNNo")
    private String refrigeratorHNNo;
    @SerializedName("RefrigeratorTempSetpoint")
    private String refrigeratorTempSetpoint;
    @SerializedName("RefrigeratorAccuracy")
    private String refrigeratorAccuracy;
    @SerializedName("RefrigeratorUseRangefrom")
    private String refrigeratorUseRangefrom;
    @SerializedName("RefrigeratorUseRangeTo")
    private String refrigeratorUseRangeTo;
    @SerializedName("RefrigeratorOpenDoorTime")
    private String refrigeratorOpenDoorTime;
    @SerializedName("RefrigeratorPMCALInterval")
    private String refrigeratorPMCALInterval;
    //            "tempin": "9.10",
//            "tempout": "30.80",
//            "humi": "52.00",
//            "StatusOpenDoors": "Close",
//            "StatusRefrig": "",
//            "LinkWeb": "http://www.telecorp.co.th/HM/login/Loadlogin"
    @SerializedName("tempin")
    private String tempIn;
    @SerializedName("tempout")
    private String tempOut;
    @SerializedName("humi")
    private String humid;
    @SerializedName("StatusOpenDoors")
    private String statusOpenDoor;
    @SerializedName("StatusRefrig")
    private String statusRefrigde;
    @SerializedName("LinkWeb")
    private String urlToWeb;

    public RefridgeGson() {
    }

    public String getCode() {
        return code;
    }

    public String getRefrigeratorName() {
        return refrigeratorName;
    }

    public String getRefrigeratorDescription() {
        return refrigeratorDescription;
    }

    public String getContact() {
        return contact;
    }

    public String getTypeUID() {
        return typeUID;
    }

    public String getLocationUID() {
        return locationUID;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getMsOrganisationUID() {
        return msOrganisationUID;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getMaxRang() {
        return maxRang;
    }

    public String getMaxRangOut() {
        return maxRangOut;
    }

    public String getMinRang() {
        return minRang;
    }

    public String getMinRangOut() {
        return minRangOut;
    }

    public String getImage() {
        return image;
    }

    public String getImageString() {
        return imageString;
    }

    public String getRefrigeratorModel() {
        return refrigeratorModel;
    }

    public String getRefrigeratorBand() {
        return refrigeratorBand;
    }

    public String getRefrigeratorSN() {
        return refrigeratorSN;
    }

    public String getRefrigeratorIDCode() {
        return refrigeratorIDCode;
    }

    public String getRefrigeratorHNNo() {
        return refrigeratorHNNo;
    }

    public String getRefrigeratorTempSetpoint() {
        return refrigeratorTempSetpoint;
    }

    public String getRefrigeratorAccuracy() {
        return refrigeratorAccuracy;
    }

    public String getRefrigeratorUseRangefrom() {
        return refrigeratorUseRangefrom;
    }

    public String getRefrigeratorUseRangeTo() {
        return refrigeratorUseRangeTo;
    }

    public String getRefrigeratorOpenDoorTime() {
        return refrigeratorOpenDoorTime;
    }

    public String getRefrigeratorPMCALInterval() {
        return refrigeratorPMCALInterval;
    }

    public String getTempIn() {
        return tempIn;
    }

    public String getTempOut() {
        return tempOut;
    }

    public String getHumid() {
        return humid;
    }

    public String getStatusOpenDoor() {
        return statusOpenDoor;
    }

    public String getStatusRefrigde() {
        return statusRefrigde;
    }

    public String getUrlToWeb() {
        return urlToWeb;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.refrigeratorName);
        dest.writeString(this.refrigeratorDescription);
        dest.writeString(this.contact);
        dest.writeString(this.typeUID);
        dest.writeString(this.locationUID);
        dest.writeString(this.locationName);
        dest.writeString(this.msOrganisationUID);
        dest.writeString(this.typeName);
        dest.writeString(this.maxRang);
        dest.writeString(this.maxRangOut);
        dest.writeString(this.minRang);
        dest.writeString(this.minRangOut);
        dest.writeString(this.image);
        dest.writeString(this.imageString);
        dest.writeString(this.refrigeratorModel);
        dest.writeString(this.refrigeratorBand);
        dest.writeString(this.refrigeratorSN);
        dest.writeString(this.refrigeratorIDCode);
        dest.writeString(this.refrigeratorHNNo);
        dest.writeString(this.refrigeratorTempSetpoint);
        dest.writeString(this.refrigeratorAccuracy);
        dest.writeString(this.refrigeratorUseRangefrom);
        dest.writeString(this.refrigeratorUseRangeTo);
        dest.writeString(this.refrigeratorOpenDoorTime);
        dest.writeString(this.refrigeratorPMCALInterval);
        dest.writeString(this.tempIn);
        dest.writeString(this.tempOut);
        dest.writeString(this.humid);
        dest.writeString(this.statusOpenDoor);
        dest.writeString(this.statusRefrigde);
        dest.writeString(this.urlToWeb);
    }

    protected RefridgeGson(Parcel in) {
        this.code = in.readString();
        this.refrigeratorName = in.readString();
        this.refrigeratorDescription = in.readString();
        this.contact = in.readString();
        this.typeUID = in.readString();
        this.locationUID = in.readString();
        this.locationName = in.readString();
        this.msOrganisationUID = in.readString();
        this.typeName = in.readString();
        this.maxRang = in.readString();
        this.maxRangOut = in.readString();
        this.minRang = in.readString();
        this.minRangOut = in.readString();
        this.image = in.readString();
        this.imageString = in.readString();
        this.refrigeratorModel = in.readString();
        this.refrigeratorBand = in.readString();
        this.refrigeratorSN = in.readString();
        this.refrigeratorIDCode = in.readString();
        this.refrigeratorHNNo = in.readString();
        this.refrigeratorTempSetpoint = in.readString();
        this.refrigeratorAccuracy = in.readString();
        this.refrigeratorUseRangefrom = in.readString();
        this.refrigeratorUseRangeTo = in.readString();
        this.refrigeratorOpenDoorTime = in.readString();
        this.refrigeratorPMCALInterval = in.readString();
        this.tempIn = in.readString();
        this.tempOut = in.readString();
        this.humid = in.readString();
        this.statusOpenDoor = in.readString();
        this.statusRefrigde = in.readString();
        this.urlToWeb = in.readString();
    }

    public static final Parcelable.Creator<RefridgeGson> CREATOR = new Parcelable.Creator<RefridgeGson>() {
        @Override
        public RefridgeGson createFromParcel(Parcel source) {
            return new RefridgeGson(source);
        }

        @Override
        public RefridgeGson[] newArray(int size) {
            return new RefridgeGson[size];
        }
    };
}
