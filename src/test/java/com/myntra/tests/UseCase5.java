package com.myntra.tests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.myntra.base.BaseTest;
import com.myntra.listeners.ExtentTestListener;
import com.myntra.pages.CartPage;
import com.myntra.pages.HomePage;
import com.myntra.pages.ProductPage;
import com.myntra.pages.SearchPage;
import com.myntra.utils.ExcelWriterUtil;
import com.myntra.utils.LoggerUtil;
import com.myntra.utils.ScreenshotUtil;

import jdk.internal.org.jline.utils.Log;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java. util.List;
@Listeners(ExtentTestListener.class)
public class UseCase5 extends BaseTest
{

//	private static final Logger logger = LogManager.getLogger(UseCase5.class);
	
	@Test
	public void emptyCartDataDrivenTest() {
	    String testId = "UC5";
	    String description = "Add multiple products and remove all to validate empty cart";

	    try {

	        LoggerUtil.info("===== Test Started =====");
	        driver.get("https://www.myntra.com/");

	        HomePage home = new HomePage(driver);
	        SearchPage search = new SearchPage(driver);
	        ProductPage product = new ProductPage(driver);
	        CartPage cart = new CartPage(driver);

	        // Step 1
	        LoggerUtil.info("Step 1: Reading product names and adding to cart");
	        home.addProduct("D:\\MyntraCart\\CartValidation\\src\\test\\resources\\products.xlsx");

	        driver.navigate().refresh();

	        // Step 2
	        LoggerUtil.info("Step 2: Validating cart count");

	        int actualCartCount = search.getCartCount();
	        int expectedCartCount = 3;

	        LoggerUtil.info("Expected Cart Count: " + expectedCartCount);
	        LoggerUtil.info("Actual Cart Count: " + actualCartCount);

	        Assert.assertEquals(actualCartCount, expectedCartCount);

	        LoggerUtil.info("Cart count validation PASSED");

	        // Step 3
	        LoggerUtil.info("Step 3: Navigating to cart page");
	        product.clickCartLogo();

	        LoggerUtil.info("Navigated to cart page");

	        // Step 4
	        LoggerUtil.info("Step 4: Removing item(s) from cart");
	        cart.removeItem();

	        // Step 5
	        LoggerUtil.info("Step 5: Validating empty cart");

	        boolean status = cart.cartStatus();

	        LoggerUtil.info("Cart empty status: " + status);

	        Assert.assertTrue(status);

	        LoggerUtil.info("Empty cart validation PASSED");

	    }
	    catch (Throwable e) {

	        LoggerUtil.error("===== TEST FAILED =====");
	        LoggerUtil.error("Error Type: " + e.getClass().getSimpleName());
	        LoggerUtil.error("Error Message: " + e.getMessage());

	        // This line is important for full debug (stacktrace)
	        e.printStackTrace();

	        Assert.fail();
	        throw e;
	    }

	    LoggerUtil.info("===== Test Completed =====");
	}	
}
