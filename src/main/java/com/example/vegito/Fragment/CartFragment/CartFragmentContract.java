package com.example.vegito.Fragment.CartFragment;

import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Models.DeleteCart.RequestDeleteCart;
import com.example.vegito.Models.DeleteCart.ResponseDeleteCart;
import com.example.vegito.Models.UpdateCart.RequestUpdateCart;
import com.example.vegito.Models.UpdateCart.ResponseUpdateCart;

import retrofit2.Response;

public interface CartFragmentContract {


    interface cartFragmentView {

        void showToast(String key);

        void showProgressDialog();

        void hideProgressDialog();

        void getCartListSuccess(ResponseGetCartList response);

        void getUpdateCartListSuccess(ResponseUpdateCart responseUpdateCart);

        void getDeleteSuccess(Response<ResponseDeleteCart> response);
    }


    interface cartFragmentPresenter {


        void getCartList(Integer userId);

        void updateCartList(RequestUpdateCart requestUpdateCart);

        void deleteProductFromCart(RequestDeleteCart requestDeleteCart);
    }


    interface cartFragmentInteractor {


        void getCartList(Integer userId, onGetCartListFinishedListener listener);

        void doUpdateCartList(RequestUpdateCart requestUpdateCart, doUpdateCartListListener listener);

        void doDeleteCartItem(RequestDeleteCart requestDeleteCart, doDeleteCartItemListener listener);

        interface onGetCartListFinishedListener {
            void onGetCartlistSuccess(ResponseGetCartList responseCreateCart);

            void onFailure(String message);
        }

        interface doUpdateCartListListener {
            void onUpdateCartlistSuccess(ResponseUpdateCart responseUpdateCart);

            void onFailure(String message);
        }

        interface doDeleteCartItemListener {

            void onFailure(String message);

            void getDeleteListSuccess(Response<ResponseDeleteCart> response);

            void failureDelete(Response<ResponseDeleteCart> response);
        }
    }
}
