package Appium;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;



public class Activity6 {
	
   WebDriverWait wait;
   AppiumDriver<MobileElement> driver;
   
	
	    @BeforeClass
	    public void setup() throws MalformedURLException {
	        // Set the Desired Capabilities
	        DesiredCapabilities caps = new DesiredCapabilities();
	        caps.setCapability("deviceid","emulator-55544");
	        caps.setCapability("platformName", "Android");
	        caps.setCapability("automationName", "UiAutomator2");
	        caps.setCapability("appPackage", "com.android.chrome");
			caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
	        caps.setCapability("noReset", true);

	        // Instantiate Appium Driver
	        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
	        driver = new AndroidDriver<MobileElement>(appServer, caps);
	        driver.get("https://www.training-support.net/selenium");
	    }
	  //Goal: Opening a page on the browser and testing a simple login page with correct and incorrect credentials
	    
	    @Test

	    public void PopUpsLogin() {

	    	driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"Popups\"))"));
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='Popups']")).click();

	    		  //Enter credentials 

	    		  //Valid Credentials
	    	      String userName = "admin";
	    	      String passWord = "password";
	    	      driver.findElement(MobileBy.AndroidUIAutomator("text(\"Sign In\")")).click();
	    	      WebElement user = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"username\")"));
	    	      WebElement pass = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"password\")"));

	    		  user.sendKeys(userName);
	    		  pass.sendKeys(passWord);

	    		  driver.findElement(MobileBy.AndroidUIAutomator("text(\"Log in\")")).click();
	    	      String loginMessage = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
	    	      Assert.assertEquals(loginMessage, "Welcome Back, admin");

	    		 //Invalid Credentials

	    		  String userName1 = "admin1";
	    	      String passWord1 = "password1";
	    	      driver.findElement(MobileBy.AndroidUIAutomator("text(\"Sign In\")")).click();
	    	      user.clear();
	    	      pass.clear();
	    	      user.sendKeys(userName1);
	    	      pass.sendKeys(passWord1);
	    	      driver.findElement(MobileBy.AndroidUIAutomator("text(\"Log in\")")).click();
	    	      String inloginMessage = driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"action-confirmation\")")).getText();
	    	      Assert.assertEquals(inloginMessage, "Invalid Credentials");

	          Assert.assertEquals(inloginMessage, "Invalid Credentials");
	    }
        @AfterClass
	    public void afterClass() {
	       driver.quit();
	       }
}