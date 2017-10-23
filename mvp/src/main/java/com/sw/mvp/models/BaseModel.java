package com.sw.mvp.models;

/**
 * Created by shiwang on 20/10/2017.
 */

public abstract class BaseModel {

    public void doMockHttp(final HttpCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    if (callback != null) {
                        callback.onSuccess();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFailed();
                    }
                }
            }
        }).start();
    }

    public interface HttpCallback {
        void onSuccess();

        void onFailed();
    }
}
