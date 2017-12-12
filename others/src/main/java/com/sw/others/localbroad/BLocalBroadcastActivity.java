package com.sw.others.localbroad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


public class BLocalBroadcastActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                sendBroadcast();
            }
        },"test local broad").start();

    }


    private void sendBroadcast() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("TEST_XX"));
        Log.i("TEST", "++++sendBroadcast"+ Thread.currentThread().getName());
    }

    private void sendBroadcastSync() {
        LocalBroadcastManager.getInstance(this).sendBroadcastSync(new Intent("TEST_XX"));
        Log.i("TEST", "++++sendBroadcastSync"+ Thread.currentThread().getName());
    }
}
