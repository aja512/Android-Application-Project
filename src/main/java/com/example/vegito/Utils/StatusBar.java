package com.example.vegito.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;


import com.example.vegito.R;


public class StatusBar {
    public StatusBar() {
    }

    public static void MatchStatusBGEqualsAndAboveLollipop(Activity act, Context ctx) {
        if (Build.VERSION.SDK_INT >= 21) {
            act.getWindow().addFlags(-2147483648);
            act.getWindow().clearFlags(67108864);
            act.getWindow().setStatusBarColor(ctx.getResources().getColor(R.color.btncolor));
        }

    }
}
