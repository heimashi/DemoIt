package com.sw.onepixel;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class OnePixelActivity extends Activity {

    private BroadcastReceiver endReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        params.height = 1;
        params.width = 1;
        window.setAttributes(params);

        endReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                finish();
            }
        };
        registerReceiver(endReceiver, new IntentFilter("FINISH_ONE_PIXEL_ACTIVITY"));

        checkScreen();
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkScreen();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (endReceiver != null) {
            unregisterReceiver(endReceiver);
        }
    }

    private void checkScreen() {
        PowerManager pm = (PowerManager) OnePixelActivity.this.getSystemService(Context.POWER_SERVICE);
        if (pm != null) {
            boolean isScreenOn = pm.isScreenOn();
            if (isScreenOn) {
                finish();
            }
        }
    }
}
