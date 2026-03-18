package com.myntra.base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.myntra.utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriver loginDriver;

    @BeforeMethod
    @Parameters("browser")
 
    public void setup(@Optional("chrome") String browser) {

    	//For Guest Driver
       driver = DriverFactory.initDriver(browser);
        //For logged in driver
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--user-data-dir=C:\\selenium-profile");
//
//        loginDriver = new ChromeDriver(options);
        //but there is one issue that simultaneosly both window initialized and it seems it breaks the flow of task but task is accoplished problem is the view of flow is not good so we will not use this way we will initiallize it when we want to open logged in window
    	 
        
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
    	if(driver!=null)
    	{
    		  DriverFactory.quitDriver();
    	}
     
      
    }
    
    /*🔑 Why this works:@AfterMethod(alwaysRun = true)
    Runs even if test fails
   Runs even if test is skipped this is so much helpful in managing the custom profile window closure because we cant open 2 window at a time in custom profile 
   Ensures browser always closes before retry*/
}