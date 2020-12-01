package com.example.vegito.Fragment.CartFragment;

import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Models.DeleteCart.RequestDeleteCart;
import com.example.vegito.Models.DeleteCart.ResponseDeleteCart;
import com.example.vegito.Models.UpdateCart.RequestUpdateCart;
import com.example.vegito.Models.UpdateCart.ResponseUpdateCart;
import com.example.vegito.Utils.Constant;

import retrofit2.Response;

public class CartFragmentPresenter implements CartFragmentContract.cartFragmentPresenter,
        CartFragmentContract.cartFragmentInteractor.onGetCartListFinishedListener,
        CartFragmentContract.cartFragmentInteractor.doUpdateCartListListener,
        CartFragmentContract.cartFragmentInteractor.doDeleteCartItemListener {


    CartFragmentContract.cartFragmentView cartFragmentView;
    CartFragmentContract.cartFragmentInteractor cartFragmentInteractor;

    public CartFragmentPresenter(CartFragmentContract.cartFragmentView mView) {
        cartFragmentView = mView;
        cartFragmentInteractor = new CartFragmentInteractor();
    }


    @Override
    public void getCartList(Integer userId) {
        cartFragmentView.showProgressDialog();
        cartFragmentInteractor.getCartList(userId, this);
    }

    @Override
    public void updateCartList(RequestUpdateCart requestUpdateCart) {
        cartFragmentView.showProgressDialog();
        cartFragmentInteractor.doUpdateCartList(requestUpdateCart, this);
    }

    @Override
    public void deleteProductFromCart(RequestDeleteCart requestDeleteCart) {
        cartFragmentView.showProgressDialog();
        cartFragmentInteractor.doDeleteCartItem(requestDeleteCart, this);
    }

    @Override
    public void onGetCartlistSuccess(ResponseGetCartList response) {
        cartFragmentView.hideProgressDialog();
        if (response != null) {
            if (response.getCode() != null) {
                if (response.getCode().equalsIgnoreCase("200")) {
                    cartFragmentView.getCartListSuccess(response);
                }
            }
        }
    }


    @Override
    public void onUpdateCartlistSuccess(ResponseUpdateCart responseUpdateCart) {
        cartFragmentView.hideProgressDialog();
        if (responseUpdateCart != null) {
            if (responseUpdateCart.getCode() != null) {
                if (responseUpdateCart.getCode().equalsIgnoreCase("200")) {
                    cartFragmentView.getUpdateCartListSuccess(responseUpdateCart);
                } else {
                    cartFragmentView.showToast(responseUpdateCart.getMsg());
                }
            }
        }
    }

    @Override
    public void onFailure(String message) {
        cartFragmentView.hideProgressDialog();
        cartFragmentView.showToast(Constant.APIError);
    }

    @Override
    public void getDeleteListSuccess(Response<ResponseDeleteCart> response) {
        cartFragmentView.hideProgressDialog();
        if (response != null) {
            if (response.body() != null) {
                if(response.body().getCode().equalsIgnoreCase("200")){
                    cartFragmentView.getDeleteSuccess(response);
                }

            }
        }
    }

    @Override
    public void failureDelete(Response<ResponseDeleteCart> response) {
        cartFragmentView.hideProgressDialog();
        if (response != null) {
            if (response.body() != null) {
                cartFragmentView.showToast(response.body().getMsg());
            }
        }

    }
}
