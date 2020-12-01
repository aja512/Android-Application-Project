package com.example.vegito.activity.ResetPassword;

import com.example.vegito.Models.ResetPassword.RequestResetPassword;

public interface ResetPasswordContract {

    interface iResetPasswordView{

        void showToast(String key);

        void showProgressDialog();

        void hideProgressDialog();


        void setUserNameError(String empty);

        void setPasswordError(String empty);
    }

    interface  iResetPassworPresenter{


        void validateCredentials(String email, String password);
    }


    interface  iResetPassworInteractor{
        void doResetPasswordRequest(RequestResetPassword requestResetPassword, onResetPasswordFinishedListener listener);

        interface onResetPasswordFinishedListener {
            void onResetpasswordSuccess();
            void onFailure();
        }


    }
}
