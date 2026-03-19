package com.myntra.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.utils.WaitUtil;



public class SearchPage {
protected WebDriver driver;

	public  SearchPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		 
		
	}
	@FindBy(xpath="//span[@class='myntraweb-sprite desktop-iconBag sprites-headerBag']/following-sibling::span[1]") WebElement cartCountIcon;
	public void searchPage()
	{
		List<WebElement> products=driver.findElements(By.xpath("//li[contains(@class,'product-base')]"));
		WaitUtil.waitForClickability(driver, products.get(0)).click();
	}
	public int getCartCount()
	{
		WaitUtil.waitForVisibility(driver,cartCountIcon);
		String count=cartCountIcon.getText().replaceAll("[^0-9]","");
		return Integer.parseInt(count);
	}

}
