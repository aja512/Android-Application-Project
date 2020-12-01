package com.example.vegito.Utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jay on 13/03/2018.
 */
//This is the entry point to the application
//Remember to register it in Manifest in application tag itself.
/*<application
        android:name="util.MyApplication"..........
*/
public class MyApplication extends Application {

    private static Context context;

    SharedPreferences sharedPreferences = null;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();

        sharedPreferences = getApplicationContext().getSharedPreferences("vegito",MODE_PRIVATE);
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }

}