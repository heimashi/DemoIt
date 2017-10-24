package com.sw.demoit.annotation.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.sw.annotation.runtime.BindViewRunTime;
import com.sw.demoit.R;


public class BindViewRuntimeActivity extends Activity {

    @BindViewRunTime(R.id.test_tv)
    public TextView textView;

    @BindViewRunTime(R.id.test_btn)
    public Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindview);
        BindViewUtil.inject(this);
        textView.setText("bind success");
        button.setText("bind success btn");
    }


    public static void invoke(Context context) {
        context.startActivity(new Intent(context, BindViewRuntimeActivity.class));
    }

}
