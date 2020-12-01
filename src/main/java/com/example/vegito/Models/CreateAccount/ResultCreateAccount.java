package com.example.vegito.Models.CreateAccount;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.vegito.Models.LoginModel.Result;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@Entity
public class ResultCreateAccount implements Serializable{


    @SerializedName("user_id")
    @Expose
    @ColumnInfo
    @PrimaryKey
    private Integer userId;
    @SerializedName("user_name")
    @Expose
    @ColumnInfo
    private String userName;
    @SerializedName("user_email")
    @Expose
    @ColumnInfo
    private String userEmail;
    @SerializedName("user_mobile")
    @Expose
    @ColumnInfo
    private String userMobile;
    @SerializedName("user_gender")
    @Expose
    @ColumnInfo
    private String userGender;
    @SerializedName("user_role_id")
    @Expose
    @ColumnInfo
    private Integer userRoleId;
    @SerializedName("is_user_subscribed")
    @Expose
    @ColumnInfo
    private String isUserSubscribed;
    @SerializedName("created_datetime")
    @Expose
    @ColumnInfo
    private String createdDatetime;
    @SerializedName("user_password")
    @Expose
    @ColumnInfo
    private String userPassword;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getIsUserSubscribed() {
        return isUserSubscribed;
    }

    public void setIsUserSubscribed(String isUserSubscribed) {
        this.isUserSubscribed = isUserSubscribed;
    }

    public String getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(String createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
