package com.sw.rxjava.hello;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by shiwang on 31/10/2017.
 */
public class FilteringObservablesOperatorsTest {

    private FilteringObservablesOperators operators = new FilteringObservablesOperators();

    @Test
    public void testDebounce() throws Exception {
        operators.testDebounce();
    }

    @Test
    public void testFilter() throws Exception {
        operators.testFilter();
    }


    @Test
    public void testSkip() throws Exception {
        operators.testSkip();
    }

    @Test
    public void testSkipLast() throws Exception {
        operators.testSkipLast();
    }

    @Test
    public void testTake() throws Exception {
        operators.testTake();
    }

    @Test
    public void testTakeLast() throws Exception {
        operators.testTakeLast();
    }

    @Test
    public void testElementAt() throws Exception {
        operators.testElementAt();
    }

    @Test
    public void testFirst() throws Exception {
        operators.testFirst();
    }

    @Test
    public void testFirst2() throws Exception {
        operators.testFirst2();
    }

}