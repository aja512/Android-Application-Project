package com.example.vegito.Fragment.ProductDetailFragment;

import com.example.vegito.Models.AddToCart.RequestAddToCart;
import com.example.vegito.Models.AddToCart.ResponseAddToCart;
import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Rest.ApiClient;
import com.example.vegito.Rest.ApiInterface;
import com.example.vegito.Utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailInteractor implements ProductFragmentContract.productFragmentInteractor {
    @Override
    public void doAddProdcutToCartRequest(RequestAddToCart requestAddToCart, onAddProductToCartFinisherListener listener) {
        try {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<ResponseAddToCart> call = apiService.addItemsToCart(requestAddToCart);

            call.enqueue(new Callback<ResponseAddToCart>() {
                @Override
                public void onResponse(Call<ResponseAddToCart> call, Response<ResponseAddToCart> response) {

                    if (response.isSuccessful()) {
                        listener.getAddedProductmessage(response);
                    } else {
                        listener.Failure(response);
                    }
                }

                @Override
                public void onFailure(Call<ResponseAddToCart> call, Throwable t) {
                    listener.Failures(t.getMessage());
                }
            });

        } catch (Exception ex) {

        }
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
