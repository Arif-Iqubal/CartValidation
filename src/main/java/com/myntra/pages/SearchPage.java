package com.myntra.pages;

import org.openqa.selenium.WebDriver;

public class SearchPage {
protected WebDriver driver;

	public void searchPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		 
		
	}
	
	public void searchPage()
	{
		List<WebElement> products=driver.findElements(By.xpath("//li[contains(@class,'product-base')]"));
		WaitUtils.waitForClickability(driver, products.get(0)).click();
	}

}
