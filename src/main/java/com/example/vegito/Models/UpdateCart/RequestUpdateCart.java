package com.example.vegito.Models.UpdateCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestUpdateCart {
    @SerializedName("prodId")
    @Expose
    private Integer prodId;
    @SerializedName("prodQty")
    @Expose
    private Integer prodQty;
    @SerializedName("cartId")
    @Expose
    private Integer cartId;

    public RequestUpdateCart(Integer prodId, int minteger, Integer userCartId) {
        this.prodId = prodId;
        prodQty = minteger;
        cartId = userCartId;
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
}
