package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic05_Element_Method_Part_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		boolean emailTxtStatus = driver.findElement(By.cssSelector("#mail")).isDisplayed();
		if (emailTxtStatus) {
			driver.findElement(By.cssSelector("#mail")).sendKeys("email@gmail.com");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}

		boolean educationTxtStatus = driver.findElement(By.cssSelector("#edu")).isDisplayed();
		if (educationTxtStatus) {
			driver.findElement(By.cssSelector("#edu")).sendKeys("Education");
			System.out.println("Education textbox is displayed");
		} else {
			System.out.println("Education textbox is not displayed");
		}

		boolean under18CbStatus = driver.findElement(By.cssSelector("#under_18")).isDisplayed();
		if (educationTxtStatus) {
			driver.findElement(By.cssSelector("#under_18")).click();
			;
			System.out.println("Under 18 checkbox is displayed");
		} else {
			System.out.println("Under 18 checkbox is not displayed");
		}

		boolean nameUser5TxtStatus = driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed();
		if (nameUser5TxtStatus) {
			driver.findElement(By.xpath("//h5[text()='Name: User5']")).click();
			;
			System.out.println("Name User 5 is displayed");
		} else {
			System.out.println("Name User 5 is not displayed");
		}
	}

	@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		boolean emailTxtStatus = driver.findElement(By.cssSelector("#mail")).isEnabled();
		if (emailTxtStatus) {
			System.out.println("Email textbox is enabled");
		} else {
			System.out.println("Email textbox is disabled");
		}

		boolean sliderOneStatus = driver.findElement(By.cssSelector("#slider-1")).isEnabled();
		if (sliderOneStatus) {
			System.out.println("Slider 1 is enabled");
		} else {
			System.out.println("Slider 1 is disabled");
		}

		boolean sliderTwoStatus = driver.findElement(By.cssSelector("#slider-2")).isEnabled();
		if (sliderTwoStatus) {
			System.out.println("Slider 2 is enabled");
		} else {
			System.out.println("Slider 2 is disabled");
		}
	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Click Under 18/ Language Java
		driver.findElement(By.id("under_18")).click();
		driver.findElement(By.id("java")).click();

		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("java")).isSelected());

		// Click Under 18/ Language Java
		driver.findElement(By.id("under_18")).click();
		driver.findElement(By.id("java")).click();

		Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
		Assert.assertFalse(driver.findElement(By.id("java")).isSelected());

	}

	@AfterClass
	private void afterClass() {
//		driver.quit();
	}
}
