package org.islamright.tebian.util;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.islamright.tebian.App;

public class Preferences {


    private static Preferences ourInstance = new Preferences();

    private Preferences() {
    }

    public static Preferences getInstance() {
        return ourInstance;
    }

    private SharedPreferences.Editor editor() {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance()).edit();
    }

    private SharedPreferences preferences() {
        return PreferenceManager.getDefaultSharedPreferences(App.getInstance());
    }

    public boolean hasKey(String key) {
        return preferences().contains(key);
    }

    public void removeKey(String key) {
        editor().remove(key).commit();
    }

    public void putIString(String key, String value) {
        editor().putString(key, value).commit();
    }

    public void putInt(String key, int value) {
        editor().putInt(key, value).commit();
    }

    public void putBoolean(String key, boolean value) {
        editor().putBoolean(key, value).commit();
    }

    public void putFloat(String key, float value) {
        editor().putFloat(key, value).commit();
    }


    public String getString(String key, String defaultValue) {
        return preferences().getString(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return preferences().getInt(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return preferences().getBoolean(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return preferences().getFloat(key, defaultValue);
    }

}
