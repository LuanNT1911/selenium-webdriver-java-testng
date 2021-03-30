package tester;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Actions action;

	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
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

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSeconds(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public void TC_01() {
		navigateToUrlByJS(driver, "http://live.demoguru99.com/");

		String homePageDomain = (String) executeForBrowser(driver, "return document.domain;");
		Assert.assertEquals(homePageDomain, "live.demoguru99.com");

		String homePageURL = (String) executeForBrowser(driver, "return document.URL;");
		Assert.assertEquals(homePageURL, "http://live.demoguru99.com/");

		highlightElement(driver, "//a[text()='Mobile']");
		clickToElementByJS(driver, "//a[text()='Mobile']");

		highlightElement(driver,
				"//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS(driver,
				"//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

		sleepInSeconds(2);

		highlightElement(driver, "//li[@class='success-msg']//span");
		Assert.assertTrue(getInnerText(driver).contains("Samsung Galaxy was added to your shopping cart."));

		highlightElement(driver, "//a[text()='Customer Service']");
		clickToElementByJS(driver, "//a[text()='Customer Service']");

		scrollToElement(driver, "//input[@id='newsletter']");
		highlightElement(driver, "//input[@id='newsletter']");

		sendkeyToElementByJS(driver, "//input[@id='newsletter']", "as@gmail.com");

		highlightElement(driver, "//span[text()='Subscribe']");
		clickToElementByJS(driver, "//span[text()='Subscribe']");

		highlightElement(driver, "//li[@class='error-msg']//span");
		Assert.assertTrue(getInnerText(driver).contains(
				"There was a problem with the subscription: This email address is already assigned to another user."));

		navigateToUrlByJS(driver, "http://demo.guru99.com/v4/");

		String homePage2Domain = (String) executeForBrowser(driver, "return document.domain;");
		Assert.assertEquals(homePage2Domain, "demo.demoguru99.com");
	}

	@Test
	public void TC_03() {
		navigateToUrlByJS(driver, "https://login.ubuntu.com/");
				
		clickToElementByJS(driver, "//button[@data-qa-id=\"login_button\"]");
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@id='id_email']"), "Vui lòng điền vào trường này.");
	
		sendkeyToElementByJS(driver, "//input[@id='id_email']", "11");
		clickToElementByJS(driver, "//button[@data-qa-id=\\\"login_button\\\"]");
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@id='id_email']"), "Vui lòng bao gồm '@' trong địa chỉ email. '11' bị thiếu '@'.");
		
		sendkeyToElementByJS(driver, "//input[@id='id_email']", "11@");
		clickToElementByJS(driver, "//button[@data-qa-id=\\\"login_button\\\"]");
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@id='id_email']"), "Vui lòng nhập phần đứng sau '@'. '11@' không hoàn chỉnh.");
	
		sendkeyToElementByJS(driver, "//input[@id='id_email']", "11@..");
		clickToElementByJS(driver, "//button[@data-qa-id=\\\"login_button\\\"]");
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@id='id_email']"), "'.' bị sử dụng sai vị trí trong '..'.");
		
	}

}
