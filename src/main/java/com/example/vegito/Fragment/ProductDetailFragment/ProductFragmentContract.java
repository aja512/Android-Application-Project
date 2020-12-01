package com.example.vegito.Fragment.ProductDetailFragment;

import com.example.vegito.Models.AddToCart.RequestAddToCart;
import com.example.vegito.Models.AddToCart.ResponseAddToCart;
import com.example.vegito.Models.CartList.ResponseGetCartList;

import retrofit2.Response;

public interface ProductFragmentContract {


    interface productFragmentView{

        void showToast(String key);

        void showProgressDialog();

        void hideProgressDialog();

        void getSuccessResult(Response<ResponseAddToCart> response);

        void getCartListSuccess(ResponseGetCartList response);
    }


    interface productFragmentPresenter{
        void addProductToCart(RequestAddToCart requestAddToCart);

        void getCartList(Integer userID);



    }


    interface productFragmentInteractor{

        void doAddProdcutToCartRequest(RequestAddToCart requestAddToCart, onAddProductToCartFinisherListener listener);

        void getCartList(Integer userID, onGetCartListFinishedListener listener);

        interface onGetCartListFinishedListener {
            void onGetCartlistSuccess(ResponseGetCartList responseCreateCart);
            void onFailure(String message);
        }

        interface  onAddProductToCartFinisherListener{
            void getAddedProductmessage(Response<ResponseAddToCart> response);
            void Failure(Response<ResponseAddToCart> response);
            void Failures(String message);
        }

    }
}
