package com.example.kawther_nalis.carearfor60;

import android.app.Application;
import android.content.Context;

/**
 * Created by kawther_nalis on 10/29/2017.
 */

public class G extends Application {

    public static Context context;
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
    }
}
