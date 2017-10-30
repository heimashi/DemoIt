package com.sw.rxjava.hello;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by shiwang on 30/10/2017.
 */
public class CreatingObservablesOperatorsTest {

    CreatingObservablesOperators operators = new CreatingObservablesOperators();

    @Test
    public void testCreate() throws Exception {
        operators.testCreate();
    }

    @Test
    public void testJust() throws Exception {
        operators.testJust();
    }

    @Test
    public void testFrom() throws Exception {
        operators.testFrom();
    }

    @Test
    public void testDefer1() throws Exception {
        operators.testDefer1();
    }

    @Test
    public void testDefer2() throws Exception {
        operators.testDefer2();
    }

    @Test
    public void testRange() throws Exception {
        operators.testRange();
    }

    @Test
    public void testRepeat() throws Exception {
        operators.testRepeat();
    }

    @Test
    public void testTimer() throws Exception {
        operators.testTimer();
    }

    @Test
    public void testInterval() throws Exception {
        operators.testInterval();
    }
}