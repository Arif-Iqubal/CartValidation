package com.myntra.listeners;



import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.myntra.utils.Driverfactory;
import com.myntra.utils.ExcelWriterUtil;
import com.myntra.utils.Extentmanager;
import com.myntra.utils.LoggerUtil;
import com.myntra.utils.ScreenshotUtil;

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

    private static ExtentReports extent = Extentmanager.getInstance();
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
        ExcelWriterUtil.writeResult(
        		"UC5",
        		 "Add multiple products and remove all to validate empty cart",
                "Cart should become empty",
                "Cart empty message displayed",
                "PASS",""
        );

        LoggerUtil.info("Test result written to Excel");
    }

    @Override
    
   
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        // Capture screenshot
        WebDriver driver = (WebDriver) result.getAttribute("driver");//this driver reference will work for doing reporting of custom Chrome profile failure
        if (driver == null) {
            driver = Driverfactory.getDriver();//this driver reference will work for doing reporting of default Chrome profile failure
        }
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
        
        

        // Add screenshot to report safely
        if (screenshotPath != null) {
            try {
                test.get().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                // Log exception in report
                test.get().warning("Failed to attach screenshot: " + e.getMessage());
            }
        }
        ExcelWriterUtil.writeResult(
        		"UC5",
       		 "Add multiple products and remove all to validate empty cart",
                "Cart should become empty",
                "Test failed due to exception",
                "FAIL",screenshotPath
        );
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
        	    driver = Driverfactory.getDriver();
        	}
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());

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
//    public static String takeScreenshot(WebDriver driver, String testName) {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String dest = System.getProperty("user.dir") + "/test-output/screenshots/" + testName + "_" + timeStamp + ".png";
//        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        try {
//            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/test-output/screenshots/"));
//            Files.copy(src.toPath(), Paths.get(dest));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return dest;
//    }
}