package com.example.vegito.Fragment.ProductDetailFragment;

import com.example.vegito.Models.AddToCart.RequestAddToCart;
import com.example.vegito.Models.AddToCart.ResponseAddToCart;
import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Utils.Constant;
import com.example.vegito.Utils.InternetStatus;

import retrofit2.Response;

public class ProductDetailsPresenter implements ProductFragmentContract.productFragmentPresenter,
        ProductFragmentContract.productFragmentInteractor.onAddProductToCartFinisherListener,
        ProductFragmentContract.productFragmentInteractor.onGetCartListFinishedListener {

    ProductFragmentContract.productFragmentView productFragmentView;
    ProductFragmentContract.productFragmentInteractor productFragmentInteractor;


    public ProductDetailsPresenter(ProductFragmentContract.productFragmentView productFragmentView) {
        this.productFragmentView = productFragmentView;
        productFragmentInteractor = new ProductDetailInteractor();
    }

    @Override
    public void addProductToCart(RequestAddToCart requestAddToCart) {

        productFragmentView.showProgressDialog();
        if (InternetStatus.checkConnection()) {
            productFragmentInteractor.doAddProdcutToCartRequest(requestAddToCart, this);
        } else {
            productFragmentView.hideProgressDialog();
            productFragmentView.showToast(Constant.NoInternet);
        }

    }

    @Override
    public void getCartList(Integer userID) {
        productFragmentView.showProgressDialog();
        productFragmentInteractor.getCartList(userID, this);
    }

    @Override
    public void getAddedProductmessage(Response<ResponseAddToCart> response) {
        productFragmentView.hideProgressDialog();
        if (response != null) {
            productFragmentView.getSuccessResult(response);
        }
    }

    @Override
    public void Failure(Response<ResponseAddToCart> response) {
        productFragmentView.hideProgressDialog();
        if (response != null) {
            productFragmentView.showToast(response.body().getMsg());
        }
    }

    @Override
    public void Failures(String message) {
        productFragmentView.hideProgressDialog();
        productFragmentView.showToast(Constant.APIError);
    }

    @Override
    public void onGetCartlistSuccess(ResponseGetCartList response) {
        productFragmentView.hideProgressDialog();
        if (response != null) {
            if (response.getCode() != null) {
                if (response.getCode().equalsIgnoreCase("200")) {
                    productFragmentView.getCartListSuccess(response);
                }
            }
        }
    }

    @Override
    public void onFailure(String message) {
        productFragmentView.hideProgressDialog();
        productFragmentView.showToast(message);
    }
}
