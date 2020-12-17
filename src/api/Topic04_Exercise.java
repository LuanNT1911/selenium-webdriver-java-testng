package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic04_Exercise {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	private void TC01_Login_with_empty_Email_and_Password() {
		driver.findElement(By.xpath("(//a[contains(text(),'My Account')])[2]")).click();
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String getMsgEmail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		String getMsgPass = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(getMsgEmail, "This is a required field.");
		Assert.assertEquals(getMsgPass, "This is a required field.");
	}

	@Test
	private void TC02_Login_with_invalid_Email() {
		driver.findElement(By.xpath("(//a[contains(text(),'My Account')])[2]")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC03_Login_with_invalid_Password() {

		driver.findElement(By.xpath("(//a[contains(text(),'My Account')])[2]")).click();
		driver.findElement(By.xpath("//ul//input[@type='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC04_Login_with_incorrect_Email_and_Password() {

		driver.findElement(By.xpath("(//a[contains(text(),'My Account')])[2]")).click();
		driver.findElement(By.xpath("//ul//input[@type='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),
				"Invalid login or password.");

	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
