package com.appstertech.tempmonitor.service.model;

import android.support.v4.media.session.MediaSessionCompat;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nuimamon on 30/7/2559.
 */
public class UserGson {
    /*    "UID": 4
                "FullName": "Mr.Puthi Puthi",
                "OrganisationID": 1,
                "TypeUser": "Admin"*/
    @SerializedName("UID")
    private String userId;
    @SerializedName("FullName")
    private String fullName;
    @SerializedName("OrganisationID")
    private String organisationId;
    @SerializedName("TypeUser")
    private String typeUser;
    @SerializedName("Username")
    private String username;
    @SerializedName("Password")
    private String password;


    public UserGson() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(String organisationId) {
        this.organisationId = organisationId;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
