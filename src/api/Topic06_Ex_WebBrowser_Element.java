package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic06_Ex_WebBrowser_Element {
	WebDriver driver;
	
  @Test
  public void TC01_Verify_Url() {
	  
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/login/");
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
  }
  
  
  @Test
  public void TC02_Verify_Title() {
	  
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  Assert.assertEquals(driver.getTitle(), "Customer Login");
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
  }
  
  
  @Test
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
	  driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
	  Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	  
  }
  
  
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}