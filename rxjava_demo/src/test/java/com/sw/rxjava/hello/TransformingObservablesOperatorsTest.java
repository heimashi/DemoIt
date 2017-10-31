package com.sw.rxjava.hello;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by shiwang on 30/10/2017.
 */
public class TransformingObservablesOperatorsTest {

    TransformingObservablesOperators operators = new TransformingObservablesOperators();

    @Test
    public void testMap() throws Exception {
        operators.testMap();
    }

    @Test
    public void testFlatMap() throws Exception {
        operators.testFlatMap();
    }


    @Test
    public void testGroupBy() throws Exception {
        operators.testGroupBy();
    }

    @Test
    public void testBuffer() throws Exception {
        operators.testBuffer();
    }

    @Test
    public void testBuffer2() throws Exception {
        operators.testBuffer2();
    }

    @Test
    public void testScan() throws Exception {
        operators.testScan();
    }

    @Test
    public void testWindow() throws Exception {
        operators.testWindow();
    }


}