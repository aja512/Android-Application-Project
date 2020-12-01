package com.example.vegito.Models.CreateAccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestCreateAccount {

    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobNo")
    @Expose
    private String mobNo;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("roleId")
    @Expose
    private Integer roleId;
    @SerializedName("password")
    @Expose
    private String password;

    public RequestCreateAccount(String username, String email, String mobile,
                                String radioButtonSelectedId, int selecteditem, String password) {

        this.userName = username;
        this.email = email;
        this.mobNo = mobile;
        gender = radioButtonSelectedId;
        roleId = selecteditem;
        this.password = password;


    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
