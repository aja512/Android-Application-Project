package com.example.vegito.activity.SplashScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.vegito.R;
import com.example.vegito.Utils.BaseActivty;
import com.example.vegito.Utils.StatusBar;
import com.example.vegito.activity.LoginScreen.LoginScreenActivity;

public class SplashActivity extends BaseActivty {

    private static int SPLASH_TIME_OUT = 2000;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;
        StatusBar.MatchStatusBGEqualsAndAboveLollipop(this, this.context);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                Intent i = new Intent(context, LoginScreenActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
