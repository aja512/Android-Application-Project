package com.example.vegito.activity.CreateAccountActivity;

import com.example.vegito.Models.CreateAccount.RequestCreateAccount;
import com.example.vegito.Models.CreateAccount.ResponseCreateAccount;
import com.example.vegito.Utils.Constant;
import com.example.vegito.Utils.InternetStatus;

import retrofit2.Response;

public class CreateAccountPresenter implements CreateAccountContact.iCreateAccountPresenter,CreateAccountContact.iCreateAccountInteractor.onSignupFinishedListener {

    CreateAccountContact.iCreateAccountView mView;
    private CreateAccountContact.iCreateAccountInteractor iCreateAccountInteractor;

    public CreateAccountPresenter(CreateAccountContact.iCreateAccountView mView) {
        this.mView = mView;
        iCreateAccountInteractor = new CreateAccountInteractor();
    }

    @Override
    public void createAccount(String username,String email,String mobile , String  radioButtonSelectedId, int selecteditem, String password) {

        mView.showProgressDialog();
        if (InternetStatus.checkConnection()) {
            iCreateAccountInteractor.doCreateAccountRequest(new RequestCreateAccount(username,email, mobile, radioButtonSelectedId,selecteditem,password), this);
        } else {
            mView.hideProgressDialog();
            mView.showToast(Constant.NoInternet);
        }
    }

    @Override
    public void onSignUpSuccess(Response<ResponseCreateAccount> response) {
        mView.hideProgressDialog();
        if(response!=null){
            mView.getCreatedAccountDetails(response);
        }

    }

    @Override
    public void onFailure(Response<ResponseCreateAccount> response) {
        mView.hideProgressDialog();
        mView.showToast(response.message());
    }

    @Override
    public void onFailures(String failure) {
        mView.hideProgressDialog();
        mView.showToast(Constant.APIError);
    }
}
