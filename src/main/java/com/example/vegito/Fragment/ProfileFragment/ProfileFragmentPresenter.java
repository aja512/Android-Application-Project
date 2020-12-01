package com.example.vegito.Fragment.ProfileFragment;

import android.content.Context;

import com.example.vegito.Models.AddToCart.RequestAddToCart;
import com.example.vegito.Models.AddToCart.ResponseAddToCart;
import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Models.DeleteCart.RequestDeleteCart;
import com.example.vegito.Models.DeleteCart.ResponseDeleteCart;
import com.example.vegito.Models.ProductList.ResponseProductList;
import com.example.vegito.Utils.Constant;
import com.example.vegito.Utils.InternetStatus;

import retrofit2.Response;

public class ProfileFragmentPresenter implements ProfileFragmentContract.iprofileFragmentPresenter,
        ProfileFragmentContract.iprofileFragmentInteractor.onGetProductListFinisherListener,
        ProfileFragmentContract.iprofileFragmentInteractor.onAddProductToCartFinisherListener,
        ProfileFragmentContract.iprofileFragmentInteractor.onDeleteProductListFinisherListener,
        ProfileFragmentContract.iprofileFragmentInteractor.onGetCartListFinishedListener {

    ProfileFragmentContract.iprofileFragmentView mView;
    ProfileFragmentContract.iprofileFragmentInteractor iprofileFragmentInteractor;

    public ProfileFragmentPresenter(ProfileFragmentContract.iprofileFragmentView mView) {
        this.mView = mView;
        iprofileFragmentInteractor = new ProfileFragmentInteractor();
    }


    @Override
    public void getProductList(int catId, Context context) {

        mView.showProgressDialog();
        if (InternetStatus.checkConnection()) {
            iprofileFragmentInteractor.doGetProdcutListRequest(catId, this, context);
        } else {
            mView.hideProgressDialog();
            mView.showToast(Constant.NoInternet);
        }
    }

    @Override
    public void addProductToCart(RequestAddToCart requestAddToCart) {
        mView.showProgressDialog();
        if (InternetStatus.checkConnection()) {
            iprofileFragmentInteractor.doAddProdcutToCartRequest(requestAddToCart, this);
        } else {
            mView.hideProgressDialog();
            mView.showToast(Constant.NoInternet);
        }
    }

    @Override
    public void deleteProductFromCart(RequestDeleteCart requestDeleteCart) {
        mView.showProgressDialog();
        if (InternetStatus.checkConnection()) {
            iprofileFragmentInteractor.doDeleteProductFromCart(requestDeleteCart, this);
        } else {
            mView.hideProgressDialog();
            mView.showToast(Constant.NoInternet);
        }
    }

    @Override
    public void getCartList(Integer userID) {
        mView.showProgressDialog();
        iprofileFragmentInteractor.getCartList(userID, this);
    }


    @Override
    public void getProductListData(Response<ResponseProductList> response, Context context) {
        mView.hideProgressDialog();
        if (response != null) {
            mView.getResponseProductList(response, context);
        }
    }

    @Override
    public void Failure() {
        mView.hideProgressDialog();
        mView.showToast(Constant.APIError);

    }

    @Override
    public void getAddedProductmessage(Response<ResponseAddToCart> response) {
        mView.hideProgressDialog();
        if (response != null) {
            mView.getAddedResponse(response);
            // mView.showToast(response.body().getMsg());
        }
    }


    @Override
    public void getDeleteListSuccess(Response<ResponseDeleteCart> response) {
        mView.hideProgressDialog();
        if (response != null) {
            mView.showToast(response.body().getMsg());
        }
    }


    @Override
    public void Failures(String message) {
        mView.hideProgressDialog();
        mView.showToast(Constant.APIError);
    }

    @Override
    public void Failure(Response<ResponseAddToCart> response) {
        mView.hideProgressDialog();
        if (response != null) {
            mView.showToast(response.body().getMsg());
        }
    }

    @Override
    public void failureDelete(Response<ResponseDeleteCart> response) {
        mView.hideProgressDialog();
        if (response != null) {
            mView.showToast(response.body().getMsg());
        }
    }


    @Override
    public void onGetCartlistSuccess(ResponseGetCartList response) {
        mView.hideProgressDialog();
        if (response != null) {
            if (response.getCode() != null) {
                if (response.getCode().equalsIgnoreCase("200")) {
                    mView.getCartListSuccess(response);
                }
            }
        }
    }

    @Override
    public void onFailure(String message) {
        mView.hideProgressDialog();
        mView.showToast(message);
    }
}
