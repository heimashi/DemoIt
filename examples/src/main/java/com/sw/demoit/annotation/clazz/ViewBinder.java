package com.sw.demoit.annotation.clazz;

public interface ViewBinder<T> {

    void inject(T host, Object source, Provider provider);
}
