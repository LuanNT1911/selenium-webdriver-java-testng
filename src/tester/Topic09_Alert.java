package tester;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic09_Alert {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Alert alert;
	By btn_JSAlert = By.xpath("//button[normalize-space()='Click for JS Alert']");
	By btn_JSConfirm = By.xpath("//button[normalize-space()='Click for JS Confirm']");
	By btn_JSPrompt = By.xpath("//button[normalize-space()='Click for JS Prompt']");
	By textResult = By.id("result");
	By url_BasicAuth = By.xpath("//a[normalize-space()='Basic Auth']");

	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(btn_JSAlert).click();

//		explicitWait.until(ExpectedConditions.alertIsPresent());
//		alert = driver.switchTo().alert();

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();

		Assert.assertEquals(driver.findElement(textResult).getText(), "You clicked an alert successfully");
	}

	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(btn_JSConfirm).click();

//		explicitWait.until(ExpectedConditions.alertIsPresent());
//		alert = driver.switchTo().alert();

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.accept();

		Assert.assertEquals(driver.findElement(textResult).getText(), "You clicked: Ok");

	}

	public void TC_03_Prompt_Alert() {
		String alertText = "Automa";
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(btn_JSPrompt).click();

//		explicitWait.until(ExpectedConditions.alertIsPresent());
//		alert = driver.switchTo().alert();

		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSeconds(1);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(alertText);
		alert.accept();

		sleepInSeconds(2);
		Assert.assertEquals(driver.findElement(textResult).getText(), "You entered: " + alertText);

	}

	public void TC_04_Authentication_Alert() {
		// Password contains special chars
		// -> URL Encode

		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
	}

	public void TC_05_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com");

		String url = driver.findElement(url_BasicAuth).getAttribute("href");
		driver.get(setCredentialToURL(url, "admin", "admin"));
	}

	@Test
	public void TC_06_Authentication_Alert_AutoIT() throws IOException {
		String chromeAuth = System.getProperty("user.dir") + "\\autoIt\\authen_chrome.exe";
		Runtime.getRuntime().exec(new String[] { chromeAuth, "admin", "admin" });
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
	}

	public String setCredentialToURL(String url, String username, String password) {
		String[] newUrl = url.split("//");
		url = newUrl[0] + "//" + username + ":" + password + "@" + newUrl[1];
		System.out.println(url);
		return url;
	}

	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);
		jsExecutor = (JavascriptExecutor) driver;
//		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	  driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

	public void sleepInSeconds(long millis) {
		try {
			Thread.sleep(millis * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
