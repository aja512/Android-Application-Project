package com.example.vegito.Models.CartList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestGetCartList {

    @SerializedName("userId")
    @Expose
    private Integer userId;

    public RequestGetCartList(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
