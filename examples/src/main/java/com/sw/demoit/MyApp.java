package com.sw.demoit;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.android.arouter.launcher.ARouter;
import com.rong360.debug.view.DebugView;
import com.rong360.debug.view.DebugViewWrapper;

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
        initDebugView();
    }

    private boolean isDebug() {
        return true;
    }

    private void initDebugView() {
        DebugViewWrapper.getInstance().init(new DebugView.Builder(this).build());
        DebugViewWrapper.getInstance().show();
    }
}
