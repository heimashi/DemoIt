package com.sw.mvp.presenters;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.sw.mvp.R;
import com.sw.mvp.views.ATaskView;

/**
 * Created by shiwang on 20/10/2017.
 */

public class TaskActivity extends Activity {

    private ATaskView aTaskView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.mvp_activity_task);
        LinearLayout containerLayout = findViewById(R.id.container_layout);
        aTaskView = new ATaskView(this);
        containerLayout.addView(aTaskView);

        aTaskView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = 4;
                int b = a + 5;
            }
        });
    }


}
