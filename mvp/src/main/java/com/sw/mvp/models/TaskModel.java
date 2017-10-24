package com.sw.mvp.models;

import com.sw.mvp.bean.BTaskData;
import com.sw.mvp.bean.ATaskData;


public class TaskModel extends BaseModel {

    public void loadPageData() {
        doMockHttp(new ModelCallback() {
            @Override
            public void onSuccess() {
                if (callback != null) {
                    ATaskData ATaskData = new ATaskData();
                    ATaskData.title = "TASK_A_" + System.currentTimeMillis();
                    ATaskData.content = System.currentTimeMillis() + "fsfsffsffsfdfsfsdf123e23324332123e23324332123e23324332123e23324332123e23324332123e23324332";

                    BTaskData bTaskData = new BTaskData();
                    bTaskData.title = "TASK_B_" + System.currentTimeMillis();
                    bTaskData.content = System.currentTimeMillis() + "fsfsfrrwerwrwr34242342423fsfsfrrwerwrwr34242342423fsfsfrrwerwrwr34242342423fsfsfrrwerwrwr34242342423fsfsfrrwerwrwr34242342423";
                    bTaskData.content2 = System.currentTimeMillis() + "fsfsffsffsfdfsfsdfSFFSDFFSFFSF123e23324332123e23324332123e23324332123e23324332";
                    callback.onPageData(ATaskData, bTaskData);
                }
            }

            @Override
            public void onFailed() {

            }
        });
    }

    public void loadATaskData() {
        doMockHttp(new ModelCallback() {
            @Override
            public void onSuccess() {
                if (callback != null) {
                    ATaskData ATaskData = new ATaskData();
                    ATaskData.title = "TASK_A_" + System.currentTimeMillis();
                    ATaskData.content = System.currentTimeMillis() + "fsfsffsffsfdfsfsdf123e23324332123e23324332123e23324332";
                    callback.onTaskALoad(ATaskData);
                }
            }

            @Override
            public void onFailed() {

            }
        });
    }

    public void loadBTaskData() {
        doMockDB(new ModelCallback() {
            @Override
            public void onSuccess() {
                if (callback != null) {
                    BTaskData taskData = new BTaskData();
                    taskData.title = "TASK_B_" + System.currentTimeMillis();
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
        void onPageData(ATaskData data, BTaskData bData);

        void onTaskALoad(ATaskData data);

        void onTaskBLoad(BTaskData data);
    }

}
