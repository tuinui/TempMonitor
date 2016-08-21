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
//            "RefrigeratorDescription": "RFM-CHM-002",
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
//            "ImageString": null,
//            "RefrigeratorModel": "Sanden Intercool",
//            "RefrigeratorBand": "Sanden Intercool",
//            "RefrigeratorSN": "SDC1500103A-0511-00234",
//            "RefrigeratorIDCode": "Sanden Intercool",
//            "RefrigeratorHNNo": "Sanden Intercool",
//            "RefrigeratorTempSetpoint": "4",
//            "RefrigeratorAccuracy": "6",
//            "RefrigeratorUseRangefrom": "2",
//            "RefrigeratorUseRangeTo": "8",
//            "RefrigeratorOpenDoorTime": "Sanden Intercool",
//            "RefrigeratorPMCALInterval": "5"
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

    public RefridgeGson() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRefrigeratorName() {
        return refrigeratorName;
    }

    public void setRefrigeratorName(String refrigeratorName) {
        this.refrigeratorName = refrigeratorName;
    }

    public String getRefrigeratorDescription() {
        return refrigeratorDescription;
    }

    public void setRefrigeratorDescription(String refrigeratorDescription) {
        this.refrigeratorDescription = refrigeratorDescription;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTypeUID() {
        return typeUID;
    }

    public void setTypeUID(String typeUID) {
        this.typeUID = typeUID;
    }

    public String getLocationUID() {
        return locationUID;
    }

    public void setLocationUID(String locationUID) {
        this.locationUID = locationUID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getMsOrganisationUID() {
        return msOrganisationUID;
    }

    public void setMsOrganisationUID(String msOrganisationUID) {
        this.msOrganisationUID = msOrganisationUID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMaxRang() {
        return maxRang;
    }

    public void setMaxRang(String maxRang) {
        this.maxRang = maxRang;
    }

    public String getMaxRangOut() {
        return maxRangOut;
    }

    public void setMaxRangOut(String maxRangOut) {
        this.maxRangOut = maxRangOut;
    }

    public String getMinRang() {
        return minRang;
    }

    public void setMinRang(String minRang) {
        this.minRang = minRang;
    }

    public String getMinRangOut() {
        return minRangOut;
    }

    public void setMinRangOut(String minRangOut) {
        this.minRangOut = minRangOut;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public String getRefrigeratorModel() {
        return refrigeratorModel;
    }

    public void setRefrigeratorModel(String refrigeratorModel) {
        this.refrigeratorModel = refrigeratorModel;
    }

    public String getRefrigeratorBand() {
        return refrigeratorBand;
    }

    public void setRefrigeratorBand(String refrigeratorBand) {
        this.refrigeratorBand = refrigeratorBand;
    }

    public String getRefrigeratorSN() {
        return refrigeratorSN;
    }

    public void setRefrigeratorSN(String refrigeratorSN) {
        this.refrigeratorSN = refrigeratorSN;
    }

    public String getRefrigeratorIDCode() {
        return refrigeratorIDCode;
    }

    public void setRefrigeratorIDCode(String refrigeratorIDCode) {
        this.refrigeratorIDCode = refrigeratorIDCode;
    }

    public String getRefrigeratorHNNo() {
        return refrigeratorHNNo;
    }

    public void setRefrigeratorHNNo(String refrigeratorHNNo) {
        this.refrigeratorHNNo = refrigeratorHNNo;
    }

    public String getRefrigeratorTempSetpoint() {
        return refrigeratorTempSetpoint;
    }

    public void setRefrigeratorTempSetpoint(String refrigeratorTempSetpoint) {
        this.refrigeratorTempSetpoint = refrigeratorTempSetpoint;
    }

    public String getRefrigeratorAccuracy() {
        return refrigeratorAccuracy;
    }

    public void setRefrigeratorAccuracy(String refrigeratorAccuracy) {
        this.refrigeratorAccuracy = refrigeratorAccuracy;
    }

    public String getRefrigeratorUseRangefrom() {
        return refrigeratorUseRangefrom;
    }

    public void setRefrigeratorUseRangefrom(String refrigeratorUseRangefrom) {
        this.refrigeratorUseRangefrom = refrigeratorUseRangefrom;
    }

    public String getRefrigeratorUseRangeTo() {
        return refrigeratorUseRangeTo;
    }

    public void setRefrigeratorUseRangeTo(String refrigeratorUseRangeTo) {
        this.refrigeratorUseRangeTo = refrigeratorUseRangeTo;
    }

    public String getRefrigeratorOpenDoorTime() {
        return refrigeratorOpenDoorTime;
    }

    public void setRefrigeratorOpenDoorTime(String refrigeratorOpenDoorTime) {
        this.refrigeratorOpenDoorTime = refrigeratorOpenDoorTime;
    }

    public String getRefrigeratorPMCALInterval() {
        return refrigeratorPMCALInterval;
    }

    public void setRefrigeratorPMCALInterval(String refrigeratorPMCALInterval) {
        this.refrigeratorPMCALInterval = refrigeratorPMCALInterval;
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
