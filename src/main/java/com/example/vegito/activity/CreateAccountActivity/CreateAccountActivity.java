package com.example.vegito.activity.CreateAccountActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.vegito.Models.CreateAccount.ResponseCreateAccount;
import com.example.vegito.Models.CreateAccount.ResultCreateAccount;
import com.example.vegito.Models.LoginModel.Result;
import com.example.vegito.R;
import com.example.vegito.Room.DatabaseClient;
import com.example.vegito.Utils.BaseActivty;
import com.example.vegito.Utils.ScreenUtils;
import com.example.vegito.Utils.StatusBar;
import com.example.vegito.activity.NavigationDrawerActivity.HomePage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import retrofit2.Response;

public class CreateAccountActivity extends BaseActivty implements CreateAccountContact.iCreateAccountView {

    Context context;

    @BindView(R.id.editTextUser)
    EditText editTextUser;

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;

    @BindView(R.id.editTextMobile)
    EditText editTextMobile;


    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    @BindView(R.id.radioGroup)
    RadioGroup radioSelectiongroup;

    @BindView(R.id.radioButton1)
    RadioButton radioButton1;

    @BindView(R.id.radioButton2)
    RadioButton radioButton2;

    @BindView(R.id.roleSpinner)
    Spinner spinnerRole;

    @BindView(R.id.btnSignUp)
    Button btnSignUp;

    int radioButtonSelectedId, selectedSpinner;
    String selectedRadioButton, item;
    RadioButton radioDesignationButton;

    List listRole = new ArrayList();

    CreateAccountContact.iCreateAccountPresenter mSignupPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtils.fullScreen(this);
        setContentView(R.layout.activity_signup);
        context = this;

        StatusBar.MatchStatusBGEqualsAndAboveLollipop(this, context);
        ButterKnife.bind(this);
        listRole.clear();
        listRole.add("User");
        listRole.add("Seller");

        getSpinnerValue();

        mSignupPresenter = new CreateAccountPresenter(this);

    }

    private void getSpinnerValue() {

        listRole.add(0, "Select");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, R.layout.spinneritem, listRole);

        spinnerRole.setAdapter(dataAdapter);

    }

    @OnItemSelected(R.id.roleSpinner)
    public void judgeSpinnerItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = (position > 0) ? parent.getItemAtPosition(position).toString() : "";
    }

    @OnClick({R.id.radioButton1, R.id.radioButton2})
    public void onRadioButtonClicked(RadioButton radioButton) {
        // Is the button now checked?
        boolean checked = radioButton.isChecked();

        // Check which radio button was clicked
        switch (radioButton.getId()) {
            case R.id.radioButton1:
                if (checked) {

                }
                break;
            case R.id.radioButton2:
                if (checked) {

                }
                break;
        }
    }

    @OnClick(R.id.btnSignUp)
    void Signup() {
        radioButtonSelectedId = radioSelectiongroup.getCheckedRadioButtonId();
        radioDesignationButton = (RadioButton) findViewById(radioButtonSelectedId);
        if (emptyFields()) {
            if (!(radioSelectiongroup.getCheckedRadioButtonId() == -1)) {
                if (item.equalsIgnoreCase("user")) {
                    selectedSpinner = 1;
                } else {
                    selectedSpinner = 2;
                }
                if (radioDesignationButton.getText().toString().equals("Male")) {
                    selectedRadioButton = "M";
                } else {
                    selectedRadioButton = "F";
                }
                mSignupPresenter.createAccount(editTextUser.getText().toString().trim(),
                        editTextEmail.getText().toString().trim(),
                        editTextMobile.getText().toString().trim(),
                        selectedRadioButton, selectedSpinner,
                        editTextPassword.getText().toString().trim());
            } else {
                showToast("Please select Gender");
            }
        }
    }


    private boolean emptyFields() {
        boolean tempvar = true;
        if (TextUtils.isEmpty(editTextEmail.getText().toString().trim()) &&
                TextUtils.isEmpty(editTextUser.getText().toString().trim()) &&
                TextUtils.isEmpty(editTextPassword.getText().toString().trim())) {
            showToast("Please enter all the Fields");
            tempvar = false;
        } else if (item.equalsIgnoreCase("")) {
            showToast("Please select Role");
            tempvar = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString()).matches()) {
            showToast("Please enter a valid email address");
            tempvar = false;
        }
        return tempvar;
    }

    @Override
    public void getCreatedAccountDetails(Response<ResponseCreateAccount> response) {
        if (response.isSuccessful()) {

            if (response.body() != null) {
                showToast(response.message());
                saveDatabase(response.body().getResults());
            }

        }

    }

    private void saveDatabase(List<Result> resultCreateAccounts) {

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Result task = new Result();
                task.setUserId(resultCreateAccounts.get(0).getUserId());
                task.setUserName(resultCreateAccounts.get(0).getUserName());
                task.setUserEmail(resultCreateAccounts.get(0).getUserEmail());
                task.setUserMobile(resultCreateAccounts.get(0).getUserMobile());
                task.setUserGender(resultCreateAccounts.get(0).getUserGender());
                task.setUserRoleId(resultCreateAccounts.get(0).getUserRoleId());
                task.setCreatedDatetime(resultCreateAccounts.get(0).getCreatedDatetime());
                task.setUserPassword(resultCreateAccounts.get(0).getUserPassword());
                task.setUserPassword(resultCreateAccounts.get(0).getUserPassword());
                task.setIsUserSubscribed(resultCreateAccounts.get(0).getIsUserSubscribed());

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .accountInfoDao()
                        .insert(task);
                Gson gson = new Gson();
                String s = gson.toJson(task);
                Log.e("InsertedData", s);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(context, HomePage.class));
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }
}

