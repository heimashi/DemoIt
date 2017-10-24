package com.sw.annotation.inherited;

import java.lang.reflect.Method;

/**
 * Created by shiwang on 24/10/2017.
 */

public class Test {

    @TestNotInherited("Not Inherited value")
    @TestInherited("Inherited value")
    public static class Base {

        @TestNotInherited("Not Inherited value")
        @TestInherited("Inherited value")
        public void funA() {

        }

        @TestNotInherited("Not Inherited value")
        @TestInherited("Inherited value")
        public void funB() {

        }
    }

    public static class Child extends Base {
        @Override
        public void funA() {
            super.funA();
        }
    }

    public static void testInheritedAnnotation() {
        boolean t1 = Child.class.isAnnotationPresent(TestInherited.class);
        boolean t2 = Child.class.isAnnotationPresent(TestNotInherited.class);
        System.out.println("Child:   TestInherited:" + t1 + "   TestNotInherited:" + t2);
        Method[] methods = Child.class.getMethods();
        for (Method method : methods) {
            boolean f1 = method.isAnnotationPresent(TestInherited.class);
            boolean f2 = method.isAnnotationPresent(TestNotInherited.class);
            System.out.println("Child Method:" + method.getName() + "   TestInherited:" + f1 + "   TestNotInherited:" + f2);
        }

    }

}
