package com.sw.dagger.todo2;


import android.os.Handler;
import android.os.Looper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by shiwang on 12/12/2017.
 */

public class Todo2Presenter implements Todo2Contract.Presenter {

    Todo2Contract.View mView;

    private Handler handler;

    @Inject
    public Todo2Presenter(Todo2Contract.View view) {
        this.mView = view;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void start() {
        mView.showLoadingView();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
                mView.updateView("init data:" + simpleDateFormat.format(new Date()));
            }
        }, 2000);
    }

    @Override
    public void dropView() {

    }

    @Override
    public void reloadData() {
        mView.showLoadingView();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
                mView.updateView("update at:" + simpleDateFormat.format(new Date()));
            }
        }, 2000);
    }
}
