package tester;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_15_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	Actions action;
	WebElement element;
	long timeoutInSecond = 3;
	long intervalMiliSecon = 300;

	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		explicitWait = new WebDriverWait(driver, 10);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

	public void sleepInSeconds(long millis) {
		try {
			Thread.sleep(millis * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void TC_01_Clickable_Invisible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Start']")));

		driver.findElement(By.xpath("//button[normalize-space()='Start']")).click();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));

		Assert.assertEquals(driver.findElement(By.xpath("//h4[normalize-space()='Hello World!']")).getText(),
				"Hello World!");
	}

	public void TC_02_Clickable_Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Start']")));

		driver.findElement(By.xpath("//button[normalize-space()='Start']")).click();

		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Hello World!']")));

		Assert.assertEquals(driver.findElement(By.xpath("//h4[normalize-space()='Hello World!']")).getText(),
				"Hello World!");
	}

	public void TC_03_Only_Explicit() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='14']/parent::td")));
		driver.findElement(By.xpath("//a[text()='14']/parent::td")).click();

		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//a[text()='14']/parent::td[@class='rcSelected']")));

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//div[@class='raDiv']/parent::div[not(@style='display:none;')]")));

		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(),
				"Sunday, March 14, 2021");

	}

	public void TC_04_Not_Found_Element_Only_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		System.out.println("START implicit wait: " + getDateTimeNow());
		// Incorrect xpath
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("END implicit wait: " + getDateTimeNow());

	}

	public void TC_05_Not_Found_Element_Implicit_Less_Explicit() {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		explicitWait = new WebDriverWait(driver, 5);

		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		System.out.println("START implicit wait: " + getDateTimeNow());
		// Incorrect xpath
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
		} catch (Exception e) {
		}
		System.out.println("END implicit wait: " + getDateTimeNow());

		System.out.println("START explicit wait: " + getDateTimeNow());
		// Incorrect xpath
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailAddress']")));
		} catch (Exception e) {
		}
		System.out.println("END explicit wait: " + getDateTimeNow());
	}

	public void TC_06_Not_Found_Element_Implicit_Equal_Explicit() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		explicitWait = new WebDriverWait(driver, 5);

		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		System.out.println("START implicit wait: " + getDateTimeNow());
		// Incorrect xpath
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
		} catch (Exception e) {
		}
		System.out.println("END implicit wait: " + getDateTimeNow());

		System.out.println("START explicit wait: " + getDateTimeNow());
		// Incorrect xpath
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailAddress']")));
		} catch (Exception e) {
		}
		System.out.println("END explicit wait: " + getDateTimeNow());
	}

	public void TC_07_Not_Found_Element_Implicit_Greater_Explicit() {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

		explicitWait = new WebDriverWait(driver, 5);

		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		System.out.println("START implicit wait: " + getDateTimeNow());
		// Incorrect xpath
		try {
			Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
		} catch (Exception e) {
		}
		System.out.println("END implicit wait: " + getDateTimeNow());

		System.out.println("START explicit wait: " + getDateTimeNow());
		// Incorrect xpath
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailAddress']")));
		} catch (Exception e) {
		}
		System.out.println("END explicit wait: " + getDateTimeNow());
	}

	public void TC_08_Not_Found_Element_Only_Explicit_Param_By() {

		explicitWait = new WebDriverWait(driver, 5);

		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		System.out.println("START explicit wait: " + getDateTimeNow());
		// Incorrect xpath
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailAddress']")));
		} catch (Exception e) {
		}
		System.out.println("END explicit wait: " + getDateTimeNow());
	}

	public void TC_09_Not_Found_Element_Only_Explicit_Param_Element() {

		explicitWait = new WebDriverWait(driver, 5);

		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		System.out.println("START explicit wait: " + getDateTimeNow());
		// Incorrect xpath
		try {
			explicitWait.until(
					ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='emailAddress']"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("END explicit wait: " + getDateTimeNow());
	}

	public void TC_10_Fluent() {
		driver.get("https://automationfc.github.io/fluent-wait/");

		WebElement countdownTime = driver.findElement(By.id("javascript_countdown_time"));
		fluentElement = new FluentWait<WebElement>(countdownTime);

		fluentElement.withTimeout(timeoutInSecond, TimeUnit.SECONDS)
				.pollingEvery(intervalMiliSecon, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
				.until(new Function<WebElement, Boolean>() {

					@Override
					public Boolean apply(WebElement countdownTime) {
						String getText = countdownTime.getText();

						return getText.endsWith("00");
					}
				});

	}

	@Test
	public void TC_11_Fluent() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
		waitForElementAndClick(By.xpath("//button[text()='Start']"));

		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='finish']/h4")));
	}

	public WebElement waitElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeoutInSecond, TimeUnit.SECONDS)
				.pollingEvery(intervalMiliSecon, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}

	public void waitForElementAndClick(By locator) {
		element = waitElement(locator);
		element.click();
	}

	public boolean isElementDisplayed(By locator) {
		element = waitElement(locator);
		FluentWait<WebElement> wait = waitByElement(element);

		boolean isDisplayed = wait.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				boolean flag = element.isDisplayed();
				return flag;
			}
		});
		return isDisplayed;
	}

	public FluentWait<WebElement> waitByElement (WebElement element){
		FluentWait<WebElement> wait = new FluentWait<WebElement>(element)
				.withTimeout(timeoutInSecond, TimeUnit.SECONDS)
				.pollingEvery(intervalMiliSecon, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		return wait;
	}
}
