package com.zodiacscope.zenith.astroverse.Model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.zodiacscope.zenith.astroverse.Database.Db_MuhratHelper;

import java.io.IOException;

public class Zodiac_MyApp extends Application {
    protected static Db_MuhratHelper db_muhratHelper;
    private static Zodiac_MyApp zodiac_myApp;
    private static SharedPreferences preferences;


    @Override
    public void onCreate() {
        super.onCreate();
        zodiac_myApp = this;
        db_muhratHelper = new Db_MuhratHelper(getApplicationContext());
        createDb();
        if (preferences == null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        }
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public static SharedPreferences getPreference() {
        return preferences;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    private void createDb() {
        try {
            db_muhratHelper.createDataBase();
        } catch (IOException unused) {
            throw new Error("Unable to create database");
        }
    }

    public static Db_MuhratHelper getDbHelper() {
        return db_muhratHelper;
    }
}