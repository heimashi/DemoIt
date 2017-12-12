package com.sw.dagger.todo2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shiwang on 12/12/2017.
 */

@Module
public class Todo2Module {

    private final Todo2Contract.View mView;

    public Todo2Module(Todo2Contract.View view) {
        this.mView = view;
    }

    @Provides
    Todo2Contract.View provideView() {
        return mView;
    }

    @Provides
    Todo2Contract.Presenter providePresenter() {
        return new Todo2Presenter(mView);
    }
}
