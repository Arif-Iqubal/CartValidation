package com.myntra.utils;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Extentmanager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {

            // Dynamic report name with timestamp
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport_" + timeStamp + ".html");

            spark.config().setReportName("Automation Exercise Report");
            spark.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // Add system info for clarity
            extent.setSystemInfo("Project Name", "Automation Exercise");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Team Member");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }
}