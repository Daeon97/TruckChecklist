package com.engelsimmanuel.truckchecklist.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsManager {
    private Context context;
    private static SharedPrefsManager instance;
    private static final String IS_LOGGED_IN = "isloggedin";
    private static final String KEY_IS_LOGGED_IN = "keyisloggedin";

    private SharedPrefsManager(Context context) {
        this.context = context;
    }

    public static SharedPrefsManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefsManager(context);
        }
        return instance;
    }

    public void setIsLoggedIn(Boolean state) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(IS_LOGGED_IN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, state);
        editor.apply();
    }

    public Boolean getIsLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(IS_LOGGED_IN, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }
}
