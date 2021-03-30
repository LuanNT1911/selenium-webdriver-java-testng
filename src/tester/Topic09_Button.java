package tester;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic09_Button {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	By tabLogin = By.xpath("//a[contains(text(),'Đăng nhập')] ");
	By btnLogin = By.xpath("//button[@class='fhs-btn-login']");
	By txtUsername = By.id("login_username");
	By txtPassword = By.id("login_password");
	By alert_Username = By.xpath("//label[text()='Số điện thoại/Email']//following-sibling::div[@class='fhs-input-alert']");
	By alert_Password = By.xpath("//label[text()='Mật khẩu']//following-sibling::div[@class='fhs-input-alert']");
	By rd_Summber = By.xpath("//input[@value='Summer']");
	
	
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		driver.findElement(tabLogin).click();
		
		Assert.assertFalse(isElementEnabled(btnLogin));
		
		driver.findElement(txtUsername).sendKeys("luan@gmail.com");
		driver.findElement(txtPassword).sendKeys("luan@gmail.com");
		sleepInSeconds(1);
		
		Assert.assertTrue(isElementEnabled(btnLogin));
		
		driver.navigate().refresh();
		
		driver.findElement(tabLogin).click();
		
		removeDisabledAttributeByJS(btnLogin);
		sleepInSeconds(1);
		
		Assert.assertTrue(isElementEnabled(btnLogin));
		driver.findElement(btnLogin).click();
		
		Assert.assertEquals(driver.findElement(alert_Username).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(alert_Password).getText(), "Thông tin này không thể để trống");
	}
	
	public void TC_02_Checbox_InputType() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for(WebElement element: checkboxes) {
			checkToCheckboxOrRadio(element);
			sleepInSeconds(1);
		}
		
		for(WebElement element: checkboxes) {
			Assert.assertTrue(isElementSelected(element));
		}
		
		for(WebElement element: checkboxes) {
			uncheckToCheckbox(element);
			sleepInSeconds(1);
		}
		
		for(WebElement element: checkboxes) {
			Assert.assertFalse(isElementSelected(element));
		}
	}
	
	public void TC_03_Radio_Default() {
		
	}
	@Test
	public void TC_03_Checbox_Custom() {
		driver.get("https://material.angular.io/components/radio/examples");
		clickToElementByJS(driver.findElement(rd_Summber));
		Assert.assertTrue(isElementSelected(driver.findElement(rd_Summber)));
	}
	
	public void clickToElementByJS(WebElement element) {
		jsExecutor.executeScript("arguments[0].click()", element);
	}
	
	public void checkToCheckboxOrRadio(WebElement element) {
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToCheckbox(WebElement element) {
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementSelected(WebElement element) {
		if (element.isSelected()) {
			System.out.println("Element is selected");
			return true;
		} else {
			System.out.println("Element is not selected");
			return false;
		}
	}
	
	
	public boolean isElementEnabled(By by) {
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
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
//		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	  driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}
	
	public void removeDisabledAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		// disabled is name of attribute not value of attribute
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
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
