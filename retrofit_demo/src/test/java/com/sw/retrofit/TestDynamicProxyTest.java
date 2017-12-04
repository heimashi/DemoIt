package com.sw.retrofit;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Created by shiwang on 01/11/2017.
 */
public class TestDynamicProxyTest {

    TestDynamicProxy proxy = new TestDynamicProxy();

    @Test
    public void testProxy() throws Exception {
        proxy.testProxy();
    }


    @Test
    public void test2() throws Exception {
        Long a = 1L;
        Long b = 2L;
        System.out.println("a=" + a + ",b=" + b);
        swap(a, b);
        System.out.println("a=" + a + ",b=" + b);

    }

    public void swap(Long a, Long b) {
        Long tmp = a;
        try {
            Field field = Long.class.getDeclaredField("value");
            field.setAccessible(true);
            field.set(a, b);
            field.set(b, tmp);
            Long i = 1L;
            System.out.println("t a=" + a + ",b=" + b + " i:"+i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}