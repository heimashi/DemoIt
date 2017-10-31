package com.sw.rxjava.hello;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by shiwang on 31/10/2017.
 */
public class CombiningObservablesOperatorsTest {

    CombiningObservablesOperators operators = new CombiningObservablesOperators();

    @Test
    public void testMerge() throws Exception {
        operators.testMerge();
    }

    @Test
    public void testStartWith() throws Exception {
        operators.testStartWith();
    }


    @Test
    public void testZip() throws Exception {
        operators.testZip();
    }

}