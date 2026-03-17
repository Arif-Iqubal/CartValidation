package com.myntra.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
/*Way 1 → Works when only 2 windows exist but may switch multiple times
Way 2 → Often works but not guaranteed because Set order is not fixed
Way 3 → Good when window title is known
Way 4 → Best and most reliable method (recommended)*/

public class WindowSwitch {
	
	/*Way 1 Usage->String parentWindow = driver.getWindowHandle();
            SearchPage.clickProduct();
            WindowUtils.switchToWindowByPreviousWindow(driver, parentWindow);*/
	public static void switchToWindowByPreviousWindow(WebDriver driver, String parentWindow)
	{
		//getWindowHandles()->return set of window handle whose order is not guarantee
		//A Set does NOT guarantee order.
	    Set<String> allWindows = driver.getWindowHandles();//due to this method returns set of window handles there is possibility of not getting the correct order of windows like you can get w1,w3,w2 etc in any order so it is good to use the way 4
	    /*driver.getWindowHandles() returns a Set.
     A Set does NOT guarantee order.
So order may be:W1 W2 W3
                or
               W2 W1 W3
                or
               W3 W1 W3*/
	   

	    for(String window : allWindows)
	    {
	        if(!window.equals(parentWindow))
	        {
	            driver.switchTo().window(window);
	        }
	    }
	}
	
	/*Way 2 Usage->WindowUtils.switchToWindowByIndex(driver, 1);
	 * 0 → parent window
       1 → first new tab
       2 → second new tab
	 * */
	
	public static void switchToWindowByIndex(WebDriver driver, int index)
	{
		 
	    List<String> windows = new ArrayList<>(driver.getWindowHandles());//set is transformed to list and set does not guarantte order thats why we cant trust this way of switching to window handles also.
	    /*But Even though Set has no guaranteed order, in most browsers the newest window handle appears last in the iterator. You can use a small trick.

Utility Method
public static void switchToLastWindow(WebDriver driver)
{
    List<String> windows = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(windows.get(windows.size() - 1));
}
Usage
searchPage.clickProduct();

WindowSwitch.switchToLastWindow(driver);*/
	    
	    driver.switchTo().window(windows.get(index));
	}
	
	
	/*Way 3 Usage->WindowUtils.switchToWindowByTitle(driver, "Sneakers");
	 * */
	
	public static void switchToWindowByTitle(WebDriver driver, String title)
	{
	    Set<String> windows = driver.getWindowHandles();

	    for(String window : windows)
	    {
	        driver.switchTo().window(window);

	        if(driver.getTitle().contains(title))
	        {
	            break;
	        }
	    }
	}
	
	
	/*Way 4 Usage->Set<String> oldWindows = driver.getWindowHandles();

           searchPage.clickProduct();

            WindowUtils.switchToNewWindow(driver, oldWindows);
	 * */
	public static void switchToNewWindow(WebDriver driver, Set<String> oldWindows)
	{
	    Set<String> newWindows = driver.getWindowHandles();

	    for(String window : newWindows)
	    {
	        if(!oldWindows.contains(window))
	        {
	            driver.switchTo().window(window);
	            break;
	        }
	    }
	}
	
	
	//Way 1 and 4 are similar but in way1 there will be multiple switch will happen between tabs but in way 4 there will be direct switching to the current window
	

}
