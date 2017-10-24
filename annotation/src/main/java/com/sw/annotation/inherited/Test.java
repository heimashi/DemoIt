package com.sw.annotation.inherited;

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

}
