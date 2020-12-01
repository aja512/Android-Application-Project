package com.example.vegito.Fragment.CartFragment;

import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Models.DeleteCart.RequestDeleteCart;
import com.example.vegito.Models.DeleteCart.ResponseDeleteCart;
import com.example.vegito.Models.UpdateCart.RequestUpdateCart;
import com.example.vegito.Models.UpdateCart.ResponseUpdateCart;
import com.example.vegito.Rest.ApiClient;
import com.example.vegito.Rest.ApiInterface;
import com.example.vegito.Utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragmentInteractor implements CartFragmentContract.cartFragmentInteractor {
    @Override
    public void getCartList(Integer userId, onGetCartListFinishedListener listener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseGetCartList> call = apiService.getCartList(userId);


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

    @Override
    public void doUpdateCartList(RequestUpdateCart requestUpdateCart, doUpdateCartListListener listener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseUpdateCart> call = apiService.updateCartItems(requestUpdateCart);


        call.enqueue(new Callback<ResponseUpdateCart>() {
            @Override
            public void onResponse(Call<ResponseUpdateCart> call, Response<ResponseUpdateCart> response) {

                if (response.isSuccessful()) {
                    listener.onUpdateCartlistSuccess(response.body());
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateCart> call, Throwable t) {
                listener.onFailure(Constant.APIError);
            }
        });
    }

    @Override
    public void doDeleteCartItem(RequestDeleteCart requestDeleteCart, doDeleteCartItemListener listener) {
        try {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<ResponseDeleteCart> call = apiService.deleteItemsFromCart(requestDeleteCart);

            call.enqueue(new Callback<ResponseDeleteCart>() {
                @Override
                public void onResponse(Call<ResponseDeleteCart> call, Response<ResponseDeleteCart> response) {

                    if (response.isSuccessful()) {
                        listener.getDeleteListSuccess(response);
                    } else {
                        listener.failureDelete(response);
                    }
                }

                @Override
                public void onFailure(Call<ResponseDeleteCart> call, Throwable t) {
                    listener.onFailure(t.getMessage());
                }
            });

        } catch (Exception ex) {

        }
    }
}
