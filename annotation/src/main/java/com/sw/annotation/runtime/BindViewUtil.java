package com.sw.annotation.runtime;


import android.app.Activity;

import java.lang.reflect.Field;

public class BindViewUtil {

    public static void inject(Activity activity) {
        if (activity == null) return;
        Field[] fields = activity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotations() != null) {
                if(field.isAnnotationPresent(BindViewRunTime.class)){
                    field.setAccessible(true);
                    BindViewRunTime bindViewRunTime = field.getAnnotation(BindViewRunTime.class);
                    try {
                        field.set(activity, activity.findViewById(bindViewRunTime.value()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        }


    }

}
