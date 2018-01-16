package com.sw.nest_demo;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

public class CoordinatorLayout1Activity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator1_layout);

    }

    public void onFabClick(View view){
        Snackbar.make(findViewById(R.id.contentView), "Hello Snackbar", Snackbar.LENGTH_SHORT).show();
    }

}
