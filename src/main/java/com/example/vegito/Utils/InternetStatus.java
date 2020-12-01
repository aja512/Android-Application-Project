package com.example.vegito.Utils;

//Remember to add in manifest
//<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetStatus {
    //returns boolean true if a network connection is available.

    public static boolean checkConnection() {

        ConnectivityManager mConnectivityManager = (ConnectivityManager) MyApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo ni = mConnectivityManager.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else {
            return true;
        }
    }
}

