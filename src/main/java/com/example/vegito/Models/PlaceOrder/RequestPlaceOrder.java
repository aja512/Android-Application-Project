package com.example.vegito.Models.PlaceOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestPlaceOrder {

    @SerializedName("shippingAddressId")
    @Expose
    private Integer shippingAddressId;
    @SerializedName("billingAddresId")
    @Expose
    private Integer billingAddresId;
    @SerializedName("userCartId")
    @Expose
    private Integer userCartId;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("isOrderDelivered")
    @Expose
    private String isOrderDelivered;
    @SerializedName("remark")
    @Expose
    private String remark;

    public RequestPlaceOrder(int shippingAddressId, int billingAddresId, int userCartId, int userId,
                             String isOrderDeliver, String remark) {

        this.shippingAddressId = shippingAddressId;
        this.billingAddresId = billingAddresId;
        this.userCartId = userCartId;
        this.userId = userId;
        this.isOrderDelivered = isOrderDeliver;
        this.remark = remark;
    }

    public Integer getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Integer shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Integer getBillingAddresId() {
        return billingAddresId;
    }

    public void setBillingAddresId(Integer billingAddresId) {
        this.billingAddresId = billingAddresId;
    }

    public Integer getUserCartId() {
        return userCartId;
    }

    public void setUserCartId(Integer userCartId) {
        this.userCartId = userCartId;
    }

    public String getIsOrderDelivered() {
        return isOrderDelivered;
    }

    public void setIsOrderDelivered(String isOrderDelivered) {
        this.isOrderDelivered = isOrderDelivered;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
