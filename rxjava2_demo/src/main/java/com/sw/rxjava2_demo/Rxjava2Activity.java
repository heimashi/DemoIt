package com.sw.rxjava2_demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;


public class Rxjava2Activity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TestBackPressure testBackPressure = new TestBackPressure();
        testBackPressure.test6();
        final Button button = new Button(this);
        setContentView(button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testBackPressure.request();
            }
        });
    }

    public static void invoke(Context context) {
        context.startActivity(new Intent(context, Rxjava2Activity.class));
    }

}
