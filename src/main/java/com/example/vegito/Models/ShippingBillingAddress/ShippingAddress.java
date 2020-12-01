package com.example.vegito.Models.ShippingBillingAddress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShippingAddress {

    @SerializedName("streetName")
    @Expose
    private String streetName;
    @SerializedName("landmark")
    @Expose
    private String landmark;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("userId")
    @Expose
    private Integer userId;

    public ShippingAddress(String streetName, String address1, String address2, String landmark, String pincode, Integer userId) {
        this.streetName = streetName;
        this.address1 = address1;
        this.address2 = address2;
        this.landmark = landmark;
        this.pincode = pincode;
        this.userId = userId;
    }




    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
