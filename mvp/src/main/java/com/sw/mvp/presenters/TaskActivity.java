package com.sw.mvp.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.sw.mvp.R;
import com.sw.mvp.bean.BTaskData;
import com.sw.mvp.bean.TaskData;
import com.sw.mvp.models.TaskModel;
import com.sw.mvp.views.ATaskView;
import com.sw.mvp.views.BTaskView;


public class TaskActivity extends Activity {

    //Views
    private ATaskView aTaskView;
    private BTaskView bTaskView;

    //Models
    private TaskModel taskModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.mvp_activity_task);
        LinearLayout containerLayout = findViewById(R.id.container_layout);
        aTaskView = new ATaskView(this);
        containerLayout.addView(aTaskView);
        bTaskView = new BTaskView(this);
        containerLayout.addView(bTaskView);
        aTaskView.setCallback(new ATaskView.ATaskViewCallback() {
            @Override
            public void onUpdateCallback() {
                taskModel.loadATaskData();
            }
        });
        bTaskView.setCallback(new BTaskView.ATaskViewCallback() {
            @Override
            public void onUpdateCallback() {
                taskModel.loadBTaskData();
            }
        });
    }

    private void initData() {
        taskModel = new TaskModel();
        taskModel.setCallback(new TaskModel.TaskCallback() {
            @Override
            public void onPageData(TaskData data, BTaskData bData) {
                aTaskView.updateData(data);
                bTaskView.updateData(bData);
            }

            @Override
            public void onTaskALoad(TaskData data) {
                aTaskView.updateData(data);
            }

            @Override
            public void onTaskBLoad(BTaskData data) {
                bTaskView.updateData(data);
            }
        });
        taskModel.loadPageData();
    }

    public static void invoke(Context context) {
        context.startActivity(new Intent(context, TaskActivity.class));
    }
}
