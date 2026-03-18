package com.myntra.listeners;



import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.myntra.utils.DriverFactory;
import com.myntra.utils.ExtentManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentTestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    
   
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        // Capture screenshot
        WebDriver driver = (WebDriver) result.getAttribute("driver");//this driver reference will work for doing reporting of custom Chrome profile failure
        if (driver == null) {
            driver = DriverFactory.getDriver();//this driver reference will work for doing reporting of default Chrome profile failure
        }
        String screenshotPath = takeScreenshot(driver, result.getMethod().getMethodName());
        
        

        // Add screenshot to report safely
        if (screenshotPath != null) {
            try {
                test.get().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                // Log exception in report
                test.get().warning("Failed to attach screenshot: " + e.getMessage());
            }
        }
    }

    //In retry mechanism if any attempt test failed then that test is considered as test skipped not test failure, so to not avoid screenshot we will take screenshot on test skipped also if test is skipped due to some exception.
    //This all we do because in many case there is no exception is thrown and test case is skipped.so to identify which test case is throwing exception according to that we will take screenshot
    //onTestFailure()  → screenshot
    //onTestSkipped()  → screenshot only if throwable exists
    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test Skipped");

        // Skip due to retry failure
        if (result.getThrowable() != null) {

        	WebDriver driver = (WebDriver) result.getAttribute("driver");

        	if (driver == null) {
        	    driver = DriverFactory.getDriver();
        	}
            String screenshotPath = takeScreenshot(driver, result.getMethod().getMethodName());

            try {
                test.get().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                test.get().warning("Screenshot attach failed");
            }
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Utility method to capture screenshot
    public static String takeScreenshot(WebDriver driver, String testName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String dest = System.getProperty("user.dir") + "/test-output/screenshots/" + testName + "_" + timeStamp + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/test-output/screenshots/"));
            Files.copy(src.toPath(), Paths.get(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
}