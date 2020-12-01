package com.example.vegito.Models.ProductList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResultProductList implements Serializable {

    @SerializedName("unitData")
    @Expose
    private List<UnitData> unitData = null;
    @SerializedName("prod_name")
    @Expose
    private String prodName;
    @SerializedName("prod_id")
    @Expose
    private Integer prodId;
    @SerializedName("prod_image")
    @Expose
    private String prodImage;
    @SerializedName("cat_id")
    @Expose
    private Integer catId;

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public List<UnitData> getUnitData() {
        return unitData;
    }

    public void setUnitData(List<UnitData> unitData) {
        this.unitData = unitData;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdImage() {
        return prodImage;
    }

    public void setProdImage(String prodImage) {
        this.prodImage = prodImage;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }
}
