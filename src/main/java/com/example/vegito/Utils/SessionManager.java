package com.example.vegito.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashSet;

/**
 * Created by Abhishek on 2/16/2017.
 */
public class SessionManager {

    private static final String PREFS_NAME = "AllisonSmithPreference";

    public static String putStringInPreferences(Context context, String value, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
        return value;
    }

    public static boolean putBooleanInPreferences(Context context, boolean value, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
        return value;
    }

    public static boolean getBooleanFromPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        boolean temp = sharedPreferences.getBoolean(key, false);
        return temp;
    }

    public static String getStringFromPreferences(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        String temp = sharedPreferences.getString(key, null);
        return temp;
    }

    public static HashSet<String> getCookies(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);
//        return (HashSet<String>) sharedPreferences.getStringSet("cookies", new HashSet<String>());
        SharedPreferences sharedPreferences = getPrefs(context);
        return (HashSet<String>) sharedPreferences.getStringSet("cookies", new HashSet<String>());
    }

    //    public static boolean setCookies(Context context, HashSet<String> cookies) {
//        SharedPreferences ss = getPrefs(context);
//        Set<String> hs = ss.getStringSet("cookies", new HashSet<String>());
//        Set<String> in = new HashSet<String>(hs);
//        in.add(String.valueOf(hs.size() + 1));
//        // SharedPreferences sss = getSharedPreferences("db", 0); // not needed
//        Log.i("TAG", "2.cookies = " + ss.getStringSet("cookies", new HashSet<String>()));
//        return ss.edit().putStringSet("cookies", cookies).commit(); // brevity
//    }
    public static void setCookies(Context context, HashSet<String> cookies) {
        SharedPreferences sharedPreferences = getPrefs(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.d("TAG", "===" + sharedPreferences.getStringSet("cookies", new HashSet<String>()));
        editor.putStringSet("cookies", cookies).commit();
    }

    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void removeKey(Context context,String key){
        SharedPreferences sharedPreferences = getPrefs(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key).apply();
    }
}
