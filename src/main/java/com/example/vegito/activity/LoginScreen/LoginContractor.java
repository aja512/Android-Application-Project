package com.example.vegito.activity.LoginScreen;

import com.example.vegito.Models.CreateCart.RequestCreateCart;
import com.example.vegito.Models.CreateCart.ResponseCreateCart;
import com.example.vegito.Models.LoginModel.RequestLogin;
import com.example.vegito.Models.LoginModel.ResponseLogin;

public interface LoginContractor {

    interface iLoginView{

        void showToast(String key);

        void showProgressDialog();

        void hideProgressDialog();

        void setUserNameError(String msg);

        void setPasswordError(String msg);

        void getLoginSuccess(ResponseLogin responseLogin);


    }

    interface  iLoginPresenter{

        void validateCredentials(String userName, String password);


    }


    interface  iLoginInteractor{

        void doLoginRequest(RequestLogin requestLogin, onLoginFinishedListener listener);

//        void creatCart(RequestCreateCart requestCreateCart, ResponseLogin responseLogin, onCreateCartFinishedListener createCartFinishedListener);

        interface onLoginFinishedListener {
            void onLoginSuccess(ResponseLogin body);
            void onFailure(String message);
        }



    }
}
