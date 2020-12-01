package com.example.vegito.Models.AddToCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestAddToCart {
    @SerializedName("prodId")
    @Expose
    private Integer prodId;
    @SerializedName("prodQty")
    @Expose
    private Integer prodQty;
    @SerializedName("cartId")
    @Expose
    private Integer cartId;
    @SerializedName("prodUnitId")
    @Expose
    private Integer prodUnitId;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public RequestAddToCart(Integer prodId, int i, Integer catId, Integer prodUnitId, String timeStamp) {
        this.prodId = prodId;
        prodQty = i;
        cartId = catId;
        this.prodUnitId = prodUnitId;
        this.timestamp = timeStamp;

    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getProdQty() {
        return prodQty;
    }

    public void setProdQty(Integer prodQty) {
        this.prodQty = prodQty;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getProdUnitId() {
        return prodUnitId;
    }

    public void setProdUnitId(Integer prodUnitId) {
        this.prodUnitId = prodUnitId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
