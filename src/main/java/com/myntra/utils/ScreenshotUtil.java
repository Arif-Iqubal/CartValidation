package com.myntra.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
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