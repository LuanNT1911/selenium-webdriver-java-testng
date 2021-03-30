package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic06_Ex_WebBrowser {
	WebDriver driver;
	
  
  public void TC01_ValidateCurrentUrl() {
	  
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("(//a[@title='My Account'])[2]")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	  
  }
  
  
 
  public void TC02_Verify_Title() {
	  
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  Assert.assertEquals(driver.getTitle(), "Customer Login");
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
  }
  
  
  
  public void TC03_Navigate_Function() {
	  
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	  driver.navigate().back();
	  Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");
	  driver.navigate().forward();
	  Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	  
	  
  }
  
  
  @Test
  public void TC04_Get_Page_Source_Code() {
	  
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("(//a[@title='My Account'])[2]")).click();
	  Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
//	  System.out.println(driver.getPageSource());
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	  System.out.println("TC04 Get Page Source: " + driver.findElement(By.xpath("//span[text()='Register']")).getCssValue("line-height"));
	  
  }
  
  
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	  driver.manage().window().maximize();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}