package com.example.vegito.activity.CreateAccountActivity;

import com.example.vegito.Models.CreateAccount.RequestCreateAccount;
import com.example.vegito.Models.CreateAccount.ResponseCreateAccount;
import com.example.vegito.Rest.ApiClient;
import com.example.vegito.Rest.ApiInterface;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountInteractor implements CreateAccountContact.iCreateAccountInteractor {
    @Override
    public void doCreateAccountRequest(RequestCreateAccount requestCreateAccount, final onSignupFinishedListener listener) {

        try {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<ResponseCreateAccount> call = apiService.doRegister(requestCreateAccount);
            Gson gson = new Gson();
            String s = gson.toJson(requestCreateAccount);


            call.enqueue(new Callback<ResponseCreateAccount>() {
                @Override
                public void onResponse(Call<ResponseCreateAccount> call, Response<ResponseCreateAccount> response) {

                    if (response.isSuccessful()) {
                        listener.onSignUpSuccess(response);
                    } else {
                        listener.onFailure(response);
                    }
                }

                @Override
                public void onFailure(Call<ResponseCreateAccount> call, Throwable t) {
                    listener.onFailures("Failure");
                }
            });

        } catch (Exception ex) {

        }

    }


}
