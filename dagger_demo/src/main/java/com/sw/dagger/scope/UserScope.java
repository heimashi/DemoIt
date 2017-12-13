package com.sw.dagger.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by shiwang on 13/12/2017.
 */

@Scope
@Retention(RUNTIME)
public @interface UserScope {
}
