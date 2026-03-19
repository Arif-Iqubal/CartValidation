package com.myntra.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.myntra.utils.*;
import com.myntra.utils.WaitUtil;

import java.util.List;

public class HomePage {
	protected WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	
@FindBy(xpath="//a[@id='header-cart-icon']//span") WebElement cartCount;
	@FindBy(xpath = "//input[@placeholder='Search for products, brands and more']")
	WebElement searchBox;

	public void addProduct(String path) {

		List<String> products = ExcelReader.getProductNames(path);

		for (String product : products) {

			try {
				LoggerUtil.info("Searching product: " + products);
			
				

				WaitUtil.waitForVisibility(driver, searchBox);
				searchBox.clear();
				searchBox.sendKeys(product);
				searchBox.sendKeys(Keys.ENTER);

				SearchPage searchPage = new SearchPage(driver);
				searchPage.searchPage();

				String parent = WindowSwitch.toChild(driver);

				ProductPage productPage = new ProductPage(driver);
				productPage.addToBag();

				LoggerUtil.info("Product added: " + product);
				

				driver.close();
				WindowSwitch.toParent(driver, parent);

			} catch (Exception e) {

				LoggerUtil.error("Failed product: " + product);

				String img = ScreenshotUtil.takeScreenshot(driver, product);

				
			}
		}
	}
	
	public int cartIconCount()
	{
		WaitUtil.waitForVisibility(driver, cartCount);
		String count=cartCount.getText().replaceAll("[^0-9]","");
	return Integer.parseInt(count);
	}
}
		
	
