package com.sw.mvp.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sw.mvp.R;
import com.sw.mvp.bean.BTaskData;
import com.sw.mvp.bean.ATaskData;
import com.sw.mvp.models.TaskModel;
import com.sw.mvp.views.ATaskView;
import com.sw.mvp.views.BTaskView;
import com.sw.mvp.views.LoadingView;


@Route(path = "/mvp/taskctivity")
public class TaskActivity extends Activity {

    //Views
    private LoadingView loadingView;
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

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void initView() {
        setContentView(R.layout.mvp_activity_task);
        LinearLayout containerLayout = findViewById(R.id.container_layout);

        //case 1: from xml
        loadingView = findViewById(R.id.loading_view);
        aTaskView = findViewById(R.id.a_task_view);

        //case 2: from java
        bTaskView = new BTaskView(this);
        containerLayout.addView(bTaskView);


        //set view callbacks
        aTaskView.setCallback(new ATaskView.ATaskViewCallback() {
            @Override
            public void onUpdateCallback() {
                loadingView.show();
                taskModel.loadATaskData();
            }
        });
        bTaskView.setCallback(new BTaskView.ATaskViewCallback() {
            @Override
            public void onUpdateCallback() {
                loadingView.show();
                taskModel.loadBTaskData();
            }
        });
    }

    private void initData() {
        taskModel = new TaskModel();
        taskModel.setCallback(new TaskModel.TaskCallback() {
            @Override
            public void onPageData(ATaskData data, BTaskData bData) {
                loadingView.hide();
                aTaskView.updateData(data);
                bTaskView.updateData(bData);
            }

            @Override
            public void onTaskALoad(ATaskData data) {
                loadingView.hide();
                aTaskView.updateData(data);
            }

            @Override
            public void onTaskBLoad(BTaskData data) {
                loadingView.hide();
                bTaskView.updateData(data);
            }
        });


    }

    private void loadData() {
        //load init data
        loadingView.show();
        taskModel.loadPageData();
    }

    public static void invoke(Context context) {
        context.startActivity(new Intent(context, TaskActivity.class));
    }
}
