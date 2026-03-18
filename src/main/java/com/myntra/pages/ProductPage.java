package com.myntra.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	protected WebDriver driver;
	public ProductPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);

	}
	
	public void addToBag() {
		
	}
}
