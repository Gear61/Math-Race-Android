package com.randomappsinc.mathrace.Persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.randomappsinc.mathrace.Utils.MyApplication;

/**
 * Created by alexanderchiou on 12/11/15.
 */
public class PreferencesManager {
    private SharedPreferences prefs;

    private static final String USER_TAG_KEY = "userTag";
    private static PreferencesManager instance;

    public static PreferencesManager get() {
        if (instance == null) {
            instance = getSync();
        }
        return instance;
    }

    private static synchronized PreferencesManager getSync() {
        if (instance == null) {
            instance = new PreferencesManager();
        }
        return instance;
    }

    private PreferencesManager() {
        Context context = MyApplication.get().getApplicationContext();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getUserTag() {
        return prefs.getString(USER_TAG_KEY, "");
    }

    public void setFirstTimeUser(String userTag) {
        prefs.edit().putString(USER_TAG_KEY, userTag).apply();
    }
}