package com.sw.rxjava.hello;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sw.rxjava.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


/**
 * Created by shiwang on 31/10/2017.
 */

public class TestRxjavaActivity extends Activity {

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_activity_test);
        findViewById(R.id.test_interval).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.interval(2, 3, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Long>() {
                            @Override
                            public void call(Long aLong) {
                                Toast.makeText(TestRxjavaActivity.this, "haha" + i++, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        findViewById(R.id.test_com).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CombiningObservablesOperators operators = new CombiningObservablesOperators();
                operators.testCombineLast();
            }
        });

        findViewById(R.id.test_join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CombiningObservablesOperators operators = new CombiningObservablesOperators();
                operators.testJoin();
            }
        });

    }

    public static void invoke(Context context) {
        context.startActivity(new Intent(context, TestRxjavaActivity.class));
    }

}
