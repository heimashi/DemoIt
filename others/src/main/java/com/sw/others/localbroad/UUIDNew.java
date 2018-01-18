package com.sw.others.localbroad;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.UUID;

public class UUIDNew {

    private static final String UNIQUE_ID_SP = "unique_id_sp";

    private volatile String uuid;

    private volatile String uniqueID;

    private UUIDNew() {
    }

    private static class SingletonHolder {
        static UUIDNew instance = new UUIDNew();
    }

    public static UUIDNew getInstance() {
        return SingletonHolder.instance;
    }

    public synchronized String getUUID(Context context) {
        if (uuid == null) {
            String macAddress = getMacAddress(context);
            String androidId = getAndroidId(context);
            String serial = getSerial();
            String otherDeviceInfo = "35" + (Build.BOARD.length() % 10)
                    + (Build.BRAND.length() % 10)
                    + (Build.CPU_ABI.length() % 10)
                    + (Build.DEVICE.length() % 10)
                    + (Build.MANUFACTURER.length() % 10)
                    + (Build.MODEL.length() % 10)
                    + (Build.PRODUCT.length() % 10);
            uuid = new UUID(otherDeviceInfo.hashCode(), (macAddress + androidId + serial).hashCode()).toString();
            Log.i("test", "+++mac::" + macAddress + " and::" + androidId + " ser::" + serial + " oth::" + otherDeviceInfo);
            Log.i("test", "+++UNIQUE_ID::" + uuid);
        }
        return uuid;
    }


    public synchronized String getUniqueId(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(UNIQUE_ID_SP, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(UNIQUE_ID_SP, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(UNIQUE_ID_SP, uniqueID);
                editor.apply();
            }
        }
        return uniqueID;
    }

    private String getSerial() {
        try {
            return android.os.Build.class.getField("SERIAL").get(null).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "serial";
    }

    @SuppressLint("HardwareIds")
    private String getMacAddress(Context context) {
        try {
            WifiManager localWifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (localWifiManager != null) {
                WifiInfo localWifiInfo = localWifiManager.getConnectionInfo();
                String macAddress = localWifiInfo.getMacAddress();
                if (TextUtils.isEmpty(macAddress) || "02:00:00:00:00:00".equals(macAddress)) {
                    macAddress = getMacAddress2(context);
                }
                return macAddress;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getMacAddress2(Context context) {
        if (isOnline(context) && getMobileTypeNetwork(context).equals("wifi")) {
            String macSerial = null;
            String str = "";

            try {
                Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
                InputStreamReader ir = new InputStreamReader(pp.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);

                while (null != str) {
                    str = input.readLine();
                    if (str != null) {
                        macSerial = str.trim();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return macSerial;
        } else {
            return "";
        }
    }

    /**
     * 获取网络类型
     */
    private static String getMobileTypeNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo == null) {
                return "other";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return "wifi";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_WIMAX) {
                return "wimax";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                return networkInfo.getSubtypeName();
            }
        }
        return "other";
    }


    @SuppressLint("HardwareIds")
    private String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getApplicationContext().getContentResolver(), "android_id");
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    /**
     * 是否联网
     */
    private static boolean isOnline(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Activity.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                return true;
            }
        }
        return false;
    }
}
