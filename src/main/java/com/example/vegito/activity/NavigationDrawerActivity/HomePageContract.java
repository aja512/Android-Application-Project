package com.example.vegito.activity.NavigationDrawerActivity;

import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Models.CreateCart.RequestCreateCart;
import com.example.vegito.Models.CreateCart.ResponseCreateCart;

public interface HomePageContract {

    interface iHomePageView {

        void showToast(String key);

        void showProgressDialog();

        void hideProgressDialog();

        void getCreateCartSuccess(Integer cartId);


        void getCartListSuccess(ResponseGetCartList response);
    }

    interface iHomePagePresenter {

        void creatCart(Integer userId, String timeStamp);


        void getCartList(Integer userID);
    }


    interface iHomePageInteractor {
        void creatCart(RequestCreateCart requestCreateCart, onCreateCartFinishedListener listener);

        void getCartList(Integer userID, onGetCartListFinishedListener listFinishedListener);


        interface onGetCartListFinishedListener {
            void onGetCartlistSuccess(ResponseGetCartList responseCreateCart);

            void onFailure(String message);
        }

        interface onCreateCartFinishedListener {
            void onCartCreateSuccess(ResponseCreateCart responseCreateCart);

            void onFailure(String message);
        }


    }
}
