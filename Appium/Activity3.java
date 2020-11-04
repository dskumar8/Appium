package Appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class Activity3 {
	
	AppiumDriver<MobileElement> driver = null;
	WebDriverWait wait;   
	    @BeforeClass
	    public void beforeClass() throws MalformedURLException{
	        // Set the Desired Capabilities
	        DesiredCapabilities caps = new DesiredCapabilities();
	        caps.setCapability("deviceId", "emulator-55544");
	        caps.setCapability("platformName", "Android");
	        caps.setCapability("appPackage", "com.google.android.keep");
	        caps.setCapability("appActivity", ".activities.BrowseActivity");
	        caps.setCapability("noReset", true);

	        // Instantiate Appium Driver
	        URL appServer = new URL("http://localhost:4723/wd/hub");
	        driver = new AndroidDriver<MobileElement>(appServer, caps);
	        wait = new WebDriverWait(driver, 10);
	    }

	  @Test
	  public void CreateNoteWithReminder() throws InterruptedException {
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  driver.findElementByAccessibilityId("New text note").click();
		  driver.findElement(By.id("com.google.android.keep:id/edit_note_text")).sendKeys("Notes_1");
		  driver.findElement(By.id("com.google.android.keep:id/editable_title")).sendKeys("Title_1");
		  driver.findElement(By.id("com.google.android.keep:id/menu_reminder")).click();
		  driver.findElement(By.id("com.google.android.keep:id/time_spinner")).click();
		  driver.findElement(By.id("com.google.android.keep:id/reminder_time_afternoon")).click();
		  driver.findElement(By.id("com.google.android.keep:id/save")).click();
		  driver.findElement(By.className("android.widget.ImageButton")).click();
		  String Title=driver.findElement(By.id("com.google.android.keep:id/index_note_title")).getAttribute("text");
		  String Description=driver.findElement(By.id("com.google.android.keep:id/index_note_text_description")).getAttribute("text");
		  String Reminder=driver.findElement(By.id("com.google.android.keep:id/reminder_chip_text")).getAttribute("text");
		  String expectedTitle="Title_1";
		  String expectedDescription="Notes_1";
		  String ExpectedReminder="Today, 1:00 PM";
		  Assert.assertEquals(Title,expectedTitle);
		  Assert.assertEquals(Description,expectedDescription);
		  System.out.println("Actual Title is  " +Title + " Expected Title is " +expectedTitle);
		  System.out.println("Actual Description is  " +Description + " Expected Description is " +expectedDescription);
		  System.out.println("Actual Reminder is  " +Reminder + " Expected Reminder is " +ExpectedReminder);
		  Reporter.log("Actual Title is  " +Title + " Expected Title is " +expectedTitle);
		  Reporter.log("Actual Description is  " +Description + " Expected Description is " +expectedDescription);
		  Reporter.log("Actual Reminder is  " +Reminder + " Expected Reminder is " +ExpectedReminder);
	  }
	  @AfterClass
	  public void afterClass() {
		  driver.quit();
	  }
	}
