package com.example.vegito.Models.GetAddresss;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseShipBillAddress {

    @SerializedName("shipping_address")
    @Expose
    private ResponseShippingAddress shippingAddress;
    @SerializedName("billing_address")
    @Expose
    private ResponseBillingAddress billingAddress;
    @SerializedName("code")
    @Expose
    private Integer code;

    public ResponseShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ResponseShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public ResponseBillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(ResponseBillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
