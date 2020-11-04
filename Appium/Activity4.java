package Appium;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;



public class Activity4 {
	
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
	
	    //    Goal: Opening a page on the browser and testing a to-do list page
@Test
public void listDetails() {

	  
	  String[] toDolist = {"Add tasks to list", "Get number of tasks", "Clear the list"};
	  driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollIntoView(textStartsWith(\"To-Do List\"))"));
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.findElement(MobileBy.xpath("//android.widget.TextView[@text='To-Do List']")).click();
      //Create toDolist

	  for (String s : toDolist) {

	  driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"taskInput\")")).sendKeys(s);
      driver.findElement(MobileBy.AndroidUIAutomator("text(\"Add Task\")")).click();

	  }
      //Clear todolist
      List<MobileElement> created_Tasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"tasksList\")"));
      for (MobileElement s : created_Tasks) {
      s.click();
      }

	 driver.findElement(By.xpath("//android.webkit.WebView/android.view.View/android.view.View[3]/android.view.View[3]")).click();
     //Assertion
     List<MobileElement> cleared_Tasks = driver.findElements(MobileBy.AndroidUIAutomator("resourceId(\"tasksList\")"));
     Assert.assertEquals(cleared_Tasks.size(), 0);
}
@AfterClass
public void afterClass() {
   driver.quit();

}

}