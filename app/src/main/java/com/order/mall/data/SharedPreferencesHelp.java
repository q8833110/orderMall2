package com.order.mall.data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

public class SharedPreferencesHelp {
    private static final String TAG = SharedPreferencesHelp.class.getSimpleName();
    private static final String PREF_FILE_NAME = "amigo";
    private Gson gson ;

    private SharedPreferences sharedPreferences ;
    @Inject
    public SharedPreferencesHelp(Context context , Gson gson){
        Application application = (Application) context.getApplicationContext();
        sharedPreferences =  application.getSharedPreferences(PREF_FILE_NAME , Context.MODE_PRIVATE);
        this.gson = gson ;
    }

    private void putString (String key , String value){
        sharedPreferences.edit().putString(key  , value).apply();
    }

    private String getString(String key){
        return sharedPreferences.getString(key , "");
    }

    private void putInt(String key , int value){
        sharedPreferences.edit().putInt(key , value).apply();
    }

    private int getInt(String key){
        return sharedPreferences.getInt(key , -1);
    }

    private void putBoolean(String key , Boolean value){
        sharedPreferences.edit().putBoolean(key , value).apply();
    }

    private Boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key , false);
    }

    private void putLong(String key , Long value){
        sharedPreferences.edit().putLong(key , value).apply();
    }

    private Long getLong(String key){
        return sharedPreferences.getLong(key , -1L);
    }

    private void putFloat(String key , float value){
        sharedPreferences.edit().putFloat(key , value).apply();
    }

    private float getFloat(String key){
        return sharedPreferences.getFloat(key ,-1f);
    }


}
