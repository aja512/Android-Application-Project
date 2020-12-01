package com.example.vegito.Models.CreateCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestCreateCart {
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("timeStamp")
    @Expose
    private String timeStamp;

    public RequestCreateCart(Integer userId, String timeStamp) {
        this.userId =userId;
        this.timeStamp=timeStamp;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
