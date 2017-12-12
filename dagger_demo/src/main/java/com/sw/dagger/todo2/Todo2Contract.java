package com.sw.dagger.todo2;


import com.sw.dagger.BasePresenter;
import com.sw.dagger.BaseView;

public interface Todo2Contract {

    interface View extends BaseView<Presenter> {

        void updateView(String msg);

        void showLoadingView();

    }

    interface Presenter extends BasePresenter<View> {

        void reloadData();
    }
}
