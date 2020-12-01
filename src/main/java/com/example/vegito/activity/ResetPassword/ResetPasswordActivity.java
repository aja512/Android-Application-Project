package com.example.vegito.activity.ResetPassword;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vegito.R;
import com.example.vegito.Utils.BaseActivty;
import com.example.vegito.Utils.ScreenUtils;
import com.example.vegito.Utils.StatusBar;
import com.example.vegito.activity.LoginScreen.LoginContractor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordActivity extends BaseActivty implements ResetPasswordContract.iResetPasswordView {

    Context context;
    @BindView(R.id.editTextUsername)
    EditText editTextUsername;

    @BindView(R.id.editTextpasswrd)
    EditText editTextpasswrd;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    ResetPasswordContract.iResetPassworPresenter iResetPassworPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_activity);
        context =this;
        ScreenUtils.fullScreen(this);
        StatusBar.MatchStatusBGEqualsAndAboveLollipop(this, context);
        ButterKnife.bind(this);

        iResetPassworPresenter= new ResetPasswordPresenter(this);

    }

    @OnClick(R.id.btnLogin)
    void ButtonClick(){

        iResetPassworPresenter.validateCredentials
                (editTextUsername.getText().toString().trim(), editTextpasswrd.getText().toString().trim());
    }


    @Override
    public void setUserNameError(String empty) {
        showToast("Please enter UserID");
    }

    @Override
    public void setPasswordError(String empty) {
        showToast("Please enter new Password");
    }
}
