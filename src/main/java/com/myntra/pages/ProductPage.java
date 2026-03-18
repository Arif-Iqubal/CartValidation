package com.myntra.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.utils.WaitUtils;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	
	private WebDriver driver;
	
	private static final Logger log = LogManager.getLogger(ProductPage.class);
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		log.info("Product Page Initialized");
	}
	
	@FindBy(xpath = "//div[@class='size-buttons-size-header']")
	private WebElement sizeButton;
	
	@FindBy(xpath = "//button[@class='size-buttons-size-button-selected size-buttons-size-button size-buttons-size-button-default']")
	private WebElement selectedSizeButton;
	
	@FindBy(xpath = "//div[normalize-space()='ADD TO BAG']")
	private WebElement addToBagButton;
	
	@FindBy(xpath = "//a[@class='pdp-goToCart pdp-add-to-bag pdp-button pdp-flex pdp-center ']")
	private WebElement goToBagButton;
	
	
	
	public ProductPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}

	public void addToBag() {
		WaitUtils.waitForVisibility(driver, sizeButton);
		
		if (sizeButton.isDisplayed()) {
			WaitUtils.waitForClickability(driver, selectedSizeButton).click();
			log.info("Size selected");
		}
		
		WaitUtils.waitForClickability(driver, addToBagButton).click();
		log.info("Clicked on Add to Bag button");
		
		WaitUtils.waitForClickability(driver, goToBagButton).click();
		log.info("Clicked on Go to Bag button");
		
	}
}
