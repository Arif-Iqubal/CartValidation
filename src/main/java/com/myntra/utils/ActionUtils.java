package com.myntra.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtils {

    public static void hover(WebDriver driver, WebElement element)
    {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public static void doubleClick(WebDriver driver, WebElement element)
    {
        Actions action = new Actions(driver);
        action.doubleClick(element).perform();
    }

    public static void rightClick(WebDriver driver, WebElement element)
    {
        Actions action = new Actions(driver);
        action.contextClick(element).perform();
    }
}