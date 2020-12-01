package com.example.vegito.activity.LoginScreen;

import android.text.TextUtils;

import com.example.vegito.Models.LoginModel.RequestLogin;
import com.example.vegito.Models.LoginModel.ResponseLogin;
import com.example.vegito.Utils.Constant;
import com.example.vegito.Utils.InternetStatus;

public class LoginPresenter implements LoginContractor.iLoginPresenter,
        LoginContractor.iLoginInteractor.onLoginFinishedListener {

    private LoginContractor.iLoginView mView;
    private LoginContractor.iLoginInteractor mLoginInteractor;


    public LoginPresenter(LoginContractor.iLoginView mView) {
        this.mView = mView;
        mLoginInteractor = new LoginScreenInteractor();

    }

    @Override
    public void validateCredentials(String username, String password) {
        if (mView != null) {
            if (TextUtils.isEmpty(username)) {
                mView.setUserNameError("Empty");
            } else if (!username.equals(username)) {
                mView.setUserNameError("Invalid");

            } else if (TextUtils.isEmpty(password)) {
                mView.setPasswordError("Empty");
            } else if (!password.equals(password)) {
                mView.setPasswordError("Invalid");

            } else {
                //valid
                mView.showProgressDialog();
                if (InternetStatus.checkConnection()) {
                    mLoginInteractor.doLoginRequest(new RequestLogin(username, password), this);
                } else {
                    mView.hideProgressDialog();
                    mView.showToast(Constant.NoInternet);
                }
            }
        }
    }


    @Override
    public void onLoginSuccess(ResponseLogin responseLogin) {
        if (mView != null) {
            mView.hideProgressDialog();
            if (responseLogin != null) {

                mView.getLoginSuccess(responseLogin);
            } else {
                mView.showToast(Constant.Error);
            }
        }
    }


    @Override
    public void onFailure(String message) {

        mView.hideProgressDialog();
        mView.showToast(message);
    }
}
