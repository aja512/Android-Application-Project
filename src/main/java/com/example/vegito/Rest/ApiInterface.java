package com.example.vegito.Rest;

import com.example.vegito.Models.AddToCart.RequestAddToCart;
import com.example.vegito.Models.AddToCart.ResponseAddToCart;
import com.example.vegito.Models.BillingAddressList.ResponseBillingAddressList;
import com.example.vegito.Models.CartList.ResponseGetCartList;
import com.example.vegito.Models.CreateAccount.RequestCreateAccount;
import com.example.vegito.Models.CreateAccount.ResponseCreateAccount;
import com.example.vegito.Models.CreateCart.RequestCreateCart;
import com.example.vegito.Models.CreateCart.ResponseCreateCart;
import com.example.vegito.Models.DeleteCart.RequestDeleteCart;
import com.example.vegito.Models.DeleteCart.ResponseDeleteCart;
import com.example.vegito.Models.GetAddresss.ResponseShipBillAddress;
import com.example.vegito.Models.LoginModel.RequestLogin;
import com.example.vegito.Models.LoginModel.ResponseLogin;
import com.example.vegito.Models.PlaceOrder.RequestPlaceOrder;
import com.example.vegito.Models.PlaceOrder.ResponsePlaceOrder;
import com.example.vegito.Models.PlaceOrder.ResponsePlaceOrderSuccess;
import com.example.vegito.Models.ProductList.ResponseProductList;
import com.example.vegito.Models.ResetPassword.RequestResetPassword;
import com.example.vegito.Models.ResetPassword.ResponseResetPassword;
import com.example.vegito.Models.ResponseCategoryList.ResponseCategoryList;
import com.example.vegito.Models.ShippingBillingAddress.RequestAddShippingBillingAddress;
import com.example.vegito.Models.ShippingBillingAddress.ResponseAddAddress;
import com.example.vegito.Models.UpdateCart.RequestUpdateCart;
import com.example.vegito.Models.UpdateCart.ResponseUpdateCart;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {

    //Login

    @POST("doLogin")
    Call<ResponseLogin> doLogin(@Body RequestLogin requestLoginActivity);

    @POST("doRegister")
    Call<ResponseCreateAccount> doRegister(@Body RequestCreateAccount requestCreateAccount);

    @POST("resetPassword")
    Call<ResponseResetPassword> resetPassword(RequestResetPassword requestResetPassword);

    @GET("getCategoryList")
    Call<ResponseCategoryList> getCategoryList();

    @GET("getProductList")
    Call<ResponseProductList> getProductList(@Query("catId") int catID);

    @GET("getCartList")
    Call<ResponseGetCartList> getCartList(@Query("userId") int userID);

    @POST("createCart")
    Call<ResponseCreateCart> createCart(@Body RequestCreateCart requestAddToCart);

    @POST("addItemsToCart")
    Call<ResponseAddToCart> addItemsToCart(@Body RequestAddToCart requestAddToCart);

    @POST("deleteItemsFromCart")
    Call<ResponseDeleteCart> deleteItemsFromCart(@Body RequestDeleteCart requestDeleteCart);

    @POST("updateCartItems")
    Call<ResponseUpdateCart> updateCartItems(@Body RequestUpdateCart requestUpdateCart);

    @GET("getUserShippingBillingAddress")
    Call<ResponseShipBillAddress> getUserShippingBillingAddress(@Query("user_id") int userID);

    @POST("saveUserAddress")
    Call<ResponseAddAddress> saveUserAddress(@Body RequestAddShippingBillingAddress requestAddShippingBillingAddress);

    @POST("placeOrder")
    Call<ResponsePlaceOrderSuccess> placeOrder(@Body RequestPlaceOrder requestPlaceOrder);
}

