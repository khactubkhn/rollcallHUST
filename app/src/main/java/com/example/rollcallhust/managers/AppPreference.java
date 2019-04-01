package com.example.rollcallhust.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.rollcallhust.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public enum  AppPreference {

    INSTANCE;
    private static final String KEY_PROFILE = "user";
    private static final String KEY_TOKEN = "token";

    private SharedPreferences preferences;
    public Gson mGson;
    private User user;
    private String token;

    public static void init(Context context) {
        INSTANCE.preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        INSTANCE.mGson = new GsonBuilder().create();
        INSTANCE.readData();
    }

    private void readData(){
        token = preferences.getString(KEY_TOKEN, null);
        user = mGson.fromJson(preferences.getString(KEY_PROFILE, null), User.class);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user){
        this.user = user;
        preferences.edit().putString(KEY_PROFILE, mGson.toJson(user)).apply();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        preferences.edit().putString(KEY_TOKEN, token).apply();
    }
}

