package com.sw.demoit;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.rong360.debug.view.DebugViewWrapper;


public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        //initDebugView();
    }

    private boolean isDebug() {
        return true;
    }

    private void initDebugView() {
        DebugViewWrapper.getInstance().init(new DebugViewWrapper.Builder(this));
        DebugViewWrapper.getInstance().show();
    }
}
