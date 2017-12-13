package com.sw.dagger.scope;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

/**
 * Created by shiwang on 13/12/2017.
 */

public class TestScopeActivity extends Activity {

    @Inject
    User user;

    @Inject
    User user1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerActivityComponent.builder().userModule(new UserModule()).build().inject(this);

        TextView textView = new TextView(this);
        textView.setText("User:" + user + "\n" + user1);
        setContentView(textView);
    }

    public static void invoke(Context context) {
        context.startActivity(new Intent(context, TestScopeActivity.class));
    }
}
