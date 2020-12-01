package com.example.vegito.activity.ResetPassword;

import android.text.TextUtils;

import com.example.vegito.Models.ResetPassword.RequestResetPassword;
import com.example.vegito.Utils.Constant;
import com.example.vegito.Utils.InternetStatus;

public class ResetPasswordPresenter implements ResetPasswordContract.iResetPassworPresenter,ResetPasswordContract.iResetPassworInteractor.onResetPasswordFinishedListener {


    ResetPasswordContract.iResetPasswordView mView;
    ResetPasswordContract.iResetPassworInteractor resetPassworInteractor;

    public ResetPasswordPresenter(ResetPasswordContract.iResetPasswordView mView) {
        this.mView = mView;
        resetPassworInteractor = new ResetPasswordInteractor();

    }

    @Override
    public void validateCredentials(String email, String password) {

        if (mView != null) {
            if (TextUtils.isEmpty(email)) {
                mView.setUserNameError("Empty");
            } else if (!email.equals(email)) {
                mView.setUserNameError("Invalid");

            } else if (TextUtils.isEmpty(password)) {
                mView.setPasswordError("Empty");
            } else if (!password.equals(password)) {
                mView.setPasswordError("Invalid");

            } else {
                //valid
                mView.showProgressDialog();
                if (InternetStatus.checkConnection()) {
                    resetPassworInteractor.doResetPasswordRequest(new RequestResetPassword(Integer.parseInt(email), password), this);
                } else {
                    mView.hideProgressDialog();
                    mView.showToast(Constant.NoInternet);
                }
            }
        }

    }

    @Override
    public void onResetpasswordSuccess() {

    }

    @Override
    public void onFailure() {

    }
}

