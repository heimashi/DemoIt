package com.sw.demoit;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by shiwang on 24/10/2017.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    private boolean isDebug() {
        return true;
    }
}
