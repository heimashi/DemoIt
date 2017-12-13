package com.sw.dagger.scope;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by shiwang on 13/12/2017.
 */

public class User {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(){

    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;

    private int age;
}
