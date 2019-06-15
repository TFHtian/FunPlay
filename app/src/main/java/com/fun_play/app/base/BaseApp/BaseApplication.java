package com.fun_play.app.base.BaseApp;

import android.support.multidex.MultiDexApplication;

public class BaseApplication extends MultiDexApplication {

    private static BaseApplication mApplication;

    public static BaseApplication getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

    }

}

