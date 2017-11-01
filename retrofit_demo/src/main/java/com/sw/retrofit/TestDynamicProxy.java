package com.sw.retrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import retrofit2.http.GET;


public class TestDynamicProxy {

    public interface Subject {
        @GET("/path/a")
        public void request(String url, String param);
    }

    public void testProxy() {
        Subject proxyInstance = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class<?>[]{Subject.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String url = (String) args[0];
                String params = (String) args[1];
                System.out.println("方法名：" + method.getName());
                System.out.println("参数：" + url + " , " + params);

                GET get = method.getAnnotation(GET.class);
                System.out.println("注解value：" + get.value());
                return null;
            }
        });
        proxyInstance.request("http://www.google.com", "test");
    }

}
