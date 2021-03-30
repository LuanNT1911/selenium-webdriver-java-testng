package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//@Listeners(ReportListener.class)
public class Topic_07_Dependencies {

	@Test
	public void User_01_Create_User() {
		Assert.assertFalse(true);
	}
	
	@Test(dependsOnMethods = "User_01_Create_User")
	public void User_05_Delete_User() {
		
	}
	
	@Test(dependsOnMethods = "User_01_Create_User")
	public void User_03_Edit_User() {
		
	}
	
	@Test(dependsOnMethods = "User_01_Create_User")
	public void User_04_Move_User() {
		
	}
	
	@Test(dependsOnMethods = "User_01_Create_User")
	public void User_02_View_User() {
		
	}
}
