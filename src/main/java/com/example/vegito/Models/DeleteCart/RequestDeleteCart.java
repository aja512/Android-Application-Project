package com.example.vegito.Models.DeleteCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestDeleteCart {

    @SerializedName("userCartId")
    @Expose
    private Integer userCartId;
    @SerializedName("prodId")
    @Expose
    private Integer prodId;

    public RequestDeleteCart(int parseInt, Integer prodId) {
        this.userCartId = parseInt;
        this.prodId = prodId;

    }

    public Integer getUserCartId() {
        return userCartId;
    }

    public void setUserCartId(Integer userCartId) {
        this.userCartId = userCartId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }
}
