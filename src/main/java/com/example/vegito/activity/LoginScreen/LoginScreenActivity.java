package com.example.vegito.activity.LoginScreen;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vegito.Models.LoginModel.ResponseLogin;
import com.example.vegito.Models.LoginModel.Result;
import com.example.vegito.R;
import com.example.vegito.Room.DatabaseClient;
import com.example.vegito.Utils.BaseActivty;
import com.example.vegito.Utils.ScreenUtils;
import com.example.vegito.Utils.StatusBar;
import com.example.vegito.activity.CreateAccountActivity.CreateAccountActivity;
import com.example.vegito.activity.NavigationDrawerActivity.HomePage;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginScreenActivity extends BaseActivty implements LoginContractor.iLoginView {

    Context context;

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;


    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    @BindView(R.id.txtfrgt)
    TextView txtfrgt;

    @BindView(R.id.createaccount)
    TextView createaccount;

    @BindView(R.id.btnLogin)
    Button btnLogin;

    LoginContractor.iLoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        ScreenUtils.fullScreen(this);
        setContentView(R.layout.activity_login);
        StatusBar.MatchStatusBGEqualsAndAboveLollipop(this, context);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
        String htmlString = "<u>Forgot Password?</u>";
        txtfrgt.setText(Html.fromHtml(htmlString));
        txtfrgt.setTextColor(context.getResources().getColor(R.color.white));

    }

    @OnClick(R.id.btnLogin)
    void onclick() {
        loginPresenter.validateCredentials
                (editTextEmail.getText().toString().trim(), editTextPassword.getText().toString().trim());
    }

    @Override
    public void setUserNameError(String msg) {

    }

    @Override
    public void setPasswordError(String msg) {

    }

    @Override
    public void getLoginSuccess(ResponseLogin responseLogin) {

        if (responseLogin != null) {
            if (responseLogin.getResults() != null) {
                if (responseLogin.getMsg() != null) {
                    if (responseLogin.getMsg().equalsIgnoreCase("successfully login !")) {
                        saveDatabase(responseLogin.getResults());

                    } else {
                        showToast(responseLogin.getMsg());
                    }
                }
            }
        }


    }


    private void saveDatabase(List<Result> results) {

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Result result = new Result();
                result.setUserId(results.get(0).getUserId());
                result.setUserName(results.get(0).getUserName());
                result.setUserEmail(results.get(0).getUserEmail());
                result.setUserMobile(results.get(0).getUserMobile());
                result.setUserGender(results.get(0).getUserGender());
                result.setUserRoleId(results.get(0).getUserRoleId());
                result.setCreatedDatetime(results.get(0).getCreatedDatetime());
                result.setUserPassword(results.get(0).getUserPassword());
                result.setIsUserSubscribed(results.get(0).getIsUserSubscribed());

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .accountInfoDao()
                        .insert(result);
                Gson gson = new Gson();
                String s = gson.toJson(result);
                Log.e("InsertedData", s);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                startActivity(new Intent(context, HomePage.class));
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    @OnClick(R.id.createaccount)
    void onclickCreate() {
        Intent intent = new Intent(context, CreateAccountActivity.class);
        startActivity(intent);
    }
}
