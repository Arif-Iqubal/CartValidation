package com.myntra.utils;

	import java.util.List;
import java.time.Duration;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class WaitUtil {

	    private static final int TIMEOUT = 20;

	    public static WebElement waitForVisibility(WebDriver driver, WebElement element) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
	        return wait.until(ExpectedConditions.visibilityOf(element));
	    }
	    public static WebElement waitForVisibility(WebDriver driver, By locator) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    }

	    public static WebElement waitForPresence(WebDriver driver, By locator) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
	        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	    }

	    public static WebElement waitForClickability(WebDriver driver, WebElement element) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
	        return wait.until(ExpectedConditions.elementToBeClickable(element));
	    }

	    public static boolean waitForInvisibility(WebDriver driver, By locator) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
	        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	    }
	    
	    public static List<WebElement> waitForVisibilityofElementsLocated(WebDriver driver, By locator) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
	        return wait.until(
			        ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	  //An expectation for checking that all elements present on the web page that match the locator are visible. Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
	    }
	    
	    public static boolean waitForWindowSwitching(WebDriver driver)
	    {
	    	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(TIMEOUT));
	    	 return wait.until(d -> d.getWindowHandles().size() > 1);
	    	 
	    	 // wait.until(ExpectedConditions.numberOfWindowsToBe(2));
	        
	    }
	}



