package com.example.vegito.activity.NavigationDrawerActivity;

import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Models.CreateCart.RequestCreateCart;
import com.example.vegito.Models.CreateCart.ResponseCreateCart;
import com.example.vegito.Utils.Constant;

public class HomePagePresenter implements HomePageContract.iHomePagePresenter,
        HomePageContract.iHomePageInteractor.onCreateCartFinishedListener,
HomePageContract.iHomePageInteractor.onGetCartListFinishedListener{

    HomePageContract.iHomePageView mView;
    HomePageContract.iHomePageInteractor homePageInteractor;


    public HomePagePresenter(HomePageContract.iHomePageView homePageView) {
        mView = homePageView;
        homePageInteractor = new HomePageInteractor();
    }

    @Override
    public void creatCart(Integer userId, String timeStamp) {
        mView.showProgressDialog();
        homePageInteractor.creatCart(new RequestCreateCart(userId, timeStamp), this);

    }

    @Override
    public void getCartList(Integer userID) {
        mView.showProgressDialog();
        homePageInteractor.getCartList(userID, this);
    }


    @Override
    public void onCartCreateSuccess(ResponseCreateCart responseCreateCart) {
        mView.hideProgressDialog();
        if (responseCreateCart != null) {
            if (responseCreateCart.getCode() != null) {
                if (responseCreateCart.getCode().equalsIgnoreCase("200")) {
                    mView.getCreateCartSuccess(responseCreateCart.getCartId());
                }
            }
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
        mView.showToast(Constant.APIError);
    }
}
