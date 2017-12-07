package com.sw.onepixel;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OnePixelReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("PIXEL", "+++++" + intent.getAction());
        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
            Intent it = new Intent(context, OnePixelActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(it);
        } else if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
            context.sendBroadcast(new Intent("FINISH_ONE_PIXEL_ACTIVITY"));
            Intent main = new Intent(Intent.ACTION_MAIN);
            main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            main.addCategory(Intent.CATEGORY_HOME);
            context.startActivity(main);
        }
    }
}
