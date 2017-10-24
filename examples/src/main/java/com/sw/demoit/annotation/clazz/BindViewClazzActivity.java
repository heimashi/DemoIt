package com.sw.demoit.annotation.clazz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.TextView;

import com.sw.annotation.clazz.BindViewClazz;
import com.sw.annotation.runtime.BindViewRunTime;
import com.sw.demoit.R;
import com.sw.demoit.annotation.runtime.BindViewUtil;


public class BindViewClazzActivity extends Activity {

    @BindViewClazz(R.id.test_tv)
    public TextView textView;

    @BindViewClazz(R.id.test_btn)
    public Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bindview);

        ViewFinder.inject(this);
        textView.setText("clazz bind success");
        button.setText("clazz bind success btn");
    }


    public static void invoke(Context context) {
        context.startActivity(new Intent(context, BindViewClazzActivity.class));
    }

}
