package com.myntra.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.utils.WaitUtils;

public class CartPage {
	
	private WebDriver driver;
	private static final Logger log = LogManager.getLogger(CartPage.class);
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		log.info("CartPage Initialized");
	}

	@FindBy(xpath = "//a[contains(@class,'desktop-cart')]")
	private WebElement cartButton;
	
	@FindBy(xpath = "//div[contains(@class,'itemContainer-base-closeIcon')]")
    private List<WebElement> removeButtons;
	
	@FindBy(xpath = "//*[contains(text(),'Remove from Bag')]")
	private WebElement removeFromBagBtn;
	
	@FindBy(xpath = "//div[contains(@class,'empty-cart')]")
	private WebElement emptyCartMessage;
	
	public void removeItem() {

		cartButton.click();
		log.info("Clicked on Cart button");
		
		while(true) {
			List<WebElement> removeButtonsList = driver.findElements(By.xpath("//div[contains(@class,'itemContainer-base-closeIcon')]"));
			if (removeButtonsList.isEmpty()) {
				log.info("No more items to remove");
				break;
			}
			WebElement removeButton = removeButtonsList.get(0);
			removeButton.click();
			log.info("Clicked on Remove button");
			
			// Wait for the confirmation dialog and click "Remove from Bag"
			WaitUtils.waitForVisibility(driver, removeFromBagBtn).click();
			
			log.info("Clicked on Remove from Bag button");
			
			// Wait for the item to be removed from the cart
			try {
			Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.error("Interrupted while waiting for item removal", e);
				Thread.currentThread().interrupt();
			}
		}
	}

	public String cartStatus() {
		if (WaitUtils.waitForVisibility(driver, emptyCartMessage).isDisplayed()) {
			return "empty";
		}
		return "not empty";
	}
}
