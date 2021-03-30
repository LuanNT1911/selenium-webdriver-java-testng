package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic06_Ex_WebElement {
	WebDriver driver;
	By txtEmail = By.xpath("//*[@id='mail']");
	By cbAge_18 = By.xpath("//label[text()='Under 18']");
	By txtEducation = By.xpath("//*[@id='edu']");
	By slJobRole1 = By.xpath("//*[@id='job1']");
	By slJobRole2 = By.xpath("//*[@id='job2']");
	By cbDevelopment = By.xpath("//label[text()='Development']");
	By slide01 = By.xpath("//*[@id='slider-1']");
	By txtUPassword = By.xpath("//input[@name='user_pass']");
	By cbAge_disabled = By.xpath("//label[text()='Radio button is disabled']/preceding-sibling::input[1]");
	By txtBiography = By.xpath("//*[@id='bio']");
	By slJobRole3 = By.xpath("//*[@id='job3']");
	By cbInterest_disabled = By.xpath("//label[text()='Checkbox is disabled']/preceding-sibling::input[1]");
	By slide02 = By.xpath("//*[@id='slider-2']");

	public void TC01_isDisplayed() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (checkIsDisplayed(txtEmail)) {
			driver.findElement(txtEmail).sendKeys("Automation Testing");
		}
		if (checkIsDisplayed(cbAge_18)) {
			driver.findElement(cbAge_18).click();
		}
		if (checkIsDisplayed(txtEducation)) {
			driver.findElement(txtEducation).sendKeys("Automation Testing");
		}
	}

	@Test
	public void TC02_isEnabled() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(checkIsEnabled(txtEmail));
		Assert.assertTrue(checkIsEnabled(cbAge_18));
		Assert.assertTrue(checkIsEnabled(txtEducation));
		Assert.assertTrue(checkIsEnabled(slJobRole1));
		Assert.assertTrue(checkIsEnabled(slJobRole2));
		Assert.assertTrue(checkIsEnabled(cbDevelopment));
		Assert.assertTrue(checkIsEnabled(slide01));
		Assert.assertFalse(checkIsEnabled(txtUPassword));
		Assert.assertFalse(checkIsEnabled(cbAge_disabled));
		Assert.assertFalse(checkIsEnabled(txtBiography));
		Assert.assertFalse(checkIsEnabled(slJobRole3));
		Assert.assertFalse(checkIsEnabled(cbInterest_disabled));
		Assert.assertFalse(checkIsEnabled(slide02));
	}

	public boolean checkIsDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element is displayed");
			return true;
		} else {
			System.out.println("Element is not displayed");
			return false;
		}
	}

	public boolean checkIsEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element is enabled");
			return true;
		} else {
			System.out.println("Element is disabled");
			return false;
		}
	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	  driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

}