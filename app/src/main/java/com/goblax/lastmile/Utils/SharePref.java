package com.goblax.lastmile.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePref {
    public static final String APP_PREFERENCE = "last_mile";

    public static String FROM_TAB = "from_tab";

    public static void setFromTab(Context context, String s) {
        SharedPreferences sp = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        sp.edit().putString(FROM_TAB, s).apply();
    }

    public static String getFromTab(Context context) {
        SharedPreferences sp = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        return sp.getString(FROM_TAB, "0");
    }
}
