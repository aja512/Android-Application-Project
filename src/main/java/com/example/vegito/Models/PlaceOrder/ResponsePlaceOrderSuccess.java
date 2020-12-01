package com.example.vegito.Models.PlaceOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePlaceOrderSuccess {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("reqBody")
    @Expose
    private ResponsePlaceOrder reqBody;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResponsePlaceOrder getReqBody() {
        return reqBody;
    }

    public void setReqBody(ResponsePlaceOrder reqBody) {
        this.reqBody = reqBody;
    }
}
