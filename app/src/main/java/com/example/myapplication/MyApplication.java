package com.example.myapplication;

import android.app.Application;

public class MyApplication extends Application {
    private static Application mApp;

    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;
    }

    public static Application getApp() {
        return mApp;
    }
}
