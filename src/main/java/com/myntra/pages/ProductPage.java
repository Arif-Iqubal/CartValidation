package com.myntra.pages;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.utils.LoggerUtil;
import com.myntra.utils.WaitUtil;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	
	private WebDriver driver;
	
//	private static final Logger log = LogManager.getLogger(ProductPage.class);
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		LoggerUtil.info("Product Page Initialized");
	}
	
//	@FindBy(xpath = "//div[@class='size-buttons-size-header']")
//	private WebElement sizeButton;
//	
//	@FindBy(xpath = "//button[@class='size-buttons-size-button-selected size-buttons-size-button size-buttons-size-button-default']")
//	private WebElement selectedSizeButton;
//	
//	@FindBy(xpath = "//div[normalize-space()='ADD TO BAG']")
//	private WebElement addToBagButton;
//	
//	@FindBy(xpath = "//a[@class='pdp-goToCart pdp-add-to-bag pdp-button pdp-flex pdp-center ']")
//	private WebElement goToBagButton;
	
	@FindBy(xpath="//div[text()='ADD TO BAG']")
	WebElement addToBagButton;
	@FindBy(xpath="//span[contains(text(),'Bag')]")  WebElement goToBagButton;
	
	@FindBy(xpath="//button[@class='size-buttons-size-button size-buttons-size-button-default']")  List<WebElement> sizeButton;
	
	
	
	
	public void addToBag() {
//		WaitUtil.waitForVisibility(driver, sizeButton);
//		
//		if (sizeButton.isDisplayed()) {
//			WaitUtil.waitForClickability(driver, selectedSizeButton).click();
//			LoggerUtil.info("Size selected");
//		}
		
		for(WebElement size:sizeButton)
		{
			if(WaitUtil.waitForVisibility(driver,size).isDisplayed())
			{
				WaitUtil.waitForClickability(driver, size).click();
				break;
			}
		}
		
		WaitUtil.waitForClickability(driver, addToBagButton).click();
		LoggerUtil.info("Clicked on Add to Bag button");
		
//		WaitUtil.waitForClickability(driver, goToBagButton).click();
//		LoggerUtil.info("Clicked on Go to Bag button");
		
	}
	public void clickCartLogo()
	{
	WaitUtil.waitForClickability(driver, goToBagButton).click();
		LoggerUtil.info("Clicked on Go to Bag button");
	}
}
