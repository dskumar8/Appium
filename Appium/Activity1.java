package Appium;

import java.awt.Desktop.Action;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Activity1 {

AppiumDriver<MobileElement> driver = null;
WebDriverWait wait;   
    @BeforeClass
    public void beforeClass() throws MalformedURLException{
        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId", "emulator-55544");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "com.google.android.apps.tasks");
        caps.setCapability("appActivity", ".ui.TaskListsActivity");
        caps.setCapability("noReset", true);

        // Instantiate Appium Driver
        URL appServer = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(appServer, caps);
        wait = new WebDriverWait(driver, 10);
    }

  @Test
  public void CreateTask() throws InterruptedException {
	  
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	driver.findElementByAccessibilityId("Create new task").click();
	driver.findElement(By.className("android.widget.EditText")).sendKeys("Complete Activity with Google Tasks");
	driver.findElement(By.id("add_task_done")).click();
	String firstTask = driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Complete Activity with Google Tasks\"]")).getAttribute("content-desc");
	Assert.assertEquals(firstTask,"Complete Activity with Google Tasks");
	System.out.println("First Task is " +firstTask);
	Reporter.log("First Task is " +firstTask);
	
	driver.findElementByAccessibilityId("Create new task").click();
	driver.findElement(By.className("android.widget.EditText")).sendKeys("Complete Activity with Google Keep");
	driver.findElement(By.id("add_task_done")).click();
	String secondTask = driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Complete Activity with Google Keep\"]")).getAttribute("content-desc");
	Assert.assertEquals(secondTask,"Complete Activity with Google Keep");
	System.out.println("Second Task is " +secondTask);
	Reporter.log("Second Task is " +secondTask);
	
	driver.findElementByAccessibilityId("Create new task").click();
	driver.findElement(By.className("android.widget.EditText")).sendKeys("Complete the second Activity Google Keep");
	driver.findElement(By.id("add_task_done")).click();
	String thirdTask = driver.findElement(By.xpath("//android.widget.FrameLayout[@content-desc=\"Complete the second Activity Google Keep\"]")).getAttribute("content-desc");
	Assert.assertEquals(thirdTask,"Complete the second Activity Google Keep");
	System.out.println("Third Task is " +thirdTask);
	Reporter.log("Third Task is " +thirdTask); 
	 
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
}
