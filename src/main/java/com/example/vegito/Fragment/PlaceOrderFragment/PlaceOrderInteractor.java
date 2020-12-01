package com.example.vegito.Fragment.PlaceOrderFragment;

import com.example.vegito.Models.GetAddresss.ResponseShipBillAddress;
import com.example.vegito.Models.PlaceOrder.RequestPlaceOrder;
import com.example.vegito.Models.PlaceOrder.ResponsePlaceOrderSuccess;
import com.example.vegito.Models.ShippingBillingAddress.BillingAddress;
import com.example.vegito.Models.ShippingBillingAddress.RequestAddShippingBillingAddress;
import com.example.vegito.Models.ShippingBillingAddress.ResponseAddAddress;
import com.example.vegito.Models.ShippingBillingAddress.ShippingAddress;
import com.example.vegito.Rest.ApiClient;
import com.example.vegito.Rest.ApiInterface;
import com.example.vegito.Utils.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceOrderInteractor implements PlaceOrderContractor.placeOrderFragmentInteractor {


    @Override
    public void dogetUserAddressList(Integer userID, doGetBillingAddressListener listener) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseShipBillAddress> call = apiService.getUserShippingBillingAddress(userID);


        call.enqueue(new Callback<ResponseShipBillAddress>() {
            @Override
            public void onResponse(Call<ResponseShipBillAddress> call, Response<ResponseShipBillAddress> response) {

                if (response.isSuccessful()) {
                    listener.getAddressListSuccess(response.body());
                } else {
                    listener.failureAddressList(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseShipBillAddress> call, Throwable t) {
                listener.onFailure(Constant.APIError);
            }
        });

    }

    @Override
    public void doAddAdress(ShippingAddress shippingAddress, BillingAddress billingAddress,
                            doAddAddressListener listener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseAddAddress> call = apiService.saveUserAddress
                (new RequestAddShippingBillingAddress(shippingAddress, billingAddress));


        call.enqueue(new Callback<ResponseAddAddress>() {
            @Override
            public void onResponse(Call<ResponseAddAddress> call, Response<ResponseAddAddress> response) {

                if (response.isSuccessful()) {
                    listener.addAddressListSuccess(response);
                } else {
                    listener.failureAddAddressList(response);
                }
            }

            @Override
            public void onFailure(Call<ResponseAddAddress> call, Throwable t) {
                listener.onFailure();
            }
        });

    }

    @Override
    public void doPlaceOrder(RequestPlaceOrder requestPlaceOrder, String cod, doPlaceOrderListener listener) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponsePlaceOrderSuccess> call = apiService.placeOrder(requestPlaceOrder);


        call.enqueue(new Callback<ResponsePlaceOrderSuccess>() {
            @Override
            public void onResponse(Call<ResponsePlaceOrderSuccess> call, Response<ResponsePlaceOrderSuccess> response) {

                if (response.isSuccessful()) {
                    listener.addPlaceOrderSuccess(response,cod);
                } else {
                    listener.failurePlaceOrder(response);
                }
            }

            @Override
            public void onFailure(Call<ResponsePlaceOrderSuccess> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}
