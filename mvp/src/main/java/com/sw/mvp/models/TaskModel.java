package com.sw.mvp.models;

import com.sw.mvp.bean.BTaskData;
import com.sw.mvp.bean.TaskData;


public class TaskModel extends BaseModel {

    public void loadPageData() {
        doMockHttp(new HttpCallback() {
            @Override
            public void onSuccess() {
                if (callback != null) {
                    TaskData taskData = new TaskData();
                    taskData.title = "TASK_A_" + System.currentTimeMillis();
                    taskData.content = System.currentTimeMillis() + "fsfsffsffsfdfsfsdf123e23324332123e23324332123e23324332123e23324332123e23324332123e23324332";

                    BTaskData bTaskData = new BTaskData();
                    bTaskData.title = "TASK_B_" + System.currentTimeMillis();
                    bTaskData.content = System.currentTimeMillis() + "fsfsfrrwerwrwr34242342423fsfsfrrwerwrwr34242342423fsfsfrrwerwrwr34242342423fsfsfrrwerwrwr34242342423fsfsfrrwerwrwr34242342423";
                    bTaskData.content2 = System.currentTimeMillis() + "fsfsffsffsfdfsfsdfSFFSDFFSFFSF123e23324332123e23324332123e23324332123e23324332";
                    callback.onPageData(taskData, bTaskData);
                }
            }

            @Override
            public void onFailed() {

            }
        });
    }

    public void loadATaskData() {
        doMockHttp(new HttpCallback() {
            @Override
            public void onSuccess() {
                if (callback != null) {
                    TaskData taskData = new TaskData();
                    taskData.title = System.currentTimeMillis() + "sfsffs123e23324332" + System.currentTimeMillis();
                    taskData.content = System.currentTimeMillis() + "fsfsffsffsfdfsfsdf123e23324332123e23324332123e23324332";
                    callback.onTaskALoad(taskData);
                }
            }

            @Override
            public void onFailed() {

            }
        });
    }

    public void loadBTaskData() {
        doMockHttp(new HttpCallback() {
            @Override
            public void onSuccess() {
                if (callback != null) {
                    BTaskData taskData = new BTaskData();
                    taskData.title = "sfsffs123e23324332123e23324332" + System.currentTimeMillis();
                    taskData.content = System.currentTimeMillis() + "fsfsffsffsfdfsfsdf123e23324332123e23324332123e23324332123e23324332123e23324332";
                    taskData.content2 = System.currentTimeMillis() + "fsfsffsffsfdfsfsdfSFFSDFFSFFSF123e23324332123e23324332123e23324332123e23324332123e23324332123e23324332123e23324332";
                    callback.onTaskBLoad(taskData);
                }
            }

            @Override
            public void onFailed() {

            }
        });
    }

    private TaskCallback callback;

    public void setCallback(TaskCallback callback) {
        this.callback = callback;
    }

    public interface TaskCallback {
        void onPageData(TaskData data, BTaskData bData);

        void onTaskALoad(TaskData data);

        void onTaskBLoad(BTaskData data);
    }

}
