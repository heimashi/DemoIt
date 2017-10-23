package com.sw.mvp.models;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by shiwang on 20/10/2017.
 */

public abstract class BaseModel {
    private Handler handler = new Handler(Looper.getMainLooper());

    public void doMockHttp(final HttpCallback callback) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess();
                }
            }
        }, 2000);
    }

    public interface HttpCallback {
        void onSuccess();

        void onFailed();
    }
}
