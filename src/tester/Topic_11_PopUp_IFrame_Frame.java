package tester;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_PopUp_IFrame_Frame {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSeconds(long millis) {
		try {
			Thread.sleep(millis * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void TC_01_Popup_Fixed() {
		driver.get("https://tiki.vn/");

		action.moveToElement(driver.findElement(By.xpath("//img[@class='profile-icon']"))).perform();

		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='dialog']/div")).isDisplayed());

		driver.findElement(By.xpath("//span[@class='tikicon icon-circle-close']")).click();

		Assert.assertFalse(driver.findElement(By.xpath("//div[@role='dialog']/div")).isDisplayed());
	}

	public void TC_02_Popup_In_DOM() {
		driver.get("https://bni.vn/");
	}

	public void TC_03_Popup_NOT_In_DOM() {
		driver.get("https://tiki.vn/");
		action.moveToElement(driver.findElement(By.xpath("//img[@class='profile-icon']"))).perform();

		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='dialog']/div")).isDisplayed());

		driver.findElement(By.xpath("//span[@class='tikicon icon-circle-close']")).click();

		Assert.assertEquals(driver.findElements(By.xpath("//div[@role='dialog']/div")).size(), 0);
	}

	public void TC_04_Popup_In_DOM_Condition() {
		driver.get("https://blog.testproject.io/");
		
		if(driver.findElement(By.xpath("//div[@class='mailch-wrap']")).isDisplayed()) {
			driver.findElement(By.id("close-mailch")).click();
		}
	}
	
	@Test
	public void TC_05_Iframe() {
		driver.get("https://kyna.vn/");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
		String likeNumber = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println(likeNumber);
		
		driver.switchTo().defaultContent();
		
//		driver.switchTo().frame(arg0);
	}
}
