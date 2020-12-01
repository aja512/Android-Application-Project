package com.example.vegito.Fragment.PlaceOrderFragment;

import com.example.vegito.Models.GetAddresss.ResponseShipBillAddress;
import com.example.vegito.Models.PlaceOrder.RequestPlaceOrder;
import com.example.vegito.Models.PlaceOrder.ResponsePlaceOrderSuccess;
import com.example.vegito.Models.ShippingBillingAddress.BillingAddress;
import com.example.vegito.Models.ShippingBillingAddress.ResponseAddAddress;
import com.example.vegito.Models.ShippingBillingAddress.ShippingAddress;
import com.example.vegito.Utils.Constant;

import retrofit2.Response;

public class PlaceOrderPresenter implements PlaceOrderContractor.placeOrderFragmentPresenter,
        PlaceOrderContractor.placeOrderFragmentInteractor.doGetBillingAddressListener,
        PlaceOrderContractor.placeOrderFragmentInteractor.doAddAddressListener,
        PlaceOrderContractor.placeOrderFragmentInteractor.doPlaceOrderListener {

    PlaceOrderContractor.placeOrderFragmentView mView;
    PlaceOrderContractor.placeOrderFragmentInteractor placeOrderFragmentInteractor;

    public PlaceOrderPresenter(PlaceOrderContractor.placeOrderFragmentView mView) {

        this.mView = mView;
        placeOrderFragmentInteractor = new PlaceOrderInteractor();
    }


    @Override
    public void getUserAddress(Integer userID) {

        mView.showProgressDialog();
        placeOrderFragmentInteractor.dogetUserAddressList(userID, this);


    }

    @Override
    public void doAddAdress(String streetName, String address1, String address2, String landmark, String pincode) {

        if (!streetName.equals("") && !address1.equals("") &&
                !address2.equals("") && !landmark.equals("") && !pincode.equals("")) {
            placeOrderFragmentInteractor.doAddAdress(new ShippingAddress(streetName, address1, address2,
                            landmark, pincode, Constant.UserID),

                    new BillingAddress(streetName, address1, address2, landmark,
                            pincode, Constant.UserID), this);

        } else {
            mView.showToast("please enter all the field");
        }

    }

    @Override
    public void doAddDiffrentAdress(String streetName, String address1, String address2, String landmark, String pincode,
                                    String streetNameBilling, String address1Billing, String address2Billing,
                                    String landmarkBilling, String pincodeBilling) {
        if (!streetName.equals("") && !address1.equals("") &&
                !address2.equals("") && !landmark.equals("") && !streetNameBilling.equals("") && !address1Billing.equals("") &&
                !address2Billing.equals("") && !landmarkBilling.equals("") && !pincode.equals("") && !pincodeBilling.equals("")) {
            placeOrderFragmentInteractor.doAddAdress(new ShippingAddress(streetName, address1, address2, landmark, pincode, Constant.UserID),
                    new BillingAddress(streetNameBilling, address1Billing, address2Billing, landmarkBilling, pincodeBilling, Constant.UserID), this);

        } else {
            mView.showToast("please enter all the field");
        }
    }

    @Override
    public void doPlaceOrder(int shippingId, int billingId, int userCartID, int userId, String isOrderDeliver, String remark, String cod) {

        if (shippingId != 0 && billingId != 0) {
            placeOrderFragmentInteractor.doPlaceOrder(new RequestPlaceOrder(shippingId, billingId, userCartID, userId, isOrderDeliver, remark),cod, this);
        } else {
            mView.showToast("Cannot place order now!!");
        }


    }

    @Override
    public void onFailure(String message) {
        mView.hideProgressDialog();
        mView.showToast(Constant.APIError);
    }

    @Override
    public void getAddressListSuccess(ResponseShipBillAddress response) {
        mView.hideProgressDialog();
        if (response != null) {
            if (response.getCode() != null) {
                if (response.getCode() == 200) {
                    mView.getAddressSuccess(response);
                }
            }

        }
    }


    @Override
    public void failureAddressList(String message) {

        mView.hideProgressDialog();
        mView.showToast(message);

    }

    @Override
    public void onFailure() {
        mView.hideProgressDialog();
        mView.showToast(Constant.APIError);
    }

    @Override
    public void addAddressListSuccess(Response<ResponseAddAddress> response) {

        mView.hideProgressDialog();
        if (response != null) {

            if (response.body().getCode() != null)
                if (response.body().getCode().equalsIgnoreCase("200")) {
                    mView.getAddress(response.body());
                }

        }
    }



    @Override
    public void addPlaceOrderSuccess(Response<ResponsePlaceOrderSuccess> response, String cod) {
        mView.hideProgressDialog();
        if (response != null) {

            if (response.body().getCode() != null)
                if (response.body().getCode().equalsIgnoreCase("200")) {
                    mView.getPlaceOrder(response.body(),cod);
                }

        }
    }

    @Override
    public void failurePlaceOrder(Response<ResponsePlaceOrderSuccess> response) {
        mView.hideProgressDialog();
        mView.showToast(Constant.APIError);
    }



    @Override
    public void failureAddAddressList(Response<ResponseAddAddress> response) {
        mView.hideProgressDialog();
        mView.showToast(response.body().getMsg());
    }
}
