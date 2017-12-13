package com.sw.dagger.scope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shiwang on 13/12/2017.
 */

@Module
public class UserModule {

    @Provides
    @Singleton
    User provideUser(){
        return new User();
    }
}
