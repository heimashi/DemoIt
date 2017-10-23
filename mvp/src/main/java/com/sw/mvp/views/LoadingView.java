package com.sw.mvp.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.sw.mvp.R;


public class LoadingView extends FrameLayout {

    public LoadingView(@NonNull Context context) {
        this(context, null);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.mvp_view_loading, this);

    }

    public void show() {
        this.setVisibility(VISIBLE);
    }

    public void hide() {
        this.setVisibility(GONE);
    }
}
