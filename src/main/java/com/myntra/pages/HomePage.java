package com.myntra.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.myntra.utils.*;

import java.util.List;

public class HomePage {

	WebDriver driver;
	Logger log = LoggerUtil.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Search for products, brands and more']")
	WebElement searchBox;

	public void addProduct(String path, ExtentTest test) {

		List<String> products = ExcelReader.getProductNames(path);

		for (String product : products) {

			try {
				log.info("Searching product: " + product);
				test.info("Searching: " + product);

				WaitUtils.waitForVisibility(driver, searchBox);
				searchBox.clear();
				searchBox.sendKeys(product);
				searchBox.sendKeys(Keys.ENTER);

				SearchPage searchPage = new SearchPage(driver);
				searchPage.searchPage();

				String parent = WindowSwitch.toChild(driver);

				ProductPage productPage = new ProductPage(driver);
				productPage.addToBag();

				log.info("Product added: " + product);
				test.pass("Added: " + product);

				driver.close();
				WindowSwitch.toParent(driver, parent);

			} catch (Exception e) {

				log.error("Failed product: " + product);

				String img = ScreenshotUtil.capture(driver, product);

				test.fail("Failed: " + product).addScreenCaptureFromPath(img);
			}
		}
	}
}