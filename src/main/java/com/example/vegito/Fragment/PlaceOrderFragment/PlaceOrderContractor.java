package com.example.vegito.Fragment.PlaceOrderFragment;


import com.example.vegito.Models.GetAddresss.ResponseShipBillAddress;
import com.example.vegito.Models.PlaceOrder.RequestPlaceOrder;
import com.example.vegito.Models.PlaceOrder.ResponsePlaceOrderSuccess;
import com.example.vegito.Models.ShippingBillingAddress.BillingAddress;
import com.example.vegito.Models.ShippingBillingAddress.ResponseAddAddress;
import com.example.vegito.Models.ShippingBillingAddress.ShippingAddress;

import retrofit2.Response;

public interface PlaceOrderContractor {

    interface placeOrderFragmentView {

        void showToast(String key);

        void showProgressDialog();

        void hideProgressDialog();

        void getAddress(ResponseAddAddress response);

        void getAddressSuccess(ResponseShipBillAddress response);

        void getPlaceOrder(ResponsePlaceOrderSuccess body, String cod);
    }


    interface placeOrderFragmentPresenter {
        void getUserAddress(Integer userID);

        void doAddAdress(String streetName, String address1, String address2, String landmark, String pincode);

        void doAddDiffrentAdress(String streetName, String address1, String address2, String landmark,String pincode,
                                 String streetNameBilling, String address1Billing, String address2Billing,
                                 String landmarkBilling,String pincodeBilling);

        void doPlaceOrder(int shippingId, int billingId, int stringFromPreferences, int UserID, String n, String s, String cod);
    }


    interface placeOrderFragmentInteractor {
        void dogetUserAddressList(Integer userID,doGetBillingAddressListener listener);
        void doAddAdress(ShippingAddress shippingAddress, BillingAddress billingAddress, doAddAddressListener listener);

        void doPlaceOrder(RequestPlaceOrder requestPlaceOrder, String cod, doPlaceOrderListener listener);


        interface doPlaceOrderListener {
            void onFailure();
            void addPlaceOrderSuccess(Response<ResponsePlaceOrderSuccess> response, String cod);
            void failurePlaceOrder(Response<ResponsePlaceOrderSuccess> response);
        }

        interface doAddAddressListener {
            void onFailure();
            void addAddressListSuccess(Response<ResponseAddAddress> response);
            void failureAddAddressList(Response<ResponseAddAddress> response);
        }

        interface doGetBillingAddressListener {
            void onFailure(String message);
            void getAddressListSuccess(ResponseShipBillAddress response);
            void failureAddressList(String message);
        }
    }
}
