package com.example.vegito.Models.ProductList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnitData {

    @SerializedName("prod_unit_id")
    @Expose
    private Integer prodUnitId;
    @SerializedName("prod_unit_price")
    @Expose
    private String prodUnitPrice;
    @SerializedName("prod_unit_name")
    @Expose
    private String prodUnitName;
    @SerializedName("is_active_prod_unit")
    @Expose
    private String isActiveProdUnit;

    public Integer getProdUnitId() {
        return prodUnitId;
    }

    public void setProdUnitId(Integer prodUnitId) {
        this.prodUnitId = prodUnitId;
    }

    public String getProdUnitPrice() {
        return prodUnitPrice;
    }

    public void setProdUnitPrice(String prodUnitPrice) {
        this.prodUnitPrice = prodUnitPrice;
    }

    public String getProdUnitName() {
        return prodUnitName;
    }

    public void setProdUnitName(String prodUnitName) {
        this.prodUnitName = prodUnitName;
    }

    public String getIsActiveProdUnit() {
        return isActiveProdUnit;
    }

    public void setIsActiveProdUnit(String isActiveProdUnit) {
        this.isActiveProdUnit = isActiveProdUnit;
    }

}
