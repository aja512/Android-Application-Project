package com.example.vegito.Utils;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.vegito.R;

public class BaseFragment extends Fragment {

    //region ProgressDialog
    ProgressDialog mProgressDialog;

    public void showProgressDialog() {

        try {
            mProgressDialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
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
        Toast.makeText(getActivity(), getStringResourceByName(key), Toast.LENGTH_LONG).show();

    }



    private String getStringResourceByName(String aString) {
        try {
            String packageName = getActivity().getPackageName();
            int resId = getResources().getIdentifier(aString, "string", packageName);
            return getString(resId);
        } catch (Exception ex) {
            return aString;
        }
    }

}
