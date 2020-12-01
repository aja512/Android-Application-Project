package com.example.vegito.Fragment.ProfileFragment;

import android.content.Context;

import com.example.vegito.Models.AddToCart.RequestAddToCart;
import com.example.vegito.Models.AddToCart.ResponseAddToCart;
import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Models.DeleteCart.RequestDeleteCart;
import com.example.vegito.Models.DeleteCart.ResponseDeleteCart;
import com.example.vegito.Models.ProductList.ResponseProductList;
import com.example.vegito.Rest.ApiClient;
import com.example.vegito.Rest.ApiInterface;
import com.example.vegito.Utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragmentInteractor implements ProfileFragmentContract.iprofileFragmentInteractor {


    @Override
    public void doGetProdcutListRequest(int catId, final onGetProductListFinisherListener listener, Context context) {


        try {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<ResponseProductList> call = apiService.getProductList(catId);

            call.enqueue(new Callback<ResponseProductList>() {
                @Override
                public void onResponse(Call<ResponseProductList> call, Response<ResponseProductList> response) {

                    if (response.isSuccessful()) {
                        listener.getProductListData(response, context);
                    } else {
                        listener.Failure();
                    }
                }

                @Override
                public void onFailure(Call<ResponseProductList> call, Throwable t) {
                    listener.Failure();
                }
            });

        } catch (Exception ex) {
            listener.Failure();
        }
    }

    @Override
    public void doAddProdcutToCartRequest(RequestAddToCart requestAddToCart,
                                          onAddProductToCartFinisherListener listener) {
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
    public void doDeleteProductFromCart(RequestDeleteCart requestDeleteCart, onDeleteProductListFinisherListener listener) {
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
                    listener.Failures(t.getMessage());
                }
            });

        } catch (Exception ex) {

        }
    }

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

}

