package com.sw.demoit.annotation.clazz;

import android.app.Activity;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class ViewBinderClazz {

    private static final ActivityProvider PROVIDER_ACTIVITY = new ActivityProvider();
    private static final ViewProvider PROVIDER_VIEW = new ViewProvider();

    private static final Map<String, ViewBinder> FINDER_MAP = new HashMap<>();

    public static void inject(Activity activity) {
        inject(activity, activity, PROVIDER_ACTIVITY);
    }

    public static void inject(View view) {
        inject(view, view);
    }

    public static void inject(Object host, View view) {
        // for fragment
        inject(host, view, PROVIDER_VIEW);
    }

    public static void inject(Object host, Object source, Provider provider) {
        String className = host.getClass().getName();
        try {
            ViewBinder viewBinder = FINDER_MAP.get(className);
            if (viewBinder == null) {
                Class<?> finderClass = Class.forName(className + "$$ViewBinder");
                viewBinder = (ViewBinder) finderClass.newInstance();
                FINDER_MAP.put(className, viewBinder);
            }
            viewBinder.inject(host, source, provider);
        } catch (Exception e) {
            throw new RuntimeException("Unable to inject for " + className, e);
        }
    }
}
