package com.example.vegito.activity.MerchantActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.vegito.R;
import com.example.vegito.activity.NavigationDrawerActivity.HomePage;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MerchantActivity extends AppCompatActivity implements PaymentResultListener {

    Checkout checkout = new Checkout();
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        //  checkout.setKeyID("rzp_test_S26BafwxfW990Q");
        // Checkout.preload(getApplicationContext());

        startPayment();
    }

    public void startPayment() {

        final Activity activity = this;
        Checkout checkout = new Checkout();

        checkout.setImage(R.drawable.tomato);
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Vegito");
            options.put("description", "TestPayment");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", "500");
            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e("Error", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        Toast.makeText(this, "Payment successfully done! " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, HomePage.class);
        Log.e("RazorPaymentId", razorpayPaymentID);
        startActivity(intent);

    }

    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment error please try again", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("OnPaymentError", "Exception in onPaymentError", e);
        }
    }
}
