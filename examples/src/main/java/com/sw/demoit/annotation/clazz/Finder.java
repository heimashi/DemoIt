package com.sw.demoit.annotation.clazz;

public interface Finder<T> {

    void inject(T host, Object source, Provider provider);
}
