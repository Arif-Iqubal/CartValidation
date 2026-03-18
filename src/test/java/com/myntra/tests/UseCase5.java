package com.myntra.tests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.myntra.base.BaseTest;
import com.myntra.pages.CartPage;
import com.myntra.pages.HomePage;
import com.myntra.pages.ProductPage;
import com.myntra.pages.SearchPage;

import jdk.internal.org.jline.utils.Log;

import org.testng.Assert;
import org.testng.annotations.Test;
import java. util.List;

public class UseCase5 {

	private static final Logger logger = LogManager.getLogger(UseCase5.class);
	
	@Test
	public void emptyCartDataDrivenTest() {
		
		String testId = "UC5";
		String description = "Add multiple products and remove all to validate empty cart";
		
		try {
			Logger log;
			log.info("===== Test Started =====");

            HomePage home = new HomePage(driver);
            SearchPage search = new SearchPage(driver);
            ProductPage product = new ProductPage(driver);
            CartPage cart = new CartPage(driver);

            // Step 1: Read product names from text file
            log.info("Reading product names from text file");

            List<String> products = FileReaderUtil.readProducts("src/test/resources/testdata/products.txt");

            int expectedCount = 0;

            // Step 2: Search and add products
            for(String item : products) {

                log.info("Searching product: " + item);

                home.addProduct(item);

                search.searchProduct(item);

                product.addToBag();

                expectedCount++;

                log.info("Product added to cart. Expected cart count: " + expectedCount);
            }

            // Step 3: Validate cart count
            int actualCartCount = home.cartIconCount();

            log.info("Cart icon count is: " + actualCartCount);

            Assert.assertEquals(actualCartCount, expectedCount);

            log.info("Cart count validation passed");

            // Step 4: Navigate to cart
            driver.get("https://www.myntra.com/checkout/cart");

            log.info("Navigated to cart page");

            // Step 5: Remove items one by one
            while(home.cartIconCount() > 0) {

                log.info("Removing item from cart");

                cart.removeItem();

                int newCount = home.cartIconCount();

                log.info("Cart count after removal: " + newCount);
            }

            // Step 6: Validate empty cart message
            boolean status = cart.cartStatus();

            log.info("Checking empty cart status");

            Assert.assertTrue(status);

            log.info("Empty cart validation successful");

            // Step 7: Write result to Excel

            ExcelWriterUtil.writeResult(
                    testId,
                    description,
                    "Cart should become empty",
                    "Cart empty message displayed",
                    "PASS"
            );

            log.info("Test result written to Excel");

        }

        catch(Exception e) {

            log.error("Test Failed due to exception: " + e.getMessage());

            ExcelWriterUtil.writeResult(
                    testId,
                    description,
                    "Cart should become empty",
                    "Test failed due to exception",
                    "FAIL"
            );

            Assert.fail();
        }

        log.info("===== Test Completed =====");

    }
	
}
