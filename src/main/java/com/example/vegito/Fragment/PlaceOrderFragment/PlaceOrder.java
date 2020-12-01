package com.example.vegito.Fragment.PlaceOrderFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.vegito.Adapter.AddressListAdapter;
import com.example.vegito.Models.GetAddresss.BaseAddressResponse;
import com.example.vegito.Models.GetAddresss.ResponseShipBillAddress;
import com.example.vegito.Models.PlaceOrder.ResponsePlaceOrderSuccess;
import com.example.vegito.Models.ShippingBillingAddress.ResponseAddAddress;
import com.example.vegito.R;
import com.example.vegito.Utils.BaseFragment;
import com.example.vegito.Utils.Constant;
import com.example.vegito.Utils.SessionManager;
import com.example.vegito.activity.MerchantActivity.MerchantActivity;
import com.example.vegito.activity.NavigationDrawerActivity.HomePage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlaceOrder extends BaseFragment implements PlaceOrderContractor.placeOrderFragmentView {

    @BindView(R.id.shippingAddress)
    RecyclerView shippingAddress;
    @BindView(R.id.addAddress)
    Button addAddress;
    @BindView(R.id.addPlaceOrder)
    Button addPlaceOrder;
    @BindView(R.id.radioGroup)
    RadioGroup radioSelectiongroup;
    @BindView(R.id.radioButton1)
    RadioButton radioButton1;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;
    @BindView(R.id.radioButton3)
    RadioButton radioButton3;
    PlaceOrderContractor.placeOrderFragmentPresenter placeOrderFragmentPresenter;
    Context context;
    AlertDialog alertDialog;
    boolean checked;
    int shippingId, billingId;
    Bundle bundle;
    String totalvalue;
    int radioButtonSelectedId;
    RadioButton radioDesignationButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_placeorder,
                container, false);
        context = getActivity();
        ButterKnife.bind(this, view);
        placeOrderFragmentPresenter = new PlaceOrderPresenter(this);
        placeOrderFragmentPresenter.getUserAddress(Constant.UserID);
        if (SessionManager.getStringFromPreferences(context, getResources().getString(R.string.userSubscribed)) != null) {
            if (SessionManager.getStringFromPreferences(context, getResources().getString(R.string.userSubscribed)).equalsIgnoreCase("Y")) {
                radioButton3.setVisibility(View.VISIBLE);
            } else {
                radioButton3.setVisibility(View.GONE);
            }
        }
        return view;
    }

    @OnClick({R.id.radioButton1, R.id.radioButton2, R.id.radioButton3})
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
            case R.id.radioButton3:
                if (checked) {

                }
                break;
        }
    }

    @OnClick(R.id.addAddress)
    void OnClickShippingAddress() {

        LayoutInflater inflater = LayoutInflater.from(context);
        alertDialog = new AlertDialog.Builder(context).create();
        final View view = inflater.inflate(R.layout.addaddressdialog, null);
        //starttime
        EditText streetName = view.findViewById(R.id.editTextstreetname);
        EditText add1 = view.findViewById(R.id.editTextadd1);
        EditText add2 = view.findViewById(R.id.editTextadd2);
        EditText landmark = view.findViewById(R.id.editTextlandmark);
        EditText streetNameBilling = view.findViewById(R.id.editTextstreet_billing);
        EditText add1Billing = view.findViewById(R.id.editTextadd1_billing);
        EditText add2Billing = view.findViewById(R.id.editTextadd2_billing);
        EditText landmarkBilling = view.findViewById(R.id.editTextlandmark_billing);
        EditText pincode = view.findViewById(R.id.editTextpincode);
        EditText pincodeBilling = view.findViewById(R.id.editTextpincode_billing);
        CheckBox biiling_CB = view.findViewById(R.id.cb_billing);
        Button addAdress = view.findViewById(R.id.btnAddAdress);
        LinearLayout llBilling = view.findViewById(R.id.ll_billing);

        biiling_CB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked = ((CheckBox) v).isChecked();
                if (checked) {
                    llBilling.setVisibility(View.GONE);
                } else {
                    llBilling.setVisibility(View.VISIBLE);
                }
            }
        });

        addAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checked) {
                    placeOrderFragmentPresenter.doAddAdress(streetName.getText().toString().trim(), add1.getText().toString().trim(),
                            add2.getText().toString().trim(), landmark.getText().toString().trim(), pincode.getText().toString().trim());
                } else {
                    placeOrderFragmentPresenter.doAddDiffrentAdress(streetName.getText().toString().trim(), add1.getText().toString().trim(),
                            add2.getText().toString().trim(), landmark.getText().toString().trim(), pincode.getText().toString().trim(),
                            streetNameBilling.getText().toString().trim(), add1Billing.getText().toString().trim(),
                            add2Billing.getText().toString().trim(), landmarkBilling.getText().toString().trim(), pincodeBilling.getText().toString().trim());
                }
            }
        });


        alertDialog.setView(view);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.show();
    }

    @Override
    public void getAddress(ResponseAddAddress response) {
        showToast(response.getMsg());
        shippingId = response.getShippingAddressId();
        billingId = response.getBillingAddressId();
        alertDialog.dismiss();
        placeOrderFragmentPresenter.getUserAddress(Constant.UserID);


    }

    @Override
    public void getAddressSuccess(ResponseShipBillAddress response) {

        List<BaseAddressResponse> lstAddress = new ArrayList<>();
        if (response.getShippingAddress() != null) {
            response.getShippingAddress().setShippingAddress(true);
            lstAddress.add(response.getShippingAddress());
        }
        if (response.getBillingAddress() != null) {
            lstAddress.add(response.getBillingAddress());
        }

        shippingId = response.getShippingAddress().getId();
        billingId = response.getBillingAddress().getId();
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        AddressListAdapter addressListAdapter = new AddressListAdapter(lstAddress, context);
        //horizontalAdapter.setListener(this);
        shippingAddress.setLayoutManager(linearLayoutManager);
        shippingAddress.setAdapter(addressListAdapter);


    }

    @Override
    public void getPlaceOrder(ResponsePlaceOrderSuccess body, String cod) {
        if (body != null) {
            if (body.getCode().equals("200")) {
                if (cod.equals("COD") || cod.equals("Vegito Wallet")) {
                    showToast("Order placed Successfully");
                    Intent in = new Intent(context, HomePage.class);
                    startActivity(in);
                } else {
                    showToast("Order placed Successfully");
                    Intent in = new Intent(context, MerchantActivity.class);
                    startActivity(in);
                }


            }
        }

    }

    @OnClick(R.id.addPlaceOrder)
    void addPlaceOrder() {
        radioButtonSelectedId = radioSelectiongroup.getCheckedRadioButtonId();
        radioDesignationButton = (RadioButton) getActivity().findViewById(radioButtonSelectedId);
        if (!(radioSelectiongroup.getCheckedRadioButtonId() == -1)) {
            if (radioDesignationButton.getText().toString().equals("COD")) {
                placeOrderFragmentPresenter.doPlaceOrder(shippingId, billingId,
                        Integer.parseInt(SessionManager.getStringFromPreferences(context, getActivity().getResources().getString(R.string.cartID))), Constant.UserID, "N", "ABC", "COD");

            } else if (radioDesignationButton.getText().toString().equals("Vegito Wallet")) {
                placeOrderFragmentPresenter.doPlaceOrder(shippingId, billingId,
                        Integer.parseInt(SessionManager.getStringFromPreferences(context, getActivity().getResources().getString(R.string.cartID))), Constant.UserID, "N", "ABC", "Vegito Wallet");

            } else {
                placeOrderFragmentPresenter.doPlaceOrder(shippingId, billingId,
                        Integer.parseInt(SessionManager.getStringFromPreferences(context, getActivity().getResources().getString(R.string.cartID))), Constant.UserID, "N", "ABC", "Online");

            }
        } else {
            showToast("please select Payment Mode");
        }

    }

}

