package com.myntra.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	protected WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	public int cartIconReader() {
		return 0;
	}

	public void addProduct() {

	}
}
