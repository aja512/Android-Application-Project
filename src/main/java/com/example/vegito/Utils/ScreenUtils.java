package com.example.vegito.Utils;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 */

public class ScreenUtils {

    public static void fullScreen(Activity activity) {

        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
