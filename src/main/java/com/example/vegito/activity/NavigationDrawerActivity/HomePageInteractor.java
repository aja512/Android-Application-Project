package com.example.vegito.activity.NavigationDrawerActivity;

import com.example.vegito.Models.CartList.RequestGetCartList;
import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Models.CreateCart.RequestCreateCart;
import com.example.vegito.Models.CreateCart.ResponseCreateCart;
import com.example.vegito.Rest.ApiClient;
import com.example.vegito.Rest.ApiInterface;
import com.example.vegito.Utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageInteractor implements HomePageContract.iHomePageInteractor {
    @Override
    public void creatCart(RequestCreateCart requestCreateCart, final onCreateCartFinishedListener listener) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseCreateCart> call = apiService.createCart(requestCreateCart);


        call.enqueue(new Callback<ResponseCreateCart>() {
            @Override
            public void onResponse(Call<ResponseCreateCart> call, Response<ResponseCreateCart> response) {

                if (response.isSuccessful()) {
                    listener.onCartCreateSuccess(response.body());
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseCreateCart> call, Throwable t) {
                listener.onFailure(Constant.APIError);
            }
        });

    }

    @Override
    public void getCartList(Integer userID, onGetCartListFinishedListener listener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseGetCartList> call = apiService.getCartList(userID);


        call.enqueue(new Callback<ResponseGetCartList>() {
            @Override
            public void onResponse(Call<ResponseGetCartList> call, Response<ResponseGetCartList> response) {

                if (response.isSuccessful()) {
                    listener.onGetCartlistSuccess(response.body());
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseGetCartList> call, Throwable t) {
                listener.onFailure(Constant.APIError);
            }
        });
    }


}
