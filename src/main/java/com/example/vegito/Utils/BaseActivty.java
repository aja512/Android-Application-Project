package com.example.vegito.Utils;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.vegito.R;

public class BaseActivty extends AppCompatActivity {

    //region ProgressDialog
    ProgressDialog mProgressDialog;

    public void showProgressDialog() {

        try {
            mProgressDialog = new ProgressDialog(this, R.style.MyAlertDialogStyle);
            mProgressDialog.setCancelable(true);//you can cancel it by pressing back button
            mProgressDialog.setMessage("Please wait ...");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.show();
        } catch (Exception e) {
        }

    }


    public void hideProgressDialog() {
        try {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        } catch (Exception e) {
        }
    }


    public void showToast(String key) {
        Toast.makeText(this, getStringResourceByName(key), Toast.LENGTH_LONG).show();

    }


    private String getStringResourceByName(String aString) {
        try {
            String packageName = getPackageName();
            int resId = getResources().getIdentifier(aString, "string", packageName);
            return getString(resId);
        } catch (Exception ex) {
            return aString;
        }
    }
}
