package com.sw.dagger;

public interface BasePresenter<T> {


    void start();

    /**
     * Drops the reference to the view when destroyed
     */
    void dropView();

}
