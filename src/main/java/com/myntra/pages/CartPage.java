package com.myntra.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.myntra.utils.LoggerUtil;
import com.myntra.utils.WaitUtil;

public class CartPage {
	
	private WebDriver driver;
//	private static final Logger log = LogManager.getLogger(CartPage.class);
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		LoggerUtil.info("CartPage Initialized");
	}

	@FindBy(xpath = "//a[contains(@class,'desktop-cart')]")
	private WebElement cartButton;
	
	@FindBy(xpath = "//div[contains(@class,'itemContainer-base-closeIcon')]")
    private List<WebElement> removeButtons;
	
	@FindBy(xpath="//div[contains(@class,'inlinebuttonV2-base-action  confirmOrCancelModal-buttonClass')]//button[text()='REMOVE']")
	WebElement removeFromBagBtn;
	
	@FindBy(xpath="//div[text()='Hey, it feels so light!']") WebElement emptyCartMessage;
	
	public void removeItem() {

//		cartButton.click();
//		LoggerUtil.info("Clicked on Cart button");
		
		while(true) {
			List<WebElement> removeButtonsList = driver.findElements(By.xpath("//*[@class='itemContainer-base-closeIcon']"));
			if (removeButtonsList.isEmpty()) {
				LoggerUtil.info("No more items to remove");
				break;
			}
			WebElement removeButton = removeButtonsList.get(0);
			removeButton.click();
			LoggerUtil.info("Clicked on Remove button");
			
			// Wait for the confirmation dialog and click "Remove from Bag"
			WaitUtil.waitForVisibility(driver, removeFromBagBtn).click();
			
			LoggerUtil.info("Clicked on Remove from Bag button");
			
			// Wait for the item to be removed from the cart
			try {
			Thread.sleep(1000);
			} catch (InterruptedException e) {
				LoggerUtil.error("Interrupted while waiting for item removal");
				Thread.currentThread().interrupt();
			}
		}
	}
	
	@FindBy(xpath = "//span[contains(text(),'/')]")
	WebElement itemCountText;
	public int getTotalItemCount() {
WaitUtil.waitForVisibility(driver,itemCountText);
	    String text = itemCountText.getText(); // "3/3 ITEMS SELECTED"
	    String total = text.split("/")[1].replaceAll("[^0-9]", "");

	    return Integer.parseInt(total);
	}

	public boolean cartStatus() {
		if (WaitUtil.waitForVisibility(driver, emptyCartMessage).isDisplayed()) {
			return true;
		}
		return false;
	}
}
