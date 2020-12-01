package com.example.vegito.Fragment.ProfileFragment;

import android.content.Context;

import com.example.vegito.Models.AddToCart.RequestAddToCart;
import com.example.vegito.Models.AddToCart.ResponseAddToCart;
import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Models.DeleteCart.RequestDeleteCart;
import com.example.vegito.Models.DeleteCart.ResponseDeleteCart;
import com.example.vegito.Models.ProductList.ResponseProductList;

import retrofit2.Response;

public interface ProfileFragmentContract {

    interface  iprofileFragmentView{

        void showToast(String key);

        void showProgressDialog();

        void hideProgressDialog();

        void getResponseProductList(Response<ResponseProductList> response, Context context);


        void getAddedResponse(Response<ResponseAddToCart> response);

        void getCartListSuccess(ResponseGetCartList response);
    }

    interface  iprofileFragmentPresenter{

        void getProductList(int s, Context context );

        void addProductToCart(RequestAddToCart requestAddToCart);

        void deleteProductFromCart(RequestDeleteCart requestDeleteCart);

        void getCartList(Integer userID);
    }

    interface  iprofileFragmentInteractor{

        void doGetProdcutListRequest(int catId, onGetProductListFinisherListener listFinisherListener, Context context);

        void doAddProdcutToCartRequest(RequestAddToCart requestAddToCart,onAddProductToCartFinisherListener listener);

        void doDeleteProductFromCart(RequestDeleteCart requestDeleteCart, onDeleteProductListFinisherListener listener);

        void getCartList(Integer userId, onGetCartListFinishedListener listener);

        interface  onAddProductToCartFinisherListener{
            void getAddedProductmessage(Response<ResponseAddToCart> response);
            void Failures(String message);
            void Failure(Response<ResponseAddToCart> response);
        }


        interface  onGetProductListFinisherListener{
            void getProductListData(Response<ResponseProductList> response, Context context);
            void Failure();

        }


        interface  onDeleteProductListFinisherListener{
            void getDeleteListSuccess(Response<ResponseDeleteCart> response);
            void Failures(String message);
            void failureDelete(Response<ResponseDeleteCart> response);
        }

        interface onGetCartListFinishedListener {
            void onGetCartlistSuccess(ResponseGetCartList responseCreateCart);

            void onFailure(String message);
        }
    }
}
