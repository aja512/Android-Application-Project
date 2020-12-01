package com.example.vegito.Models.ResetPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestResetPassword {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("password")
    @Expose
    private String password;

    public RequestResetPassword(int email, String password) {
        userId =email;
        this.password=password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
