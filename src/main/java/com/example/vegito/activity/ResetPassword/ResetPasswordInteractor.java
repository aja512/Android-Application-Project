package com.example.vegito.activity.ResetPassword;

import com.example.vegito.Models.ResetPassword.RequestResetPassword;
import com.example.vegito.Models.ResetPassword.ResponseResetPassword;
import com.example.vegito.Rest.ApiClient;
import com.example.vegito.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordInteractor implements ResetPasswordContract.iResetPassworInteractor {
    @Override
    public void doResetPasswordRequest(RequestResetPassword requestResetPassword, final onResetPasswordFinishedListener listener) {
        try {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<ResponseResetPassword> call = apiService.resetPassword(requestResetPassword);

            call.enqueue(new Callback<ResponseResetPassword>() {
                @Override
                public void onResponse(Call<ResponseResetPassword> call, Response<ResponseResetPassword> response) {

                    if (response.isSuccessful()) {
                        listener.onResetpasswordSuccess();
                    } else {
                        listener.onFailure();
                    }
                }

                @Override
                public void onFailure(Call<ResponseResetPassword> call, Throwable t) {
                    listener.onFailure();
                }
            });

        } catch (Exception ex) {
            listener.onFailure();
        }

    }
}

