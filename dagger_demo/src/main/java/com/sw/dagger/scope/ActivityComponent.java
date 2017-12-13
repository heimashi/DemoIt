package com.sw.dagger.scope;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by shiwang on 13/12/2017.
 */

@Singleton
@Component(modules = UserModule.class)
public interface ActivityComponent {

    void inject(TestScopeActivity activity);
}
