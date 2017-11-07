package com.example.osiris.testapp;

import android.app.Application;

/**
 * Created by Osiris on 3/11/2017.
 */

public class FireApp extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        // Firebase.setAndroidContext(this);
    }
}
