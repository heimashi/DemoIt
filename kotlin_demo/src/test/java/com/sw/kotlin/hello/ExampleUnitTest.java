package com.sw.kotlin.hello;


import org.junit.Test;
import com.sw.kotlin.hello.*;

import static junit.framework.Assert.assertEquals;

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

    public void testMain(){

    }

    @Test
    public void test11() throws Exception {
        if(true){
            System.out.println("aa");
            if(true){
                System.out.println("bb");
                return;
            }
            System.out.println("cc");

        }
        System.out.println("dd");
    }
}