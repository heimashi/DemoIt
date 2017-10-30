package com.sw.mylibrary;

import com.sw.rxjava.hello.Hello;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    Hello hello = new Hello();

    @Test
    public void test00() {
        hello.test00();
    }

    @Test
    public void test01() {
        hello.test01();
    }

    @Test
    public void test02() {
        hello.test02();
    }

    @Test
    public void test04() {
        hello.test04();
    }
}