package com.example.vegito.Models.CartList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultGetCartList implements Serializable {

    @SerializedName("prod_id")
    @Expose
    private Integer prodId;
    @SerializedName("prod_name")
    @Expose
    private String prodName;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;
    @SerializedName("prod_image")
    @Expose
    private String prodImage;
    @SerializedName("prod_unit")
    @Expose
    private String prodUnit;
    @SerializedName("prod_available_qty")
    @Expose
    private String prodAvailableQty;
    @SerializedName("prod_price")
    @Expose
    private String prodPrice;
    @SerializedName("prod_is_discount_available")
    @Expose
    private String prodIsDiscountAvailable;
    @SerializedName("prod_discount_price")
    @Expose
    private String prodDiscountPrice;
    @SerializedName("prod_description")
    @Expose
    private Object prodDescription;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("updated_date")
    @Expose
    private Object updatedDate;
    @SerializedName("prod_unit_id")
    @Expose
    private Integer prodUnitId;
    @SerializedName("prod_unit_name")
    @Expose
    private String prodUnitName;
    @SerializedName("prod_unit_price")
    @Expose
    private String prodUnitPrice;
    @SerializedName("user_cart_item_id")
    @Expose
    private Integer userCartItemId;
    @SerializedName("user_cart_item_prod_qty")
    @Expose
    private Integer userCartItemProdQty;
    @SerializedName("user_cart_id")
    @Expose
    private Integer userCartId;
    @SerializedName("user_cart_item_prod_unit_id")
    @Expose
    private Integer userCartItemProdUnitId;
    @SerializedName("created_datetime")
    @Expose
    private String createdDatetime;

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getProdImage() {
        return prodImage;
    }

    public void setProdImage(String prodImage) {
        this.prodImage = prodImage;
    }

    public String getProdUnit() {
        return prodUnit;
    }

    public void setProdUnit(String prodUnit) {
        this.prodUnit = prodUnit;
    }

    public String getProdAvailableQty() {
        return prodAvailableQty;
    }

    public void setProdAvailableQty(String prodAvailableQty) {
        this.prodAvailableQty = prodAvailableQty;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdIsDiscountAvailable() {
        return prodIsDiscountAvailable;
    }

    public void setProdIsDiscountAvailable(String prodIsDiscountAvailable) {
        this.prodIsDiscountAvailable = prodIsDiscountAvailable;
    }

    public String getProdDiscountPrice() {
        return prodDiscountPrice;
    }

    public void setProdDiscountPrice(String prodDiscountPrice) {
        this.prodDiscountPrice = prodDiscountPrice;
    }

    public Object getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(Object prodDescription) {
        this.prodDescription = prodDescription;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Object getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Object updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getProdUnitId() {
        return prodUnitId;
    }

    public void setProdUnitId(Integer prodUnitId) {
        this.prodUnitId = prodUnitId;
    }

    public String getProdUnitName() {
        return prodUnitName;
    }

    public void setProdUnitName(String prodUnitName) {
        this.prodUnitName = prodUnitName;
    }

    public String getProdUnitPrice() {
        return prodUnitPrice;
    }

    public void setProdUnitPrice(String prodUnitPrice) {
        this.prodUnitPrice = prodUnitPrice;
    }

    public Integer getUserCartItemId() {
        return userCartItemId;
    }

    public void setUserCartItemId(Integer userCartItemId) {
        this.userCartItemId = userCartItemId;
    }

    public Integer getUserCartItemProdQty() {
        return userCartItemProdQty;
    }

    public void setUserCartItemProdQty(Integer userCartItemProdQty) {
        this.userCartItemProdQty = userCartItemProdQty;
    }

    public Integer getUserCartId() {
        return userCartId;
    }

    public void setUserCartId(Integer userCartId) {
        this.userCartId = userCartId;
    }

    public Integer getUserCartItemProdUnitId() {
        return userCartItemProdUnitId;
    }

    public void setUserCartItemProdUnitId(Integer userCartItemProdUnitId) {
        this.userCartItemProdUnitId = userCartItemProdUnitId;
    }

    public String getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(String createdDatetime) {
        this.createdDatetime = createdDatetime;
    }
}
