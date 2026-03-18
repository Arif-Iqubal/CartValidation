package com.myntra.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {
	public static String capture(WebDriver d, String name) {
		try {
			File src = ((TakesScreenshot) d).getScreenshotAs(OutputType.FILE);
			String path = "screenshots/" + name + ".png";
			FileUtils.copyFile(src, new File(path));
			return path;
		} catch (Exception e) {
			return "";
		}
	}
}