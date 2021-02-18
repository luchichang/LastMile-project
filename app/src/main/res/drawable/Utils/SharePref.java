package com.example.truckaggregator.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {

    public static String APP_NAME = "Shoes";
    public static String USER_INFO = "user_info";

    public static void saveUserinfo(Activity loginActivity, String s) {
        SharedPreferences sharedPreferences = loginActivity.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(USER_INFO, s).apply();
    }

    public static String getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        return sp.getString(USER_INFO, "0");
    }
}
