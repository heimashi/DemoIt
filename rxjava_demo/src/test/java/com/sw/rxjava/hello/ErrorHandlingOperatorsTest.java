package com.sw.rxjava.hello;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by shiwang on 31/10/2017.
 */
public class ErrorHandlingOperatorsTest {

    ErrorHandlingOperators operators = new ErrorHandlingOperators();

    @Test
    public void testError() throws Exception {
        operators.testError();
    }

    @Test
    public void testRetry() throws Exception {
        operators.testRetry();
    }


    @Test
    public void testRetryWhen() throws Exception {
        operators.testRetryWhen();
    }

}