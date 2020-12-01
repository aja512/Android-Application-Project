package com.example.vegito.activity.CreateAccountActivity;

import com.example.vegito.Models.CreateAccount.RequestCreateAccount;
import com.example.vegito.Models.CreateAccount.ResponseCreateAccount;

import retrofit2.Response;

public interface CreateAccountContact {

    interface iCreateAccountView {

        void showToast(String key);

        void showProgressDialog();

        void hideProgressDialog();


        void getCreatedAccountDetails(Response<ResponseCreateAccount> response);
    }

    interface iCreateAccountPresenter {


        void createAccount(String username, String email, String mobile, String radioButtonSelected, int item, String password);
    }


    interface iCreateAccountInteractor {
        void doCreateAccountRequest(RequestCreateAccount requestCreateAccount, onSignupFinishedListener listener);

        //        void doLoginRequest(RequestLogin requestLogin, LoginContractor.iLoginInteractor.onLoginFinishedListener listener);
//
        interface onSignupFinishedListener {
            void onSignUpSuccess(Response<ResponseCreateAccount> response);

            void onFailure(Response<ResponseCreateAccount> response);

            void onFailures(String failure);
        }


    }
}
