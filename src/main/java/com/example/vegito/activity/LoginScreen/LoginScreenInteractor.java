package com.example.vegito.activity.LoginScreen;

import com.example.vegito.Models.CreateCart.RequestCreateCart;
import com.example.vegito.Models.CreateCart.ResponseCreateCart;
import com.example.vegito.Models.LoginModel.RequestLogin;
import com.example.vegito.Models.LoginModel.ResponseLogin;
import com.example.vegito.Rest.ApiClient;
import com.example.vegito.Rest.ApiInterface;
import com.example.vegito.Utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreenInteractor implements LoginContractor.iLoginInteractor {


    @Override
    public void doLoginRequest(RequestLogin requestLogin, final onLoginFinishedListener listener) {

        try {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<ResponseLogin> call = apiService.doLogin(requestLogin);
            call.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                    if (response.isSuccessful()) {
                        listener.onLoginSuccess(response.body());
                    } else {
                        listener.onFailure(response.message());
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    listener.onFailure("Invalid username or password");
                }
            });

        } catch (Exception ex) {
            listener.onFailure("Failure");
        }

    }


}
