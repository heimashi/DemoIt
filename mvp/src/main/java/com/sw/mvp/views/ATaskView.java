package com.sw.mvp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.sw.mvp.bean.TaskData;

/**
 * Created by shiwang on 20/10/2017.
 */

public class ATaskView extends RelativeLayout implements BaseView<TaskData> {

    public ATaskView(Context context) {
        this(context, null);
    }

    public ATaskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {

    }


    @Override
    public void updateData(TaskData data) {

    }


}
