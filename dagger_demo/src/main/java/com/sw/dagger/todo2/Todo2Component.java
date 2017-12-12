package com.sw.dagger.todo2;


import dagger.Component;

/**
 * Created by shiwang on 12/12/2017.
 */

@Component(modules = Todo2Module.class)
public interface Todo2Component {

    void inject(Todo2Activity activity);
}
