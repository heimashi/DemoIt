package com.sw.mvp.models;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by shiwang on 20/10/2017.
 */

public abstract class BaseModel {
    private Handler handler = new Handler(Looper.getMainLooper());

    public void doMockHttp(final ModelCallback callback) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess();
                }
            }
        }, 2000);
    }

    public void doMockDB(final ModelCallback callback) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess();
                }
            }
        }, 1000);
    }

    public interface ModelCallback {
        void onSuccess();

        void onFailed();
    }
}
