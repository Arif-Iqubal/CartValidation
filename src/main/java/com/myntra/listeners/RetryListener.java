package com.myntra.listeners;


import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Method;

import com.myntra.utils.RetryAnalyzer;

public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation,
                          Class testClass,
                          java.lang.reflect.Constructor testConstructor,
                          Method testMethod) {

        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}