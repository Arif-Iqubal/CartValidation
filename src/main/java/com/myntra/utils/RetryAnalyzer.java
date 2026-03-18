package com.myntra.utils;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;  // retry 2 times only

    @Override
    public boolean retry(ITestResult result) {

        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            return true;   // Retry the test
        }

        return false;  // Do not retry anymore
    }
}
